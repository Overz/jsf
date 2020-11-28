package br.com.jsf.model.bo;

public class EnderecoBO {

	public static Boolean cep(String cep) {
		if (cep != null && !cep.trim().isEmpty()) {
			cep = cep.trim().replaceAll("\\D", "");
			return cep.length() == 8;
		}
		return false;
	}
}
