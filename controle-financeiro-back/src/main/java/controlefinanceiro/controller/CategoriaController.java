package controlefinanceiro.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

import controlefinanceiro.dto.categoria.CategoriaEntrada;
import controlefinanceiro.dto.categoria.CategoriaSaida;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping ("categoria")
@CrossOrigin
@Tag(name = "Categoria", description = "Controle de categoria")
@ApiResponse(responseCode = "403", description = "Não autorizado")
@ApiResponse(responseCode = "500", description = "Erro interno")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	@Operation(summary = "Inserir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir") 
	public ResponseEntity<CategoriaSaida> inserir(@RequestBody @Valid CategoriaEntrada categoria) throws URISyntaxException {
		CategoriaSaida saida = categoriaService.inserir(categoria);
		
		return ResponseEntity.created(new URI( String.valueOf( saida.id() ) )).body(saida);
	}
	
	@Operation(summary = "Listar as categorias")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Categoria.class)))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/listar")
	public ResponseEntity<List<CategoriaSaida>> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) String tipo) {
		List<CategoriaSaida> listSaida = categoriaService.listar(nome, tipo);
		
		return ResponseEntity.ok(listSaida);
	}
	
	@Operation(summary = "Editar")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	@RequestMapping(method = RequestMethod.PUT, path = "/editar/{id}")
	public ResponseEntity<CategoriaSaida> editar(@RequestBody @Valid CategoriaEntrada categoria,@PathVariable Integer id)  {
		CategoriaSaida saida = categoriaService.editar(categoria, id);
		
		return ResponseEntity.ok(saida);
	}
	
	@Operation(summary = "Excluir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	@RequestMapping(method = RequestMethod.DELETE, path =  "/excluir/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		categoriaService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Listar uma categoria")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(schema = @Schema(implementation = Categoria.class))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<CategoriaSaida> retornarCategoriaId(@PathVariable Integer id) {
		CategoriaSaida saida = categoriaService.retornarCategoriaId(id);
		
		return ResponseEntity.ok(saida);
	} 

}
