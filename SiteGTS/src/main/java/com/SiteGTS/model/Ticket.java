package com.SiteGTS.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tabelatickets")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
@Column(name="solicitante_nome")
	private String solicitante;

	private int atendente;
	private int tecnico;
	@Column(name="descricao_problema")
	private String problema;
	@Column(name="descricao_solucao")
	private String solucao;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	private int nivel;
	private boolean backupBanco;
	private String motivoBackup;
	private boolean alteracaoBanco;
	private String motivoAlteracao;
	private int status;

	@Column(name = "tipo")
	private int tipoTicket;
	@Column(name = "data_abertura")
	private Date dataAbertura;

	private Date dataFechamento;

	private int software;

	private int rotina;

	public Ticket() {

	}

	public Date retornaDataAtual() {
		Date date = new Date();
		return date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSoftware() {
		return software;
	}

	public void setSoftware(int software) {
		this.software = software;
	}

	public int getRotina(){
		return rotina;
	}
	
	public void setRotina(int rotina) {
		this.rotina = rotina;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante.toUpperCase();
	}

	public int getAtendente() {
		return atendente;
	}

	public void setAtendente(int atendente) {
		this.atendente = atendente;
	}

	public int getTecnico() {
		return tecnico;
	}

	public void setTecnico(int tecnico) {
		this.tecnico = tecnico;
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

	public int getTipoTicket() {
		return tipoTicket;
	}

	public void setTipoTicket(int tipoTicket) {
		this.tipoTicket = tipoTicket;
	}

	public Date getDataAbertura() {
		return this.dataAbertura;
	}

	public void setDataAbertura(Date dataTicket) {
		this.dataAbertura = dataTicket;
	}

	public Date getDataFechamento() {
		return this.dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescricaoNivel() {
		String descricao = "";
		if (this.nivel == 0)
			descricao = "BAIXO";
		if (this.nivel == 1)
			descricao = "MÉDIO";
		if (this.nivel == 2)
			descricao = "ALTO";
		if (this.nivel == 3)
			descricao = "URGENTE";

		return descricao;
	}

	public String getDescricaoStatus() {
		String descricao = "";
		if (this.status == 0)
			descricao = "ABERTO";
		if (this.status == 1)
			descricao = "EM ANDAMENTO";
		if (this.status == 2)
			descricao = "FECHADO";
		return descricao;
	}

}
