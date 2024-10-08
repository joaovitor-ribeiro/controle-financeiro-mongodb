import { AfterContentChecked, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Cartao } from 'src/app/cartao/cartao.model';
import { CartaoService } from 'src/app/cartao/cartao.service';
import { Categoria, FiltroCategoria } from 'src/app/categoria/categoria.model';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';
import { FormularioUtilsService } from 'src/app/shared/utils/formulario-utils.service';

import { EntradaEnvio, FormularioEnvio } from 'src/app/shared/utils/formulario-abstract';
import { CategoriaService } from './../../categoria/categoria.service';
import { Despesa, DespesaForm } from './../despesa.model';
import { DespesaService } from './../despesa.service';

@Component({
  selector: 'app-despesa-form',
  templateUrl: './despesa-form.component.html',
  styleUrls: ['./despesa-form.component.scss'],
})
export class DespesaFormComponent extends FormularioEnvio implements OnInit, AfterContentChecked {

  rotaListagem = 'despesa/listar';
  despesaFormulario!: FormGroup;
  carregando = true;
  cartoes!: Cartao[];
  categorias!: Categoria[];
  id!: number;
  editar!: boolean;
  despesa!: Despesa;

  get propriedade() {
    return this.despesaFormulario.controls;
  }

  constructor(
    private formBuilder: FormBuilder,
    private cartaoService: CartaoService,
    private categoriaService: CategoriaService,
    public  despesaService: DespesaService,
    private spinner: NgxSpinnerService,
    public  router: Router,
    private route: ActivatedRoute,
    public  alertService: AlertModalService,
    private adapter: DateAdapter<any>,
    private cdr: ChangeDetectorRef,
    public  formularioUtils: FormularioUtilsService,
  ) {
    super(despesaService, router, alertService)
   }

  ngAfterContentChecked(): void {
    this.cdr.detectChanges();
  }

  async ngOnInit() {
    this.adapter.setLocale('pt-br');

    this.spinner.show();

    this.despesaFormulario = this.formBuilder.group({
      cartao: ['', [Validators.required]],
      descricao: ['', [Validators.required, Validators.minLength(3)]],
      categoria: ['', [Validators.required],],
      valor: ['', [Validators.required], [this.validarValor.bind(this)]],
      data: ['', [Validators.required]],
    });

    this.route.params?.subscribe(value => {
      if (value?.id) {
        this.id = value.id;
        this.editar = true;
        this.despesaService.retornarDespesaId(this.id).subscribe( result => {
          this.despesa = result;
          this.preencherFormulario();
        });
      }
    });

    await this.carregarCartao();
    await this.carregarCategoria();

    this.spinner.hide();
    this.carregando = false;

    this.colocarFocoCampoCartao();
  }

  preencherFormulario() {
    this.despesaFormulario.patchValue({
      cartao: this.despesa.cartao.id,
      descricao: this.despesa.descricao,
      categoria: this.despesa.categoria.id,
      valor:  Number(this.despesa.valor),
      data: new Date(this.despesa.data),
    });
  }

  async validarValor(formControl: FormControl) {
    let valor = String(formControl?.value);
    valor = valor.includes('.') ? valor?.replace('.', '') : valor;
    valor = valor.includes(',') ? valor?.replace(',', '.') : valor;
    return Number(valor) <= 0 ? { valorInvalido: true } : null;
  }

  private async carregarCartao() {
    await this.cartaoService.listar().toPromise().then(cartoes => {
      this.cartoes = cartoes;
    }).catch (() => {
      this.spinner.hide();
      this.carregando = false;
    });
  }

  private async carregarCategoria() {
    await this.categoriaService.listar({nome: '', tipo: 'D'} as FiltroCategoria)
    .toPromise().then(categorias => {
      this.categorias = categorias;
    }).catch (() => {
      this.spinner.hide();
      this.carregando = false;
    });
  }

  envia() {
    this.enviarFormulario({
      id: this.id,
      edit: this.editar,
      mensagemSucessoEdicao: 'Despesa editada com sucesso',
      mensagemSucessoInsercao: 'Despesa cadastrada com sucesso',
      rota: this.rotaListagem,
      formGroup: this.despesaFormulario,
      objeto: this.getDespesaForm()
    } as  EntradaEnvio );
  }

  getDespesaForm() {
    const despesa = {
      descricao: this.despesaFormulario.get('descricao')?.value,
      cartao_id: this.despesaFormulario.get('cartao')?.value,
      categoria_id: this.despesaFormulario.get('categoria')?.value,
      valor: this.despesaFormulario.get('valor')?.value,
      data: this.despesaFormulario.get('data')?.value,
    } as DespesaForm;

    despesa.valor = this.formataValor(despesa.valor);

    return despesa;
  }

  colocarFocoCampoCartao() {
    setTimeout(() => {
      document.getElementById('cartao')?.focus();
    }, 100);
  }

  formataValor(valor: string) {
    valor = String(valor);
    valor = valor.includes('.') ? valor?.replace('.', '') : valor;
    valor = valor.includes(',') ? valor?.replace(',', '.') : valor;
    return valor;
  }

}
