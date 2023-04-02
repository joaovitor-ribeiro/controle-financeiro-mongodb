import { AfterContentChecked, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';

import { Bandeira, Cartao } from '../cartao.model';
import { CartaoService } from './../cartao.service';

@Component({
  selector: 'app-cartao-form',
  templateUrl: './cartao-form.component.html',
  styleUrls: ['./cartao-form.component.scss']
})
export class CartaoFormComponent implements OnInit, AfterContentChecked {

  //Declaração de variáveis
  cartaoFormulario!: FormGroup;
  id!: number;
  cartao!: Cartao;
  editar = false;
  carregando = false;
  bandeiras = ['Visa', 'Mastercard', 'American Express', 'JCB', 'Diners Club', 'Aura', 'Hipercard'];

  get propriedade() {
    return this.cartaoFormulario.controls;
  }

  constructor(
    private formBuilder: FormBuilder, //Instaciando o fomulário
    private cartaoService: CartaoService,
    private route: ActivatedRoute, //Através da url podemos pegar/passar variáveis. Ex.: pegar o id para editar
    private router: Router,
    private alertService: AlertModalService,
    private spinner: NgxSpinnerService,
    private cdr: ChangeDetectorRef,
  ) { }

  ngAfterContentChecked(): void {
    this.cdr.detectChanges();
  }

  ngOnInit(): void {
    this.cartaoFormulario = this.formBuilder.group({ //Criaando o formulário
      nome: ['', [Validators.required, Validators.minLength(3)]],
      bandeira: ['', [Validators.required, Validators.minLength(3)]],
      numero: ['', [Validators.required, Validators.minLength(13)], [this.validarCartao.bind(this), this.validarNumeroCorrespondeABandeira.bind(this)]],
      limite: ['', Validators.required, this.validarLimite]
    });

    this.route.params.subscribe(value => { //subscribe é usado para receber algo que é retornado por um observable
      if (value?.id) {
        this.id = value.id;
        this.editar = true;
        this.carregando = true;
        this.spinner.show();
        this.cartaoService.retornarCartaoId(this.id).pipe(finalize(() => {
          this.spinner.hide();
          this.carregando = false;
        })).subscribe( result => {
          this.cartao = result;
          this.preencherFormulario();
        });
      }
    });
  }

  async validarLimite(formControl: FormControl) {
    let valor = String(formControl?.value);
    valor = valor.includes('.') ? valor?.replace('.', '') : valor;
    valor = valor.includes(',') ? valor?.replace(',', '.') : valor;
    return Number(valor) <= 0 ? { limiteInvalido: true } : null;
  }

  async validarCartao(formControl: FormControl) {
    return this.validarNumero(String(formControl.value)) ? null : { cartaoInvalido: true };
  }

  async validarNumeroCorrespondeABandeira(formControl: FormControl) {
    return this.validarNumeroCorrespondenteABandeira() ? null : { numeroNaoCorrespondeABandeira: true };
  }

  enviarFormulario() {
    console.log(this.cartaoFormulario.get('limite')?.value);
    if (this.cartaoFormulario.invalid) {
      this.cartaoFormulario.markAllAsTouched();
    }else{
      const cartao = this.cartaoFormulario.getRawValue();
      cartao.limite = this.formataValor(cartao.limite);

      if (this.editar) {
        this.cartaoService.editar(this.id, cartao).subscribe(() => {
          this.router.navigate(['/cartao/listar']);
          this.alertService.showAlertSuccess('Cartão editado com sucesso');
        });
      } else {
        this.cartaoService.inserir(cartao).subscribe(() => {
          this.router.navigate(['/cartao/listar'], { queryParamsHandling: 'preserve' });
          this.alertService.showAlertSuccess('Cartão cadastrado com sucesso');
        });
      }
    }
  }

  limparCampo(valor: string) {
    this.cartaoFormulario.get(valor)?.setValue('');
  }

  preencherFormulario(){
    this.cartaoFormulario.patchValue({ //Passando os valores para o formulário
      nome: this.cartao.nome,
      bandeira: this.cartao.bandeira,
      numero: this.cartao.numero,
      limite: Number(this.cartao.limite).toLocaleString('pt-BR', { minimumFractionDigits: 2})
    })
  }

  voltar(){
    this.router.navigate(['/cartao/listar'], { queryParamsHandling: 'preserve' });
  }

  validarNumero(numero: string) {
    let total = 0;
    let deveDobrar = false;
    for (let i = numero.length - 1; i >=0; i--) {
      let digito = +numero[i];
      if (deveDobrar) {
        digito *= 2;
        if (digito > 9) digito -= 9;
      }
      total += digito;
      deveDobrar = !deveDobrar;
    }
    return total % 10 === 0;
  }

  verificarErrorsNumeroCartao() {
    setTimeout(() => {
      const errors = this.cartaoFormulario.get('numero')?.errors;
      if (this.validarNumeroCorrespondenteABandeira()) {
        if (errors) {
          delete errors.numeroNaoCorrespondeABandeira;
          this.cartaoFormulario.get('numero')?.setErrors(Object.keys(errors).length > 0 ? errors : null);
        } else {
          this.cartaoFormulario.get('numero')?.setErrors(null);
        }
      } else {
        this.cartaoFormulario.get('numero')?.setErrors({ numeroNaoCorrespondeABandeira: true, ...errors });
      }
    }, 50);
  }

  validarNumeroCorrespondenteABandeira() {
    const bandeira = this.cartaoFormulario.get('bandeira')?.value;
    const numero   = String(this.cartaoFormulario.get('numero')?.value);

    switch (bandeira) {
      case Bandeira.mastercard:
        return numero.startsWith("51") || numero.startsWith("52") || numero.startsWith("53") ||
               numero.startsWith("54") || numero.startsWith("55");
      case Bandeira.visa:
        return numero.startsWith("4");
      case Bandeira.jcb:
        return numero.startsWith("35");
      case Bandeira.americanExpress:
        return numero.startsWith("34") || numero.startsWith("37");
      case Bandeira.disnerClub:
        return numero.startsWith("300") || numero.startsWith("301") || numero.startsWith("302") ||
               numero.startsWith("303") || numero.startsWith("304") || numero.startsWith("305") ||
               numero.startsWith("36")  || numero.startsWith("38");
      case Bandeira.aura:
        return numero.startsWith("50");
      case Bandeira.hipercard:
        return numero.startsWith("606282");
      default:
        return false;
    }
  }

  formataValor(valor: string) {
    valor = String(valor);
    valor = valor.includes('.') ? valor?.replace('.', '') : valor;
    valor = valor.includes(',') ? valor?.replace(',', '.') : valor;
    return valor;
  }

}
