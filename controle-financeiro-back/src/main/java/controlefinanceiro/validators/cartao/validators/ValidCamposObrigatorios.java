package controlefinanceiro.validators.cartao.validators;

import java.util.HashMap;

import controlefinanceiro.model.Cartao;
import controlefinanceiro.utils.ValidUtils;
import controlefinanceiro.validators.cartao.ValidatorCartao;

public class ValidCamposObrigatorios extends ValidatorCartao {
	
	ValidUtils utils = new ValidUtils();
	
	public ValidCamposObrigatorios(ValidatorCartao proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Cartao cartao) throws Exception {
		HashMap<String, String> campos = new HashMap<>();
		campos.put("nome", "nome");
		campos.put("bandeira", "bandeira");
		campos.put("numero", "número");
		campos.put("limite", "limite");
		
		utils.validaObject(cartao, campos, 3, 20);
		return false;
	}

	@Override
	public void lancaException(Cartao cartao) {}

}
