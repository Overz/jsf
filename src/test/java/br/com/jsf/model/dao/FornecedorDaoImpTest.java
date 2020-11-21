package br.com.jsf.model.dao;

import br.com.jsf.db.Connection;
import br.com.jsf.model.dao.daoi.FornecedorDAO;
import br.com.jsf.model.dao.impl.FornecedorDaoImp;
import br.com.jsf.model.vo.EnderecoVO;
import br.com.jsf.model.vo.FornecedorVO;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FornecedorDaoImpTest extends Assert implements BaseTEST {
	private static Session s;
	private static FornecedorVO f;
	private static FornecedorDAO dao;

	public FornecedorDaoImpTest() {
		dao = new FornecedorDaoImp();
		s = Connection.getSession();
	}

	@Before
	@Test
	public void preencherObj() {
		List<FornecedorVO> l = dao.find(s);
		f = l != null && !l.isEmpty() ? l.get(0) : null;

		if (f == null) {
			criarObj();
			dao.save(s, f);
			f = dao.find(s).get(0);
		}
	}

	private void criarObj() {
		f =
			FornecedorVO
				.builder()
				.setNome("teste")
				.setEmail("teste")
				.setTelefone("teste")
				.setDescricao("teste")
				.setDtCadastro(new Date())
				.build();
	}

	@Test
	@Override
	public void cadastrar() {
		System.out.println(getClass().getSimpleName() + " Cadastrar");
		EnderecoVO e = new EnderecoVO();
		f =
			FornecedorVO
				.builder()
				.setNome("teste")
				.setEmail("teste")
				.setTelefone("teste")
				.setDescricao("teste")
				.setDtCadastro(new Date())
				.build();
		s = Connection.getSession();
		assertTrue(dao.save(s, f));
	}

	@Test
	@Override
	public void alterar() {
		System.out.println(getClass().getSimpleName());
		preencherObj();
		f.setNome("aaaaaaa");
		f.setDescricao("bbbbbbb");
		f.setTelefone("ccccccc");
		assertTrue(dao.save(s, f));
	}

	@Test
	@Override
	public void consultarId() {
		System.out.println(getClass().getSimpleName() + " Consultar ID");
		cadastrar();
		s = Connection.getSession();
		f = dao.findOne(s, f.getId());
		System.out.println("FORNECEDOR\n" + f);
		assertNotNull(f);
	}

	@Test
	@Override
	public void consultarNome() {
		System.out.println(getClass().getSimpleName() + " Consultar Nome");
		s = Connection.getSession();
		assertNotNull(dao.find(s, "teste"));
	}

	@Test
	@Override
	public void listar() {
		System.out.println(getClass().getSimpleName() + " Listar");
		s = Connection.getSession();
		assertNotNull(dao.find(s));
	}

	@Test
	@Override
	public void excluir() {
		System.out.println(getClass().getSimpleName() + " Excluir");
		cadastrar();
		s = Connection.getSession();
		assertTrue(dao.delete(s, f));
	}
}
