package com.SiteGTS.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.SiteGTS.filter.ClienteFilter;
import com.SiteGTS.model.Cliente;
import com.SiteGTS.service.NegocioException;
import com.SiteGTS.util.jpa.Transactional;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Cliente guardar(Cliente cliente) {
		cliente = manager.merge(cliente);
		return cliente;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> filtrar(ClienteFilter filtro, String op) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);

		if (StringUtils.isNotBlank(filtro.getCnpj())) {
			criteria.add(Restrictions.ilike("cnpj", filtro.getCnpj(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getInscricao())) {
			criteria.add(Restrictions.ilike("inscricao", filtro.getInscricao(), MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(filtro.getRazaosocial())) {
			criteria.add(Restrictions.ilike("razaosocial", filtro.getRazaosocial().toUpperCase(), MatchMode.START));
		}
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.eq("nome", filtro.getNome()));
		}
		if (op.equals("status")) {
			criteria.add(Restrictions.eq("bloqueado", filtro.isStatus()));
		}
		return criteria.list();
	}

	public Cliente porId(Long id) {
		return manager.find(Cliente.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscar(String opcao, String nome) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);
		if (!opcao.equals("vazio")){
			criteria.add(Restrictions.ilike(opcao, nome.toUpperCase(), MatchMode.START));
		}else if (opcao.equals("cnpj")){
			criteria.add(Restrictions.eq(opcao, nome)); 
		}
		return criteria.list();
	}

	@Transactional
	public void remover(Cliente cliente) {
		try {
			cliente = porId(cliente.getId());
			manager.remove(cliente);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Cliente não pode ser excluido!");
		}

	}
}
