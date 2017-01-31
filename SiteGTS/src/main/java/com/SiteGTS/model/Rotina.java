package com.SiteGTS.model;

import java.io.Serializable;

public class Rotina implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String funcao;

	public Rotina() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

}
