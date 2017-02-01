package com.SiteGTS.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;

	private String solicitante;
	private String atendente;
	private String tecnico;
	private String problema;
	private String solucao;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	private Nivel nivel;
	private boolean backupBanco;
	private String motivoBackup;
	private boolean alteracaoBanco;
	private String motivoAlteracao;
	private Status status;
	private String tipoTicket;
	private Calendar dataAbertura;
	private Calendar dataFechamento;
	private Rotina rotina;

	public Ticket() {
		setDataAbertura(retornaDataAtual());
	}

	public Calendar retornaDataAtual() {
		Calendar diaAtual = Calendar.getInstance();
		return diaAtual;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante.toUpperCase();
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente.toUpperCase();
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico.toUpperCase();
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema.toUpperCase();
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao.toUpperCase();
	}

	public boolean isBackupBanco() {
		return backupBanco;
	}

	public void setBackupBanco(boolean backupBanco) {
		this.backupBanco = backupBanco;
	}

	public String getMotivoBackup() {
		return motivoBackup;
	}

	public void setMotivoBackup(String motivoBackup) {
		this.motivoBackup = motivoBackup.toUpperCase();
	}

	public boolean isAlteracaoBanco() {
		return alteracaoBanco;
	}

	public void setAlteracaoBanco(boolean alteracaoBanco) {
		this.alteracaoBanco = alteracaoBanco;
	}

	public String getMotivoAlteracao() {
		return motivoAlteracao;
	}

	public void setMotivoAlteracao(String motivoAlteracao) {
		this.motivoAlteracao = motivoAlteracao.toUpperCase();
	}

	public String getTipoTicket() {
		return tipoTicket;
	}

	public void setTipoTicket(String tipoTicket) {
		this.tipoTicket = tipoTicket.toUpperCase();
	}

	public Calendar getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(Calendar dataTicket) {
		this.dataAbertura = dataTicket;
	}

	public Calendar getDataFechamento() {
		if (this.status.equals(Status.FECHADO)) {
			setDataFechamento(retornaDataAtual());
		}
		return dataFechamento;
	}

	public void setDataFechamento(Calendar dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Rotina getRotina() {
		return rotina;
	}

	public void setRotina(Rotina rotina) {
		this.rotina = rotina;
	}

	public String retornaDataString() {
		DateFormat dataHora = DateFormat.getDateTimeInstance();
		return dataHora.format(this.getDataAbertura());
	}
}
