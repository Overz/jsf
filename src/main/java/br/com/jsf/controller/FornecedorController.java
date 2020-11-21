package br.com.jsf.controller;

import br.com.jsf.db.Connection;
import br.com.jsf.model.dao.daoi.FornecedorDAO;
import br.com.jsf.model.dao.impl.FornecedorDaoImp;
import br.com.jsf.model.vo.FornecedorVO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import lombok.*;
import org.hibernate.Session;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ViewScoped
@ManagedBean(name = "fornecedorC")
public class FornecedorController {
	private FornecedorVO fornecedorVO;
	private FornecedorDAO dao;
	private DataModel<FornecedorVO> dataModel;
	private Session s;
	private String aba;

	public void pesquisarPorNome() {
		try {
			defineProperties();

			List<FornecedorVO> l = dao.find(s, fornecedorVO.getNome());
			dataModel = new ListDataModel<>(l);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
		} finally {
			s.close();
		}
	}

	public FornecedorVO getFornecedorVO() {
		if (fornecedorVO == null) {
			fornecedorVO = new FornecedorVO();
		}

		return fornecedorVO;
	}

	private void defineProperties() {
		if (dao == null) {
			dao = new FornecedorDaoImp();
		}

		if (s == null) {
			s = Connection.getSession();
		}
	}
}
