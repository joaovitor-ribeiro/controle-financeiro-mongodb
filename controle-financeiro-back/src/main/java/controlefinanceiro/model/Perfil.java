package controlefinanceiro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Document
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	@Id 
	private Long id;
	
	private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}
	
}