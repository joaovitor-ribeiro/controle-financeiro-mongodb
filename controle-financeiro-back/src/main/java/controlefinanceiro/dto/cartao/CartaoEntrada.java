package controlefinanceiro.dto.cartao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CartaoEntrada(
		@NotBlank 
		@Size(min = 3, max = 20)
		String nome, 
		
		@NotBlank 
		@Size(min = 3, max = 20)
		String bandeira,
		
		@NotBlank 
		@Size(min = 13, max = 16)
		String numero, 
		
		@NotNull
		@Positive
		Double limite) {

	
}
