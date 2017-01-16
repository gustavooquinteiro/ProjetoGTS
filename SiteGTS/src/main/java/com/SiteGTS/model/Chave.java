package com.SiteGTS.model;

import java.util.Date;

public class Chave {

	private Long idcliente;
	private String chave;
	private Long iduser;
	private Date datachave;

	public Long getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Long idcliente) {
		this.idcliente = idcliente;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Long getIduser() {
		return iduser;
	}

	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}

	public Date getDatachave() {
		return datachave;
	}

	public void setDatachave(Date datachave) {
		this.datachave = datachave;
	}

}
