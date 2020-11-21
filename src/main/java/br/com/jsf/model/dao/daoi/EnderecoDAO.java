package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.EnderecoVO;
import br.com.jsf.model.vo.FornecedorVO;
import java.util.List;
import org.hibernate.Session;

public interface EnderecoDAO extends BaseDAO<EnderecoVO, Long> {
	List<EnderecoVO> find(Session s, String v);

	List<EnderecoVO> find(Session s);
}
