package controlefinanceiro.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import controlefinanceiro.model.Despesa;

public interface DespesaRepository extends MongoRepository<Despesa, Integer>, RepositoryControleFinanceiro{
	
	@Query( " {categoria_id: ?0} ")
	Optional<Despesa> findDespesaByIdCategoria(Integer categoria);
	
	@Query("{cartao_id: ?0} ")
	Optional<Despesa> findDespesaByIdCartao(Integer cartao);
	
	@Aggregation(pipeline = {
		    "{ $match : { data : { $gt : ?0 } } }",
		    "{ $group : { _id : null, total : { $sum : \"$valor\" } } }"
	})
	Double getTotalValor(Date data);
	
}
