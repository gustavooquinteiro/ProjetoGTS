package com.SiteGTS.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.SiteGTS.model.Ticket;
import com.SiteGTS.service.PesquisaTicketService;

@Named
@ViewScoped
public class PesquisaTicketBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PesquisaTicketService pts;
	private String nome;
	private List<Ticket> ticketsFiltrados;
	private Ticket ticketSelecionado;
	private String data;
	private String nivel;
	private String status;
	private String op;
	private String pesquisa;

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa.toUpperCase();
	}

	public PesquisaTicketBean() {

		ticketsFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		ticketsFiltrados = pts.pesquisar(op, pesquisa);
	}

	public String getData() {
		return data;
	}

	public void setData(String format) {
		this.data = format;
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

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}