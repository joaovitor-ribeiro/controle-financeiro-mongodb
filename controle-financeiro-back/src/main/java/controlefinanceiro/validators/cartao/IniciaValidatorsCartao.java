package controlefinanceiro.validators.cartao;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.validators.cartao.validators.SemErro;
import controlefinanceiro.validators.cartao.validators.ValidCamposObrigatorios;
import controlefinanceiro.validators.cartao.validators.ValidNumeroCorrespondenteABandeira;
import controlefinanceiro.validators.cartao.validators.ValidNumeroValido;

public class IniciaValidatorsCartao {
	
	public void inicia(Cartao cartao) throws Exception {
		ValidatorCartao validatorCartao = new ValidCamposObrigatorios(
							              new ValidNumeroCorrespondenteABandeira(
						                  new ValidNumeroValido(
						                  new SemErro())));
		validatorCartao.verifica(cartao);
	}

}
