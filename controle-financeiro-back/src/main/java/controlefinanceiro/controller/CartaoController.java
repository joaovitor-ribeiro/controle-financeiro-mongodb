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

import controlefinanceiro.dto.cartao.CartaoEntrada;
import controlefinanceiro.dto.cartao.CartaoSaida;
import controlefinanceiro.model.Cartao;
import controlefinanceiro.service.CartaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
	public ResponseEntity<CartaoSaida> inserir(@RequestBody @Valid CartaoEntrada entrada) throws URISyntaxException  { 
		CartaoSaida cartao = cartaoService.inserir(entrada);
		
		return ResponseEntity.created(new URI(  String.valueOf(cartao.id()) )).body(cartao);
	}
	
	@Operation(summary = "Listar os cartões")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(array = @ArraySchema(schema = @Schema(implementation = Cartao.class)))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/listar")
	public ResponseEntity<List<CartaoSaida>> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) List<String> bandeiras) {
		List<CartaoSaida> cartoes = cartaoService.listar(nome, bandeiras);
		
		return ResponseEntity.ok(cartoes);
	}
	
	@Operation(summary = "Listar um cartão")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(schema = @Schema(implementation = Cartao.class))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<CartaoSaida> retornarCartaoId(@PathVariable Integer id) {
		CartaoSaida cartao = cartaoService.retornarCartaoId(id);
		
		return ResponseEntity.ok(cartao);
	}
	
	@Operation(summary = "Editar")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/editar/{id}")
	@Transactional
	public ResponseEntity<CartaoSaida> editar(@RequestBody CartaoEntrada cartaoNovo, @PathVariable Integer id) {
		CartaoSaida cartao = cartaoService.editar(cartaoNovo, id);
		
		return ResponseEntity.ok(cartao);
	}
	
	@Operation(summary = "Excluir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/excluir/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		cartaoService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}

}
