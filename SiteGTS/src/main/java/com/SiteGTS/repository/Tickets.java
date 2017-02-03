package com.SiteGTS.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
	public List<Ticket> pesquisar(String op, String pesquisa) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Ticket.class);
		if (op.equals("razaosocial") || op.equals("tecnico"))
		criteria.add(Restrictions.ilike(op, pesquisa, MatchMode.START));
		if (op.equals("status")){
			if (pesquisa.equals("ABERTO"))
				criteria.add(Restrictions.eq(op, 0));
			if (pesquisa.equals("EM ANDAMENTO"))
				criteria.add(Restrictions.eq(op, 1));
			if (pesquisa.equals("FECHADO"))
				criteria.add(Restrictions.eq(op, 2));
		}
		if (op.equals("nivel")){
			if (pesquisa.equals("BAIXO"))
				criteria.add(Restrictions.eq(op, 0));
			if (pesquisa.equals("MEDIO") || pesquisa.equals("MÉDIO"))
				criteria.add(Restrictions.eq(op, 1));
			if (pesquisa.equals("ALTO"))
				criteria.add(Restrictions.eq(op, 2));
			if (pesquisa.equals("URGENTE"))
				criteria.add(Restrictions.eq(op, 3));		
		}
		return criteria.list();
	}

	public Ticket porId(Long id) {
		return manager.find(Ticket.class, id);
	}

}
