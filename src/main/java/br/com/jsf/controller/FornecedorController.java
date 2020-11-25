package br.com.jsf.controller;

import static br.com.util.Constantes.ABA_NOVO;
import static br.com.util.Constantes.ABA_PESQUISAR;

import br.com.jsf.db.Connection;
import br.com.jsf.model.dao.daoi.EnderecoDAO;
import br.com.jsf.model.dao.daoi.FornecedorDAO;
import br.com.jsf.model.dao.impl.FornecedorDaoImp;
import br.com.jsf.model.vo.EnderecoVO;
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
	private DataModel<FornecedorVO> dataModelFornecedor;
	private List<FornecedorVO> listFornecedor;
	private List<EnderecoVO> listEndereco;
	private FornecedorVO fornecedorVO;
	private EnderecoVO enderecoVO;
	private FornecedorDAO daoF;
	private Session s;
	private Integer aba;
	private FacesContext context;

	public void pesquisar() {
		try {
			defineProperties();
			if (
				fornecedorVO.getNome() != null &&
				fornecedorVO.getNome().trim().isEmpty()
			) {
				listFornecedor = daoF.find(s);
			} else {
				listFornecedor = daoF.find(s, fornecedorVO.getNome());
			}

			dataModelFornecedor = new ListDataModel<>(listFornecedor);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName());
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getMessage());
			message(
				"Erro!",
				"Erro ao Consultar por Nome",
				FacesMessage.SEVERITY_ERROR,
				null
			);
		} finally {
			clean(ABA_PESQUISAR);
		}
	}

	public void salvar() {
		try {
			defineProperties();

			Boolean r = daoF.save(s, fornecedorVO);

			if (!r) {
				message(
					"Erro!",
					"Erro ao Salvar o Fornecedor",
					FacesMessage.SEVERITY_WARN,
					fornecedorVO.toString()
				);
				return;
			}

			message(
				"Sucesso!",
				"Fornecedor Cadastrado com Sucesso!",
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
				FacesMessage.SEVERITY_ERROR,
				fornecedorVO.toString()
			);
		} finally {
			fornecedorVO = null;
			clean(ABA_NOVO);
		}
	}

	public void excluir() {
		try {
			defineProperties();
			fornecedorVO = dataModelFornecedor.getRowData();
			Boolean r = daoF.delete(s, fornecedorVO);
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
			fornecedorVO = null;
			clean(ABA_PESQUISAR);
		}
	}

	public void limpar() {
		this.aba = ABA_NOVO;
		fornecedorVO = null;
	}

	public void editar() {
		try {
			fornecedorVO = dataModelFornecedor.getRowData();
			listEndereco = fornecedorVO.getEnderecoVOS();
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
		} finally {
			clean(ABA_NOVO);
		}
	}

	public FornecedorVO getFornecedorVO() {
		if (fornecedorVO == null) {
			fornecedorVO = new FornecedorVO();
		}

		return fornecedorVO;
	}

	private void clean(int aba) {
		this.aba = aba;
		s.close();
	}

	private void defineProperties() {
		if (daoF == null) {
			daoF = new FornecedorDaoImp();
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
