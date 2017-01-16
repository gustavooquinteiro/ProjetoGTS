package com.SiteGTS.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.SiteGTS.model.Usuario;
import com.SiteGTS.repository.Usuarios;
import com.SiteGTS.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios clientes;
	
	@Transactional
	public Usuario salvar(Usuario cliente) {
		return clientes.guardar(cliente); 	
	}
}

