package controlefinanceiro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import controlefinanceiro.model.Cartao;

public interface CartaoRepository extends MongoRepository<Cartao, Integer> {

	@Query("{ nome: { $regex: ?0 }  } ")
	List<Cartao> findByNome(String nome);

	@Query(" { bandeira: { $in: ?0 } } ")
	List<Cartao> findByBandeiras(List<String> bandeiras);

	@Query("{ nome: { $regex: ?0 },  bandeira: { $in: ?1 }  }  ")
	List<Cartao> findNomeAndBandeiras(String nome, List<String> bandeiras);
	
	@Aggregation(pipeline = { "{ $group : { _id : null, maximo : { $max : \"$_id\" } } }" })
	Integer maxId();

}
