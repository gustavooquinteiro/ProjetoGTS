package com.SiteGTS.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
	
	@OneToOne (mappedBy = "ticket")		
	private Cliente cliente;
	
	private String nivel;
	private boolean backupBanco;
	private String motivoBackup;
	private boolean alteracaoBanco;
	private String motivoAlteracao;
	private String status;
	private String tipoTicket;
	private String dataTicket; 
	private String rotina; 

	public Ticket (){
		setDataTicket(retornaDataAtual());
	}
	
	private String retornaDataAtual() {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataCadastroTicket = sdf.format(data);
		return dataCadastroTicket;
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
		this.solicitante = solicitante;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

	public String getProblema() {
		return problema;
	}

	public void setProblema(String problema) {
		this.problema = problema;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
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
		this.motivoBackup = motivoBackup;
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
		this.motivoAlteracao = motivoAlteracao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipoTicket() {
		return tipoTicket;
	}

	public void setTipoTicket(String tipoTicket) {
		this.tipoTicket = tipoTicket;
	}

	public String getDataTicket() {
		return dataTicket;
	}

	public void setDataTicket(String dataTicket) {
		this.dataTicket = dataTicket;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getRotina() {
		return rotina;
	}

	public void setRotina(String rotina) {
		this.rotina = rotina;
	}
	
}
