package controlefinanceiro.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import controlefinanceiro.model.Usuario;
import controlefinanceiro.repository.UsuarioRepository;
import controlefinanceiro.validators.usuario.IniciaValidatorsUsuario;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public Integer inserir(Usuario usuario) throws Exception {
		new IniciaValidatorsUsuario().inicia(usuario);
		int id = usuarioRepository.findAll().size() > 0 ? usuarioRepository.maxId() + 1 : 1;
		usuario.setId(id);
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		usuarioRepository.save(usuario);
		return usuario.getId();
	}
	
	public void inserirFoto(MultipartFile foto, Integer id) throws IOException {
		if (usuarioRepository.findById(id).isPresent()) {
			Query query = new Query().addCriteria(Criteria.where("_id").is(id));
			Update update = new Update().set("foto", foto.getBytes());
			mongoTemplate.updateFirst(query, update, Usuario.class);
		} else {
			throw new RuntimeException("Usuário não encontrado!");
		}
	}
		
	public Usuario retornarUsuarioId(Integer id) {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		
		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}
		
		throw new RuntimeException("Usuário não encontrado!");
	}

	public void editar(Integer id, Usuario usuario) throws Exception {
		new IniciaValidatorsUsuario().inicia(usuario);
		if (usuarioRepository.findById(id).isPresent()) {
			Query query = new Query().addCriteria(Criteria.where("_id").is(id));
			Update update = new Update().set("nome", usuario.getNome())
										.set("cpf", usuario.getCpf())
										.set("email", usuario.getEmail())
										.set("senha", usuario.getSenha());
			mongoTemplate.updateFirst(query, update, Usuario.class);
		} else {
			throw new RuntimeException("Usuário não foi encontrado para edição!");
		}
	}

	public void excluir(Integer id){
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		
		if (optionalUsuario.isPresent()) {	
			usuarioRepository.deleteById(id);
		} else {
			throw new RuntimeException("Usuário não foi encontrado para a exclusão!");
		}
	}
	
	public boolean validarCPF(String cpf) {
		int soma = 0;
		int resto;
		cpf = cpf.replace(" ", "");
		
		if (cpf == "00000000000" || cpf.length() != 11) return false;

		for (int i = 1; i <= 9; i++) {
			soma = soma + Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
		}
		resto = soma * 10 % 11;

		if ((resto == 10) || (resto == 11)) resto = 0;
		if (resto != Integer.parseInt(cpf.substring(9, 10))) return false;

		soma = 0;
		for (int i = 1; i <= 10; i++){
			soma = soma + Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
		}
		resto = soma * 10 % 11;

		if ((resto == 10) || (resto == 11)) resto = 0;
		if (resto != Integer.parseInt(cpf.substring(10, 11))) return false;

		return true;
	}
}
