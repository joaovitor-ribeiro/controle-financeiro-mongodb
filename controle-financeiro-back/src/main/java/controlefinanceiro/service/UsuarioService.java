package controlefinanceiro.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import controlefinanceiro.dto.usuario.UsuarioEntrada;
import controlefinanceiro.dto.usuario.UsuarioSaida;
import controlefinanceiro.exception.ValidationException;
import controlefinanceiro.model.Usuario;
import controlefinanceiro.repository.UsuarioRepository;
import controlefinanceiro.utils.Identification;
import controlefinanceiro.validators.usuario.IniciaValidatorsUsuario;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository; 
	
    private final PasswordEncoder encoder;
    
    private final IniciaValidatorsUsuario validator;
	
    @Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder, IniciaValidatorsUsuario validator) {
		super();
		this.usuarioRepository = usuarioRepository;
		this.encoder           = encoder;
		this.validator         = validator;
	}

	public UsuarioSaida inserir(UsuarioEntrada entrada) {
		// V A L I D A Ç Õ E S
		validacoesInsert(entrada);
		
		// I N S E R T 
		int id = Identification.getId(usuarioRepository);
		Usuario usuario = usuarioRepository.insert(new Usuario(id, entrada, encoder.encode(entrada.senha())));
		
		// S A Í D A 
		return new UsuarioSaida(usuario);
	}

	public UsuarioSaida inserirFoto(MultipartFile foto, Integer id) throws IOException {
		// S A V E
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ValidationException("Usuário não encontrado!"));
		usuario.setFoto(foto.getBytes());
		Usuario usuarioSaida = usuarioRepository.save(usuario);
		
		// S A Í D A 
		return new UsuarioSaida(usuarioSaida);
	}
		
	public UsuarioSaida retornarUsuarioId(Integer id) {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ValidationException("Usuário não encontrado!"));
		
		return new UsuarioSaida(usuario);
	}

	public UsuarioSaida editar(Integer id, UsuarioEntrada entrada) {
		// V A L I D A Ç Õ E S
		validator.inicia(entrada);
		
		// S A V E
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ValidationException("Usuário não foi encontrado para edição!"));
		usuario.setNome(entrada.nome());
		usuario.setCpf(entrada.cpf());
		usuario.setEmail(entrada.email());
		usuario.setSenha(entrada.senha());
		
		Usuario usuarioSaida = usuarioRepository.save(usuario);
		
		// S A Í D A 
		return new UsuarioSaida(usuarioSaida);
	}

	public void excluir(Integer id){
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ValidationException("Usuário não encontrado!"));
		
		usuarioRepository.delete(usuario);
	}
	
	private void validacoesInsert(UsuarioEntrada entrada) {
		if (usuarioRepository.findByEmail(entrada.email()).orElse(null) != null) {
			throw new ValidationException("Usuário já cadastrado!");
		}
		validator.inicia(entrada);
	}
}
