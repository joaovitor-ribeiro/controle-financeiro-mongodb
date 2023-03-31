package controlefinanceiro.validators.categoria;

import controlefinanceiro.model.Categoria;
import controlefinanceiro.validators.categoria.validators.SemErro;
import controlefinanceiro.validators.categoria.validators.ValidCamposObrigatorios;
import controlefinanceiro.validators.categoria.validators.ValidTamanhoNome;
import controlefinanceiro.validators.categoria.validators.ValidTamanhoTipo;
import controlefinanceiro.validators.categoria.validators.ValidTipoValido;

public class IniciaValidatorsCategoria {
	
	public void inicia(Categoria categoria) throws Exception {
		ValidatorCategoria validatorCategoria = new ValidCamposObrigatorios(
				                                new ValidTamanhoNome(
						                        new ValidTamanhoTipo(
						                        new ValidTipoValido(
						                        new SemErro()))));
		validatorCategoria.verifica(categoria);
	}

}
