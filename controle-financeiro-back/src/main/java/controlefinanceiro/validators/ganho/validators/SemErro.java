package controlefinanceiro.validators.ganho.validators;

import controlefinanceiro.model.Ganho;
import controlefinanceiro.validators.ganho.ValidatorGanho;

public class SemErro extends ValidatorGanho {

	public SemErro() {
		super(null);
	}
	
	@Override
	public boolean erro(Ganho ganho) {
		return true;
	}

	@Override
	public void lancaException(Ganho ganho) {}

}
