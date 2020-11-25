package br.com.jsf.controller;

import static br.com.util.Constantes.ABA_NOVO;
import static br.com.util.Constantes.ABA_PESQUISAR;

import br.com.jsf.db.Connection;
import br.com.jsf.model.dao.daoi.ProdutoDAO;
import br.com.jsf.model.dao.impl.ProdutoDaoImp;
import br.com.jsf.model.vo.ProdutoVO;
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
@ManagedBean(name = "produtoC")
public class ProdutoController {
	private DataModel<ProdutoVO> dataModelProduto;
	private FacesContext context;
	private List<ProdutoVO> l;
	private ProdutoVO produtoVO;
	private ProdutoDAO dao;
	private Session s;
	private Integer aba;

	public void pesquisar() {
		try {
			defineProperties();
			if (produtoVO.getNome() != null && produtoVO.getNome().trim().isEmpty()) {
				l = dao.find(s);
				dataModelProduto = new ListDataModel<>(l);
				return;
			}

			l = dao.find(s, produtoVO.getNome());
			dataModelProduto = new ListDataModel<>(l);
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			message(
				"Erro!",
				"Erro ao Tentar Consultar os Dados!",
				null,
				FacesMessage.SEVERITY_ERROR
			);
		} finally {
			clean(ABA_PESQUISAR);
		}
	}

	public void salvar() {
		try {
			defineProperties();

			Boolean r = dao.save(s, produtoVO);

			if (!r) {
				message(
					"Erro",
					"Erro ao Salvar o Produto",
					produtoVO.toString(),
					FacesMessage.SEVERITY_WARN
				);
			}
			message(
				"Sucesso!",
				"Produto Cadastrado com Sucesso!",
				produtoVO.toString(),
				FacesMessage.SEVERITY_INFO
			);
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			message(
				"Erro",
				"Erro ao Salvar o Produto",
				produtoVO.toString(),
				FacesMessage.SEVERITY_ERROR
			);
		} finally {
			produtoVO = null;
			clean(ABA_NOVO);
		}
	}

	public void excluir() {
		try {
			defineProperties();
			produtoVO = dataModelProduto.getRowData();

			Boolean r = dao.delete(s, produtoVO);

			if (!r) {
				message(
					"Erro!",
					"Erro ao Excluir o Produto: ",
					produtoVO.toString(),
					FacesMessage.SEVERITY_WARN
				);
				return;
			}

			message(
				"Sucesso!",
				"Produto Excluido com Sucesso!",
				produtoVO.toString(),
				FacesMessage.SEVERITY_INFO
			);
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			message(
				"Erro!",
				"Erro ao Excluir o Produto: ",
				produtoVO.toString(),
				FacesMessage.SEVERITY_ERROR
			);
		} finally {
			produtoVO = null;
			clean(ABA_PESQUISAR);
		}
	}

	public void editar() {
		try {
			produtoVO = dataModelProduto.getRowData();
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
		} finally {
			clean(ABA_NOVO);
		}
	}

	public void limpar() {
		this.aba = ABA_NOVO;
		produtoVO = null;
	}

	private void clean(int aba) {
		this.aba = aba;
		s.close();
	}

	public ProdutoVO getProdutoVO() {
		if (produtoVO == null) {
			produtoVO = new ProdutoVO();
		}

		return produtoVO;
	}

	private void defineProperties() {
		if (dao == null) {
			dao = new ProdutoDaoImp();
		}

		if (s == null || !s.isOpen()) {
			s = Connection.getSession();
		}
	}

	private void message(
		String title,
		String msg,
		String toString,
		FacesMessage.Severity type
	) {
		context = FacesContext.getCurrentInstance();
		context.addMessage(
			null,
			new FacesMessage(type, title, msg + " '" + toString + "' ")
		);
	}
}
