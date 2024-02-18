package controlefinanceiro.validators.categoria.validators;

import org.springframework.stereotype.Component;

import controlefinanceiro.dto.categoria.CategoriaEntrada;
import controlefinanceiro.validators.categoria.ValidatorCategoria;
import jakarta.validation.ValidationException;

@Component
public class ValidTipoValido implements ValidatorCategoria {
	
	@Override
	public void valida(CategoriaEntrada categoria) {
		if ( !(categoria.tipo().equals("G") || categoria.tipo().equals("D")) ) {
			throw new ValidationException("O tipo tem que ter um caracter. Informe G para ganho ou D para despesa!");
		}
	}

}