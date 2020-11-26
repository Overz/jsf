package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.ClienteVO;
import java.util.List;
import org.hibernate.Session;

public interface ClienteDAO extends BaseDAO<ClienteVO, Long> {
	List<ClienteVO> find(Session s, String value);

	List<ClienteVO> find(Session s);
}
