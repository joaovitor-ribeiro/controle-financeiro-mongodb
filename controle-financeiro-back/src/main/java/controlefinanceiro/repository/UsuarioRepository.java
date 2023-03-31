package controlefinanceiro.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import controlefinanceiro.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Integer>{
	
	Optional<Usuario> findByEmail(String email);
	
	@Aggregation(pipeline = { "{ $group : { _id : null, maximo : { $max : \"$_id\" } } }" })
	Integer maxId();

}
