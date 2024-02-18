package controlefinanceiro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import controlefinanceiro.dto.cartao.CartaoEntrada;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Document
@Getter
@Setter
@NoArgsConstructor
public class Cartao {
	
	@Id 
	private Integer id;
	
	private String nome;
	
	private String bandeira;
	
	private String numero;
	
	private Double limite;	
	
	
	public Cartao(Integer id, CartaoEntrada entrada) {
		this.id       = id;
		this.nome     = entrada.nome();
		this.bandeira = entrada.bandeira();
		this.numero   = entrada.numero();
		this.limite   = entrada.limite();
	}
}