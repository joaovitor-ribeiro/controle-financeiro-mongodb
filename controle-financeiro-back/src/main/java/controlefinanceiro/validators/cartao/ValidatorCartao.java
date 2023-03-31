package controlefinanceiro.validators.cartao;

import controlefinanceiro.model.Cartao;

public abstract class ValidatorCartao {
	
	private ValidatorCartao proximo;
	
	public ValidatorCartao(ValidatorCartao proximo) {
		this.proximo = proximo;
	}
	
	public void verifica(Cartao cartao) throws Exception {
		if (erro(cartao)) {
			lancaException(cartao);
			return;
		}
		proximo.verifica(cartao);
	}
	
	public abstract boolean erro(Cartao cartao) throws Exception;
	public abstract void lancaException(Cartao cartao);

}
