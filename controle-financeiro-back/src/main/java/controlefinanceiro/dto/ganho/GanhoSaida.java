package controlefinanceiro.dto.ganho;

import java.util.Date;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Ganho;

public record GanhoSaida(Integer id, Categoria categoria, String descricao, Double valor, Date data) {

	public GanhoSaida(Ganho ganhoSalvo) {
		this(ganhoSalvo.getId(), ganhoSalvo.getCategoria(), ganhoSalvo.getDescricao(), ganhoSalvo.getValor(), ganhoSalvo.getData());
	}
	
}
