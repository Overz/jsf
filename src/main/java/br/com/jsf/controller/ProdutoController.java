package br.com.jsf.controller;

import br.com.jsf.model.dao.daoi.ProdutoDAO;
import br.com.jsf.model.vo.ProdutoVO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
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
	private DataModel<ProdutoVO> dataModel;
	private List<ProdutoVO> l;
	private ProdutoVO produtoVO;
	private ProdutoDAO dao;
	private Session s;

	public void pesquisar() {}

	public void salvar() {}

	public void editar() {}

	public void excluir() {}

	public void limpar() {}

	private void defineProperties() {}
}
