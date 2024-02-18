package controlefinanceiro.model;

import java.util.Date;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import controlefinanceiro.dto.ganho.GanhoEntrada;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Document
@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor
public class Ganho {
	
	@Id 
	private Integer id;
	
	private String descricao;
	
	private Categoria categoria;
	
	private Double valor;
	
	private Date data;
	
	public Ganho(int id, GanhoEntrada entrada, Categoria categoria) {
		this.id = id;
		this.descricao = entrada.descricao();
		this.categoria = categoria;
		this.valor = entrada.valor();
		this.data = entrada.data();
	}
}