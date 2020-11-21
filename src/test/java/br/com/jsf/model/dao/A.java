package br.com.jsf.model.dao;

import br.com.jsf.db.Connection;
import org.hibernate.Session;

public class A {

	public static void main(String[] args) {
		Session s = Connection.getSession();
	}
}
