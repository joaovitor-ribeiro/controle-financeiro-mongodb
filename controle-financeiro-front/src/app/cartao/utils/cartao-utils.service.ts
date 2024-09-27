import { Injectable } from '@angular/core';
import { Bandeira } from '../cartao.model';

@Injectable({
  providedIn: 'root'
})
export class CartaoUtilsService {

  constructor() { }

  validarNumero(numero: string) {
    let total = 0;
    let deveDobrar = false;
    for (let i = numero.length - 1; i >=0; i--) {
      let digito = +numero[i];
      if (deveDobrar) {
        digito *= 2;
        if (digito > 9) digito -= 9;
      }
      total += digito;
      deveDobrar = !deveDobrar;
    }
    return total % 10 === 0;
  }

  validarNumeroCorrespondenteABandeira(bandeira: string, numero: string) {
    switch (bandeira) {
      case Bandeira.mastercard:
        return numero.startsWith("51") || numero.startsWith("52") || numero.startsWith("53") ||
               numero.startsWith("54") || numero.startsWith("55");
      case Bandeira.visa:
        return numero.startsWith("4");
      case Bandeira.jcb:
        return numero.startsWith("35");
      case Bandeira.americanExpress:
        return numero.startsWith("34") || numero.startsWith("37");
      case Bandeira.disnerClub:
        return numero.startsWith("300") || numero.startsWith("301") || numero.startsWith("302") ||
               numero.startsWith("303") || numero.startsWith("304") || numero.startsWith("305") ||
               numero.startsWith("36")  || numero.startsWith("38");
      case Bandeira.aura:
        return numero.startsWith("50");
      case Bandeira.hipercard:
        return numero.startsWith("606282");
      default:
        return false;
    }
  }


}
