package com.SiteGTS.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.SiteGTS.filter.ClienteFilter;
import com.SiteGTS.model.Cliente;
import com.SiteGTS.repository.Clientes;
import com.SiteGTS.service.PesquisaClienteService;

@Named
@ViewScoped
public class PesquisaClienteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;

	private String opcao;
	private String pesquisa;
	
	private ClienteFilter filtro;
	private List<Cliente> clientesfiltrados;
	private Cliente clienteSelecionado;
	
	@Inject
	private PesquisaClienteService pcs;

	public PesquisaClienteBean() {
		filtro = new ClienteFilter();
		clientesfiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		clientesfiltrados = pcs.pesquisar(opcao, filtro, pesquisa); 

	}

	public void excluir() {
		clientes.remover(clienteSelecionado);
		clientesfiltrados.remove(clienteSelecionado); 
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ClienteFilter filtro) {
		this.filtro = filtro;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public String getOpcao() {
		return opcao;
	}

	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}

	public List<Cliente> getClientesfiltrados() {
		return clientesfiltrados;
	}

	public void setClientesfiltrados(List<Cliente> clientesfiltrados) {
		this.clientesfiltrados = clientesfiltrados;
	}

}
