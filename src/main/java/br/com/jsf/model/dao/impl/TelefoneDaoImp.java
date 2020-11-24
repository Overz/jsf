package br.com.jsf.model.dao.impl;

import br.com.jsf.model.dao.BaseDAOImp;
import br.com.jsf.model.dao.daoi.TelefoneDAO;
import br.com.jsf.model.vo.TelefoneVO;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class TelefoneDaoImp
	extends BaseDAOImp<TelefoneVO, Long>
	implements TelefoneDAO, Serializable {

	@Override
	public List<TelefoneVO> find(Session s, String v) {
		Query<TelefoneVO> qry = s.createQuery(
			"FROM telefone FETCH ALL PROPERTIES WHERE operadora LIKE :operadora",
			TelefoneVO.class
		);
		qry.setParameter("operadora", like(v));
		return qry.list();
	}

	@Override
	public List<TelefoneVO> find(Session s) {
		return s.createQuery("FROM telefone", TelefoneVO.class).list();
	}

	@Override
	public TelefoneVO findOne(Session s, Long id) {
		return s.get(TelefoneVO.class, id);
	}
}
