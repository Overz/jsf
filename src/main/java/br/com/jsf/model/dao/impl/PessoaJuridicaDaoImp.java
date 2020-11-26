package br.com.jsf.model.dao.impl;

import br.com.jsf.model.dao.BaseDAOImp;
import br.com.jsf.model.dao.daoi.PessoaJuridicaDAO;
import br.com.jsf.model.vo.PessoaJuridicaVO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class PessoaJuridicaDaoImp
	extends BaseDAOImp<PessoaJuridicaVO, Long>
	implements PessoaJuridicaDAO {

	@Override
	public List<PessoaJuridicaVO> find(Session s, String value) {
		Query<PessoaJuridicaVO> qry = s.createQuery(
			"FROM PessoaJuridicaVO FETCH ALL PROPERTIES WHERE nome LIKE :nome",
			PessoaJuridicaVO.class
		);
		qry.setParameter("nome", like(value).toUpperCase());
		return qry.list();
	}

	@Override
	public List<PessoaJuridicaVO> find(Session s) {
		return s
			.createQuery("FROM PessoaJuridicaVO", PessoaJuridicaVO.class)
			.list();
	}

	@Override
	public PessoaJuridicaVO findOne(Session s, Long id) {
		return s.get(PessoaJuridicaVO.class, id);
	}
}
