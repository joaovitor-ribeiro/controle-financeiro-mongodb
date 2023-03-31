package controlefinanceiro.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter 
@Getter 
public class TokenBean {
	
	private String token;
	private String tipo;
	private Integer  id;
	private String nome;
	private byte[] foto;

}
