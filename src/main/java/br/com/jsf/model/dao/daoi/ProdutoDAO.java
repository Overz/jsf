package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.FornecedorVO;
import br.com.jsf.model.vo.ProdutoVO;
import java.util.List;
import org.hibernate.Session;

public interface ProdutoDAO extends BaseDAO<ProdutoVO, Long> {
	List<ProdutoVO> find(Session s, String v);

	List<ProdutoVO> find(Session s);
}
