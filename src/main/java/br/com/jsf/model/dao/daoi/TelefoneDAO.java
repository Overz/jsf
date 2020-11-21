package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.FornecedorVO;
import br.com.jsf.model.vo.TelefoneVO;
import java.util.List;
import org.hibernate.Session;

public interface TelefoneDAO extends BaseDAO<TelefoneVO, Long> {
	List<TelefoneVO> find(Session s, String v);

	List<TelefoneVO> find(Session s);
}
