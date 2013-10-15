package br.com.guedes.sistemacomercial.dao;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.util.BusinessException;

/**
 * 
 * @author AndréLessa
 *
 */
public class UsuarioDaoImpl extends HibernateDaoSupport implements UsuarioDao {

	private static final Logger LOGGER = Logger.getLogger(UsuarioDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.sistemacomercial.guedes.dao.UsuarioDao#efetuarLogin(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Usuario efetuarLogin(final String login, final String senha) throws BusinessException {
		LOGGER.debug("Efetuando Login...");
		
		StringBuilder hql = new StringBuilder();
		hql.append("from Usuario ");
		hql.append(" where usuLogin = '" + login + "'");
		hql.append(" and usuSenha = '" + senha + "'");
		
		ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) getHibernateTemplate().find(hql.toString());
		
		if(listaUsuarios != null && !listaUsuarios.isEmpty()) {
			
			return listaUsuarios.get(0);
		}
		
		throw new BusinessException("Login ou senha inválidos.");
	}
}
