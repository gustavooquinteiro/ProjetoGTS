package com.SiteGTS.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.SiteGTS.model.Cliente;
import com.SiteGTS.service.CadastroClienteService;
import com.SiteGTS.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	@Inject
	private CadastroClienteService ccs;

	public CadastroClienteBean() {
		limpar();
	}

	private void limpar() {
		cliente = new Cliente();
	}

	public void salvar() {
		ccs.salvar(cliente);
		limpar();
		FacesUtil.addInfoMessage("Cadastro feito com sucesso!!");
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		}
}
