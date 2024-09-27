import { AfterContentChecked, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';

import { EntradaEnvio, FormularioEnvio } from 'src/app/shared/utils/formulario-abstract';
import { FormularioUtilsService } from 'src/app/shared/utils/formulario-utils.service';
import { Cartao } from '../cartao.model';
import { CartaoUtilsService } from '../utils/cartao-utils.service';
import { CartaoService } from './../cartao.service';

@Component({
  selector: 'app-cartao-form',
  templateUrl: './cartao-form.component.html',
  styleUrls: ['./cartao-form.component.scss']
})
export class CartaoFormComponent extends FormularioEnvio implements  OnInit, AfterContentChecked {

  //Declaração de variáveis
  rotaListagem = '/cartao/listar';
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
    public  cartaoService: CartaoService,
    private route: ActivatedRoute, //Através da url podemos pegar/passar variáveis. Ex.: pegar o id para editar
    public  router: Router,
    public  alertService: AlertModalService,
    private spinner: NgxSpinnerService,
    private cdr: ChangeDetectorRef,
    public  formularioUtils: FormularioUtilsService,
    public  utils: CartaoUtilsService
  ) {
    super(cartaoService, router, alertService);
  }

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
    return this.utils.validarNumero(String(formControl.value)) ? null : { cartaoInvalido: true };
  }

  async validarNumeroCorrespondeABandeira(formControl: FormControl) {
    const bandeira = this.cartaoFormulario.get('bandeira')?.value;
    const numero   = String(this.cartaoFormulario.get('numero')?.value);

    return this.utils.validarNumeroCorrespondenteABandeira(bandeira, numero) ? null : { numeroNaoCorrespondeABandeira: true };
  }

  verificarErrorsNumeroCartao() {
    setTimeout(() => {
      const errors = this.cartaoFormulario.get('numero')?.errors;
      const bandeira = this.cartaoFormulario.get('bandeira')?.value;
      const numero   = String(this.cartaoFormulario.get('numero')?.value);

      if (this.utils.validarNumeroCorrespondenteABandeira(bandeira, numero)) {
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

  envia() {
    const cartao = this.cartaoFormulario.getRawValue();
    cartao.limite = this.formataValor(cartao.limite);

    this.enviarFormulario({
      id: this.id,
      edit: this.editar,
      mensagemSucessoEdicao: 'Cartão editado com sucesso',
      mensagemSucessoInsercao: 'Cartão cadastrado com sucesso',
      rota: this.rotaListagem,
      formGroup: this.cartaoFormulario,
      objeto: cartao
    } as  EntradaEnvio );

  }

  preencherFormulario(){
    this.cartaoFormulario.patchValue({ //Passando os valores para o formulário
      nome: this.cartao.nome,
      bandeira: this.cartao.bandeira,
      numero: this.cartao.numero,
      limite: Number(this.cartao.limite).toLocaleString('pt-BR', { minimumFractionDigits: 2})
    })
  }

  formataValor(valor: string) {
    valor = String(valor);
    valor = valor.includes('.') ? valor?.replace('.', '') : valor;
    valor = valor.includes(',') ? valor?.replace(',', '.') : valor;
    return valor;
  }

}
