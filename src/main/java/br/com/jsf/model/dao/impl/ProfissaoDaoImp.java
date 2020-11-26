package br.com.jsf.model.dao.impl;

import br.com.jsf.model.dao.BaseDAOImp;
import br.com.jsf.model.dao.daoi.ProfissaoDAO;
import br.com.jsf.model.vo.ProfissaoVO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ProfissaoDaoImp
	extends BaseDAOImp<ProfissaoVO, Long>
	implements ProfissaoDAO {

	@Override
	public List<ProfissaoVO> find(Session s, String value) {
		Query<ProfissaoVO> qry = s.createQuery(
			"FROM ProfissaoVO WHERE nome LIKE :nome",
			ProfissaoVO.class
		);
		qry.setParameter("nome", like(value));
		return qry.list();
	}

	@Override
	public List<ProfissaoVO> find(Session s) {
		return s.createQuery("FROM ProfissaoVO", ProfissaoVO.class).list();
	}

	@Override
	public ProfissaoVO findOne(Session s, Long id) {
		return s.get(ProfissaoVO.class, id);
	}
}
