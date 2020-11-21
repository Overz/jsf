package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.FornecedorVO;
import java.util.List;
import org.hibernate.Session;

public interface FornecedorDAO extends BaseDAO<FornecedorVO, Long> {
	List<FornecedorVO> find(Session s, String v);

	List<FornecedorVO> find(Session s);
}
