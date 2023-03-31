package controlefinanceiro.validators.categoria.validators;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.utils.ValidUtils;
import controlefinanceiro.validators.categoria.ValidatorCategoria;

public class ValidTamanhoNome extends ValidatorCategoria {
	
	ValidUtils utils = new ValidUtils();
	
	public ValidTamanhoNome(ValidatorCategoria proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Categoria categoria) throws Exception {
		utils.validTamanho(categoria.getNome(), "nome", 3, 20);
		return false;
	}

	@Override
	public void lancaException(Categoria categoria) {}


}
