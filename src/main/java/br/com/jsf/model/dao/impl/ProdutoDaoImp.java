package br.com.jsf.model.dao.impl;

import br.com.jsf.model.dao.BaseDAOImp;
import br.com.jsf.model.dao.daoi.ProdutoDAO;
import br.com.jsf.model.vo.ProdutoVO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class ProdutoDaoImp
	extends BaseDAOImp<ProdutoVO, Long>
	implements ProdutoDAO {

	@Override
	public List<ProdutoVO> find(Session s, String v) {
		Query<ProdutoVO> qry = s.createQuery(
			"FROM produto FETCH ALL PROPERTIES WHERE nome LIKE :nome",
			ProdutoVO.class
		);
		qry.setParameter("nome", like(v));
		return qry.list();
	}

	@Override
	public List<ProdutoVO> find(Session s) {
		return s.createQuery("FROM produto", ProdutoVO.class).list();
	}

	@Override
	public ProdutoVO findOne(Session s, Long id) {
		return s.get(ProdutoVO.class, id);
	}
}
