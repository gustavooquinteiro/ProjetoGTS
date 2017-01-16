package com.SiteGTS.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.SiteGTS.model.Cliente;
import com.SiteGTS.repository.Clientes;
import com.SiteGTS.util.jpa.Transactional;

public class CadastroClienteService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		return clientes.guardar(cliente); 	
	}
}

