package controlefinanceiro.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import controlefinanceiro.bean.PainelBean;
import controlefinanceiro.service.PainelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("painel")
@CrossOrigin
@Tag(name = "Painel", description = "Painel do controle financeiro")
@ApiResponse(responseCode = "403", description = "não autorizado")
public class PainelController {
	
	@Autowired
	private PainelService painelService;
	
	@Operation(summary = "Total", description = "Total do ganho, despesa e o saldo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "operação realizada com sucesso", 
					content = @Content(schema = @Schema(implementation = PainelBean.class))) })	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET)
	public PainelBean painelControleFinanceiro(@RequestParam(required = false) String data) throws ParseException {
		return painelService.painelControleFinanceiro(data);	
	}
}
