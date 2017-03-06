package com.SiteGTS.model.vo;

import java.io.Serializable;

public class DadosValor implements Serializable {

	private static final long serialVersionUID = 1L;
	private String status;
	private Long quantidade;

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

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}
