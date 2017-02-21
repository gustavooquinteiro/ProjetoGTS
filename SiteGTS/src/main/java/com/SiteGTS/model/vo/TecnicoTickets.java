package com.SiteGTS.model.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class TecnicoTickets implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private BigInteger quantidade;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigInteger getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigInteger quantidade) {
		this.quantidade = quantidade;
	}

}
