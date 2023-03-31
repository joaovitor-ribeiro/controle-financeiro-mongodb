package controlefinanceiro.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import controlefinanceiro.dto.GanhoDTO;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Ganho;
import controlefinanceiro.repository.CategoriaRepository;
import controlefinanceiro.repository.GanhoCustomRepository;
import controlefinanceiro.repository.GanhoRepository;
import controlefinanceiro.validators.ganho.IniciaValidatorsGanho;

@Service
public class GanhoService {

	@Autowired
	private GanhoRepository ganhoRepository;
	
	@Autowired
	private GanhoCustomRepository ganhoCustomRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public void inserir(Ganho ganho) throws Exception {		
		int id = ganhoRepository.findAll().size() > 0 ? ganhoRepository.maxId() + 1 : 1;
		ganho.setId(id);
		new IniciaValidatorsGanho().inicia(ganho);
		ganhoRepository.save(ganho);
	}

	public void editar(Integer id, Ganho ganho) throws Exception {
		new IniciaValidatorsGanho().inicia(ganho);
		if (ganhoRepository.findById(id).isPresent()) {
			Query query = new Query().addCriteria(Criteria.where("_id").is(id));
			Update update = new Update().set("categoria_id", ganho.getCategoria_id())
										.set("data", ganho.getData())
										.set("descricao", ganho.getDescricao())
										.set("valor", ganho.getValor());
			mongoTemplate.updateFirst(query, update, Ganho.class);
		}else {
			throw new RuntimeException("Ganho não foi encontrado para edição!");
		}
	}

	public void excluir(Integer id) {
		Optional<Ganho> optionGanho = ganhoRepository.findById(id);
		if (optionGanho.isPresent()) {
			ganhoRepository.deleteById(id);
		} else {
			throw new RuntimeException("Ganho não foi encontrado para exclusão!");
		}
	}

	public GanhoDTO retornarGanhoId(Integer id) {
		Optional<Ganho> optionGanho = ganhoRepository.findById(id);
		if (optionGanho.isPresent()) {
			Ganho ganho = optionGanho.get();
			Categoria categoria = categoriaRepository.findById(ganho.getCategoria_id()).get();
			return new GanhoDTO(ganho, categoria);
			
		}
		throw new RuntimeException("Ganho não encontrado!");
	}
	
	public Page<GanhoDTO> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		if ( (descricao == null || descricao.isEmpty()) && (categorias == null || categorias.isEmpty()) && dataInicial == null && dataFinal == null )  {
			return ganhoCustomRepository.findAll(paginacao);
		}
		return ganhoCustomRepository.listar(descricao, categorias, dataInicial, dataFinal, paginacao);				
	}
	
}
