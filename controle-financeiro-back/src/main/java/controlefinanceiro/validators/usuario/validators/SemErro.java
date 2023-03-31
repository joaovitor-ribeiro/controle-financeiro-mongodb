package controlefinanceiro.validators.usuario.validators;

import controlefinanceiro.model.Usuario;
import controlefinanceiro.validators.usuario.ValidatorUsuario;

public class SemErro extends ValidatorUsuario {

	public SemErro() {
		super(null);
	}
	
	@Override
	public boolean erro(Usuario usuario) {
		return true;
	}

	@Override
	public void lancaException(Usuario usuario) {}

}
