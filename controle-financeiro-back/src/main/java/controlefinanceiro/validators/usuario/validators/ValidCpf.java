package controlefinanceiro.validators.usuario.validators;

import controlefinanceiro.model.Usuario;
import controlefinanceiro.validators.usuario.ValidatorUsuario;

public class ValidCpf extends ValidatorUsuario {

	public ValidCpf(ValidatorUsuario proximo) {
		super(proximo);
	}
	
	@Override
	public boolean erro(Usuario usuario) throws Exception {
		return !validarCPF(usuario.getCpf());
	}

	@Override
	public void lancaException(Usuario usuario) {
		throw new RuntimeException("CPF inválido!");
	}
	
	
	private boolean validarCPF(String cpf) {
		int soma = 0;
		int resto;
		cpf = cpf.replace(" ", "");
		
		if (cpf == "00000000000" || cpf.length() != 11) return false;

		for (int i = 1; i <= 9; i++) {
			soma = soma + Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
		}
		resto = soma * 10 % 11;

		if ((resto == 10) || (resto == 11)) resto = 0;
		if (resto != Integer.parseInt(cpf.substring(9, 10))) return false;

		soma = 0;
		for (int i = 1; i <= 10; i++){
			soma = soma + Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
		}
		resto = soma * 10 % 11;

		if ((resto == 10) || (resto == 11)) resto = 0;
		if (resto != Integer.parseInt(cpf.substring(10, 11))) return false;

		return true;
	}

}
