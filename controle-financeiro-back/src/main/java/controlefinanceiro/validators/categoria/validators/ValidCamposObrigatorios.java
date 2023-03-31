package controlefinanceiro.validators.categoria.validators;

import java.util.HashMap;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.utils.ValidUtils;
import controlefinanceiro.validators.categoria.ValidatorCategoria;

public class ValidCamposObrigatorios extends ValidatorCategoria {
	
	ValidUtils utils = new ValidUtils();
	
	public ValidCamposObrigatorios(ValidatorCategoria proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Categoria categoria) throws Exception {
		HashMap<String, String> campos = new HashMap<>();
		campos.put("nome", "nome");
		campos.put("tipo", "tipo");
		
		utils.validaObject(categoria, campos);
		return false;
	}

	@Override
	public void lancaException(Categoria categoria) {}

}
