package controlefinanceiro.validators.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import controlefinanceiro.dto.usuario.UsuarioEntrada;

@Component
public class IniciaValidatorsUsuario {
	
	private List<ValidatorUsuario> validator;
	
	@Autowired
	public IniciaValidatorsUsuario(List<ValidatorUsuario> validator) {
		super();
		this.validator = validator;
	}

	public void inicia(UsuarioEntrada usuario) {
		validator.forEach(v -> v.valida(usuario));
	}

}
