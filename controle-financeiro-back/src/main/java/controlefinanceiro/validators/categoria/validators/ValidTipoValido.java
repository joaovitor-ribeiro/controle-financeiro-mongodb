package controlefinanceiro.validators.categoria.validators;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.validators.categoria.ValidatorCategoria;

public class ValidTipoValido extends ValidatorCategoria {
	
	public ValidTipoValido(ValidatorCategoria proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Categoria categoria) throws Exception {
		return !categoria.getTipo().equals("G") && !categoria.getTipo().equals("D");
	}

	@Override
	public void lancaException(Categoria categoria) {
		throw new RuntimeException("Informe G para ganho ou D para despesa!");
	}

}