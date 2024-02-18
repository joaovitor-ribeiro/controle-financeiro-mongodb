package controlefinanceiro.validators.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import controlefinanceiro.dto.categoria.CategoriaEntrada;

@Component
public class IniciaValidatorsCategoria {
	
	private List<ValidatorCategoria> validacoes;
	
	@Autowired
	public IniciaValidatorsCategoria(List<ValidatorCategoria> validacoes) {
		super();
		this.validacoes = validacoes;
	}

	public void inicia(CategoriaEntrada entrada) {
		validacoes.forEach(v -> v.valida(entrada));
	}

}
