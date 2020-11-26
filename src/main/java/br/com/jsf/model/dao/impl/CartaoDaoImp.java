package br.com.jsf.model.dao.impl;

import br.com.jsf.model.dao.BaseDAOImp;
import br.com.jsf.model.dao.daoi.CartaoDAO;
import br.com.jsf.model.vo.CartaoVO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CartaoDaoImp
	extends BaseDAOImp<CartaoVO, Long>
	implements CartaoDAO {

	@Override
	public List<CartaoVO> find(Session s, String value) {
		Query<CartaoVO> qry = s.createQuery(
			"FROM CartaoVO WHERE numero = :numero",
			CartaoVO.class
		);
		qry.setParameter("numero", value);
		return qry.list();
	}

	@Override
	public List<CartaoVO> find(Session s) {
		return s.createQuery("FROM CartaoVO", CartaoVO.class).list();
	}

	@Override
	public CartaoVO findOne(Session s, Long id) {
		return s.get(CartaoVO.class, id);
	}
}
