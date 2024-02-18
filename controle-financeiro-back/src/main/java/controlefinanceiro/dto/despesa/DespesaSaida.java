package controlefinanceiro.dto.despesa;

import java.util.Date;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Despesa;

public record DespesaSaida(Integer id, Categoria categoria, String descricao, Cartao cartao , Double valor, Date data) {

	public DespesaSaida(Despesa despesa) {
		this(despesa.getId(), despesa.getCategoria(), despesa.getDescricao(), despesa.getCartao(), despesa.getValor(), despesa.getData());
	}
	
}
