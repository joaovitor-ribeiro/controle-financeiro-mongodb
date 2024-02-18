package controlefinanceiro.validators.cartao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import controlefinanceiro.dto.cartao.CartaoEntrada;

@Component
public class IniciaValidatorsCartao {
	
	List<ValidatorCartao> validacoes;
	
	@Autowired
	public IniciaValidatorsCartao(List<ValidatorCartao> validacoes) {
		this.validacoes = validacoes;
	}
	
	public void inicia(CartaoEntrada entrada) {
		validacoes.forEach(v -> v.valida(entrada));
	}

}
