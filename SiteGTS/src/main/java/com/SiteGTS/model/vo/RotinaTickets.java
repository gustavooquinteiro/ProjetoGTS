package com.SiteGTS.model.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class RotinaTickets implements Serializable {

	private static final long serialVersionUID = 1L;

	private String rotina;
	private BigInteger quantidade;

	public String getRotina() {
		return rotina;
	}

	public void setRotina(String rotina) {
		this.rotina = rotina;
	}

	public BigInteger getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigInteger quantidade) {
		this.quantidade = quantidade;
	}

}
