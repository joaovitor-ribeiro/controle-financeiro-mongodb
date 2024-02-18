package controlefinanceiro.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import controlefinanceiro.dto.despesa.DespesaEntrada;
import controlefinanceiro.dto.despesa.DespesaSaida;
import controlefinanceiro.exception.ValidationException;
import controlefinanceiro.model.Cartao;
import controlefinanceiro.model.Categoria;
import controlefinanceiro.model.Despesa;
import controlefinanceiro.repository.CartaoRepository;
import controlefinanceiro.repository.CategoriaRepository;
import controlefinanceiro.repository.DespesaCustomRepository;
import controlefinanceiro.repository.DespesaRepository;

@Service
public class DespesaService {
	
	
	private final DespesaRepository despesaRepository; 
	
	private final DespesaCustomRepository despesaCustomRepository; 
	
	private final CartaoRepository cartaoRepository;
	
	private final CategoriaRepository categoriaRepository;
	
	
	@Autowired
	public DespesaService( DespesaRepository despesaRepository, 
						   DespesaCustomRepository despesaCustomRepository,
						   CartaoRepository cartaoRepository, 
						   CategoriaRepository categoriaRepository) {
		super();
		this.despesaRepository       = despesaRepository;
		this.despesaCustomRepository = despesaCustomRepository;
		this.cartaoRepository        = cartaoRepository;
		this.categoriaRepository     = categoriaRepository;
	}

	public DespesaSaida inserir(DespesaEntrada entrada) {
		// I N S E R T 
		Cartao cartao       = cartaoRepository.findById(entrada.cartao_id()).orElseThrow(() -> new ValidationException("Cartão não encontrado!"));
		Categoria categoria = categoriaRepository.findById(entrada.categoria_id()).orElseThrow(() -> new ValidationException("Categoria não encontrada!"));
		Integer id          = despesaRepository.findAll().size() > 0 ? despesaRepository.maxId() + 1 : 1;
		
		Despesa despesa     = despesaRepository.insert(new Despesa(id, entrada, cartao, categoria));
		
		//S A Í D A 
		return new DespesaSaida(despesa);
	}

	public Page<DespesaSaida> listar(String descricao, List<Integer> categorias, Date dataInicial, Date dataFinal, Pageable paginacao) {
		if ( (descricao == null || descricao.isEmpty()) && (categorias == null || categorias.isEmpty()) && dataInicial == null && dataFinal == null )  {
			return despesaCustomRepository.findAll(paginacao);
		}
		return despesaCustomRepository.listar(descricao, categorias, dataInicial, dataFinal, paginacao);
	}

	public DespesaSaida retornarDespesaId(Integer id) {
		Despesa despesa = despesaRepository.findById(id).orElseThrow(() -> new ValidationException("Despesa não encontrada!"));
		return new DespesaSaida(despesa);
	}

	public DespesaSaida editar(Integer id, DespesaEntrada entrada) {
		// S A V E 
		Despesa despesa     = despesaRepository.findById(id).orElseThrow(() -> new ValidationException("Despesa não encontrada!"));
		Cartao cartao       = cartaoRepository.findById(entrada.cartao_id()).orElseThrow(() -> new ValidationException("Cartão não encontrado!"));
		Categoria categoria = categoriaRepository.findById(entrada.categoria_id()).orElseThrow(() -> new ValidationException("Categoria não encontrada!"));
		
		despesa.setCartao(cartao);
		despesa.setCategoria(categoria);
		despesa.setData(entrada.data());
		despesa.setDescricao(entrada.descricao());
		despesa.setValor(entrada.valor());
		
		Despesa despesaSalva = despesaRepository.save(despesa);
		
		//S A Í D A 
		return new DespesaSaida(despesaSalva);
	}

	public void excluir(Integer id) {
		Despesa despesa = despesaRepository.findById(id).orElseThrow(() -> new ValidationException("Despesa não encontrada para a exclusão!"));
		despesaRepository.delete(despesa);
	}

}
