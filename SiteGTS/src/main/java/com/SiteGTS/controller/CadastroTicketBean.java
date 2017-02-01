package com.SiteGTS.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.SiteGTS.model.Cliente;
import com.SiteGTS.model.Status;
import com.SiteGTS.model.Ticket;
import com.SiteGTS.model.Usuario;
import com.SiteGTS.repository.Clientes;
import com.SiteGTS.repository.Usuarios;
import com.SiteGTS.service.CadastroTicketService;
import com.SiteGTS.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroTicketBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Ticket ticket;

	private Cliente cliente;

	@Inject
	private Clientes clientes;

	@Inject
	private CadastroTicketService cts;

	private String op;

	private List<Cliente> clientesListados;
	private List<Usuario> usuarios;

	@Inject
	private Usuarios user;

	public CadastroTicketBean() {
		limpar();
	}

	public void salvar() {
		if (this.ticket.getStatus().equals(Status.FECHADO)) {
			FacesUtil.addInfoMessage("Chamado fechado com sucesso!");
			this.ticket.setDataFechamento(ticket.retornaDataAtual());
		} else if (this.ticket.getStatus().equals(Status.ABERTO)) {
			FacesUtil.addInfoMessage("Chamado aberto com sucesso!");
		} else if(this.ticket.getStatus().equals(Status.EM_ANDAMENTO)){
			FacesUtil.addInfoMessage("Chamado atualizado com sucesso!");
		}
		cts.salvar(ticket);
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

	public String colocarTelefone() {
		String tel = cliente.getFone1();
		return tel;
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
