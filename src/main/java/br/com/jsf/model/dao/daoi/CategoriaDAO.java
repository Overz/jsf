package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.CategoriaVO;
import br.com.jsf.model.vo.FornecedorVO;
import java.util.List;
import org.hibernate.Session;

public interface CategoriaDAO extends BaseDAO<CategoriaVO, Long> {
	List<CategoriaVO> find(Session s, String v);

	List<CategoriaVO> find(Session s);
}
