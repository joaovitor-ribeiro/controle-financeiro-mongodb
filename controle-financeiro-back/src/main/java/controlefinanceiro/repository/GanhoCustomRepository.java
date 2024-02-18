package controlefinanceiro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import controlefinanceiro.dto.ganho.GanhoSaida;
import controlefinanceiro.model.Ganho;

@Repository
public class GanhoCustomRepository {

	private final MongoTemplate mongoTemplate;
	
	@Autowired
	public GanhoCustomRepository(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}
	
	public Page<GanhoSaida> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		Query query = new Query();
		
		if (!(descricao == null || descricao.isEmpty())) {
			query.addCriteria(Criteria.where("descricao").regex(descricao));
		}
		
		if (!(categorias == null || categorias.isEmpty())) {
			query.addCriteria(Criteria.where("categoria_id").in(categorias));
		}
		
		if (!(dataInicial == null)) {
			query.addCriteria(Criteria.where("data").gte(dataInicial));
		}
		
		if (!(dataFinal == null)) {
			query.addCriteria(Criteria.where("data").lte(dataFinal));
		}
		
		query.with(Sort.by(Sort.Direction.DESC, "data"));
		
		List<Ganho> ganhos = mongoTemplate.find(query, Ganho.class);
		List<GanhoSaida> ganhosDTO = ganhos.stream().map(g -> new GanhoSaida(g)).toList();
		
		int size = ganhosDTO.size();
		
		int toIndex = paginacao.getPageNumber() + paginacao.getPageSize();
		
		if (toIndex > ganhos.size()) {
			toIndex = ganhos.size();
		}
		
		ganhosDTO = ganhosDTO.subList(paginacao.getPageNumber(), toIndex);
		
		Page<GanhoSaida> pageGanho = new PageImpl<GanhoSaida>( ganhosDTO, paginacao, size);
		
		return pageGanho;
	}
	
	
	public Page<GanhoSaida> findAll(Pageable paginacao) {
		List<Ganho> ganhos = mongoTemplate.findAll(Ganho.class);
		List<GanhoSaida> ganhosDTO = ganhos.stream().map(g -> new GanhoSaida(g)).toList();
		
		int size = ganhosDTO.size();
		
		int toIndex = paginacao.getPageNumber() + paginacao.getPageSize();
		
		if (toIndex > ganhos.size()) {
			toIndex = ganhos.size();
		}
		
		ganhosDTO = ganhosDTO.subList(paginacao.getPageNumber(), toIndex);
		
		Page<GanhoSaida> pageGanho = new PageImpl<GanhoSaida>( ganhosDTO, paginacao, size);
		
		return pageGanho;
	}


}
