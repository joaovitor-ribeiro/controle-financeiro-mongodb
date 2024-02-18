package controlefinanceiro.dto.categoria;

import controlefinanceiro.model.Categoria;

public record CategoriaSaida(Integer id, String nome, String tipo) {
	
	public CategoriaSaida(Categoria categoria) {
		this(categoria.getId(), categoria.getNome(), categoria.getTipo());
	}

}
