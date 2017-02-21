package com.SiteGTS.controller;

import java.math.BigInteger;
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
import com.SiteGTS.util.jsf.FacesUtil;

@Named
@RequestScoped
public class GraficosBean{

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");

	//private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
	@Inject
	private Tickets tickets;

	private DateTime dataInicial;
	private DateTime dataFinal;

	private BarChartModel modeloBarra = new BarChartModel();
	private LineChartModel modeloLinha = new LineChartModel();
	private PieChartModel modeloPizza = new PieChartModel();

	private String opcao;
	private String rotulo;
	private String tipoGrafico;
	private int diasEntre = 30;

	/*public void consultarGraficos() {
		if (getDataInicial() != null && getDataFinal() != null) {
			diasEntre = Days.daysBetween(dataInicial, dataFinal).getDays();
			FacesUtil.addWarnMessage("Insira duas datas!");
			
		}

		if (tipoGrafico.equals("vazio"))
			FacesUtil.addWarnMessage("Defina um tipo de gráfico para exibição!");

		if (tipoGrafico.equals("bar"))
			adicionarSerieEmBarra();

		if (tipoGrafico.equals("line"))
			adicionarSerieEmLinha();

		if (tipoGrafico.equals("pie"))
			adicionarSerieEmPizza();
	}*/

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

	
	public void preRender(){
		this.modeloBarra = new BarChartModel();  
		diasEntre = 30; 
		Map<String, BigInteger> resultado = this.tickets.ticketsPorRotina();
		ChartSeries series = new ChartSeries("Tickets por Status");
		
		
		modeloBarra.setLegendPosition("e");
		modeloBarra.setTitle("Tickets por Status");

		Axis yAxis = modeloBarra.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(110);
		
		for (String data : resultado.keySet()) {
			series.set(data, resultado.get(data));
		}
	 
		this.modeloBarra.addSeries(series);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void adicionarSerieEmPizza() {
		// TODO Auto-generated method stub
		this.modeloPizza = new PieChartModel();

	}

	private void adicionarSerieEmLinha() {
		// TODO Auto-generated method stub
		this.modeloLinha = new LineChartModel();
	}

	public void geraGrafico() {
		if (getDataInicial() != null && getDataFinal() != null) {
			diasEntre = Days.daysBetween(dataInicial, dataFinal).getDays();
			FacesUtil.addWarnMessage("Insira duas datas!");
		}

		if (tipoGrafico.equals("vazio"))
			FacesUtil.addWarnMessage("Defina um tipo de gráfico para exibição!");

		if (tipoGrafico.equals("bar"))
			adicionarSerieEmBarra();

		if (tipoGrafico.equals("line"))
			adicionarSerieEmLinha();

		if (tipoGrafico.equals("pie"))
			adicionarSerieEmPizza();

	}

	private void adicionarSerieEmBarra() {
		Map<Date, Long> resultado = this.tickets.ticketsPorMes(diasEntre);
		ChartSeries series = new ChartSeries(tipoDePesquisa());

	

		for (Date data : resultado.keySet()) {
			series.set(data, resultado.get(data));
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
