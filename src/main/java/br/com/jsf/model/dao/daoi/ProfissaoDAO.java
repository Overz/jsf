package br.com.jsf.model.dao.daoi;

import br.com.jsf.model.dao.BaseDAO;
import br.com.jsf.model.vo.ProfissaoVO;
import java.util.List;
import org.hibernate.Session;

public interface ProfissaoDAO extends BaseDAO<ProfissaoVO, Long> {
	List<ProfissaoVO> find(Session s, String value);

	List<ProfissaoVO> find(Session s);
}
