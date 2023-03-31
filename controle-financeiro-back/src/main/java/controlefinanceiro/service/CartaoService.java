package controlefinanceiro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.repository.CartaoRepository;
import controlefinanceiro.validators.cartao.IniciaValidatorsCartao;


@Service
public class CartaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository; //Instanciando o Repository
	
	@Autowired
	private MongoTemplate mongoTemplate; //Instanciando o Repository
	
	public void inserir (Cartao cartao) throws Exception {
		int id = cartaoRepository.findAll().size() > 0 ? cartaoRepository.maxId() + 1 : 1;
		cartao.setId(id);
		new IniciaValidatorsCartao().inicia(cartao);
		cartaoRepository.save(cartao);		
	}

	public List<Cartao> listar(String nome, List<String> bandeiras) {
		if(!(nome == null || nome.isEmpty()) && !(bandeiras == null || bandeiras.isEmpty())) {
			return cartaoRepository.findNomeAndBandeiras(nome, bandeiras);
		} else if(!(nome == null || nome.isEmpty())) {
			return cartaoRepository.findByNome(nome);
		} else if (!(bandeiras == null || bandeiras.isEmpty())) {
			return cartaoRepository.findByBandeiras(bandeiras);
		}
		return cartaoRepository.findAll();
	}

	public Cartao retornarCartaoId(Integer id){	
		Optional<Cartao> cartao = cartaoRepository.findById(id);
		if (cartao.isPresent()) {
			return cartao.get();
		}
		throw new RuntimeException("Cartão não encontrado!");	
	}
	
	
	public void excluir(Integer id){
		Optional<Cartao> optionalCartao = cartaoRepository.findById(id);
		
		if (optionalCartao.isPresent()) {	
			cartaoRepository.deleteById(id);
		} else {
			throw new RuntimeException("Cartão não foi encontrado para a exclusão!");
		}
	}
	
	public void editar(Cartao cartao, Integer id) {
		if (cartaoRepository.findById(id).isPresent()) {
			Query query = Query.query(Criteria.where("_id").is(id));
			Update update = new Update().set("nome", cartao.getNome())
					.set("bandeira", cartao.getBandeira())
					.set("numero", cartao.getNumero())
					.set("limite", cartao.getLimite());
			mongoTemplate.updateFirst(query, update, Cartao.class);		
		} else {
			throw new RuntimeException("Cartão não encontrado!");	
		}
	}

}
