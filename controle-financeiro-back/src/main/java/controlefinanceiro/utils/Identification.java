package controlefinanceiro.utils;

import controlefinanceiro.repository.RepositoryControleFinanceiro;

public class Identification {
	
	public static Integer getId(RepositoryControleFinanceiro repository) {
		Integer id = repository.maxId();
		if (id == null) {
			return 1;
		}
		return id + 1;
	}

}
