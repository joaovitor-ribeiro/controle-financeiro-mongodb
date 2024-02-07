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

import controlefinanceiro.dto.GanhoDTO;
import controlefinanceiro.model.Ganho;
import controlefinanceiro.service.GanhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
	public void inserir(@RequestBody Ganho ganho) throws Exception {
		ganhoService.inserir(ganho);
	}	
	
	@Operation(summary = "Listar os ganhos")
	@ApiResponse(responseCode = "200", description = "operação realizada com sucesso" )
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path= "/listar")
	public Page<GanhoDTO> listar(
			@RequestParam(required = false) String descricao, 
			@RequestParam(required = false) List<Integer> categorias,
			@RequestParam(required = false) Date dataInicial, 
			@RequestParam(required = false) Date dataFinal,
			@PageableDefault(sort = "data", direction = Direction.DESC, page = 0, size = 5) Pageable paginacao) {
		return ganhoService.listar(descricao, categorias, dataInicial, dataFinal, paginacao);
	}
	
	@Operation(summary = "Editar")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.PUT, path= "/editar/{id}")
	@Transactional
	public void editar(@PathVariable Integer id, @RequestBody Ganho ganhoNovo) throws Exception {
		ganhoService.editar(id, ganhoNovo);
	}
	
	@Operation(summary = "Excluir")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "operação realizada com sucesso") })
	@ResponseStatus(HttpStatus.NO_CONTENT)	
	@RequestMapping(method = RequestMethod.DELETE, path =  "/excluir/{id}")
	public void excluir(@PathVariable Integer id) {
		ganhoService.excluir(id);
	}
	
	@Operation(summary = "Listar um ganho")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(schema = @Schema(implementation = GanhoDTO.class))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public GanhoDTO retornarGanhoId(@PathVariable Integer id) {
		return ganhoService.retornarGanhoId(id);
	} 
}
