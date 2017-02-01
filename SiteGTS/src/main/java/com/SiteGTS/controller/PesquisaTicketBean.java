package com.SiteGTS.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.SiteGTS.model.Ticket;
import com.SiteGTS.repository.Tickets;

@Named
@ViewScoped
public class PesquisaTicketBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Tickets tickets;

	private String nome;
	private List<Ticket> ticketsFiltrados;
	private Ticket ticketSelecionado;
	private String data;

	public PesquisaTicketBean() {

		ticketsFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		ticketsFiltrados = tickets.pesquisar();
		for (Ticket ticket : ticketsFiltrados) {
			DateFormat dataHora = DateFormat.getDateInstance();
			 setData(dataHora.format(ticket.getDataAbertura().getTime()));
		}
	}

	
	public String getData() {
		return data;
	}

	public void setData(String format) {
		this.data = format; 		
	}

	public void excluir() {
		tickets.remover(ticketSelecionado);
		ticketsFiltrados.remove(ticketSelecionado);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Ticket> getTicketsFiltrados() {
		return ticketsFiltrados;
	}

	public void setTicketsFiltrados(List<Ticket> ticketsFiltrados) {
		this.ticketsFiltrados = ticketsFiltrados;
	}

	public Ticket getTicketSelecionado() {
		return ticketSelecionado;
	}

	public void setTicketSelecionado(Ticket ticketSelecionado) {
		this.ticketSelecionado = ticketSelecionado;
	}

}