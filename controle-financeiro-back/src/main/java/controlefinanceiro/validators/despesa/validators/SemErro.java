package controlefinanceiro.validators.despesa.validators;

import controlefinanceiro.model.Despesa;
import controlefinanceiro.validators.despesa.ValidatorDespesa;

public class SemErro extends ValidatorDespesa {

	public SemErro() {
		super(null);
	}
	
	@Override
	public boolean erro(Despesa despesa) {
		return true;
	}

	@Override
	public void lancaException(Despesa despesa) {}

}
