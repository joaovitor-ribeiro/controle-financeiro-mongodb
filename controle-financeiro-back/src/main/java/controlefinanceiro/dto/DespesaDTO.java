package controlefinanceiro.dto;

import java.util.Date;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Despesa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DespesaDTO {

	private Integer id;

	private Categoria categoria;

	private String descricao;

	private Cartao cartao;

	private Double valor;

	private Date data;
	
	
	public DespesaDTO(Despesa despesa, Categoria categoria, Cartao cartao) {
		this.id        = despesa.getId();
		this.descricao = despesa.getDescricao();
		this.valor     = despesa.getValor();
		this.data      = despesa.getData();
		this.categoria = categoria;
		this.cartao    = cartao;
	}
	
}
