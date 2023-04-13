import { Component, OnInit, Input } from '@angular/core';
import { CardPainel } from './card-paine.model';

@Component({
  selector: 'app-card-painel',
  templateUrl: './card-painel.component.html',
  styleUrls: ['./card-painel.component.scss']
})
export class CardPainelComponent implements OnInit {

  @Input()
  cardPaineis: CardPainel[] = []

  constructor() { }

  ngOnInit(): void {
  }

}
