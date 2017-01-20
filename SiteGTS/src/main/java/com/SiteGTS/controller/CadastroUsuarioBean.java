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

	private List<Grupo> grupos;

	private Grupo grupo;

	private List<Usuario> users;

	@Inject
	private Grupos usergroup;

	public CadastroUsuarioBean() {
		limpar();
	}

	private void limpar() {
		user = new Usuario();
	}

	public void salvar() {
		grupo.setUsuarios(user);
		user.setGrupo(grupo);

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

	public List<Grupo> getGrupos() {
		grupos = usergroup.buscar();
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
	}
}