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

import controlefinanceiro.model.Cartao;
import controlefinanceiro.service.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping ("cartao")
@CrossOrigin
@Tag(name = "Cartão", description = "Controle de cartão")
@ApiResponse(responseCode = "403", description = "não autorizado")
public class CartaoController {
	
	@Autowired 
	private CartaoService cartaoService;
	
	@Operation(summary = "Inserir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir")	
	public void inserir(@RequestBody Cartao cartao) throws Exception { 
		cartaoService.inserir(cartao);
	}
	
	@Operation(summary = "Listar os cartões")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cartao.class)))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/listar")
	public List<Cartao> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) List<String> bandeiras) {
		return cartaoService.listar(nome, bandeiras);
	}
	
	@Operation(summary = "Listar um cartão")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(schema = @Schema(implementation = Cartao.class))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Cartao retornarCartaoId(@PathVariable Integer id) {
		return cartaoService.retornarCartaoId(id);
	}
	
	@Operation(summary = "Editar")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/editar/{id}")
	@Transactional
	public void editar(@RequestBody Cartao cartaoNovo, @PathVariable Integer id) throws Exception {
		cartaoService.editar(cartaoNovo, id);
	}
	
	@Operation(summary = "Excluir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/excluir/{id}")
	@Transactional
	public void excluir(@PathVariable Integer id) {
		cartaoService.excluir(id);
	}

}
