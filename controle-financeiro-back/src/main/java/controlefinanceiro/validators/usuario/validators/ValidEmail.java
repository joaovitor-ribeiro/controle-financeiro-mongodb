package controlefinanceiro.validators.usuario.validators;

import controlefinanceiro.model.Usuario;
import controlefinanceiro.validators.usuario.ValidatorUsuario;

public class ValidEmail extends ValidatorUsuario {

	public ValidEmail(ValidatorUsuario proximo) {
		super(proximo);
	}
	
	@Override
	public boolean erro(Usuario usuario) throws Exception {
		return usuario.getEmail().length() > 50;
	}

	@Override
	public void lancaException(Usuario usuario) {
		throw new RuntimeException("O email não pode ter mais do que 50 caracteres!");
	}

}