package controlefinanceiro.service.cartao.filtro;

import java.util.List;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.repository.CartaoRepository;

public class CartaoFiltroContext {

	private CartaoFiltroStrategy strategy;

	public CartaoFiltroContext(String nome, List<String> bandeiras) {
		if (nome != null && !nome.isEmpty() && bandeiras != null && !bandeiras.isEmpty()) {
			this.strategy = new NomeEBandeirasFiltroStrategy();
		} else if (nome != null && !nome.isEmpty()) {
			this.strategy = new NomeFiltroStrategy();
		} else if (bandeiras != null && !bandeiras.isEmpty()) {
			this.strategy = new BandeirasFiltroStrategy();
		} else {
			this.strategy = new TodosFiltroStrategy();
		}
	}

	public List<Cartao> buscarCartoes(CartaoRepository cartaoRepository, String nome, List<String> bandeiras) {
		return strategy.buscarCartoes(cartaoRepository, nome, bandeiras);
	}

}
