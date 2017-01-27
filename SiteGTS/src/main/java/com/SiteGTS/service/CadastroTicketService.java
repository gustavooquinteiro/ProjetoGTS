package com.SiteGTS.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.SiteGTS.model.Ticket;
import com.SiteGTS.repository.Tickets;
import com.SiteGTS.util.jpa.Transactional;

public class CadastroTicketService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Tickets tickets;
	
	@Transactional
	public Ticket salvar (Ticket ticket){
		return tickets.guardar(ticket); 
	}
}
