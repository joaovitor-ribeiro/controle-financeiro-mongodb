import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { CardPainelComponent } from './card-painel.component';
import { MatIconModule } from '@angular/material/icon';



@NgModule({
  declarations: [CardPainelComponent],
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule
  ],
  exports: [CardPainelComponent]
})
export class CardPainelModule { }
