package controlefinanceiro.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import controlefinanceiro.dto.usuario.UsuarioEntrada;
import controlefinanceiro.dto.usuario.UsuarioSaida;
import controlefinanceiro.model.Usuario;
import controlefinanceiro.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
	public ResponseEntity<UsuarioSaida> inserir(@RequestBody @Valid UsuarioEntrada entrada) throws URISyntaxException {
		
		UsuarioSaida usuario = usuarioService.inserir(entrada);		
		
		return ResponseEntity.created(new URI( String.valueOf(usuario.id()) )).body(usuario);
	}
	
	@Operation(summary = "Inserir foto")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir/{id}")
	@Transactional
	public ResponseEntity<UsuarioSaida> inserirFoto(@RequestParam("file") MultipartFile foto, @PathVariable Integer id) throws IOException {
		
		UsuarioSaida usuario = usuarioService.inserirFoto(foto, id);
		
		return ResponseEntity.ok(usuario);
	}
	
	@Operation(summary = "Listar um usuário")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", description = "operação realizada com sucesso",
					content = @Content(schema = @Schema(implementation = Usuario.class))),
			@ApiResponse(responseCode = "403", description = "não autorizado")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<UsuarioSaida> retornarUsuarioId(@PathVariable Integer id) {
		
		UsuarioSaida usuario = usuarioService.retornarUsuarioId(id);
		
		return ResponseEntity.ok(usuario);
	}
	
	@Operation(summary = "Editar")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso"),
			@ApiResponse(responseCode = "403", description = "não autorizado")})
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/editar/{id}")
	@Transactional
	public ResponseEntity<UsuarioSaida> editar(@PathVariable Integer id, @RequestBody @Valid UsuarioEntrada usuarioNovo) {
		
		UsuarioSaida usuario = usuarioService.editar(id, usuarioNovo);		
		
		return ResponseEntity.ok(usuario);
	}
	
	@Operation(summary = "Excluir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "operação realizada com sucesso"),
			@ApiResponse(responseCode = "403", description = "não autorizado")})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/excluir/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		usuarioService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
