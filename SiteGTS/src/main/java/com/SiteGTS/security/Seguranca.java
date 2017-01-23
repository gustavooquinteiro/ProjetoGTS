package com.SiteGTS.security;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.SiteGTS.model.Usuario;
import com.SiteGTS.repository.Usuarios;

@Named
@RequestScoped
public class Seguranca {

	@Inject
	private ExternalContext externalContext;

	public String getNomeUsuario() {
		String nome = null;

		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}

		return nome;
	}

	public Long getIdUsuario() {
		Long id = null;
		UsuarioSistema user = getUsuarioLogado();
		if (user != null) {
			id = user.getUsuario().getId();
		}
		return id;
	}

	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;

		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		return usuario;
	}

	public boolean isPermitidoExclusaoCliente() {
		return externalContext.isUserInRole("ADMINISTRADOR");
	}

	public boolean isPermitidoExclusaoUsuario() {
		return externalContext.isUserInRole("ADMINISTRADOR");
	}

	public boolean isPermitidoAcesso() {
		return externalContext.isUserInRole("ADMINISTRADOR");
	}
}
