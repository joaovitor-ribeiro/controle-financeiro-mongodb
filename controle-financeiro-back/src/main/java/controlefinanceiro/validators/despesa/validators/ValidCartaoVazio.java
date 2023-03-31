package controlefinanceiro.validators.despesa.validators;

import controlefinanceiro.model.Despesa;
import controlefinanceiro.validators.despesa.ValidatorDespesa;

public class ValidCartaoVazio extends ValidatorDespesa {
	
	public ValidCartaoVazio(ValidatorDespesa proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Despesa despesa) throws Exception {
		return despesa.getCartao_id() == null || despesa.getCartao_id() <= 0;
	}

	@Override
	public void lancaException(Despesa despesa) {
		throw new RuntimeException("O campo cartão é de preenchimento obrigatório!");
	}

}
