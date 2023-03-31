package controlefinanceiro.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import controlefinanceiro.model.Ganho;

public interface GanhoRepository extends MongoRepository<Ganho, Integer> {
	
	@Query(value = "{categoria_id: $0")
	Optional<Ganho> findGanhoByIdCategoria(Integer categoria);
	
	@Aggregation(pipeline = {
		    "{ $match : { data : { $gte : ?0 } } }",
		    "{ $group : { _id : null, total : { $sum : \"$valor\" } } }"
	})
	Double getTotalValor(Date data);
	
	@Aggregation(pipeline = { "{ $group : { _id : null, maximo : { $max : \"$_id\" } } }" })
	Integer maxId();
}
