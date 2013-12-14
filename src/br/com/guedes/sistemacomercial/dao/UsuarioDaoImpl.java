package br.com.guedes.sistemacomercial.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemacomercial.model.Funcionalidade;
import br.com.guedes.sistemacomercial.model.Perfil;
import br.com.guedes.sistemacomercial.model.PerfilFuncionalidade;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

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
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.UsuarioDao#listaFuncionalidadesPorPerfil(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	@SuppressWarnings("unchecked")
	public List<PerfilFuncionalidade> listaFuncionalidadesPorPerfil(final Perfil perfil) throws BusinessException, IntegrationException {
		try {
			LOGGER.info("Obtendo lista de funcionalidades por perfil.");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from PerfilFuncionalidade where perfil.perCodigo = " + perfil.getPerCodigo());
			
			return getHibernateTemplate().findByValueBean(hql.toString(), new PerfilFuncionalidade());
		} catch (Exception e) {
			LOGGER.error("Erro ao obter lista de funcionalidades por perfil.", e);
			throw new IntegrationException("Erro ao obter lista de funcionalidades por perfil.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.UsuarioDao#listaFuncionalidadesNotExistsPerfil(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	@SuppressWarnings("unchecked")
	public List<Funcionalidade> listaFuncionalidadesNotExistsPerfil(final Perfil perfil) throws BusinessException, IntegrationException {
		try {
			LOGGER.info("Obtendo lista de funcionalidades por perfil.");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from Funcionalidade where not exists (select funcionalidade.funCodigo from PerfilFuncionalidade ");
			hql.append("where funcionalidade.funCodigo = funcionalidade.funCodigo and perfil.perCodigo = " + perfil.getPerCodigo() + ")");
			
			return getHibernateTemplate().findByValueBean(hql.toString(), new Funcionalidade());
		} catch (Exception e) {
			LOGGER.error("Erro ao obter lista de funcionalidades que não existe no perfil.", e);
			throw new IntegrationException("Erro ao obter lista de funcionalidades que não existe no perfil.", e);
		}
	}	
}
