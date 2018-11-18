package com.SiteGTS.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.SiteGTS.filter.TicketFilter;
import com.SiteGTS.model.Ticket;
import com.SiteGTS.repository.Tickets;

public class PesquisaTicketService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private Tickets tickets;
	private TicketFilter filtro;

	public List<Ticket> pesquisar(String op, String pesquisa) {
		filtro = new TicketFilter();
		if (op.equals("dataAbertura"))
			filtro.setDataAbertura(pesquisa);
		if (op.equals("dataFechamento"))
			filtro.setDataFechamento(pesquisa);
		if (op.equals("nivel"))
			filtro.setNivel(pesquisa);
		if (op.equals("razaosocial"))
			filtro.setRazaosocial(pesquisa);
		if (op.equals("status"))
			filtro.setStatus(pesquisa);
		if (op.equals("tecnico"))
			filtro.setTecnico(pesquisa);
		if (op.equals("vazio"))
			filtro = new TicketFilter();
		return tickets.pesquisar(filtro, op);

	}
}
