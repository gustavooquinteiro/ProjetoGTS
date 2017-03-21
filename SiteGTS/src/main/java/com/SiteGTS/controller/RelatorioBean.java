package com.SiteGTS.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.SiteGTS.util.jsf.FacesUtil;
import com.SiteGTS.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	private GraficosBean graficos;

	private Date dataInicio = graficos.getDataInicial();
	private Date dataFim = graficos.getDataFinal(); 
	
	
	
	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		
		if (graficos.getOpcao().equals("porstatus"))
			parametros.put("SQL", "SELECT tabelatickets.status, count(tabelatickets.id)"
				+ "FROM bdgestao.tabelatickets " + "WHERE str_to_date(data_abertura, '%Y-%m-%d') BETWEEN '"
				+ dataInicio + "' and '" + dataFim + "' " + "group by 1");
		
		else if(graficos.getOpcao().equals("portecnico"))
			parametros.put("SQL", "SELECT coalesce(t.name, 'Técnico não informado') nome, COUNT(c.id) quantidade "
						+ "FROM bdgestao.tabelatickets c "
						+ "WHERE str_to_date(data_abertura, '%Y-%m-%d') BETWEEN '" + dataInicio + "' and '" + dataFim + "' " 
						+ "LEFT JOIN bdgestao.tabelausuario t ON (t.id = c.tecnico) GROUP BY 1" );
		
		else if (graficos.getOpcao().equals("porrotina"))
			parametros.put("SQL", "SELECT COALESCE(r.descricao, 'Rotina não informado') rotina, COUNT(t.id) quantidade "
						+ "FROM bdgestao.tabelatickets t WHERE str_to_date(data_abertura, '%Y-%m-%d') BETWEEN '"
						+ dataInicio + "' and '" + dataFim + "' "
						+ "LEFT JOIN bdgestao.tabelasoftware so ON (t.software = so.id)" 
						+ "LEFT JOIN bdgestao.tabelarotina r ON (r.id = t.rotina) GROUP BY 1");
		
		else if (graficos.getOpcao().equals("porcliente"))
			parametros.put("SQL", "SELECT COALESCE(e.nome,'Cliente não informado') cliente, COUNT(c.id) quantidade "
						+ "FROM	bdgestao.tabelatickets c " + "WHERE str_to_date(data_abertura, '%Y-%m-%d') BETWEEN '"
						+ dataInicio + "' and '" + dataFim + "' "
						+ "LEFT JOIN bdgestao.clientetickets e ON (e.id = c.empresa) GROUP BY 1"); 
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorios.jasper", 
				this.response, parametros);
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}

}
