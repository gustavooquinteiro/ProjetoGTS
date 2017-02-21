package com.SiteGTS.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tabelatickets")
public class TicketGTS implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	private Date data_abertura;

	private int empresa;
	private String solicitante_nome;
	private int solicitante_telefone;
	private int atendente;
	private int tecnico;
	private String descricao_problema;
	private String descricao_solucao;
	private int software;
	private int rotina;

	private int nivel;
	private int status;
	private int tipo;
	private Date dataFechamento;
	private int cliente_id;

	public TicketGTS() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData_abertura() {
		return data_abertura;
	}

	public void setData_abertura(Date data_abertura) {
		this.data_abertura = data_abertura;
	}

	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	public String getSolicitante_nome() {
		return solicitante_nome;
	}

	public void setSolicitante_nome(String solicitante_nome) {
		this.solicitante_nome = solicitante_nome;
	}

	public int getSolicitante_telefone() {
		return solicitante_telefone;
	}

	public void setSolicitante_telefone(int solicitante_telefone) {
		this.solicitante_telefone = solicitante_telefone;
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

	public String getDescricao_problema() {
		return descricao_problema;
	}

	public void setDescricao_problema(String descricao_problema) {
		this.descricao_problema = descricao_problema;
	}

	public String getDescricao_solucao() {
		return descricao_solucao;
	}

	public void setDescricao_solucao(String descricao_solucao) {
		this.descricao_solucao = descricao_solucao;
	}

	public int getSoftware() {
		return software;
	}

	public void setSoftware(int software) {
		this.software = software;
	}

	public int getRotina() {
		return rotina;
	}

	public void setRotina(int rotina) {
		this.rotina = rotina;
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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Date getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public int getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}

}
