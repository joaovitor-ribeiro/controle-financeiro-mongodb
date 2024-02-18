package controlefinanceiro.dto.cartao;

import controlefinanceiro.utils.ValidUtils;

public enum Bandeira {
	
	MASTERCARD ("Mastercard"),
	VISA ("Visa"),
	JCB ("JCB"),
	AMERICAN_EXPRESS ("American Express"),
	DINERS_CLUB ("Diners Club"),
	AURA("Aura"),
	HIPERCARD("Hipercard");
	
	private String value;

	private Bandeira(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static Bandeira find(String bandeira) {
		if (ValidUtils.isEmpty(bandeira)) {
			return null;
		}
		
		for (Bandeira b : Bandeira.values()) {
			if (b.getValue().equals(bandeira)) {
				return b;
			}
		}
		
		return null;
	}
}
