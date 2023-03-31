package controlefinanceiro.validators.cartao.validators;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.validators.cartao.ValidatorCartao;

public class ValidNumeroValido extends ValidatorCartao {
	
	public ValidNumeroValido(ValidatorCartao proximo) {
		super(proximo);
	}
	
	@Override
	public boolean erro(Cartao cartao) {
		return cartao.getNumero().length() < 13 || cartao.getNumero().length() > 16 || !validarNumeroCartao(cartao.getNumero());
	}

	@Override
	public void lancaException(Cartao cartao) {
		throw new RuntimeException("Número de cartão inválido!");
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
