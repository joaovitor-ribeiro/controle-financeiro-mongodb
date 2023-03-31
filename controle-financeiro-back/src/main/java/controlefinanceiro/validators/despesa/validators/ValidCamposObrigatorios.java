package controlefinanceiro.validators.despesa.validators;

import java.util.HashMap;

import controlefinanceiro.model.Despesa;
import controlefinanceiro.utils.ValidUtils;
import controlefinanceiro.validators.despesa.ValidatorDespesa;

public class ValidCamposObrigatorios extends ValidatorDespesa {
	
	ValidUtils utils = new ValidUtils();
	
	public ValidCamposObrigatorios(ValidatorDespesa proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Despesa despesa) throws Exception {
		HashMap<String, String> campos = new HashMap<>();
		campos.put("descricao", "descrição");
		campos.put("valor", "valor");
		campos.put("data", "data");
		
		utils.validaObject(despesa, campos, 3, 20);
		return false;
	}

	@Override
	public void lancaException(Despesa despesa) {}

}
