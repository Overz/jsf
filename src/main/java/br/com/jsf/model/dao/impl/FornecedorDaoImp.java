package br.com.jsf.model.dao.impl;

import br.com.jsf.model.dao.BaseDAOImp;
import br.com.jsf.model.dao.daoi.FornecedorDAO;
import br.com.jsf.model.vo.FornecedorVO;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class FornecedorDaoImp
	extends BaseDAOImp<FornecedorVO, Long>
	implements FornecedorDAO, Serializable {

	@Override
	public List<FornecedorVO> find(Session s, String v) {
		Query<FornecedorVO> qry = s.createQuery(
			"FROM fornecedor FETCH ALL PROPERTIES WHERE nome LIKE :nome",
			FornecedorVO.class
		);
		qry.setParameter("nome", like(v));
		return qry.list();
	}

	@Override
	public List<FornecedorVO> find(Session s) {
		return s.createQuery("FROM fornecedor", FornecedorVO.class).list();
	}

	@Override
	public FornecedorVO findOne(Session s, Long id) {
		return s.get(FornecedorVO.class, id);
	}
}
