package controlefinanceiro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import controlefinanceiro.dto.categoria.CategoriaEntrada;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Document
@Getter
@Setter
@NoArgsConstructor 
public class Categoria {
	
	@Id 
	private Integer id;
	
	private String nome;
	
	private String tipo;

	public Categoria(Integer id, CategoriaEntrada entrada) {
		super();
		this.id = id;
		this.nome = entrada.nome();
		this.tipo = entrada.tipo();
	}
	
	
	
}