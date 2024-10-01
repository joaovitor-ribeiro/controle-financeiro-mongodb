package controlefinanceiro.service.categoria.filtro;

import java.util.List;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.repository.CategoriaRepository;

public class CategoriaFiltroContext {
	
	private CategoriaFiltroStrategy strategy;

	public CategoriaFiltroContext(String nome, String tipo) {
		if ( nome != null && !nome.isEmpty() && tipo != null && !tipo.isEmpty() && !tipo.equalsIgnoreCase("T") ) {
			this.strategy = new NomeETipoFiltroStrategy();
		} else if ( nome != null && !nome.isEmpty() ) {
			this.strategy = new NomeFiltroStrategy();
		} else if ( tipo != null && !tipo.isEmpty() && !tipo.equalsIgnoreCase("T") ) {
			this.strategy = new TipoFiltroStrategy();
		} else {
			this.strategy = new TodosFiltroStrategy();
		}
	}

	public List<Categoria> buscarCategorias(CategoriaRepository categoriaRepository, String nome, String tipo) {
		return strategy.buscarCategoria(categoriaRepository, nome, tipo);
	}

}
