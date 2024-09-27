import { FormGroup } from "@angular/forms";
import { Router } from "@angular/router";
import { AlertModalService } from "../alert-modal/alert-modal.service";
import { FormularioServiceInterface } from "./formulario-service-interface";

export interface EntradaEnvio {
  id : number;
  objeto : any;
  edit : boolean;
  formGroup ?: FormGroup;
  rota ?: string;
  mensagemSucessoEdicao : string;
  mensagemSucessoInsercao : string;
}

export class FormularioEnvio {

  constructor (
    public service: FormularioServiceInterface,
    public router: Router,
    public alertService: AlertModalService,
   ) {}

  enviarFormulario(entrada: EntradaEnvio ) {
    const formGroup = entrada?.formGroup;

    if ( formGroup && this.formularioInvalido(formGroup) ) {
      formGroup.markAllAsTouched();
      return;
    }

    if ( entrada?.edit ) {
      this.editarFormulario(entrada);
      return;
    }

    this.inserirFormulario(entrada);
  }

  private inserirFormulario(entrada: EntradaEnvio) {
    this.service.inserir(entrada?.objeto).subscribe(() => {
       this.sucesso(entrada?.rota || '', entrada?.mensagemSucessoInsercao);
    });
  }


  private editarFormulario(entrada: EntradaEnvio) {
    this.service.editar(entrada?.id, entrada?.objeto).subscribe(() => {
      this.sucesso(entrada?.rota || '', entrada?.mensagemSucessoEdicao);
    });
  }

  private sucesso(rota: string, mensagem: string) {
    if (rota) {
      this.router.navigate([rota], { queryParamsHandling: 'preserve' });
    }
    this.alertService.showAlertSuccess(mensagem);
  }

  private formularioInvalido(formGroup: FormGroup) : boolean {
    if (formGroup?.invalid) {
      return true;
    }

    return false;
  }
}
