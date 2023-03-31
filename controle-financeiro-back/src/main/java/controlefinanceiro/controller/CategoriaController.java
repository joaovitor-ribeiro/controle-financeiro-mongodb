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

@RestController
@RequestMapping ("categoria")
@CrossOrigin
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, path = "/inserir") 
	public void inserir(@RequestBody Categoria categoria) throws Exception {
		categoriaService.inserir(categoria);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/listar")
	public List<Categoria> listar(@RequestParam(required = false) String nome, @RequestParam(required = false) String tipo) {
		return categoriaService.listar(nome, tipo);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	@RequestMapping(method = RequestMethod.PUT, path = "/editar/{id}")
	public void editar(@RequestBody Categoria categoria,@PathVariable Integer id) throws Exception {
		categoriaService.editar(categoria, id);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Transactional
	@RequestMapping(method = RequestMethod.DELETE, path =  "/excluir/{id}")
	public void excluir(@PathVariable Integer id) {
		categoriaService.excluir(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Categoria retornarCategoriaId(@PathVariable Integer id) {
		return categoriaService.retornarCategoriaId(id);
	} 

}
