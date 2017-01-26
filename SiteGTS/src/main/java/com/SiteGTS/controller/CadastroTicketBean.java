package com.SiteGTS.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.SiteGTS.model.Cliente;
import com.SiteGTS.model.Ticket;
import com.SiteGTS.model.Usuario;
import com.SiteGTS.repository.Clientes;
import com.SiteGTS.repository.Usuarios;
import com.SiteGTS.service.PesquisaClienteService;

@Named
@ViewScoped
public class CadastroTicketBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Ticket ticket;

	private Cliente cliente;

	@Inject
	private Clientes clientes;

	@Inject
	private PesquisaClienteService pcs;

	private String op;

	private List<Cliente> clientesListados;
	private List<Usuario> usuarios;

	@Inject
	private Usuarios user;

	public CadastroTicketBean() {
		limpar();
	}

	private void limpar() {
		clientesListados = new ArrayList<>();
		ticket = new Ticket();
		cliente = new Cliente();
	}

	public List<Cliente> completarEmpresa(String nome) {
		String op = this.getOp();
		if (op != null) {
			clientesListados = clientes.buscar(op, nome);
		} else {
			op = "razaosocial";
			clientesListados = clientes.buscar(op, nome);
		}
		return clientesListados;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Clientes getClientes() {
		return clientes;
	}

	public List<Usuario> getUsuarios() {
		usuarios = user.buscar();
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuarios getUser() {
		return user;
	}

	public void setUser(Usuarios user) {
		this.user = user;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
}
