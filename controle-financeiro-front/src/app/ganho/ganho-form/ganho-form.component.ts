import { FormularioUtilsService } from 'src/app/shared/utils/formulario-utils.service';
import { AfterContentChecked, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';

import { Categoria, FiltroCategoria } from './../../categoria/categoria.model';
import { CategoriaService } from './../../categoria/categoria.service';
import { Ganho, GanhoForm } from './../ganho.model';
import { GanhoService } from './../ganho.service';
import { EntradaEnvio, FormularioEnvio } from 'src/app/shared/utils/formulario-abstract';

@Component({
  selector: 'app-ganho-form',
  templateUrl: './ganho-form.component.html',
  styleUrls: ['./ganho-form.component.scss']
})
export class GanhoFormComponent extends FormularioEnvio implements OnInit, AfterContentChecked {

  rotaListagem = '/ganho/listar';
  ganhoFormulario!: FormGroup;
  editar = false;
  carregando = false;
  id!: number;
  ganho!: Ganho;
  categorias!: Categoria[];

  get propriedade() {
    return this.ganhoFormulario.controls;
  }

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    public  ganhoService: GanhoService,
    private categoriaService: CategoriaService,
    public  router: Router,
    public  alertService: AlertModalService,
    private spinner: NgxSpinnerService,
    private adapter: DateAdapter<any>,
    private cdr: ChangeDetectorRef,
    public  formularioUtils: FormularioUtilsService
  ) {
    super(ganhoService, router, alertService)
   }

  ngAfterContentChecked(): void {
    this.cdr.detectChanges();
  }

  async ngOnInit() {
    this.adapter.setLocale('pt-br');

    this.carregando = true;
    this.spinner.show();

    this.ganhoFormulario = this.formBuilder.group({
      descricao: ['', [Validators.required, Validators.minLength(3)]],
      categoria: ['', [Validators.required]],
      valor: ['', [Validators.required], [this.validarValor.bind(this)]],
      data: ['', Validators.required]
    });

    this.route.params?.subscribe(value => {
      if (value?.id){
        this.id = value.id;
        this.editar = true;
        this.carregando = true;
        this.spinner.show();
        this.ganhoService.retornarGanhoId(this.id).pipe(finalize(() => {
          this.spinner.hide();
          this.carregando = false;
        })).subscribe( result => {
          this.ganho = result;
          this.preencherFormulario();
        });
      }
    });

    await this.carregarCategoria();

    this.spinner.hide();
    this.carregando = false;

  }

  preencherFormulario(){
    this.ganhoFormulario.patchValue({
      descricao: this.ganho.descricao,
      categoria: this.ganho.categoria.id,
      valor: Number(this.ganho.valor),
      data: new Date(this.ganho.data),
    });
  }

  private async carregarCategoria() {
    await this.categoriaService.listar({nome: '', tipo: 'G'} as FiltroCategoria).toPromise().then(categorias => {
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
      mensagemSucessoEdicao: 'Ganho editado com sucesso',
      mensagemSucessoInsercao: 'Ganho cadastrado com sucesso',
      rota: this.rotaListagem,
      formGroup: this.ganhoFormulario,
      objeto: this.getGanhoForm()
    } as  EntradaEnvio );
  }

  getGanhoForm(){
    const ganho = {
      descricao: this.ganhoFormulario.get('descricao')?.value,
      valor: this.ganhoFormulario.get('valor')?.value,
      data: this.ganhoFormulario.get('data')?.value,
      categoria_id: this.ganhoFormulario.get('categoria')?.value,
    } as GanhoForm;

    ganho.valor =  this.formataValor(ganho.valor);

    return ganho;
  }

  async validarValor(formControl: FormControl) {
    let valor = String(formControl?.value);
    valor = valor.includes('.') ? valor?.replace('.', '') : valor;
    valor = valor.includes(',') ? valor?.replace(',', '.') : valor;
    return Number(valor) <= 0 ? { valorInvalido: true } : null;
  }

  formataValor(valor: string) {
    valor = String(valor);
    valor = valor.includes('.') ? valor?.replace('.', '') : valor;
    valor = valor.includes(',') ? valor?.replace(',', '.') : valor;
    return valor;
  }

}
