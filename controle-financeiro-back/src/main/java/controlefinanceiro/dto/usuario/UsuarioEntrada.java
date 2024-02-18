package controlefinanceiro.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioEntrada(
		
		@NotBlank
		@Size(min = 3, max = 50)
		String nome,
		
		@NotBlank
		String cpf,
		
		@NotBlank
		@Email
		@Size(max = 50)
		String email,
		
		@NotBlank
		@Size(min = 6, max = 8)
		String senha
		) {
	
}
