package controlefinanceiro.validators.categoria;

import controlefinanceiro.model.Categoria;

public abstract class ValidatorCategoria {

	private ValidatorCategoria proximo;
	
	public ValidatorCategoria(ValidatorCategoria proximo) {
		this.proximo = proximo;
	}
	
	public void verifica(Categoria categoria) throws Exception {
		if (erro(categoria)) {
			lancaException(categoria);
			return;
		}
		proximo.verifica(categoria);
	}
	
	public abstract boolean erro(Categoria categoria) throws Exception;
	public abstract void lancaException(Categoria categoria);
	
}
