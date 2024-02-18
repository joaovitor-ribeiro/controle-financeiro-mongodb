package controlefinanceiro.dto.ganho;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record GanhoEntrada(
							@NotBlank 
							@Size(min = 3, max = 20)
							String descricao,
							
							@NotNull 
							@Positive
							Integer categoria_id,
							
							@NotNull 
							@Positive
							Double valor,
							
							@NotNull 
							Date data
							) {
	
}
