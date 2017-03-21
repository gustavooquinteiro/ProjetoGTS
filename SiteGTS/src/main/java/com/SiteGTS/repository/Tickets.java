package com.SiteGTS.repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.SiteGTS.filter.TicketFilter;
import com.SiteGTS.model.Cliente;
import com.SiteGTS.model.Ticket;
import com.SiteGTS.model.vo.ClienteTickets;
import com.SiteGTS.model.vo.DadosValor;
import com.SiteGTS.model.vo.RotinaTickets;
import com.SiteGTS.model.vo.TecnicoTickets;
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

	public Ticket porId(Long id) {
		return manager.find(Ticket.class, id);
	}

	@SuppressWarnings("unchecked")
	private List<Cliente> pesquisaCliente(String op, String pesquisa) {
		Session session = manager.unwrap(Session.class);
		Criteria criteriaCliente = session.createCriteria(Cliente.class);
		criteriaCliente.add(Restrictions.ilike(op, pesquisa, MatchMode.START));
		return criteriaCliente.list();
	}

	@SuppressWarnings("unchecked")
	public Map<String, Long> ticketsPorStatus(Date dataInicial, Date dataFinal) {
		Session session = manager.unwrap(Session.class);
		int numeroDias = 30;
		numeroDias = verificaQuantidadeDias(dataInicial, dataFinal, numeroDias);
		Map<String, Long> resultado = criarMapaVazio(numeroDias);

		String dataInicio = modificarFormatoData(dataInicial);
		String dataFim = modificarFormatoData(dataFinal);
		SQLQuery select = session.createSQLQuery("SELECT tabelatickets.status, count(tabelatickets.id)"
				+ "FROM bdgestao.tabelatickets " + "WHERE str_to_date(data_abertura, '%Y-%m-%d') "
				+ "BETWEEN '"+ dataInicio +"' and '" + dataFim + "'" 
				+ "group by 1");

		List<DadosValor> valoresPorStatus = select.setResultTransformer(Transformers.aliasToBean(DadosValor.class))
				.list();

		for (DadosValor dataValor : valoresPorStatus) {
			resultado.put(dataValor.getStatus(), dataValor.getQuantidade());
		}

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public Map<String, BigInteger> ticketsPorCliente(Date dataInicial, Date dataFinal) {
		Session session = manager.unwrap(Session.class);
		int numeroDias = 30;
		numeroDias = verificaQuantidadeDias(dataInicial, dataFinal, numeroDias);

		Map<String, BigInteger> resultado = gerarMapaVazio(numeroDias);

		String dataInicio = modificarFormatoData(dataInicial);
		String dataFim = modificarFormatoData(dataFinal);

		SQLQuery select = session
				.createSQLQuery("SELECT COALESCE(e.nome,'Cliente não informado') cliente, COUNT(c.id) quantidade "
						+ "FROM	bdgestao.tabelatickets c " + "WHERE str_to_date(data_abertura, '%Y-%m-%d') "
								+ "BETWEEN str_to_date("+ dataInicio +", '%Y-%m-%d') and str_to_date(" + dataFim + ", '%Y-%m-%d') "
						+ "LEFT JOIN bdgestao.clientetickets e ON (e.id = c.empresa) GROUP BY 1");

		List<ClienteTickets> valoresPorCliente = select
				.setResultTransformer(Transformers.aliasToBean(ClienteTickets.class)).list();

		for (ClienteTickets dataValor : valoresPorCliente) {
			resultado.put(dataValor.getCliente(), dataValor.getQuantidade());
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public Map<String, BigInteger> ticketsPorTecnico(Date dataInicial, Date dataFinal) {
		Session session = manager.unwrap(Session.class);
		int numeroDias = 30;
		numeroDias = verificaQuantidadeDias(dataInicial, dataFinal, numeroDias);

		Map<String, BigInteger> resultado = gerarMapaVazio(numeroDias);

		String dataInicio = modificarFormatoData(dataInicial);
		String dataFim = modificarFormatoData(dataFinal);

		SQLQuery select = session
				.createSQLQuery("SELECT coalesce(t.name, 'Técnico não informado') nome, COUNT(c.id) quantidade "
						+ "FROM bdgestao.tabelatickets c " + "WHERE str_to_date(data_abertura, '%Y-%m-%d') "
						+ "BETWEEN str_to_date("+ dataInicio +", '%Y-%m-%d') and str_to_date(" + dataFim + ", '%Y-%m-%d') "
						+ "LEFT JOIN bdgestao.tabelausuario t ON (t.id = c.tecnico) GROUP BY 1");
		List<TecnicoTickets> valoresPorTecnico = select
				.setResultTransformer(Transformers.aliasToBean(TecnicoTickets.class)).list();
		for (TecnicoTickets dataValor : valoresPorTecnico) {
			resultado.put(dataValor.getNome(), dataValor.getQuantidade());
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	public Map<String, BigInteger> ticketsPorRotina(Date dataInicial, Date dataFinal) {
		Session session = manager.unwrap(Session.class);
		int numeroDias = 30;
		numeroDias = verificaQuantidadeDias(dataInicial, dataFinal, numeroDias);
		Map<String, BigInteger> resultado = gerarMapaVazio(numeroDias);
		String dataInicio = modificarFormatoData(dataInicial);
		String dataFim = modificarFormatoData(dataFinal);
		SQLQuery select = session
				.createSQLQuery("SELECT COALESCE(r.descricao, 'Rotina não informado') rotina, COUNT(t.id) quantidade "
						+ "FROM bdgestao.tabelatickets t WHERE str_to_date(data_abertura, '%Y-%m-%d') BETWEEN '"
						+ dataInicial + "' and '" + dataFinal + "' "
						+ "LEFT JOIN bdgestao.tabelasoftware so ON (t.software = so.id)"
						+ "LEFT JOIN bdgestao.tabelarotina r ON (r.id = t.rotina) GROUP BY 1");
		List<RotinaTickets> valoresPorRotina = select
				.setResultTransformer(Transformers.aliasToBean(RotinaTickets.class)).list();
		for (RotinaTickets dataValor : valoresPorRotina) {
			resultado.put(dataValor.getRotina(), dataValor.getQuantidade());
		}
		return resultado;
	}

	/*
	 * @SuppressWarnings({ "unchecked" }) public Map<Calendar, Long>
	 * ticketsPorMes(int numeroDias) { Session session =
	 * manager.unwrap(Session.class);
	 * 
	 * numeroDias -= 1;
	 * 
	 * Calendar dataInicial = Calendar.getInstance(); dataInicial =
	 * DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
	 * dataInicial.add(Calendar.DAY_OF_MONTH, numeroDias * -1);
	 * 
	 * Map<Calendar, Long> resultado = criarMapaVazio(numeroDias, dataInicial);
	 * 
	 * Criteria criteria = session.createCriteria(TicketGTS.class);
	 * 
	 * criteria.setProjection(Projections.projectionList()
	 * .add(Projections.sqlGroupProjection("date(data_abertura) as data",
	 * "date(data_abertura)", new String[] { "data" }, new Type[] {
	 * StandardBasicTypes.DATE }))
	 * .add(Projections.count("id").as("quantidade")))
	 * .add(Restrictions.ge("data_abertura", dataInicial.getTime()));
	 * 
	 * List<DataValor> valoresPorData =
	 * criteria.setResultTransformer(Transformers.aliasToBean(DataValor.class))
	 * .list();
	 * 
	 * for (DataValor dataValor : valoresPorData) {
	 * resultado.put(dataValor.getData(), dataValor.getQuantidade()); } return
	 * resultado; }
	 */

	private Map<String, Long> criarMapaVazio(int numeroDias) {
		Calendar dataInicial = Calendar.getInstance();
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		dataInicial.add(Calendar.DAY_OF_MONTH, numeroDias * -1);

		dataInicial = (Calendar) dataInicial.clone();
		Map<String, Long> mapaInicial = new TreeMap<>();

		for (int i = 0; i <= numeroDias; i++) {
			mapaInicial.put("", Long.valueOf(0));
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		return mapaInicial;
	}

	private Map<String, BigInteger> gerarMapaVazio(int numeroDias) {
		Calendar dataInicial = Calendar.getInstance();
		dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
		dataInicial.add(Calendar.DAY_OF_MONTH, numeroDias * -1);

		dataInicial = (Calendar) dataInicial.clone();
		Map<String, BigInteger> mapaInicial = new TreeMap<>();

		for (int i = 0; i <= numeroDias; i++) {
			mapaInicial.put("", BigInteger.ZERO);
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}
		return mapaInicial;
	}

	@SuppressWarnings("unused")
	private Map<Date, Long> criarMapaVazio(int numeroDias, Calendar dataInicial) {
		dataInicial = (Calendar) dataInicial.clone();
		Map<Date, Long> mapaInicial = new TreeMap<>();

		for (int i = 0; i <= numeroDias; i++) {
			mapaInicial.put(dataInicial.getTime(), Long.valueOf(0));
			dataInicial.add(Calendar.DAY_OF_MONTH, 1);
		}

		return mapaInicial;
	}

	private String modificarFormatoData(Date data) {
		// TODO Pensar em lógica de conversão de data no estilo pt-BR para data
		// en-US
		DateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String dataFormatada = formato.format(data); 
		return dataFormatada;
	}

	private int verificaQuantidadeDias(Date dataInicial, Date dataFinal, int numeroDias) {
		if (dataInicial != null && dataFinal != null) {
			numeroDias = dataFinal.compareTo(dataInicial);
			if (numeroDias < 0) {
				numeroDias *= -1;
			}
			numeroDias -= 1;
		}
		return numeroDias;
	}
}
