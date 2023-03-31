package controlefinanceiro.validators.ganho;

import controlefinanceiro.model.Ganho;

public abstract class ValidatorGanho {
	
	private ValidatorGanho proximo;

	public ValidatorGanho(ValidatorGanho proximo) {
		this.proximo = proximo;
	}

	public void verifica(Ganho ganho) throws Exception {
		if (erro(ganho)) {
			lancaException(ganho);
			return;
		}
		proximo.verifica(ganho);
	}

	public abstract boolean erro(Ganho ganho) throws Exception;
	public abstract void lancaException(Ganho ganho);

}
