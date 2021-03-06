package br.com.jsf.controller;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ViewScoped
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

	public String limpar() {
		this.nome = null;
		this.salario = null;
		this.dtNascimento = null;
		System.out.println("Limpou " + nome);
		System.out.println("Limpou " + salario);
		System.out.println("Limpou " + dtNascimento);
		return null;
	}
}
