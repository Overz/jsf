package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.FornecedorVO;
import br.com.jsf.model.vo.PessoaVO;
import java.util.List;
import org.hibernate.Session;

public interface PessoaDAO extends BaseDAO<PessoaVO, Long> {
	List<PessoaVO> find(Session s, String v);

	List<PessoaVO> find(Session s);
}
