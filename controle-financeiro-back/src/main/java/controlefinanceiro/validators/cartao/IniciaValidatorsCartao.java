package controlefinanceiro.validators.cartao;

import java.util.List;

import controlefinanceiro.validators.IniciaValidators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import controlefinanceiro.dto.cartao.CartaoEntrada;

@Component
public class IniciaValidatorsCartao implements IniciaValidators<CartaoEntrada> {
	
	List<ValidatorCartao> validacoes;
	
	@Autowired
	public IniciaValidatorsCartao(List<ValidatorCartao> validacoes) {
		this.validacoes = validacoes;
	}
	
	public void inicia(CartaoEntrada entrada) {
		validacoes.forEach( v -> v.valida(entrada) );
	}

}
