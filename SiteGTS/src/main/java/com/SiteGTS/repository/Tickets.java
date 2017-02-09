package com.SiteGTS.repository;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.SiteGTS.filter.TicketFilter;
import com.SiteGTS.model.Cliente;
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

	@SuppressWarnings("unchecked")
	public List<Ticket> pesquisar(TicketFilter filtro, String op) {
		Session session = manager.unwrap(Session.class);
		Criteria criteriaTicket = session.createCriteria(Ticket.class);
		
		if (op.equals("razaosocial")) {
			pesquisaCliente(op, filtro.getRazaosocial());
		}
		
		if (op.equals("tecnico"))
			criteriaTicket.add(Restrictions.ilike(op, filtro.getTecnico(), MatchMode.START));
		
		if (op.equals("status")) {
			if (filtro.getStatus().equals("ABERTO"))
				criteriaTicket.add(Restrictions.eq(op, 0));
			if (filtro.getStatus().equals("EM ANDAMENTO"))
				criteriaTicket.add(Restrictions.eq(op, 1));
			if (filtro.getStatus().equals("FECHADO"))
				criteriaTicket.add(Restrictions.eq(op, 2));
		}

		if (op.equals("nivel")) {
			if (filtro.getNivel().equals("BAIXO"))
				criteriaTicket.add(Restrictions.eq(op, 0));
			if (filtro.getNivel().equals("MEDIO") || filtro.getNivel().equals("MÉDIO"))
				criteriaTicket.add(Restrictions.eq(op, 1));
			if (filtro.getNivel().equals("ALTO"))
				criteriaTicket.add(Restrictions.eq(op, 2));
			if (filtro.getNivel().equals("URGENTE"))
				criteriaTicket.add(Restrictions.eq(op, 3));
		}

		if (op.equals("dataAbertura"))
			criteriaTicket.add(Restrictions.le(op, converteParaCalendar(filtro.getDataAbertura())));
		if (op.equals("dataFechamento"))
			criteriaTicket.add(Restrictions.ge(op, converteParaCalendar(filtro.getDataFechamento())));

		return criteriaTicket.list();
	}

	@SuppressWarnings("unchecked")
	private List<Cliente> pesquisaCliente(String op, String pesquisa) {
		Session session = manager.unwrap(Session.class);
		Criteria criteriaCliente = session.createCriteria(Cliente.class);
		criteriaCliente.add(Restrictions.ilike(op, pesquisa, MatchMode.START));
		return criteriaCliente.list();
	}

	public Ticket porId(Long id) {
		return manager.find(Ticket.class, id);
	}

	private Calendar converteParaCalendar(String pesquisa) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(pesquisa));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}

}
