package controlefinanceiro.dto.cartao;

import controlefinanceiro.model.Cartao;

public record CartaoSaida(Integer id, String nome, String bandeira, String numero, Double limite) {

	public CartaoSaida(Cartao cartao) {
		this(cartao.getId(), cartao.getNome(), cartao.getBandeira(), cartao.getNumero(), cartao.getLimite());
	}

}
