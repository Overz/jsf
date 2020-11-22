package br.com.jsf.controller;

import br.com.jsf.db.Connection;
import br.com.jsf.model.dao.daoi.FornecedorDAO;
import br.com.jsf.model.dao.impl.FornecedorDaoImp;
import br.com.jsf.model.vo.FornecedorVO;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			defineProperties();

			Boolean r = dao.save(s, fornecedorVO);

			if (!r) {
				context.addMessage(
					null,
					new FacesMessage(
						"Erro!",
						"Erro ao Salvar o Fornecedor '" + fornecedorVO.toString() + "' "
					)
				);
			}

			context.addMessage(
				null,
				new FacesMessage(
					"Sucesso!",
					"Fornecedor '" + fornecedorVO.toString() + "' Cadastrado com Sucesso!"
				)
			);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());

			context.addMessage(
				null,
				new FacesMessage(
					"Erro!",
					"Erro ao Salvar o Fornecedor '" + fornecedorVO.toString() + "' "
				)
			);
		} finally {
			s.close();
		}
	}

	public void excluir() {
		fornecedorVO = dataModel.getRowData();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			defineProperties();
			Boolean r = dao.delete(s, fornecedorVO);
			if (!r) {
				context.addMessage(
					null,
					new FacesMessage(
						"Erro!",
						"Erro ao Excluir o Fornecedor '" + fornecedorVO.toString() + "' "
					)
				);
			}
			context.addMessage(
				null,
				new FacesMessage(
					"Sucesso!",
					"Fornecedor '" + fornecedorVO.toString() + "' Excluido com Sucesso!"
				)
			);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			context.addMessage(
				null,
				new FacesMessage(
					"Erro!",
					"Erro ao excluir o Fornecedor '" + fornecedorVO.toString() + "' "
				)
			);
		} finally {
			s.close();
		}
	}

	public void pesquisarTodos() {
		try {
			List<FornecedorVO> l = dao.find(s);
			System.out.println(l);
			dataModel = new ListDataModel<>(l);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
		} finally {
			s.close();
		}
	}

	public void editar() {}

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

		if (s == null || !s.isOpen()) {
			s = Connection.getSession();
		}
	}
}
