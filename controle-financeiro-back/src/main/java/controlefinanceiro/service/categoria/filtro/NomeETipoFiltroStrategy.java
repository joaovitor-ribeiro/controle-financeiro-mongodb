package controlefinanceiro.service.categoria.filtro;

import java.util.List;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.repository.CategoriaRepository;

public class NomeETipoFiltroStrategy implements CategoriaFiltroStrategy {

	@Override
	public List<Categoria> buscarCategoria(CategoriaRepository categoriaRepository, String nome, String tipo) {
		return categoriaRepository.findNomeAndTipo(nome, tipo);
	}

}