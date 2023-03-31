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

import controlefinanceiro.dto.DespesaDTO;
import controlefinanceiro.model.Cartao;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Despesa;
import controlefinanceiro.repository.CartaoRepository;
import controlefinanceiro.repository.CategoriaRepository;
import controlefinanceiro.repository.DespesaCustomRepository;
import controlefinanceiro.repository.DespesaRepository;
import controlefinanceiro.validators.despesa.IniciaValidatorsDespesa;

@Service
public class DespesaService {
	
	
	@Autowired
	private DespesaRepository despesaRepository; 
	
	@Autowired
	private DespesaCustomRepository despesaCustomRepository; 
	
	@Autowired
	private MongoTemplate mongoTemplate;	
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public void inserir(Despesa despesa) throws Exception {
		int id = despesaRepository.findAll().size() > 0 ? despesaRepository.maxId() + 1 : 1;
		despesa.setId(id);
		new IniciaValidatorsDespesa().inicia(despesa);
		despesaRepository.save(despesa);
	}

	public Page<DespesaDTO> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		if ( (descricao == null || descricao.isEmpty()) && (categorias == null || categorias.isEmpty()) && dataInicial == null && dataFinal == null )  {
			return despesaCustomRepository.findAll(paginacao);
		}
		return despesaCustomRepository.listar(descricao, categorias, dataInicial, dataFinal, paginacao);
	}

	public DespesaDTO retornarDespesaId(Integer id) {
		Optional<Despesa> optionalDespesa = despesaRepository.findById(id);
		if (optionalDespesa.isPresent()) {
			Despesa despesa     = optionalDespesa.get();
			Categoria categoria = categoriaRepository.findById(despesa.getCategoria_id()).get();
			Cartao cartao       = cartaoRepository.findById(despesa.getCartao_id()).get();
			return new DespesaDTO(despesa, categoria, cartao);
		}
		throw new RuntimeException("Despesa não encontrada!");	
	}

	public void editar(Integer id, Despesa despesa) throws Exception {
		new IniciaValidatorsDespesa().inicia(despesa);
		if (despesaRepository.findById(id).isPresent()) {
			Query query = Query.query(Criteria.where("_id").is(id));
			Update update = new Update().set("categoria_id", despesa.getCategoria_id())
							.set("descricao", despesa.getDescricao())
							.set("cartao_id", despesa.getCartao_id())
							.set("valor", despesa.getValor())
							.set("data", despesa.getData());
			mongoTemplate.updateFirst(query, update, Despesa.class);
		} else {
			throw new RuntimeException("Despesa não encontrada!");	
		}
	}

	public void excluir(Integer id) {
		Optional<Despesa> optionalDespesa = despesaRepository.findById(id);
		if (optionalDespesa.isPresent()) {
			despesaRepository.deleteById(id);
		} else {
			throw new RuntimeException("Despesa não encontrada para a exclusão!");
		}
	}

}
