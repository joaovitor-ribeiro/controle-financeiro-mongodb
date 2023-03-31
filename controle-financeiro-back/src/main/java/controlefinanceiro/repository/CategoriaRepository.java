package controlefinanceiro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import controlefinanceiro.model.Categoria;

public interface CategoriaRepository extends MongoRepository<Categoria, Integer> {
	
	@Query(value = "{ nome: {$regex: ?0} }")
	List<Categoria> findByNome(String nome);
	
	List<Categoria> findByTipo(String tipo);
	
	@Query(value = "{ nome: {$regex: ?0}, tipo: ?1 }")
	List<Categoria> findNomeAndTipo(String nome, String tipo);
	
	@Aggregation(pipeline = { "{ $group : { _id : null, maximo : { $max : \"$_id\" } } }" })
	Integer maxId();

}
