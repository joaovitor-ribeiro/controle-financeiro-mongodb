package controlefinanceiro.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaEntrada(@NotBlank @Size(min = 3, max = 20) String nome, @NotBlank @Size(max = 1) String tipo) {

}