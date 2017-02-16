package com.SiteGTS.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import com.SiteGTS.repository.Tickets;

@Named
@RequestScoped
public class GraficosBean {

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
	@Inject
	private Tickets tickets;

	private LineChartModel linha1;
	private Date dataInicial;
	private Date dataFinal;
	private CartesianChartModel model;
	private String opcao;

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

	public LineChartModel getLinha1() {
		return linha1;
	}

	public void preRender() {
		this.model = new CartesianChartModel();

		adicionarSerie("Tickets por mês");
	}

	private void adicionarSerie(String rotulo) {
		Map<Date, Long> resultado = this.tickets.ticketsPorMes(30);

		ChartSeries series = new ChartSeries(rotulo);

		for (Date data : resultado.keySet()) {
			series.set(DATE_FORMAT.format(data), resultado.get(data));
		}

		this.model.addSeries(series);

	}

	public CartesianChartModel getModel() {
		return model;
	}
}
