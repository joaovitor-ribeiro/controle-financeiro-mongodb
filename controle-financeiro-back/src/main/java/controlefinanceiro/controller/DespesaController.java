package controlefinanceiro.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import controlefinanceiro.dto.DespesaDTO;
import controlefinanceiro.model.Despesa;
import controlefinanceiro.service.DespesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping ("despesa")
@CrossOrigin
@Tag(name = "Despesa", description = "Controle de despesa")
@ApiResponse(responseCode = "403", description = "não autorizado")
public class DespesaController {
	
	@Autowired
	private DespesaService despesaService;
	
	@Operation(summary = "Inserir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir")	
	public void inserir(@RequestBody Despesa despesa) throws Exception {
		despesaService.inserir(despesa);
	}
	
	@Operation(summary = "Listar as despesas filtadas")
	@ApiResponse(responseCode = "200", description = "operação realizada com sucesso" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/listar")
	public Page<DespesaDTO> listar(
			@RequestParam(required = false) String descricao, 
			@RequestParam(required = false) List<Integer> categorias,
			@RequestParam(required = false) Date dataInicial, 
			@RequestParam(required = false) Date dataFinal,
			@PageableDefault(sort = "data", direction = Direction.DESC, page = 0, size = 5) Pageable paginacao) {
		return despesaService.listar(descricao, categorias, dataInicial, dataFinal, paginacao);
	}
	
	@Operation(summary = "Listar uma despesa")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(schema = @Schema(implementation = DespesaDTO.class))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public DespesaDTO retornarDespesaId(@PathVariable Integer id) {
		return despesaService.retornarDespesaId(id);
	}
	
	@Operation(summary = "Editar")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path = "/editar/{id}")
	@Transactional
	@CrossOrigin
	public void editar(@PathVariable Integer id, @RequestBody Despesa despesaNovo) throws Exception {
		despesaService.editar(id, despesaNovo);
	}
	
	@Operation(summary = "Excluir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(method = RequestMethod.DELETE, path = "/excluir/{id}")
	@Transactional
	public void excluir(@PathVariable Integer id) {
		despesaService.excluir(id);
	}
}

