package controlefinanceiro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import controlefinanceiro.model.Usuario;
import controlefinanceiro.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("usuario")
@CrossOrigin
@Tag(name = "Usuário", description = "Controle de usuário")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Operation(summary = "Inserir dados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir")
	public Integer inserir(@RequestBody Usuario usuario) throws Exception {
		return usuarioService.inserir(usuario);		
	}
	
	@Operation(summary = "Inserir foto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir/{id}")
	@Transactional
	public void inserirFoto(@RequestParam("file") MultipartFile foto, @PathVariable Integer id) throws IOException {
		usuarioService.inserirFoto(foto, id);
	}
	
	@Operation(summary = "Listar um usuário")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", description = "operação realizada com sucesso",
					content = @Content(schema = @Schema(implementation = Usuario.class))),
			@ApiResponse(responseCode = "403", description = "não autorizado")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Usuario retornarUsuarioId(@PathVariable Integer id) {
		return usuarioService.retornarUsuarioId(id);
	}
	
	@Operation(summary = "Editar")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso"),
			@ApiResponse(responseCode = "403", description = "não autorizado")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/editar/{id}")
	@Transactional
	public void editar(@PathVariable Integer id, @RequestBody Usuario usuarioNovo) throws Exception {
		usuarioService.editar(id, usuarioNovo);		
	}
	
	@Operation(summary = "Excluir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "operação realizada com sucesso"),
			@ApiResponse(responseCode = "403", description = "não autorizado")})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/excluir/{id}")
	@Transactional
	public void excluir(@PathVariable Integer id) {
		usuarioService.excluir(id);
	}
	
}
