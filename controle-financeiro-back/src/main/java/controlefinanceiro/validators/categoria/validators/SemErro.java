package controlefinanceiro.validators.categoria.validators;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.validators.categoria.ValidatorCategoria;

public class SemErro extends ValidatorCategoria {

	public SemErro() {
		super(null);
	}
	
	@Override
	public boolean erro(Categoria categoria) {
		return true;
	}

	@Override
	public void lancaException(Categoria categoria) {}

}
