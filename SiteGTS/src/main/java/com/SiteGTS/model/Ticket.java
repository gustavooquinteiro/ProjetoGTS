package com.SiteGTS.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	private int nivel;
	private boolean backupBanco;
	private String motivoBackup;
	private boolean alteracaoBanco;
	private String motivoAlteracao;
	private int status;
	private String tipoTicket;
	private String dataAbertura;
	private String dataFechamento;
	private Rotina rotina;

	public Ticket() {
		setDataAbertura(retornaDataAtual());
	}

	public String retornaDataAtual() {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String dataCadastro = sdf.format(data);
		return dataCadastro;
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

	public String getDataAbertura() {
		return this.dataAbertura;
	}

	public void setDataAbertura(String dataTicket) {
		this.dataAbertura = dataTicket;
	}

	public String getDataFechamento() {
		return this.dataFechamento;
	}

	public void setDataFechamento(String dataFechamento) {
		if (this.status == 2)
			this.dataFechamento = retornaDataAtual();
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

	public Rotina getRotina() {
		return rotina;
	}

	public void setRotina(Rotina rotina) {
		this.rotina = rotina;
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
