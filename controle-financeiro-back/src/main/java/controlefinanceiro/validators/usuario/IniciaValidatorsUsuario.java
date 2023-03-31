package controlefinanceiro.validators.usuario;

import controlefinanceiro.model.Usuario;
import controlefinanceiro.validators.usuario.validators.SemErro;
import controlefinanceiro.validators.usuario.validators.ValidCamposObrigatorios;
import controlefinanceiro.validators.usuario.validators.ValidCpf;
import controlefinanceiro.validators.usuario.validators.ValidEmail;
import controlefinanceiro.validators.usuario.validators.ValidEmailValido;
import controlefinanceiro.validators.usuario.validators.ValidTamanhoNome;
import controlefinanceiro.validators.usuario.validators.ValidTamanhoSenha;

public class IniciaValidatorsUsuario {
	
	public void inicia(Usuario usuario) throws Exception {
		ValidatorUsuario validatorUsuario = new ValidCamposObrigatorios(
						                    new ValidTamanhoNome(
						                    new ValidCpf(
						                   	new ValidEmail(
						                    new ValidEmailValido(
						                    new ValidTamanhoSenha(
						                    new SemErro()))))));
		validatorUsuario.verifica(usuario);
	}

}
