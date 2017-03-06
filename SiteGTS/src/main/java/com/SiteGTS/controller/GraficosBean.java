package com.SiteGTS.controller;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
public class GraficosBean implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public GraficosBean() {
		modeloBarra = new BarChartModel();
		modeloLinha = new LineChartModel();
		modeloPizza = new PieChartModel();
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

		if (tipoGrafico.equals("bar"))
			adicionarSerieEmBarra();

		if (tipoGrafico.equals("line"))
			adicionarSerieEmLinha();

		if (tipoGrafico.equals("pie"))
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

	public void preRender() {

	}

	private void adicionarSerieEmPizza() {
		this.modeloPizza = new PieChartModel();

		if (opcao.equals("porstatus")) {
			Map<String, Long> resultado = this.tickets.ticketsPorStatus(diasEntre);

			for (String data : resultado.keySet()) {
				modeloPizza.set(data.toString(), resultado.get(data));
			}

		}
		if (opcao.equals("portecnico")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorTecnico();

			for (String data : resultado.keySet()) {
				modeloPizza.set(data, resultado.get(data));
			}

		}
		if (opcao.equals("porrotina")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorRotina();

			for (String data : resultado.keySet()) {
				modeloPizza.set(data, resultado.get(data));
			}

		}
		if (opcao.equals("porcliente")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorCliente(diasEntre);

			for (String data : resultado.keySet()) {
				modeloPizza.set(data, resultado.get(data));
			}

		}

		modeloPizza.setLegendPosition("e");
		modeloPizza.setTitle(tipoDePesquisa());
		modeloPizza.setDiameter(1000);
		modeloPizza.setShowDataLabels(true);
		modeloPizza.setMouseoverHighlight(true);
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
			Map<String, Long> resultado = this.tickets.ticketsPorStatus(diasEntre);
			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}
		}
		if (opcao.equals("portecnico")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorTecnico();
			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}
		}
		if (opcao.equals("porrotina")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorRotina();
			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}
		}
		if (opcao.equals("porcliente")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorCliente(diasEntre);
			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}
		}

		this.modeloLinha.addSeries(series);
	}

	private void adicionarSerieEmBarra() {
		this.modeloBarra = new BarChartModel();
		ChartSeries series = new ChartSeries(tipoDePesquisa());
		modeloBarra.setLegendPosition("e");
		modeloBarra.setTitle(tipoDePesquisa());

		Axis yAxis = modeloBarra.getAxis(AxisType.Y);
		yAxis.setMin(0);

		if (opcao.equals("porstatus")) {
			Map<String, Long> resultado = this.tickets.ticketsPorStatus(diasEntre);

			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}

		}
		if (opcao.equals("portecnico")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorTecnico();

			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}

		}
		if (opcao.equals("porrotina")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorRotina();

			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}

		}
		if (opcao.equals("porcliente")) {
			Map<String, BigInteger> resultado = this.tickets.ticketsPorCliente(diasEntre);

			for (String data : resultado.keySet()) {
				series.set(data, resultado.get(data));
			}

		}
		modeloBarra.setLegendCols(diasEntre);
		this.modeloBarra.addSeries(series);
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
}
