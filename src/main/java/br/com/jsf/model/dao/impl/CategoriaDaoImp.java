package br.com.jsf.model.dao.impl;

import br.com.jsf.model.dao.BaseDAOImp;
import br.com.jsf.model.dao.daoi.CategoriaDAO;
import br.com.jsf.model.vo.CategoriaVO;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CategoriaDaoImp
	extends BaseDAOImp<CategoriaVO, Long>
	implements CategoriaDAO, Serializable {

	@Override
	public List<CategoriaVO> find(Session s, String v) {
		Query<CategoriaVO> qry = s.createQuery(
			"FROM categoria FETCH ALL PROPERTIES WHERE nome LIKE :nome",
			CategoriaVO.class
		);
		qry.setParameter("nome", like(v));
		return qry.list();
	}

	@Override
	public List<CategoriaVO> find(Session s) {
		return s.createQuery("FROM categoria", CategoriaVO.class).list();
	}

	@Override
	public CategoriaVO findOne(Session s, Long id) {
		return s.get(CategoriaVO.class, id);
	}
}
