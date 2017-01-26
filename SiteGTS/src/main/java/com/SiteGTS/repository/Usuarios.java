package com.SiteGTS.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.SiteGTS.model.Grupo;
import com.SiteGTS.model.Usuario;
import com.SiteGTS.service.NegocioException;
import com.SiteGTS.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Usuario porId(Long id) {
		return manager.find(Usuario.class, id);
	}

	public Usuario guardar(Usuario user) {
		user = manager.merge(user);
		return user;
	}

	@Transactional
	public void remover(Usuario usuario) {
		try {
			usuario = porId(usuario.getId());
			manager.remove(usuario);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Usuário não pode ser excluído!");
		}

	}

	@SuppressWarnings("unchecked")
	public List<Usuario> filtrar(String nome) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);

		if (StringUtils.isNotBlank(nome)) {
			criteria.add(Restrictions.ilike("nome", nome, MatchMode.ANYWHERE));
		}

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscar() {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Usuario.class);
		return criteria.list();
	}

	public Usuario porEmail(String email) {
		Usuario usuario = null;
		try {
			usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuario encontrado com o e-mail informado
		}
		return usuario;
	}

}
