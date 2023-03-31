package controlefinanceiro.validators.ganho.validators;

import java.util.HashMap;

import controlefinanceiro.model.Ganho;
import controlefinanceiro.utils.ValidUtils;
import controlefinanceiro.validators.ganho.ValidatorGanho;

public class ValidCamposObrigatorios extends ValidatorGanho {
	
	ValidUtils utils = new ValidUtils();
	
	public ValidCamposObrigatorios(ValidatorGanho proximo) {
		super(proximo);
	}

	@Override
	public boolean erro(Ganho ganho) throws Exception {
		HashMap<String, String> campos = new HashMap<>();
		campos.put("descricao", "descrição");
		campos.put("valor", "valor");
		campos.put("data", "data");
		
		utils.validaObject(ganho, campos, 3, 20);
		return false;
	}

	@Override
	public void lancaException(Ganho ganho) {}

}
