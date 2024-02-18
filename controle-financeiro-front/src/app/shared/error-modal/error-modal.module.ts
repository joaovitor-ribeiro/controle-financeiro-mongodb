import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ErrorModalComponent } from './error-modal.component';
import { MatExpansionModule } from '@angular/material/expansion';

@NgModule({
  declarations: [ErrorModalComponent],
  imports: [
    CommonModule,
    MatExpansionModule
  ],
  exports: [ErrorModalComponent]
})
export class ErrorModalModule { }
