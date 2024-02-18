package controlefinanceiro.repository;

import org.springframework.data.mongodb.repository.Aggregation;

public interface RepositoryControleFinanceiro {
	
	@Aggregation(pipeline = { "{ $group : { _id : null, maximo : { $max : \"$_id\" } } }" })
	Integer maxId();

}
