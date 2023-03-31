package controlefinanceiro.validators.ganho.validators;

import controlefinanceiro.model.Ganho;
import controlefinanceiro.validators.ganho.ValidatorGanho;

public class ValidCategoriaVazia extends ValidatorGanho {
	
	public ValidCategoriaVazia(ValidatorGanho proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Ganho ganho) throws Exception {
		return ganho.getCategoria_id() == null || ganho.getCategoria_id() <= 0;
	}

	@Override
	public void lancaException(Ganho ganho) {
		throw new RuntimeException("O campo categoria é de preenchimento obrigatório!");
	}

}
