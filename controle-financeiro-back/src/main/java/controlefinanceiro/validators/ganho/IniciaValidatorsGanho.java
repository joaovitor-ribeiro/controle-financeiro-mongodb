package controlefinanceiro.validators.ganho;

import controlefinanceiro.model.Ganho;
import controlefinanceiro.validators.ganho.validators.SemErro;
import controlefinanceiro.validators.ganho.validators.ValidCamposObrigatorios;
import controlefinanceiro.validators.ganho.validators.ValidCategoriaVazia;

public class IniciaValidatorsGanho {
	
	
	public void inicia(Ganho ganho) throws Exception {
		ValidatorGanho validatorGanho = new ValidCamposObrigatorios(
						                new ValidCategoriaVazia(
						                new SemErro()));
		validatorGanho.verifica(ganho);
	}

}
