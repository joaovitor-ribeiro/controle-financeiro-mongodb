package controlefinanceiro.validators.despesa;

import controlefinanceiro.model.Despesa;
import controlefinanceiro.validators.despesa.validators.SemErro;
import controlefinanceiro.validators.despesa.validators.ValidCamposObrigatorios;
import controlefinanceiro.validators.despesa.validators.ValidCartaoVazio;
import controlefinanceiro.validators.despesa.validators.ValidCategoriaVazia;

public class IniciaValidatorsDespesa {
	
	public void inicia(Despesa despesa) throws Exception {
		ValidatorDespesa validatorDespesa = new ValidCamposObrigatorios(
				                            new ValidCartaoVazio(
						                    new ValidCategoriaVazia(
						                    new SemErro())));
		validatorDespesa.verifica(despesa);
	}


}
