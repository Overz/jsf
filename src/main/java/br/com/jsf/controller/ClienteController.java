package br.com.jsf.controller;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "clienteC")
public class ClienteController {
	private String nome;
	private Double salario;
	private Date dtNascimento;

	public String salvar() {
		System.out.println(nome);
		System.out.println(salario);
		System.out.println(dtNascimento);
		return "print";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
}
