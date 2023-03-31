package controlefinanceiro.validators.cartao.validators;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.validators.cartao.ValidatorCartao;

public class SemErro extends ValidatorCartao {

	public SemErro() {
		super(null);
	}
	
	@Override
	public boolean erro(Cartao cartao) {
		return true;
	}

	@Override
	public void lancaException(Cartao cartao) {}

}
