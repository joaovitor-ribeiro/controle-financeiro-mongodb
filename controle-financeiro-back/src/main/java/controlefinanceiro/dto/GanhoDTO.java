package controlefinanceiro.dto;

import java.util.Date;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Ganho;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GanhoDTO {
	
	private Integer id;

	private Categoria categoria;

	private String descricao;

	private Double valor;

	private Date data;

	public GanhoDTO(Ganho ganho, Categoria categoria) {
		this.id        = ganho.getId();
		this.descricao = ganho.getDescricao();
		this.valor     = ganho.getValor();
		this.data      = ganho.getData();
		this.categoria = categoria;
	}

}
