import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';
import { EntradaEnvio, FormularioEnvio } from 'src/app/shared/utils/formulario-abstract';
import { FormularioUtilsService } from 'src/app/shared/utils/formulario-utils.service';

import { Categoria } from '../categoria.model';
import { CategoriaService } from '../categoria.service';

interface Tipo {
  label: string;
  value: string;
}

@Component({
  selector: 'app-categoria-form',
  templateUrl: './categoria-form.component.html',
  styleUrls: ['./categoria-form.component.scss']
})
export class CategoriaFormComponent extends FormularioEnvio implements OnInit {


  rotaListagem = 'categoria/listar';
  categoriaFormulario!: FormGroup;
  carregando = true;
  id!: number;
  categoria!: Categoria;
  editar!: boolean;

  get propriedade() {
    return this.categoriaFormulario.controls;
  }

  tipos: Tipo[] = [
    {
      label: 'Ganho',
      value: 'G'
    },
    {
      label: 'Despesa',
      value: 'D'
    }];

  constructor(
    private formBuilder: FormBuilder,
    public  service: CategoriaService,
    private spinner: NgxSpinnerService,
    public  router: Router,
    private route: ActivatedRoute,
    public  alertService: AlertModalService,
    public  formularioUtils: FormularioUtilsService,
  ) {
    super(service, router, alertService);
  }

  ngOnInit(): void {
    this.spinner.show();

    this.categoriaFormulario = this.formBuilder.group({
      nome:['', [Validators.required, Validators.minLength(3)]],
      tipo:['', [Validators.required]],
    });

    this.route.params?.subscribe(value => {
      if (value?.id) {
        this.id = value.id;
        this.editar = true;
        this.service.retornarCategoriaId(this.id).subscribe( result => {
          this.categoria = result;
          this.preencherFormulario();
        });
      }
    });

    this.spinner.hide();
    this.carregando = false;

  }

  preencherFormulario() {
    this.categoriaFormulario.patchValue({
      nome: this.categoria.nome,
      tipo: this.categoria.tipo,
    });
  }

  envia() {
    const categoria = this.categoriaFormulario.getRawValue();

    this.enviarFormulario({
      id: this.id,
      edit: this.editar,
      mensagemSucessoEdicao: 'Categoria editada com sucesso',
      mensagemSucessoInsercao: 'Categoria cadastrada com sucesso',
      rota: this.rotaListagem,
      formGroup: this.categoriaFormulario,
      objeto: categoria
    } as  EntradaEnvio );

  }

}
