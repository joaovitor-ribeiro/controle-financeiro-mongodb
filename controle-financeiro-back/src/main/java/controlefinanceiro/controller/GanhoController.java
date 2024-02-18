package controlefinanceiro.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import controlefinanceiro.dto.ganho.GanhoEntrada;
import controlefinanceiro.dto.ganho.GanhoSaida;
import controlefinanceiro.service.GanhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("ganho")
@CrossOrigin
@Tag(name = "Ganho", description = "Controle de ganho")
@ApiResponse(responseCode = "403", description = "não autorizado")
public class GanhoController {
	
	@Autowired
	private GanhoService ganhoService;
	
	@Operation(summary = "Inserir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir")
	public ResponseEntity<GanhoSaida> inserir(@RequestBody @Valid GanhoEntrada ganho) throws URISyntaxException {
		GanhoSaida saida = ganhoService.inserir(ganho);
		
		return ResponseEntity.created(new URI( String.valueOf(saida.id()) )).body(saida);
	}	
	
	@Operation(summary = "Listar os ganhos")
	@ApiResponse(responseCode = "200", description = "operação realizada com sucesso" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path= "/listar")
	public ResponseEntity<Page<GanhoSaida>> listar(
			@RequestParam(required = false) String descricao, 
			@RequestParam(required = false) List<Integer> categorias,
			@RequestParam(required = false) Date dataInicial, 
			@RequestParam(required = false) Date dataFinal,
			@PageableDefault(sort = "data", direction = Direction.DESC, page = 0, size = 5) Pageable paginacao) {
		Page<GanhoSaida> lista = ganhoService.listar(descricao, categorias, dataInicial, dataFinal, paginacao);
		
		return ResponseEntity.ok(lista);
	}
	
	@Operation(summary = "Editar")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path= "/editar/{id}")
	@Transactional
	public ResponseEntity<GanhoSaida> editar(@PathVariable Integer id, @RequestBody @Valid GanhoEntrada ganhoNovo) {
		GanhoSaida ganho = ganhoService.editar(id, ganhoNovo);
		
		return ResponseEntity.ok(ganho);
	}
	
	@Operation(summary = "Excluir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	@RequestMapping(method = RequestMethod.DELETE, path =  "/excluir/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Integer id) {
		ganhoService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Listar um ganho")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(schema = @Schema(implementation = GanhoSaida.class))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<GanhoSaida> retornarGanhoId(@PathVariable Integer id) {
		GanhoSaida ganho = ganhoService.retornarGanhoId(id);
		
		return ResponseEntity.ok(ganho);
	} 
}
