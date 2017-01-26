package com.SiteGTS.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.SiteGTS.filter.ClienteFilter;
import com.SiteGTS.model.Cliente;
import com.SiteGTS.repository.Clientes;

public class PesquisaClienteService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes; 
	private ClienteFilter filtro; 
	
	
	private ClienteFilter pesquisarPor(String opcao, String pesquisa){
		if(opcao.equals("vazio")){
			filtro = new ClienteFilter(); 
		}
		if (opcao.equals("cnpj")) {
			filtro = new ClienteFilter();
			filtro.setCnpj(pesquisa);
		}
		if (opcao.equals("razaosocial")) {
			filtro = new ClienteFilter();
			filtro.setRazaosocial(pesquisa);
		}
		if (opcao.equals("inscricao")) {
			filtro = new ClienteFilter();
			filtro.setInscricao(pesquisa);
		}
		if (opcao.equals("status")) {
			if (pesquisa.startsWith("A") || pesquisa.startsWith("a")) {
				filtro = new ClienteFilter();
				filtro.setStatus(false);
			}
			if (pesquisa.startsWith("B")|| pesquisa.startsWith("b")) {
				filtro = new ClienteFilter();
				filtro.setStatus(true);
			}
		}
		if(opcao.equals("nomefantasia")){
			filtro = new ClienteFilter();
			filtro.setNome(pesquisa);
		}
		return filtro;
		
	}

	public List<Cliente> pesquisar (String op, ClienteFilter filtro, String pesquisa){
		filtro = pesquisarPor(op, pesquisa); 
		return clientes.filtrar(filtro, op);
		
	}
}
