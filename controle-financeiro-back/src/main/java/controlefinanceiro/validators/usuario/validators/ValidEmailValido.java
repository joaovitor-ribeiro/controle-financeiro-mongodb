package controlefinanceiro.validators.usuario.validators;

import controlefinanceiro.model.Usuario;
import controlefinanceiro.validators.usuario.ValidatorUsuario;

public class ValidEmailValido extends ValidatorUsuario {

	public ValidEmailValido(ValidatorUsuario proximo) {
		super(proximo);
	}
	
	@Override
	public boolean erro(Usuario usuario) throws Exception {
		return !usuario.getEmail().contains("@") || !usuario.getEmail().contains(".com");
	}

	@Override
	public void lancaException(Usuario usuario) {
		throw new RuntimeException("Email inválido!");
	}

}
