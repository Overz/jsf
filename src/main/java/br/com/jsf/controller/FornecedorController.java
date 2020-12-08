package br.com.jsf.controller;

import static br.com.jsf.util.Constantes.ABA_NOVO;
import static br.com.jsf.util.Constantes.ABA_PESQUISAR;

import br.com.jsf.db.Connection;
import br.com.jsf.model.bo.EnderecoBO;
import br.com.jsf.model.dao.daoi.FornecedorDAO;
import br.com.jsf.model.dao.impl.EnderecoDaoImp;
import br.com.jsf.model.dao.impl.FornecedorDaoImp;
import br.com.jsf.model.dto.EnderecoDTO;
import br.com.jsf.model.vo.EnderecoVO;
import br.com.jsf.model.vo.FornecedorVO;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.util.ArrayList;
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
	private List<String> listEstados;
	private FornecedorVO fornecedorVO;
	private EnderecoVO enderecoVO;
	private EnderecoVO oldEnderecoVO;
	private FornecedorDAO daoF;
	private Session s;
	private Integer aba;
	private FacesContext context;
	private String cep;

	public void pesquisar() {
		try {
			defineProperties();
			if (
				fornecedorVO.getNome() != null &&
				fornecedorVO.getNome().trim().isEmpty()
			) {
				listFornecedor = daoF.find(s);
				dataModelFornecedor = new ListDataModel<>(listFornecedor);
				return;
			}

			listFornecedor = daoF.find(s, fornecedorVO.getNome());
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

	public void pesquisarCEP() {
		try {
			Boolean r = EnderecoBO.cep(cep);
			if (r) {
				Unirest.setTimeouts(0, 0);
				HttpResponse<String> response = Unirest
					.get(
						"https://viacep.com.br/ws/" +
						cep.trim().replaceAll("\\D", "") +
						"/json/"
					)
					.asString();
				Gson g = new Gson();
				EnderecoDTO dto = g.fromJson(response.getBody(), EnderecoDTO.class);
				enderecoVO = dto.getEnderecoVO();
				this.cep = enderecoVO.getCep();
				return;
			}

			message(
				"Erro!",
				"Digite o CEP no Tamanho Correto!",
				FacesMessage.SEVERITY_WARN,
				null
			);
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			message(
				"Erro!",
				"Erro ao consultar o CEP!",
				FacesMessage.SEVERITY_ERROR,
				null
			);
		}
	}

	public void salvar() {
		try {
			defineProperties();

			fornecedorVO.setEnderecoVOS(listEndereco);
			enderecoVO.setFornecedorVO(fornecedorVO);
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
			enderecoVO = null;
			clean(ABA_NOVO);
		}
	}

	public void salvarEndereco() {
		defineProperties();
		if (listEndereco == null) {
			listEndereco = new ArrayList<>();
		}
		if (enderecoVO.getId() != null) {
			if (enderecoVO.getId().equals(oldEnderecoVO.getId())) {
				listEndereco.remove(oldEnderecoVO);
				this.oldEnderecoVO = null;
			}
		}
		listEndereco.add(enderecoVO);
		aba = ABA_NOVO;
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
			aba = ABA_PESQUISAR;
			clean(ABA_PESQUISAR);
		}
	}

	public void excluirEndereco(EnderecoVO end) {
		try {
			if (end.getId() == null) {
				listEndereco.remove(enderecoVO);
				return;
			}

			defineProperties();
			fornecedorVO.getEnderecoVOS().remove(end);
			end.setFornecedorVO(null);
			Boolean r = new EnderecoDaoImp().delete(s, end);

			if (!r) {
				message(
					"Erro!",
					"Erro ao Remover o Endereço",
					FacesMessage.SEVERITY_WARN,
					null
				);
			}

			message(
				"Sucesso!",
				"Endereço Excluido com Sucesso!",
				FacesMessage.SEVERITY_INFO,
				""
			);
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			message(
				"Erro!",
				"Erro ao Remover o Endereço",
				FacesMessage.SEVERITY_ERROR,
				null
			);
		} finally {
			enderecoVO = null;
			clean(ABA_NOVO);
		}
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

	public void editarEndereco(EnderecoVO end) {
		try {
			this.enderecoVO = end;
			this.oldEnderecoVO = end;
			this.cep = end.getCep();
		} catch (Exception e) {
			System.out.println(getClass().getSimpleName());
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
		}
	}

	public FornecedorVO getFornecedorVO() {
		if (fornecedorVO == null) {
			fornecedorVO = new FornecedorVO();
		}

		return fornecedorVO;
	}

	public EnderecoVO getEnderecoVO() {
		if (enderecoVO == null) {
			enderecoVO = new EnderecoVO();
		}

		return enderecoVO;
	}

	public List<String> getListEstados() {
		if (listEstados == null || listEstados.isEmpty()) {
			listEstados = new ArrayList<>();
			listEstados = populateStates();
		}
		return listEstados;
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

		if (enderecoVO == null) {
			enderecoVO = new EnderecoVO();
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

	public void limpar() {
		this.cep = null;
		this.enderecoVO = null;
		this.fornecedorVO = null;
		this.listEndereco = null;
		this.listFornecedor = null;
		this.dataModelFornecedor = null;
	}

	private List<String> populateStates() {
		listEstados.add("RO");
		listEstados.add("AC");
		listEstados.add("AM");
		listEstados.add("RR");
		listEstados.add("PA");
		listEstados.add("AP");
		listEstados.add("TO");
		listEstados.add("MA");
		listEstados.add("PI");
		listEstados.add("CE");
		listEstados.add("RN");
		listEstados.add("PB");
		listEstados.add("PE");
		listEstados.add("AL");
		listEstados.add("SE");
		listEstados.add("BA");
		listEstados.add("MG");
		listEstados.add("ES");
		listEstados.add("RJ");
		listEstados.add("SP");
		listEstados.add("PR");
		listEstados.add("SC");
		listEstados.add("RS");
		listEstados.add("MS");
		listEstados.add("MT");
		listEstados.add("GO");
		listEstados.add("DF");
		return listEstados;
	}
}
