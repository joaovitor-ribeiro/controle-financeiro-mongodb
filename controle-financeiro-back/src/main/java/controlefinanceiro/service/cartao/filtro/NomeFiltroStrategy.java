package controlefinanceiro.service.cartao.filtro;

import java.util.List;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.repository.CartaoRepository;

public class NomeFiltroStrategy implements CartaoFiltroStrategy {
	
    @Override
    public List<Cartao> buscarCartoes(CartaoRepository cartaoRepository, String nome, List<String> bandeiras) {
        return cartaoRepository.findByNome(nome);
    }

}