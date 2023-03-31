import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { NgxMaskModule } from 'ngx-mask';
import { NgxSpinnerModule } from 'ngx-spinner';
import { AlertModalModule } from 'src/app/shared/alert-modal/alert-modal.module';

import { InputFieldModule } from './../../shared/input-field/input-field.module';
import { NumberFieldModule } from './../../shared/number-field/number-field.module';
import { CartaoFormComponent } from './cartao-form.component';

@NgModule({
  declarations: [
    CartaoFormComponent
  ],
  imports: [
    CommonModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    NgxMaskModule,
    NgxMaskModule.forRoot(),
    AlertModalModule,
    NgxSpinnerModule,
    MatSelectModule,
    InputFieldModule,
    NumberFieldModule
  ]
})
export class CartaoFormModule { }
