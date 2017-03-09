package com.SiteGTS.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.SiteGTS.util.jsf.FacesUtil;
import com.SiteGTS.util.report.ExecutorRelatorio;

public class RelatorioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	private GraficosBean graficos;

	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		if (graficos.getOpcao().equals("porstatus"))
			parametros.put("SQL", "");
		else if(graficos.getOpcao().equals("portecnico"))
			parametros.put("SQL", "" );
		else if (graficos.getOpcao().equals("porrotina"))
			parametros.put("SQL", "");
		else if (graficos.getOpcao().equals("porcliente"))
			parametros.put("SQL", ""); 
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_nota.jasper", 
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
