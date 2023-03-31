package controlefinanceiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.repository.CategoriaRepository;
import controlefinanceiro.validators.categoria.IniciaValidatorsCategoria;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public void inserir(Categoria categoria) throws Exception {
		int id = categoriaRepository.findAll().size() > 0 ? categoriaRepository.maxId() + 1 : 1;
		categoria.setId(id);
		new IniciaValidatorsCategoria().inicia(categoria);
		categoriaRepository.save(categoria);
	}

	public List<Categoria> listar(String nome, String tipo) {
		if (!(nome == null || nome.isEmpty()) && !(tipo == null || tipo.isEmpty() || tipo.equals("T"))) {
			return categoriaRepository.findNomeAndTipo(nome, tipo);
		} else if (!(nome == null || nome.isEmpty())) {
			return categoriaRepository.findByNome(nome);
		} else if (!(tipo == null || tipo.isEmpty() || tipo.equals("T"))) {
			return categoriaRepository.findByTipo(tipo);
		}
		return categoriaRepository.findAll();
	}

	public void editar(Categoria categoria, Integer id) throws Exception {	
		if (categoriaRepository.findById(id).isPresent()) {
			new IniciaValidatorsCategoria().inicia(categoria);
			Query query = Query.query(Criteria.where("_id").is(id));
			Update update = new Update().set("nome", categoria.getNome())
					.set("tipo", categoria.getTipo());
			mongoTemplate.updateFirst(query, update, Categoria.class);
		} else {
			throw new RuntimeException("Categoria não encontrada!");	
		}
	}

	public void excluir(Integer id) {
		Optional<Categoria> optionCategoria = categoriaRepository.findById(id);
		if (optionCategoria.isPresent()) {
			categoriaRepository.deleteById(id);
		} else {
			throw new RuntimeException("Categoria não foi encontrada para exclusão!");
		}
	}

	public Categoria retornarCategoriaId(Integer id) {
		Optional<Categoria> optionCategoria = categoriaRepository.findById(id);
		if (optionCategoria.isPresent()) {
			return optionCategoria.get();
		}
		throw new RuntimeException("Categoria não encontrada!");
	}

}
