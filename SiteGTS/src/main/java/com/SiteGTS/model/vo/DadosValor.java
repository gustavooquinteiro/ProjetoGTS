package com.SiteGTS.model.vo;

import java.io.Serializable;

public class DadosValor implements Serializable {

	private static final long serialVersionUID = 1L;
	private int status;
	private Long quantidade;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	

}
