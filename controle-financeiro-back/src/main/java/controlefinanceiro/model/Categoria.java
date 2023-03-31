package controlefinanceiro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class Categoria {
	
	@Id 
	private Integer id;
	
	private String nome;
	
	private String tipo;
}