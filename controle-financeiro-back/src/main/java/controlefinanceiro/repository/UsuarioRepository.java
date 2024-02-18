package controlefinanceiro.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import controlefinanceiro.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Integer>, RepositoryControleFinanceiro {
	
	Optional<Usuario> findByEmail(String email);
	
}
