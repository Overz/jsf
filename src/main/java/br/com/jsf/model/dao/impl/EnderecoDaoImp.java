package br.com.jsf.model.dao.impl;

import br.com.jsf.model.dao.BaseDAOImp;
import br.com.jsf.model.dao.daoi.EnderecoDAO;
import br.com.jsf.model.vo.EnderecoVO;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class EnderecoDaoImp
	extends BaseDAOImp<EnderecoVO, Long>
	implements EnderecoDAO, Serializable {

	@Override
	public List<EnderecoVO> find(Session s, String v) {
		Query<EnderecoVO> qry = s.createQuery(
			"FROM endereco FETCH ALL PROPERTIES WHERE bairro LIKE :bairro",
			EnderecoVO.class
		);
		qry.setParameter("bairro", like(v));
		return qry.list();
	}

	@Override
	public List<EnderecoVO> find(Session s) {
		return s.createQuery("FROM endereco", EnderecoVO.class).list();
	}

	@Override
	public EnderecoVO findOne(Session s, Long id) {
		return s.get(EnderecoVO.class, id);
	}
}
