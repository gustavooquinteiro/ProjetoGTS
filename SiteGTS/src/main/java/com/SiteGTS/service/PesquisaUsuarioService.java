package com.SiteGTS.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.SiteGTS.model.Usuario;
import com.SiteGTS.repository.Usuarios;

public class PesquisaUsuarioService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios users;
	
	public List<Usuario> pesquisar(String nome) {
		return users.filtrar(nome);
	}

}
