package controlefinanceiro.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;

public class ValidUtils {
	
	public <T, U> void validaObject(U object, HashMap<String, String> campos) throws Exception {
		this.validaObject(object, campos, 0, 0);
	}
	
	public <T, U> void validaObject(U object, String... campos) throws Exception {
		validaObject(object, 0, 0, campos);
	}
	
	public <T, U> void validaObject(U object, int menor, int maior, String... campos) throws Exception {
		
		for (Field atributo : object.getClass().getDeclaredFields()) {
			
			boolean canAccess = atributo.canAccess(object);
			
			String message = null;
			
			atributo.setAccessible(true);
			
			for (String campo : campos) {
				
				if ( campo != atributo.getName() ) continue;
				
				if (atributo.get(object) == null || atributo.get(object).toString().isEmpty()) {
					message = "O campo " + campo + " é de preenchimento obrigatório!";
					break;
				}  
				
				if (atributo.getType().getName().equals("java.lang.String") && (menor > 0 || maior > 0)) {
					
					if (campo.length() < menor) {
						message = "O campo " + campo + " não pode ter menos do que " + menor + " caracteres!";
						break;
					}
					
					message = "O campo " + campo + " não pode ter mais do que " + maior + " caracteres!";
					break;
					
				} 
				
				if (!atributo.getType().getName().equals("java.lang.String") && atributo.get(object).toString().replace("-", "").matches("-?\\d+(\\.\\d+)?")) {
					BigDecimal numero =  new BigDecimal(atributo.get(object).toString());
					if (BigDecimal.ZERO.compareTo(numero) <= 0 ) {
						message = "O campo " + campo + " não pode ser menor ou igual a zero!";
						break;
					}
				}
				
			}
			
			if ( message != null ) {
				atributo.setAccessible(canAccess);
				throw new RuntimeException(message);		
			}
			
		}
		
	}
	
	public <T, U> void validaObject(U object, HashMap<String, String> campos, int menor, int maior) throws Exception {
		for (Field atributo : object.getClass().getDeclaredFields()) {
			atributo.setAccessible(true);
			
			if (campos.containsKey(atributo.getName())) {
				if (atributo.get(object) == null || atributo.get(object).toString().isEmpty()) {
					throw new RuntimeException("O campo " + campos.get(atributo.getName()) + " é de preenchimento obrigatório!");
				} else if (atributo.getType().getName().equals("java.lang.String") && (menor > 0 || maior > 0)) {
					validTamanho(String.valueOf(atributo.get(object)), campos.get(atributo.getName()), menor, maior);
				} else if (!atributo.getType().getName().equals("java.lang.String") && atributo.get(object).toString().replace("-", "").matches("-?\\d+(\\.\\d+)?")) {
					BigDecimal numero =  new BigDecimal(atributo.get(object).toString());
					if (BigDecimal.ZERO.compareTo(numero) <= 0 ) {
						throw new RuntimeException("O campo " + campos.get(atributo.getName()) + " não pode ser menor ou igual a zero!");		
					}
				}
			} 
		}
	}
	
	public void validTamanho(String campo, String descricao, int menor, int maior) {
		if (campo.length() < menor) {
			throw new RuntimeException("O campo " + descricao + " não pode ter menos do que " + menor + " caracteres!");
		} else if (campo.length() > maior) {
			throw new RuntimeException("O campo " + descricao + " não pode ter mais do que " + maior + " caracteres!");
		}
	}
	
	public static boolean isEmpty(String campo) {
		if (campo == null || campo.isEmpty() ) {
			return true;
		} 
		return false;
	}
	
}
