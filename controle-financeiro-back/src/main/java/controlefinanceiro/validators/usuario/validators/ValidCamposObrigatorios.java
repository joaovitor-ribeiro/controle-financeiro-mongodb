package controlefinanceiro.validators.usuario.validators;

import java.util.HashMap;

import controlefinanceiro.model.Usuario;
import controlefinanceiro.utils.ValidUtils;
import controlefinanceiro.validators.usuario.ValidatorUsuario;

public class ValidCamposObrigatorios extends ValidatorUsuario {
	
	ValidUtils utils = new ValidUtils();
	
	public ValidCamposObrigatorios(ValidatorUsuario proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Usuario usuario) throws Exception {
		HashMap<String, String> campos = new HashMap<>();
		campos.put("nome", "nome");
		campos.put("cpf", "CPF");
		campos.put("email", "email");
		campos.put("senha", "senha");
		
		utils.validaObject(usuario, campos);
		return false;
	}

	@Override
	public void lancaException(Usuario usuario) {}

}
