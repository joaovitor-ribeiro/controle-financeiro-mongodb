package controlefinanceiro.validators.usuario;

import controlefinanceiro.model.Usuario;

public abstract class ValidatorUsuario {
	
	private ValidatorUsuario proximo;

	public ValidatorUsuario(ValidatorUsuario proximo) {
		this.proximo = proximo;
	}

	public void verifica(Usuario usuario) throws Exception {
		if (erro(usuario)) {
			lancaException(usuario);
			return;
		}
		proximo.verifica(usuario);
	}

	public abstract boolean erro(Usuario usuario) throws Exception;
	public abstract void lancaException(Usuario usuario);


}
