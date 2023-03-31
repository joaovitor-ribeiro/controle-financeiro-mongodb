package controlefinanceiro.model;

import java.util.Date;

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
public class Despesa {
	
	@Id
	private Integer id;
	
	private Integer categoria_id;
	
	private String descricao;
	
	private Integer cartao_id;
	
	private Double valor;
	
	private Date data;
	

}
