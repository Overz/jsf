package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.PessoaFisicaVO;
import java.util.List;
import org.hibernate.Session;

public interface PessoaFisicaDAO extends BaseDAO<PessoaFisicaVO, Long> {
	List<PessoaFisicaVO> find(Session s, String value);

	List<PessoaFisicaVO> find(Session s);
}
