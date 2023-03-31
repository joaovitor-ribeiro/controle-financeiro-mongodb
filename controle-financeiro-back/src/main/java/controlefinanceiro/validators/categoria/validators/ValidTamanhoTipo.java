package controlefinanceiro.validators.categoria.validators;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.validators.categoria.ValidatorCategoria;

public class ValidTamanhoTipo extends ValidatorCategoria {
	
	public ValidTamanhoTipo(ValidatorCategoria proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Categoria categoria) throws Exception {
		return categoria.getTipo().length() > 1;
	}

	@Override
	public void lancaException(Categoria categoria) {
		throw new RuntimeException("O tipo tem que ter um caracter. Informe G para ganho ou D para despesa!");
	}

}
