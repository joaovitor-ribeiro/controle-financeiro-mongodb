package controlefinanceiro.service.cartao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import controlefinanceiro.dto.cartao.CartaoEntrada;
import controlefinanceiro.dto.cartao.CartaoSaida;
import controlefinanceiro.model.Cartao;
import controlefinanceiro.repository.CartaoRepository;
import controlefinanceiro.service.cartao.filtro.CartaoFiltroContext;
import controlefinanceiro.utils.Identification;
import controlefinanceiro.validators.cartao.IniciaValidatorsCartao;
import jakarta.validation.ValidationException;


@Service
public class CartaoService {
	
	private final CartaoRepository cartaoRepository; 
	
	private final IniciaValidatorsCartao validator; 
	
	@Autowired
	public CartaoService(CartaoRepository cartaoRepository, IniciaValidatorsCartao validator) {
		super();
		this.cartaoRepository = cartaoRepository;
		this.validator = validator;
	}

	public CartaoSaida inserir (CartaoEntrada entrada) {
		// V A L I D A Ç Ã O 
 		validator.inicia(entrada);

		// I N S E R T 
		int id = Identification.getId(cartaoRepository);
		Cartao cartao = cartaoRepository.insert(new Cartao(id, entrada));	
		
		// S A Í D A  
		return new CartaoSaida(cartao);
	}

	public List<CartaoSaida> listar(String nome, List<String> bandeiras) {
		CartaoFiltroContext filtroContext = new CartaoFiltroContext(nome, bandeiras);
		
		List<Cartao>cartoes = filtroContext.buscarCartoes(cartaoRepository, nome, bandeiras);
		
		return cartoes.stream().map(c -> new CartaoSaida(c)).toList();
	}

	public CartaoSaida retornarCartaoId(Integer id){	
		Cartao cartao = cartaoRepository.findById(id).orElseThrow(() -> new ValidationException("Cartão não encontrado!"));
		
		return new CartaoSaida(cartao);
	}
	
	public void excluir(Integer id){
		Cartao cartao = cartaoRepository.findById(id).orElseThrow(() -> new ValidationException("Cartão não foi encontrado para a exclusão!"));
		
		cartaoRepository.delete(cartao);
	}
	
	public CartaoSaida editar(CartaoEntrada entrada, Integer id) {
		// V A L I D A Ç Ã O 
		validator.inicia(entrada);
		
		// S A V E
		Cartao cartao = cartaoRepository.findById(id).orElseThrow(() -> new ValidationException("Cartão não encontrado!"));
		
		cartao.setNome(entrada.nome());
		cartao.setBandeira(entrada.bandeira());
		cartao.setLimite(entrada.limite());
		cartao.setNumero(entrada.numero());
		
		Cartao cartaoSave = cartaoRepository.save(cartao);
		
		// S A Í D A  
		return new CartaoSaida(cartaoSave);
	}

}
