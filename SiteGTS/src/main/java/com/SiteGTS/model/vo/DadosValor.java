package com.SiteGTS.model.vo;

import java.io.Serializable;

public class DadosValor implements Serializable {

	private static final long serialVersionUID = 1L;
	private int status;
	private Long valor;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

}
