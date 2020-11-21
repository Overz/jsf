package br.com.jsf.model.dao.impl;

import br.com.jsf.model.dao.BaseDAOImp;
import br.com.jsf.model.dao.daoi.PessoaDAO;
import br.com.jsf.model.vo.PessoaVO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PessoaDaoImp
	extends BaseDAOImp<PessoaVO, Long>
	implements PessoaDAO {

	@Override
	public List<PessoaVO> find(Session s, String v) {
		Query<PessoaVO> qry = s.createQuery(
			"FROM pessoa FETCH ALL PROPERTIES WHERE nome LIKE :nome",
			PessoaVO.class
		);
		qry.setParameter("nome", like(v));
		return qry.list();
	}

	@Override
	public List<PessoaVO> find(Session s) {
		return s.createQuery("FROM pessoa", PessoaVO.class).list();
	}

	@Override
	public PessoaVO findOne(Session s, Long id) {
		return s.get(PessoaVO.class, id);
	}
}
