package br.com.jsf.model.dao;

import br.com.jsf.db.Connection;
import br.com.jsf.model.dao.daoi.ProdutoDAO;
import br.com.jsf.model.dao.impl.ProdutoDaoImp;
import br.com.jsf.model.vo.ProdutoVO;
import java.util.List;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

public class ProdutoDaoImpTest extends Assert implements BaseTEST {
	private static Session s;
	private static ProdutoVO p;
	private static ProdutoDAO dao;

	public ProdutoDaoImpTest() {
		dao = new ProdutoDaoImp();
		s = Connection.getSession();
	}

	public void preencherObj() {
		List<ProdutoVO> l = dao.find(s);
		p = l != null && !l.isEmpty() ? l.get(0) : null;

		if (p == null) {
			criarObj();
			dao.save(s, p);
			p = dao.find(s).get(0);
		}
	}

	private void criarObj() {
		p = new ProdutoVO("teste", 10.0, 100, 110, "aaaaaaaaaaaaaaaa");
	}

	@Test
	@Override
	public void cadastrar() {
		System.out.println(getClass().getSimpleName() + " Cadastrar");
		criarObj();
		assertTrue(dao.save(s, p));
	}

	@Test
	@Override
	public void alterar() {
		System.out.println(getClass().getSimpleName() + " Alterar");
		preencherObj();
		p.setDescricao("bbbbbbb");
		assertTrue(dao.save(s, p));
	}

	@Test
	@Override
	public void consultarNome() {
		System.out.println(getClass().getSimpleName() + " Consultar Nome");
		assertNotNull(dao.find(s, "teste"));
	}

	@Test
	@Override
	public void consultarId() {
		System.out.println(getClass().getSimpleName() + " Consultar ID");
		cadastrar();
		p = dao.findOne(s, p.getId());
		System.out.println("PRODUTO\n" + p);
		assertNotNull(p);
	}

	@Test
	@Override
	public void listar() {
		System.out.println(getClass().getSimpleName() + " Listar");
		assertNotNull(dao.find(s));
	}

	@Test
	@Override
	public void excluir() {
		System.out.println(getClass().getSimpleName() + " Excluir");
		preencherObj();
		assertTrue(dao.delete(s, p));
	}
}
