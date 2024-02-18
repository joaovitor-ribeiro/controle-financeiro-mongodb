package controlefinanceiro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import controlefinanceiro.dto.despesa.DespesaSaida;
import controlefinanceiro.model.Despesa;

@Service
public class DespesaCustomRepository {
	
	private MongoTemplate mongoTemplate;
	
	@Autowired
	public DespesaCustomRepository(MongoTemplate mongoTemplate) {
		super();
		this.mongoTemplate = mongoTemplate;
	}

	public Page<DespesaSaida> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		
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

		List<Despesa> despesas           = mongoTemplate.find(query, Despesa.class);
		List<DespesaSaida> despesasSadia = despesas.stream().map(d -> new DespesaSaida(d)).toList();
		
		int size = despesasSadia.size();
		
		int toIndex = paginacao.getPageNumber() + paginacao.getPageSize();
		
		if (toIndex > despesas.size()) {
			toIndex = despesas.size();
		}
		
		despesasSadia = despesasSadia.subList(paginacao.getPageNumber(), toIndex);
		
		Page<DespesaSaida> pageDespesa = new PageImpl<DespesaSaida>( despesasSadia, paginacao, size);
		
		return pageDespesa;
	}
	
	public Page<DespesaSaida> findAll(Pageable paginacao) {
		List<Despesa> despesas           = mongoTemplate.findAll(Despesa.class);
		List<DespesaSaida> despesasSaida = despesas.stream().map(d -> new DespesaSaida(d)).toList();
		
		int size = despesasSaida.size();
		
		int toIndex = paginacao.getPageNumber() + paginacao.getPageSize();
		
		if (toIndex > despesas.size()) {
			toIndex = despesas.size();
		}
		
		despesasSaida = despesasSaida.subList(paginacao.getPageNumber(), toIndex);
		
		Page<DespesaSaida> pageDespesa = new PageImpl<DespesaSaida>( despesasSaida, paginacao, size);
		
		return pageDespesa;
	}

}
