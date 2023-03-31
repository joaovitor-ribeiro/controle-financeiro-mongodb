package controlefinanceiro.validators.despesa.validators;

import controlefinanceiro.model.Despesa;
import controlefinanceiro.validators.despesa.ValidatorDespesa;

public class ValidCategoriaVazia extends ValidatorDespesa {
	
	public ValidCategoriaVazia(ValidatorDespesa proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Despesa despesa) throws Exception {
		return despesa.getCategoria_id() == null || despesa.getCategoria_id() <= 0;
	}

	@Override
	public void lancaException(Despesa despesa) {
		throw new RuntimeException("O campo categoria é de preenchimento obrigatório!");
	}

}
