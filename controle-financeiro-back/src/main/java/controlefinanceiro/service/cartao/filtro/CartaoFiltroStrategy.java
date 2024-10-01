package controlefinanceiro.service.cartao.filtro;

import java.util.List;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.repository.CartaoRepository;

public interface CartaoFiltroStrategy {
		
	 public List<Cartao> buscarCartoes(CartaoRepository cartaoRepository, String nome, List<String> bandeiras);
}
