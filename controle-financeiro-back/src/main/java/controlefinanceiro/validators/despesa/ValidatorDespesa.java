package controlefinanceiro.validators.despesa;

import controlefinanceiro.model.Despesa;

public abstract class ValidatorDespesa {
	
	private ValidatorDespesa proximo;

	public ValidatorDespesa(ValidatorDespesa proximo) {
		this.proximo = proximo;
	}

	public void verifica(Despesa despesa) throws Exception {
		if (erro(despesa)) {
			lancaException(despesa);
			return;
		}
		proximo.verifica(despesa);
	}

	public abstract boolean erro(Despesa despesa) throws Exception;
	public abstract void lancaException(Despesa despesa);

}
