package controlefinanceiro.repository;

import java.util.ArrayList;
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

import controlefinanceiro.dto.GanhoDTO;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Ganho;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class GanhoCustomRepository {

	@Autowired
	private final MongoTemplate mongoTemplate;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<GanhoDTO> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
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
		List<GanhoDTO> ganhosDTO = new ArrayList<GanhoDTO>();
		
		for (Ganho despesa : ganhos) {
			Categoria categoria = categoriaRepository.findById(despesa.getCategoria_id()).get();
			ganhosDTO.add(new GanhoDTO(despesa, categoria));
		}
		
		int size = ganhosDTO.size();
		
		int toIndex = paginacao.getPageNumber() + paginacao.getPageSize();
		
		if (toIndex > ganhos.size()) {
			toIndex = ganhos.size();
		}
		
		ganhosDTO = ganhosDTO.subList(paginacao.getPageNumber(), toIndex);
		
		Page<GanhoDTO> pageGanho = new PageImpl<GanhoDTO>( ganhosDTO, paginacao, size);
		
		return pageGanho;
	}
	
	
	public Page<GanhoDTO> findAll(Pageable paginacao) {
		List<Ganho> ganhos = mongoTemplate.findAll(Ganho.class);
		List<GanhoDTO> ganhosDTO = new ArrayList<GanhoDTO>();
		
		for (Ganho despesa : ganhos) {
			Categoria categoria = categoriaRepository.findById(despesa.getCategoria_id()).get();
			ganhosDTO.add(new GanhoDTO(despesa, categoria));
		}
		
		int size = ganhosDTO.size();
		
		int toIndex = paginacao.getPageNumber() + paginacao.getPageSize();
		
		if (toIndex > ganhos.size()) {
			toIndex = ganhos.size();
		}
		
		ganhosDTO = ganhosDTO.subList(paginacao.getPageNumber(), toIndex);
		
		Page<GanhoDTO> pageGanho = new PageImpl<GanhoDTO>( ganhosDTO, paginacao, size);
		
		return pageGanho;
	}
	
}
