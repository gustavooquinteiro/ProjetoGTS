package com.SiteGTS.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.SiteGTS.model.Ticket;
import com.SiteGTS.util.jpa.Transactional;

public class Tickets implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject 
	private EntityManager manager;

	@Transactional
	public Ticket guardar(Ticket ticket) {
		ticket = manager.merge(ticket); 
		return null;
	}

}
