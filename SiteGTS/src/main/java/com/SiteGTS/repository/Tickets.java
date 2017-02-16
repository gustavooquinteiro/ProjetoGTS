package com.SiteGTS.repository;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import com.SiteGTS.filter.TicketFilter;
import com.SiteGTS.model.Cliente;
import com.SiteGTS.model.Ticket;
import com.SiteGTS.model.TicketGTS;
import com.SiteGTS.model.vo.DataValor;
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
			criteriaTicket.add(Restrictions.le(op, filtro.getDataAbertura()));
		if (op.equals("dataFechamento"))
			criteriaTicket.add(Restrictions.ge(op, filtro.getDataFechamento()));

		return criteriaTicket.addOrder(Order.desc("id")).list();
	}

	@SuppressWarnings("unchecked")
	private List<Cliente> pesquisaCliente(String op, String pesquisa) {
		Session session = manager.unwrap(Session.class);
		Criteria criteriaCliente = session.createCriteria(Cliente.class);
		criteriaCliente.add(Restrictions.ilike(op, pesquisa, MatchMode.START));
		return criteriaCliente.list();
	}

	/*@SuppressWarnings("unchecked")
	public List<Ticket> ticketsPorStatus(Date dataInicio) {

		Calendar dataInicial = Calendar.getInstance();
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		dataInicial.add(Calendar.DAY_OF_MONTH, -30);
		Map<Date, Long> resultado = criarMapaVazio(30, dataInicial);

	}*/

	@SuppressWarnings("unchecked")
	public List<Ticket> ticketsPorCliente(Date dataInicio, Date dataFinal) {
		Session session = manager.unwrap(Session.class);
		SQLQuery select = session
				.createSQLQuery("SELECT COALESCE(e.nome,'NÃO INFORMADO CLIENTE')  nome, COUNT(c.id) total "
						+ "FROM	gestaosist.tickets c " + "LEFT JOIN gestaosist.clientes e ON (e.id = c.empresa) "
						+ "GROUP BY 1 BETWEEN '" + dataInicio + "' and '" + dataFinal + "';");
		return select.list();
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> ticketsPorTecnico(Date dataInicio, Date dataFinal) {
		Session session = manager.unwrap(Session.class);
		SQLQuery select = session.createSQLQuery("SELECT coalesce(t.name, 'nao informado') tecnico, COUNT(c.id) "
				+ "FROM gestaosist.tickets c " + "LEFT JOIN  gestaosist.users t ON (t.id = c.tecnico)"
				+ "WHERE c.data_abertura BETWEEN '" + dataInicio + "' AND '" + dataFinal + "'; " + "GROUP BY 1;");
		return select.list();
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> ticketsPorRotina(Date dataInicio, Date dataFinal) {
		Session session = manager.unwrap(Session.class);
		SQLQuery select = session.createSQLQuery(
				"SELECT COALESCE(so.descricao, 'Nao informado') software, COALESCE(r.descricao, 'Não informado') rotina, COUNT(t.id) total "
						+ "FROM gestaosist.tickets t" + "LEFT JOIN gestaosist.softwares so ON (t.software = so.id)"
						+ "LEFT JOIN gestaosist.rotinas r ON (r.id = t.rotina) " + "WHERE  t.data_abertura BETWEEN '"
						+ dataInicio + "' and '" + dataFinal + "' " + "GROUP BY rotina;");
		return select.list();
	}

	public Ticket porId(Long id) {
		return manager.find(Ticket.class, id);
	}

	@SuppressWarnings({ "unchecked"})
	public Map<Date, Long> ticketsPorMes(int numeroDias) {
		Session session = manager.unwrap(Session.class);
		
		numeroDias -= 1;
		
		Calendar dataInicial = Calendar.getInstance();
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		dataInicial.add(Calendar.DAY_OF_MONTH, numeroDias * -1);
		
		Map<Date, Long> resultado = criarMapaVazio(numeroDias, dataInicial);
			
		Criteria criteria = session.createCriteria(TicketGTS.class);

		criteria.setProjection(Projections.projectionList()
				.add(Projections.sqlGroupProjection("date(data_abertura) as data", "date(data_abertura)",
						new String[] { "data" }, new Type[] { StandardBasicTypes.DATE }))
				.add(Projections.count("id").as("quantidade")))
				.add(Restrictions.ge("data_abertura", dataInicial.getTime()));

		List<DataValor> valoresPorData = criteria.setResultTransformer(Transformers.aliasToBean(DataValor.class))
				.list();

		for (DataValor dataValor: valoresPorData){
			resultado.put(dataValor.getData(), dataValor.getQuantidade());
		}
		return resultado;
	}

	private Map<Date, Long> criarMapaVazio(int numeroDias, Calendar dataInicial) {
		dataInicial = (Calendar) dataInicial.clone();
		Map<Date, Long> mapaInicial = new TreeMap<>();
		
		for (int i = 0; i <= numeroDias; i++) {
			mapaInicial.put(dataInicial.getTime(), Long.MIN_VALUE);
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return mapaInicial;
	}

}
