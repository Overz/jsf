package br.com.jsf.controller;

import br.com.jsf.model.vo.FornecedorVO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@ViewScoped
@ManagedBean(name = "fornecedorC")
public class FornecedorController {
	private FornecedorVO fornecedorVO;
	private String aba;

	public FornecedorVO find() {
		return null;
	}

	public FornecedorVO getFornecedorVO() {
		return fornecedorVO == null ? new FornecedorVO() : fornecedorVO;
	}
}
