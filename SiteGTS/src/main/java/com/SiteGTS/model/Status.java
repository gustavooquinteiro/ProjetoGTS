package com.SiteGTS.model;

public enum Status {
	ABERTO("Aberto"), EM_ANDAMENTO("Em andamento"), FECHADO("Fechado");
	private final String descricao;

	private Status(String status) {
		this.descricao = status;
	}

	public String descricao() {
		return this.descricao;
	}
}
