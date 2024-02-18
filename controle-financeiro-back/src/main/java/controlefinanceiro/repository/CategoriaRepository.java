package controlefinanceiro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import controlefinanceiro.model.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, Integer>, RepositoryControleFinanceiro {
	
	@Query(value = "{ nome: {$regex: ?0} }")
	List<Categoria> findByNome(String nome);
	
	List<Categoria> findByTipo(String tipo);
	
	@Query(value = "{ nome: {$regex: ?0}, tipo: ?1 }")
	List<Categoria> findNomeAndTipo(String nome, String tipo);
	
}
