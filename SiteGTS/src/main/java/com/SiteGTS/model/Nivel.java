package com.SiteGTS.model;

public enum Nivel {
	BAIXO("Baixo"), MEDIO("Médio"), ALTO("Alto"), URGENTE("Urgente");
	private final String descricao;

	private Nivel(String nivel) {
		this.descricao = nivel;
	}

	public String getDescricao() {
		return descricao;
	}
}
