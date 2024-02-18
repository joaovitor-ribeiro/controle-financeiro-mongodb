package controlefinanceiro.validators.cartao.validators;

import org.springframework.stereotype.Component;

import controlefinanceiro.dto.cartao.Bandeira;
import controlefinanceiro.dto.cartao.CartaoEntrada;
import controlefinanceiro.validators.cartao.ValidatorCartao;
import jakarta.validation.ValidationException;

@Component
public class ValidNumeroCorrespondenteABandeira implements ValidatorCartao {
	
	@Override
	public void valida(CartaoEntrada cartao) {
		if (!validarNumeroCorrespondenteABandeira(cartao.numero(), cartao.bandeira())) {
			throw new ValidationException("O número de cartão informado não corresponde com a bandeira!");
		}
		
	}
	
	private boolean validarNumeroCorrespondenteABandeira(String numero, String bandeira) {
		Bandeira b = Bandeira.find(bandeira);
		
		switch (b) {
			case MASTERCARD:
				return numero.startsWith("51") || numero.startsWith("52") || numero.startsWith("53") || 
					   numero.startsWith("54") || numero.startsWith("55");
			case VISA:
				return numero.startsWith("4");
			case JCB:
				return numero.startsWith("35");
			case AMERICAN_EXPRESS:
				return numero.startsWith("34") || numero.startsWith("37");
			case DINERS_CLUB:
				return numero.startsWith("300") || numero.startsWith("301") || numero.startsWith("302") || 
					   numero.startsWith("303") || numero.startsWith("304") || numero.startsWith("305") || 
					   numero.startsWith("36")  || numero.startsWith("38");
			case AURA:
				return numero.startsWith("50");
			case HIPERCARD:
				return numero.startsWith("606282");
			default:
				return false;
		}
		
	}

	

}
