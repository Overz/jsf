package br.com.jsf.controller;

import static br.com.util.Constantes.ABA_NOVO;
import static br.com.util.Constantes.ABA_PESQUISAR;

import br.com.jsf.db.Connection;
import br.com.jsf.model.dao.daoi.FornecedorDAO;
import br.com.jsf.model.dao.impl.FornecedorDaoImp;
import br.com.jsf.model.vo.FornecedorVO;
import java.util.Date;
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
	private FacesContext context;
	private Session s;
	private Integer aba;

	public void pesquisar() {
		try {
			defineProperties();
			List<FornecedorVO> l;
			if (
				fornecedorVO.getNome() != null &&
				fornecedorVO.getNome().trim().isEmpty()
			) {
				l = dao.find(s);
				dataModel = new ListDataModel<>(l);
				return;
			}

			l = dao.find(s, fornecedorVO.getNome());
			dataModel = new ListDataModel<>(l);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName());
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getMessage());
			message(
				"Erro!",
				"Erro ao Consultar por Nome",
				FacesMessage.SEVERITY_FATAL,
				null
			);
		} finally {
			clean(ABA_PESQUISAR);
		}
	}

	public void salvar() {
		try {
			defineProperties();

			Boolean r = dao.save(s, fornecedorVO);

			if (!r) {
				message(
					"Erro!",
					"Erro ao Salvar o Fornecedor",
					FacesMessage.SEVERITY_INFO,
					fornecedorVO.toString()
				);
				return;
			}

			message(
				"Sucesso!",
				"Fornecedor Cadastrador com Sucesso!",
				FacesMessage.SEVERITY_INFO,
				fornecedorVO.toString()
			);
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			message(
				"Erro",
				"Erro ao salvar o Fornecedor:",
				FacesMessage.SEVERITY_FATAL,
				fornecedorVO.toString()
			);
		} finally {
			clean(ABA_NOVO);
		}
	}

	public void excluir() {
		fornecedorVO = dataModel.getRowData();
		try {
			defineProperties();
			Boolean r = dao.delete(s, fornecedorVO);
			if (!r) {
				message(
					"Erro!",
					"Erro ao Excluir o Fornecedor: ",
					FacesMessage.SEVERITY_ERROR,
					fornecedorVO.toString()
				);
				return;
			}
			message(
				"Sucesso!",
				"Fornecedor Excluido com Sucesso!",
				FacesMessage.SEVERITY_INFO,
				fornecedorVO.toString()
			);
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			message(
				"Erro!",
				"Erro ao Excluir o Fornecedor: ",
				FacesMessage.SEVERITY_FATAL,
				fornecedorVO.toString()
			);
		} finally {
			clean(ABA_PESQUISAR);
		}
	}

	public void editar() {}

	public FornecedorVO getFornecedorVO() {
		if (fornecedorVO == null) {
			fornecedorVO = new FornecedorVO();
		}

		return fornecedorVO;
	}

	private void clean(int aba) {
		this.aba = aba;
		s.close();
		fornecedorVO = null;
	}

	private void defineProperties() {
		if (dao == null) {
			dao = new FornecedorDaoImp();
		}

		if (s == null || !s.isOpen()) {
			s = Connection.getSession();
		}
	}

	private void message(
		String title,
		String message,
		FacesMessage.Severity type,
		String toString
	) {
		context = FacesContext.getCurrentInstance();
		context.addMessage(
			null,
			new FacesMessage(type, title, message + " '" + toString + "' ")
		);
	}
}
