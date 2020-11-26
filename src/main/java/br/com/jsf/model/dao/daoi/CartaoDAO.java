package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.CartaoVO;
import java.util.List;
import org.hibernate.Session;

public interface CartaoDAO extends BaseDAO<CartaoVO, Long> {
	List<CartaoVO> find(Session s, String value);

	List<CartaoVO> find(Session s);
}
