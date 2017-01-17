package com.SiteGTS.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.SiteGTS.model.Grupo;
import com.SiteGTS.model.Usuario;
import com.SiteGTS.repository.Grupos;
import com.SiteGTS.service.CadastroUsuarioService;
import com.SiteGTS.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService cus;

	private Usuario user;

	private Grupo[] gruposSelecionados;

	private List<Grupo> grupos;

/*	private List<String> gruposString;
*/
	private List<String> gruposString;
	@Inject
	private Grupos usuarios;

	public CadastroUsuarioBean() {
		limpar();
	}

	private void limpar() {
		user = new Usuario();
	}

	public void salvar() {
		cus.salvar(user);
		limpar();
		FacesUtil.addInfoMessage("Cadastro feito com sucesso!!");
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Grupo[] getGruposSelecionados() {
		return gruposSelecionados;
	}

	public void setGruposSelecionados(Grupo[] gruposSelecionados) {
		this.gruposSelecionados = gruposSelecionados;
	}

	public List<Grupo> getGrupos() {
		grupos = usuarios.buscar(); 
		return grupos; 
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

}