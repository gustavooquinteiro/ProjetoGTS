package com.SiteGTS.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.SiteGTS.model.Ticket;

public class Tickets implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private EntityManager manager;

	public Ticket guardar(Ticket ticket) {
		ticket = manager.merge(ticket); 
		return null;
	}

}
