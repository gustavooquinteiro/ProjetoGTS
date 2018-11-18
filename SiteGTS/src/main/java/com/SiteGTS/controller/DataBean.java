package com.SiteGTS.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class DataBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getanoAtual() {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anoAtual = sdf.format(data);
		return anoAtual;
	}
}
