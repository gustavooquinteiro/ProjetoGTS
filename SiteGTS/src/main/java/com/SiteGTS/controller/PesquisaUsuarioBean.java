package com.SiteGTS.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.SiteGTS.model.Usuario;
import com.SiteGTS.repository.Usuarios;
import com.SiteGTS.service.PesquisaUsuarioService;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios users;

	private String nome;
	private List<Usuario> usuariosfiltrados;
	private Usuario usuarioSelecionado;

	@Inject
	private PesquisaUsuarioService pus;

	public PesquisaUsuarioBean() {

		usuariosfiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		usuariosfiltrados = pus.pesquisar(nome);

	}

	public void excluir() {
		users.remover(usuarioSelecionado);
		usuariosfiltrados.remove(usuarioSelecionado);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Usuario> getUsuariosfiltrados() {
		return usuariosfiltrados;
	}

	public void setUsuariosfiltrados(List<Usuario> usuariosfiltrados) {
		this.usuariosfiltrados = usuariosfiltrados;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

}
