package com.SiteGTS.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.SiteGTS.model.Grupo;
import com.SiteGTS.model.Usuario;
import com.SiteGTS.repository.Usuarios;
import com.SiteGTS.service.CadastroUsuarioService;
import com.SiteGTS.util.jsf.FacesUtil;

@Named
@SessionScoped
public class EdicaoUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CadastroUsuarioService cus;

	private Usuario user;

	private Grupo grupo;

	private List<Usuario> users;
	
	@Inject
	private Usuarios grupouser; 
	
	public EdicaoUsuarioBean() {
		limpar();
	}

	private void limpar() {
		user = new Usuario();
	}

	public void salvar() {		
		cus.salvar(user);
		FacesUtil.addInfoMessage("Alterações da configuração de conta realizada com sucesso!");
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		
		user.setGrupo(grupouser.porId(user.getId()).getGrupo());
		this.user = user;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		grupo.setUsuarios(user); 
		this.grupo = grupo;
	}

	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
	}
}