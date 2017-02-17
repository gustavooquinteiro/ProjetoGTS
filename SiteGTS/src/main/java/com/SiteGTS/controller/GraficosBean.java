package com.SiteGTS.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import com.SiteGTS.repository.Tickets;

@Named
@RequestScoped
public class GraficosBean {

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");

	@Inject
	private Tickets tickets;

	private DateTime dataInicial;
	private DateTime dataFinal;
	private BarChartModel modeloBarra;
	private LineChartModel modeloLinha;
	private PieChartModel modeloPizza;
	private String opcao;
	private String rotulo;
	private String tipoGrafico;
	private int diasEntre = 30;

	public void consultarGraficos() {
		diasEntre = Days.daysBetween(dataInicial, dataFinal).getDays();

		if (tipoGrafico.equals("bar")) {
			adicionarSerieEmBarra();
		}
		if (tipoGrafico.equals("line")) {
			adicionarSerieEmLinha(); 
		}
		if (tipoGrafico.equals("pie")) {
			adicionarSerieEmPizza();
		}

		
	}

	private String tipoDePesquisa() {
		if (opcao.equals("porstatus")){
			this.rotulo = "Tickets por Status";
			
			
		}
		if (opcao.equals("portecnico")){
			this.rotulo = "Tickets por Técnico";
		}
			
		if (opcao.equals("porrotina")){
			this.rotulo = "Tickets por rotina";
		}
			
		if (opcao.equals("porcliente")){
			this.rotulo = "Tickets por Cliente"; 
			
		}
			
		return rotulo; 
	}

	private void adicionarSerieEmPizza() {
		// TODO Auto-generated method stub
		
	}

	private void adicionarSerieEmLinha() {
		// TODO Auto-generated method stub
		
	}

	public void preRender() {
		this.modeloBarra = new BarChartModel();
		/*this.modeloLinha = new LineChartModel();
		this.modeloPizza = new PieChartModel();*/

		adicionarSerieEmBarra();

	}

	private void adicionarSerieEmBarra() {
		Map<Integer, Long> resultado = this.tickets.ticketsPorStatus(diasEntre);

		ChartSeries series = new ChartSeries(tipoDePesquisa());

		modeloBarra.setLegendPosition("e");
		modeloBarra.setTitle(tipoDePesquisa());

		Axis yAxis = modeloBarra.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);

		for (Integer nome : resultado.keySet()) {
			series.set(resultado.keySet(), resultado.get(nome));
		}

		modeloBarra.addSeries(series);
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public DateTime getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(DateTime dataInicial) {
		this.dataInicial = dataInicial;
	}

	public DateTime getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(DateTime dataFinal) {
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
}
