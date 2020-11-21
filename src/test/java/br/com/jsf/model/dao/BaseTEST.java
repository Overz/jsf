package br.com.jsf.model.dao;

import org.junit.Before;
import org.junit.Test;

public interface BaseTEST {
	@Before
	@Test
	void cadastrar();

	@Test
	void alterar();

	@Test
	void consultarNome();

	@Test
	void consultarId();

	@Test
	void listar();

	@Test
	void excluir();
}
