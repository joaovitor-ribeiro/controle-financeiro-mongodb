package controlefinanceiro.repository;

import java.util.ArrayList;
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

import controlefinanceiro.dto.DespesaDTO;
import controlefinanceiro.model.Cartao;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Despesa;

@Service
public class DespesaCustomRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Page<DespesaDTO> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		
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

		List<Despesa> despesas = mongoTemplate.find(query, Despesa.class);
		List<DespesaDTO> despesasDTO = new ArrayList<DespesaDTO>();
		
		for (Despesa despesa : despesas) {
			Categoria categoria = categoriaRepository.findById(despesa.getCategoria_id()).get();
			Cartao cartao = cartaoRepository.findById(despesa.getCartao_id()).get();
			despesasDTO.add(new DespesaDTO(despesa, categoria, cartao));
		}
		
		int size = despesasDTO.size();
		
		int toIndex = paginacao.getPageNumber() + paginacao.getPageSize();
		
		if (toIndex > despesas.size()) {
			toIndex = despesas.size();
		}
		
		despesasDTO = despesasDTO.subList(paginacao.getPageNumber(), toIndex);
		
		Page<DespesaDTO> pageDespesa = new PageImpl<DespesaDTO>( despesasDTO, paginacao, size);
		
		return pageDespesa;
	}
	
	public Page<DespesaDTO> findAll(Pageable paginacao) {
		List<Despesa> despesas = mongoTemplate.findAll(Despesa.class);
		List<DespesaDTO> despesasDTO = new ArrayList<DespesaDTO>();
		
		for (Despesa despesa : despesas) {
			Categoria categoria = categoriaRepository.findById(despesa.getCategoria_id()).get();
			Cartao cartao = cartaoRepository.findById(despesa.getCartao_id()).get();
			despesasDTO.add(new DespesaDTO(despesa, categoria, cartao));
		}
		
		int size = despesasDTO.size();
		
		int toIndex = paginacao.getPageNumber() + paginacao.getPageSize();
		
		if (toIndex > despesas.size()) {
			toIndex = despesas.size();
		}
		
		despesasDTO = despesasDTO.subList(paginacao.getPageNumber(), toIndex);
		
		Page<DespesaDTO> pageDespesa = new PageImpl<DespesaDTO>( despesasDTO, paginacao, size);
		
		return pageDespesa;
	}

}
