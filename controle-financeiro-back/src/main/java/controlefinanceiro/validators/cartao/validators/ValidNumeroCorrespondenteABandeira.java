package controlefinanceiro.validators.cartao.validators;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.validators.cartao.ValidatorCartao;

public class ValidNumeroCorrespondenteABandeira extends ValidatorCartao {
	
	public ValidNumeroCorrespondenteABandeira(ValidatorCartao proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Cartao cartao) {
		return !validarNumeroCorrespondenteABandeira(cartao.getNumero(), cartao.getBandeira());
	}

	@Override
	public void lancaException(Cartao cartao) {
		throw new RuntimeException("O número de cartão informado não corresponde com a bandeira!");
	}
	
	private boolean validarNumeroCorrespondenteABandeira(String numero, String bandeira) {
		switch (bandeira) {
		case "Mastercard":
			return numero.startsWith("51") || numero.startsWith("52") || numero.startsWith("53") || 
				   numero.startsWith("54") || numero.startsWith("55");
		case "Visa":
			return numero.startsWith("4");
		case "JCB":
			return numero.startsWith("35");
		case "American Express":
			return numero.startsWith("34") || numero.startsWith("37");
		case "Diners Club":
			return numero.startsWith("300") || numero.startsWith("301") || numero.startsWith("302") || 
				   numero.startsWith("303") || numero.startsWith("304") || numero.startsWith("305") || 
				   numero.startsWith("36")  || numero.startsWith("38");
		case "Aura":
			return numero.startsWith("50");
		case "Hipercard":
			return numero.startsWith("606282");
		default:
			return false;
		}
		
	}
	

}
