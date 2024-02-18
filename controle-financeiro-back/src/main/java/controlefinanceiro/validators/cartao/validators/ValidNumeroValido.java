package controlefinanceiro.validators.cartao.validators;

import org.springframework.stereotype.Component;

import controlefinanceiro.dto.cartao.CartaoEntrada;
import controlefinanceiro.validators.cartao.ValidatorCartao;
import jakarta.validation.ValidationException;

@Component
public class ValidNumeroValido implements ValidatorCartao {
	
	@Override
	public void valida(CartaoEntrada cartao) {
		if (!validarNumeroCartao(cartao.numero())) {
			throw new ValidationException("Número de cartão inválido!");
		}
		
	}
	
	private boolean validarNumeroCartao(String numero) {
		int total = 0;
		boolean deveDobrar = false;
		numero = numero.replace(" ", "");
		if ( !numero.matches("[0-9]*") ) {
			return false;
		}
		for (int i = numero.length() - 1; i >= 0; i--) {
			int digito = Integer.parseInt(numero.substring(i, (i + 1)));
			if (deveDobrar) {
				digito *= 2;
				if (digito > 9) digito -= 9;
			}
			total += digito;
			deveDobrar = !deveDobrar;
		}
		if (total <= 0) {
			return false;
		}
		return total % 10 == 0;
	}

}
