package controlefinanceiro.dto.usuario;

import controlefinanceiro.model.Usuario;

public record UsuarioSaida(Integer id, String nome, String cpf, String email, String senha, byte[] foto) {
	
	public UsuarioSaida(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getCpf(), usuario.getEmail(), usuario.getSenha(), usuario.getFoto());
	}

}
