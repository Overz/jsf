package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.PessoaJuridicaVO;
import java.util.List;
import org.hibernate.Session;

public interface PessoaJuridicaDAO extends BaseDAO<PessoaJuridicaVO, Long> {
	List<PessoaJuridicaVO> find(Session s, String value);

	List<PessoaJuridicaVO> find(Session s);
}
