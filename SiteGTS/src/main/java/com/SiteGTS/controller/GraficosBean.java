package com.SiteGTS.controller;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
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
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.SiteGTS.repository.Tickets;
import com.SiteGTS.util.jsf.FacesUtil;
import com.SiteGTS.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class GraficosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	private Tickets novo = new Tickets();
	private ExecutorRelatorio executor;
	@Inject
	private Tickets tickets;

	private Date dataInicial;
	private Date dataFinal;

	private BarChartModel modeloBarra;
	private LineChartModel modeloLinha;
	private PieChartModel modeloPizza;

	private String opcao;
	private String rotulo;
	private String tipoGrafico;
	private int diasEntre;
	private String saida;
	private boolean mostrarGraficoBarra;
	private boolean mostrarGraficoLinha;
	private boolean mostrarGraficoPizza;

	public GraficosBean() {
		modeloBarra = new BarChartModel();
		modeloLinha = new LineChartModel();
		modeloPizza = new PieChartModel();
		saida = "/dashboard/PesquisaGrafico.xhtml";
		mostrarGraficoBarra = false;
		mostrarGraficoLinha = false;
		mostrarGraficoPizza = false;
	}

	public void consultarGraficos() {
		if (getDataInicial() != null && getDataFinal() != null) {
			diasEntre = dataFinal.compareTo(dataInicial);
			if (diasEntre < 0) {
				diasEntre *= -1;
			}
		} else {
			FacesUtil.addWarnMessage("Insira duas datas!");
		}

		if (tipoGrafico.equals("vazio"))
			FacesUtil.addWarnMessage("Defina um tipo de gráfico para exibição!");
		else if (tipoGrafico.equals("bar"))
			adicionarSerieEmBarra();
		else if (tipoGrafico.equals("line"))
			adicionarSerieEmLinha();
		else if (tipoGrafico.equals("pie"))
			adicionarSerieEmPizza();
	}

	private String tipoDePesquisa() {
		if (opcao.equals("porstatus")) {
			this.rotulo = "Tickets por Status";
		}

		if (opcao.equals("portecnico")) {
			this.rotulo = "Tickets por Técnico";
		}

		if (opcao.equals("porrotina")) {
			this.rotulo = "Tickets por rotina";
		}

		if (opcao.equals("porcliente")) {
			this.rotulo = "Tickets por Cliente";
		}

		return rotulo;
	}

	private void adicionarSerieEmPizza() {
		this.modeloPizza = new PieChartModel();

		if (opcao.equals("porstatus")) {
			Map<String, Long> resultado = this.tickets.ticketsPorStatus(getDataInicial(), getDataFinal());

			for (String data : resultado.keySet()) {
				modeloPizza.set(data.toString(), resultado.get(data));
			}

		}
		if (opcao.equals("portecnico")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorTecnico(dataInicial, dataFinal);

			for (String data : resultado.keySet()) {
				modeloPizza.set(data, resultado.get(data));
			}

		}
		if (opcao.equals("porrotina")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorRotina(dataInicial, dataFinal);

			for (String data : resultado.keySet()) {
				modeloPizza.set(data, resultado.get(data));
			}

		}
		if (opcao.equals("porcliente")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorCliente(dataInicial, dataFinal);

			for (String data : resultado.keySet()) {
				modeloPizza.set(data, resultado.get(data));
			}

		}

		modeloPizza.setLegendPosition("e");
		modeloPizza.setTitle(tipoDePesquisa());
		modeloPizza.setDiameter(850);
		modeloPizza.setShowDataLabels(true);
		modeloPizza.setMouseoverHighlight(true);
		mostrarGraficoPizza = true;
	}

	private void adicionarSerieEmLinha() {
		this.modeloLinha = new LineChartModel();
		ChartSeries series = new ChartSeries(tipoDePesquisa());
		modeloLinha.setAnimate(true);
		modeloLinha.setLegendPosition("e");
		modeloLinha.setTitle(tipoDePesquisa());
		Axis yAxis = modeloLinha.getAxis(AxisType.Y);
		yAxis.setMin(0);

		if (opcao.equals("porstatus")) {
			Map<String, Long> resultado = this.tickets.ticketsPorStatus(dataInicial, dataFinal);
			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}
		}
		if (opcao.equals("portecnico")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorTecnico(dataInicial, dataFinal);
			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}
		}
		if (opcao.equals("porrotina")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorRotina(dataInicial, dataFinal);
			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}
		}
		if (opcao.equals("porcliente")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorCliente(dataInicial, dataFinal);
			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}
		}

		this.modeloLinha.addSeries(series);
		mostrarGraficoLinha = true;
		saida = "/dashboard/GraficoLinha.xhtml";
	}

	private void adicionarSerieEmBarra() {
		this.modeloBarra = new BarChartModel();
		ChartSeries series = new ChartSeries(tipoDePesquisa());
		modeloBarra.setLegendPosition("e");
		modeloBarra.setTitle(tipoDePesquisa());

		Axis yAxis = modeloBarra.getAxis(AxisType.Y);
		yAxis.setMin(0);

		if (opcao.equals("porstatus")) {
			Map<String, Long> resultado = this.tickets.ticketsPorStatus(dataInicial, dataFinal);

			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}

		}
		if (opcao.equals("portecnico")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorTecnico(dataInicial, dataFinal);

			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}

		}
		if (opcao.equals("porrotina")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorRotina(dataInicial, dataFinal);

			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}

		}
		if (opcao.equals("porcliente")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorCliente(dataInicial, dataFinal);

			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}

		}
		modeloBarra.setLegendCols(diasEntre);
		this.modeloBarra.addSeries(series);
		saida = "/dashboard/GraficoBarra.xhtml";
		mostrarGraficoBarra = true;
	}

	public void emitir() {
		
		Map<String, Object> parametros = new HashMap<>();
		
		 String dataInicio = novo.modificarFormatoData(this.dataInicial);
		 String dataFim = novo.modificarFormatoData(this.dataFinal); 
		SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd");
		formatar.setLenient(false);
		try{
		dataInicial = formatar.parse(dataInicio);
		dataFinal = formatar.parse(dataFim);
		}catch (Exception e) {
			System.out.println(e);
		}
		parametros.put("dataInicio", dataInicial);
		
		parametros.put("dataFim", dataFinal); 
		
		if (getOpcao().equals("porstatus"))
			executor = new ExecutorRelatorio("/relatorios/relatorios.jasper", 
					this.response, parametros);
			
		
		if(getOpcao().equals("portecnico"))
			executor = new ExecutorRelatorio("/relatorios/relatorioTicketsPorTecnico.jasper", 
					this.response, parametros);
			 
		if (getOpcao().equals("porrotina"))
			executor = new ExecutorRelatorio("/relatorios/relatorioTicketsPorRotina.jasper", 
					this.response, parametros);
			
		
		if (getOpcao().equals("porcliente"))
			executor = new ExecutorRelatorio("/relatorios/relatorioTicketCliente.jasper", 
					this.response, parametros);
			
		
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
		
	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public BarChartModel getModeloBarra() {
		return modeloBarra;
	}

	public LineChartModel getModeloLinha() {
		return modeloLinha;
	}

	public PieChartModel getModeloPizza() {
		return modeloPizza;
	}

	public String getTipoGrafico() {
		return tipoGrafico;
	}

	public void setTipoGrafico(String tipoGrafico) {
		this.tipoGrafico = tipoGrafico;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public boolean isMostrarGraficoBarra() {
		return mostrarGraficoBarra;
	}

	public boolean isMostrarGraficoLinha() {
		return mostrarGraficoLinha;
	}

	public boolean isMostrarGraficoPizza() {
		return mostrarGraficoPizza;
	}
}
