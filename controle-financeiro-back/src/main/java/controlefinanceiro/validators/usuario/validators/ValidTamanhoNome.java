package controlefinanceiro.validators.usuario.validators;

import controlefinanceiro.model.Usuario;
import controlefinanceiro.utils.ValidUtils;
import controlefinanceiro.validators.usuario.ValidatorUsuario;

public class ValidTamanhoNome extends ValidatorUsuario {
	
	ValidUtils utils = new ValidUtils();

	public ValidTamanhoNome(ValidatorUsuario proximo) {
		super(proximo);
	}
	
	@Override
	public boolean erro(Usuario usuario) throws Exception {
		utils.validTamanho(usuario.getNome(), "nome", 3, 50);
		return false;
	}

	@Override
	public void lancaException(Usuario usuario) {}

}