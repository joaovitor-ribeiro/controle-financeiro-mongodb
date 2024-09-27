import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class FormularioUtilsService {

  constructor(
    private router: Router
  ) { }

  limparCampo(formGroup: FormGroup, campo: string) {
    formGroup.get(campo)?.setValue('');
  }

  voltar( rota: string ){
    this.router.navigate([rota], { queryParamsHandling: 'preserve' });
  }

}
