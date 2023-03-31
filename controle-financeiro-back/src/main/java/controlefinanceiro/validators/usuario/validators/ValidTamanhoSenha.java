package controlefinanceiro.validators.usuario.validators;

import controlefinanceiro.model.Usuario;
import controlefinanceiro.validators.usuario.ValidatorUsuario;

public class ValidTamanhoSenha extends ValidatorUsuario {

	public ValidTamanhoSenha(ValidatorUsuario proximo) {
		super(proximo);
	}
	
	@Override
	public boolean erro(Usuario usuario) throws Exception {
		return usuario.getSenha().length() < 6 || usuario.getSenha().length() > 8;
	}

	@Override
	public void lancaException(Usuario usuario) {
		throw new RuntimeException("A senha deve conter entre 6 e 8 caracteres!");
	}

}