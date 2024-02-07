package controlefinanceiro.controller;

import java.util.List;

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

import controlefinanceiro.model.Categoria;
import controlefinanceiro.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping ("categoria")
@CrossOrigin
@Tag(name = "Categoria", description = "Controle de categoria")
@ApiResponse(responseCode = "403", description = "não autorizado")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	@Operation(summary = "Inserir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir") 
	public void inserir(@RequestBody Categoria categoria) throws Exception {
		categoriaService.inserir(categoria);
	}
	
	@Operation(summary = "Listar as categorias")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Categoria.class)))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/listar")
	public List<Categoria> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) String tipo) {
		return categoriaService.listar(nome, tipo);
	}
	
	@Operation(summary = "Editar")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	@RequestMapping(method = RequestMethod.PUT, path = "/editar/{id}")
	public void editar(@RequestBody Categoria categoria,@PathVariable Integer id) throws Exception {
		categoriaService.editar(categoria, id);
	}
	
	@Operation(summary = "Excluir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	@RequestMapping(method = RequestMethod.DELETE, path =  "/excluir/{id}")
	public void excluir(@PathVariable Integer id) {
		categoriaService.excluir(id);
	}
	
	@Operation(summary = "Listar uma categoria")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(schema = @Schema(implementation = Categoria.class))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Categoria retornarCategoriaId(@PathVariable Integer id) {
		return categoriaService.retornarCategoriaId(id);
	} 

}
