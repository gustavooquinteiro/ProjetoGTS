package com.SiteGTS.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.SiteGTS.model.Nivel;
import com.SiteGTS.model.Status;

@Named
@ViewScoped
public class DataBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> statuses = new ArrayList<>();
	private List<String> nivels = new ArrayList<>();

	public String getanoAtual() {
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anoAtual = sdf.format(data);
		return anoAtual;
	}

	public Status[] getStatus() {
		statuses.add(Status.ABERTO.descricao());
		statuses.add(Status.EM_ANDAMENTO.descricao());
		statuses.add(Status.FECHADO.descricao());
		return Status.values();
	}

	public Nivel[] getNivel() {
		nivels.add(Nivel.BAIXO.getDescricao());
		nivels.add(Nivel.MEDIO.getDescricao());
		nivels.add(Nivel.ALTO.getDescricao());
		nivels.add(Nivel.URGENTE.getDescricao());
		return Nivel.values();
	}
}
