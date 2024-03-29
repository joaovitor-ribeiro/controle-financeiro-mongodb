import { Component, Input, OnInit } from '@angular/core';
import { BsModalRef } from 'ngx-bootstrap/modal';
import { Subject } from 'rxjs';

import { ErrorModel } from '../model/error.model';

@Component({
  selector: 'app-error-modal',
  templateUrl: './error-modal.component.html',
  styleUrls: ['./error-modal.component.scss']
})
export class ErrorModalComponent implements OnInit {

  @Input() msg ?: string;

  @Input() erros ?: ErrorModel[];

  @Input() exibirTemplate = false;

  errorResult!: Subject<boolean>;

  constructor(public bsModalRef: BsModalRef) { }

  ngOnInit() {
    this.errorResult = new Subject();
  }

  onClose() {
    this.errorAndClose(false);
  }

  private errorAndClose(value: boolean) {
    this.errorResult.next(value);
    this.bsModalRef.hide();
  }

}
