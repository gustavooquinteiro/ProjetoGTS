package com.SiteGTS.model.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class DadosValor implements Serializable {

	private static final long serialVersionUID = 1L;
	private String status;
	private BigInteger quantidade;

	public String getStatus() {
		return status;
	}

	public void setStatus(int status) {
		if (status == 1)
			this.status = "Aberto";
		if (status == 2)
			this.status = "Em andamento";
		if (status == 3)
			this.status = "Fechado";
	}

	public BigInteger getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigInteger quantidade) {
		this.quantidade = quantidade;
	}

}
