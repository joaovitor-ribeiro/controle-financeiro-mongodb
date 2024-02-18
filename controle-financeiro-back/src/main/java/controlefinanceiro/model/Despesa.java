package controlefinanceiro.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import controlefinanceiro.dto.despesa.DespesaEntrada;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Document
@Getter
@Setter
@NoArgsConstructor 
public class Despesa {
	
	@Id
	private Integer id;
	
	private Categoria categoria;
	
	private String descricao;
	
	private Cartao cartao;
	
	private Double valor;
	
	private Date data;
	
	public Despesa(Integer id, DespesaEntrada entrada, Cartao cartao, Categoria categoria) {
		this.id        = id;
		this.categoria = categoria;
		this.descricao = entrada.descricao();
		this.cartao    = cartao;
		this.valor     = entrada.valor();
		this.data      = entrada.data();
	}
	
}
