package br.com.jsf.db;

import br.com.jsf.model.vo.*;
import java.util.Arrays;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Connection {
	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry service;
	private static Session session;

	static {
		try {
			Configuration configuration = new Configuration();
			Connection.annotated(configuration);

			configuration.configure("/META-INF/hibernate.cfg.xml");

			// Caminho "absoluto" para o arquivo de configuração do hivernate
			configuration.configure("/META-INF/hibernate.cfg.xml");

			// Deixa as consultas em memoria, para não precisar consultar 2x as mesmas coisas
			service =
				new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.build();

			// Cria a configuração pelas instancias em memoria
			sessionFactory = configuration.buildSessionFactory(service);
		} catch (Exception e) {
			// Log the exception.
			System.err.println(Connection.class.getSimpleName());
			System.err.println(e.getMessage());
			System.err.println(Arrays.toString(e.getStackTrace()));
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Session getSession() {
		try {
			if (session == null || !session.isOpen()) {
				session = sessionFactory.openSession();
			}
		} catch (Exception e) {
			System.out.println(Connection.class.getSimpleName());
			System.out.println(e.getMessage());
			System.err.println(Arrays.toString(e.getStackTrace()));
		}
		return session;
	}

	private static void annotated(Configuration config) {
		config.addAnnotatedClass(PessoaVO.class);
		config.addAnnotatedClass(FornecedorVO.class);
		config.addAnnotatedClass(ProdutoVO.class);
		config.addAnnotatedClass(CategoriaVO.class);
		config.addAnnotatedClass(TelefoneVO.class);
		config.addAnnotatedClass(EnderecoVO.class);
	}
}
