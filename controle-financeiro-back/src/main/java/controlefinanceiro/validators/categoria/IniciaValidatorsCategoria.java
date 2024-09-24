package controlefinanceiro.validators.categoria;

import java.util.List;

import controlefinanceiro.validators.IniciaValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import controlefinanceiro.dto.categoria.CategoriaEntrada;

@Component
public class IniciaValidatorsCategoria implements IniciaValidators<CategoriaEntrada>  {
	
	private List<ValidatorCategoria> validacoes;
	
	@Autowired
	public IniciaValidatorsCategoria(List<ValidatorCategoria> validacoes) {
		super();
		this.validacoes = validacoes;
	}

	public void inicia(CategoriaEntrada entrada) {
		validacoes.forEach(v -> v.valida( entrada));
	}

}
