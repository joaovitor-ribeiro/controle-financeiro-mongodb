package controlefinanceiro.validators.usuario.validators;

import org.springframework.stereotype.Component;

import controlefinanceiro.dto.usuario.UsuarioEntrada;
import controlefinanceiro.exception.ValidationException;
import controlefinanceiro.validators.usuario.ValidatorUsuario;

@Component
public class ValidCpf implements ValidatorUsuario {

	@Override
	public void valida(UsuarioEntrada usuario) {
		if (!validarCPF(usuario.cpf())) {
			throw new ValidationException("CPF inválido!");
		}
	}

	private static boolean validarCPF(String cpf) {
		int soma = 0;
		int resto;

		if (cpf == null) return false;

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
