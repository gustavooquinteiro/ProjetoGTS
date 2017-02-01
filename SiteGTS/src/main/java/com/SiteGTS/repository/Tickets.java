package com.SiteGTS.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.SiteGTS.model.Ticket;
import com.SiteGTS.util.jpa.Transactional;

public class Tickets implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public Ticket guardar(Ticket ticket) {
		ticket = manager.merge(ticket);
		return null;
	}

	public void remover(Ticket ticketSelecionado) {
		// TODO

	}

	@SuppressWarnings("unchecked")
	public List<Ticket> pesquisar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Ticket.class);
		return criteria.list();
	}

	public Ticket porId(Long id) {
		return manager.find(Ticket.class, id);
	}

}
