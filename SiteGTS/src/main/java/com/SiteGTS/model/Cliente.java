package com.SiteGTS.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true, nullable = false)
	@CNPJ
	private String cnpj;

	@Column
	private String inscricao;

	@Column
	private String nome;

	@Column
	private String razaosocial;

	@Transient
	private Long idcidade;

	@Column
	private String nomecidade;

	@Column
	private String uf;

	@Column
	private String cep;

	private String bairro;
	private String numero;
	private String endereco;
	private String fone1;
	private String fone2;

	@Email
	private String email1;

	@Email
	private String email2;

	private String site;
	private String numserie;
	private int numlicenca;
	private String datacadastro;
	private boolean bloqueado;
	private int diaschave;
	private String sistema;
	private boolean contrato;

	@OneToMany(mappedBy = "cliente", targetEntity = Ticket.class,
				fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ticket> ticket;

	public Cliente() {
		setDiaschave(30);
		setNumlicenca(1);
		setDatacadastro(retornaDataAtual());
	}

	private String retornaDataAtual() {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataCadastro = sdf.format(data);
		return dataCadastro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj.replaceAll("[0-9]^", "");
	}

	public String getInscricao() {
		return inscricao;
	}

	public void setInscricao(String inscricao) {
		if (inscricao == null || inscricao == "") {
			this.inscricao = "ISENTO";
		} else {
			this.inscricao = inscricao.toUpperCase();
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial.toUpperCase();
	}

	public Long getIdcidade() {
		return idcidade;
	}

	public void setIdcidade(Long idcidade) {
		this.idcidade = idcidade;
	}

	public String getNomecidade() {
		return nomecidade;
	}

	public void setNomecidade(String nomecidade) {
		this.nomecidade = nomecidade.toUpperCase();
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep.replaceAll("[0-9]^", "");
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro.toUpperCase();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getFone1() {
		return fone1;
	}

	public void setFone1(String fone1) {
		this.fone1 = fone1.replaceAll("[0-9]^", "");
	}

	public String getFone2() {
		return fone2;
	}

	public void setFone2(String fone2) {
		this.fone2 = fone2.replaceAll("[0-9]^", "");
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1.toLowerCase();
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2.toLowerCase();
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site.toLowerCase();
	}

	public String getNumserie() {
		return numserie;
	}

	public void setNumserie(String numserie) {
		this.numserie = numserie;
	}

	public int getNumlicenca() {
		return numlicenca;
	}

	public void setNumlicenca(int numlicenca) {
		this.numlicenca = numlicenca;
	}

	public String getDatacadastro() {
		return datacadastro;
	}

	public void setDatacadastro(String datacadastro) {
		this.datacadastro = datacadastro;
	}

	public boolean isBloqueado() {
		return this.bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public int getDiaschave() {
		return diaschave;
	}

	public void setDiaschave(int diaschave) {
		this.diaschave = diaschave;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public boolean isContrato() {
		return contrato;
	}

	public void setContrato(boolean contrato) {
		this.contrato = contrato;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco.toUpperCase();
	}

	public List<Ticket> getTicket() {
		return ticket;
	}

	public void setTicket(List<Ticket> ticket) {
		this.ticket = ticket;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
