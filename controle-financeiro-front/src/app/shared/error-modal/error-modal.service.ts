import { Injectable } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { ErrorModalComponent } from './error-modal.component';
import { ErrorModel } from '../model/error.model';

@Injectable({
  providedIn: 'root'
})
export class ErrorModalService {

  constructor(private modalService: BsModalService) {}

  showError(msg: string | ErrorModel[]) {
    const bsModalRef: BsModalRef = this.modalService.show(ErrorModalComponent);

    if (Array.isArray(msg)) {
      bsModalRef.content.erros = msg;
      bsModalRef.content.exibirTemplate = true;
    } else {
      bsModalRef.content.msg = msg;
    }

    return (<ErrorModalComponent>bsModalRef.content).errorResult;
  }
}
