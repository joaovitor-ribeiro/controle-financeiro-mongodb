package controlefinanceiro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controlefinanceiro.dto.categoria.CategoriaEntrada;
import controlefinanceiro.dto.categoria.CategoriaSaida;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.repository.CategoriaRepository;
import controlefinanceiro.validators.categoria.IniciaValidatorsCategoria;
import jakarta.validation.ValidationException;

@Service
public class CategoriaService {
	
	private final CategoriaRepository categoriaRepository;
	private final IniciaValidatorsCategoria validacao;
	
	@Autowired
	public CategoriaService(CategoriaRepository categoriaRepository, IniciaValidatorsCategoria validacao) {
		super();
		this.categoriaRepository = categoriaRepository;
		this.validacao = validacao;
	}

	public CategoriaSaida inserir(CategoriaEntrada entrada) {
		// V A L I D A Ç Õ E S
		validacao.inicia(entrada);
		
		// I N S E R T 
		int id = categoriaRepository.findAll().size() > 0 ? categoriaRepository.maxId() + 1 : 1;
		Categoria responsta = categoriaRepository.insert(new Categoria(id, entrada));
		
		// S A Í D A  
		return new CategoriaSaida(responsta);
	}

	public List<CategoriaSaida> listar(String nome, String tipo) {
		List<Categoria> categorias = new ArrayList<Categoria>();
		 
		if (!(nome == null || nome.isEmpty()) && !(tipo == null || tipo.isEmpty() || tipo.equals("T"))) {
			categorias = categoriaRepository.findNomeAndTipo(nome, tipo);
		} else if (!(nome == null || nome.isEmpty())) {
			categorias = categoriaRepository.findByNome(nome);
		} else if (!(tipo == null || tipo.isEmpty() || tipo.equals("T"))) {
			categorias = categoriaRepository.findByTipo(tipo);
		}
		
		categorias = categoriaRepository.findAll();
		
		return categorias.stream().map(c -> new CategoriaSaida(c)).toList();
	}

	public CategoriaSaida editar(CategoriaEntrada entrada, Integer id) {	
		// V A L I D A Ç Õ E S
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ValidationException("Categoria não encontrada!"));
		validacao.inicia(entrada);
		
		// S A V E 
		categoria.setNome(entrada.nome());
		categoria.setTipo(entrada.tipo());
		Categoria saida = categoriaRepository.save(categoria);
		
		// S A Í D A  
		return new CategoriaSaida(saida);
	}

	public void excluir(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ValidationException("Categoria não foi encontrada para exclusão!"));
		categoriaRepository.delete(categoria);
	}

	public CategoriaSaida retornarCategoriaId(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new ValidationException("Categoria não encontrada!"));
		return new CategoriaSaida(categoria);
	}

}
