package br.com.guedes.sistemacomercial.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemacomercial.model.Funcionalidade;
import br.com.guedes.sistemacomercial.model.Perfil;
import br.com.guedes.sistemacomercial.model.PerfilFuncionalidade;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.model.UsuarioPerfil;
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
	 * @see br.com.guedes.sistemacomercial.dao.UsuarioDao#listaPerfisPorUsuario(br.com.guedes.sistemacomercial.model.Usuario)
	 */
	@SuppressWarnings("unchecked")
	public List<UsuarioPerfil> listaPerfisPorUsuario(final Usuario usuario) throws BusinessException, IntegrationException {
		try {
			LOGGER.info("Obtendo lista de perfis por usuário.");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from UsuarioPerfil where usuario.usuCodigo = " + usuario.getUsuCodigo());
			
			return getHibernateTemplate().findByValueBean(hql.toString(), new UsuarioPerfil());
		} catch (Exception e) {
			LOGGER.error("Erro ao obter lista de perfis por usuário.", e);
			throw new IntegrationException("Erro ao obter lista de perfis por usuário.", e);
		}
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
			
			hql.append("from Funcionalidade F where not exists (select PF.funcionalidade.funCodigo from PerfilFuncionalidade PF ");
			hql.append("where PF.funcionalidade.funCodigo = F.funCodigo and PF.perfil.perCodigo = " + perfil.getPerCodigo() + ")");
			
			return getHibernateTemplate().findByValueBean(hql.toString(), new Funcionalidade());
		} catch (Exception e) {
			LOGGER.error("Erro ao obter lista de funcionalidades que não existe no perfil.", e);
			throw new IntegrationException("Erro ao obter lista de funcionalidades que não existe no perfil.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.UsuarioDao#isPerfilUsadoUsuario(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	@SuppressWarnings("unchecked")
	public boolean isPerfilUsadoUsuario(final Perfil perfil) throws BusinessException, IntegrationException {
		try {
			LOGGER.info("Verificando se o perfil está sendo usado por algum Usuário.");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from Perfil P where exists (select UP.perfil.perCodigo from UsuarioPerfil UP ");
			hql.append("where UP.perfil.perCodigo = P.perCodigo and UP.perfil.perCodigo = " + perfil.getPerCodigo() + ")");
			
			List<Perfil> lista = getHibernateTemplate().findByValueBean(hql.toString(), new Perfil());
			if (lista == null || lista.isEmpty()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			LOGGER.error("Erro ao verificar se o perfil está sendo usado por algum Usuário.", e);
			throw new IntegrationException("Erro ao verificar se o perfil está sendo usado por algum Usuário.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.UsuarioDao#deletarFuncionalidadesPorPerfil(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	public void deletarFuncionalidadesPorPerfil(final Perfil perfil) throws IntegrationException {
		try {
			LOGGER.info("Deletando as funcionalidades por Perfil.");
			StringBuilder hql = new StringBuilder();
			
			hql.append("delete from PerfilFuncionalidade PF where PF.perfil.perCodigo = " + perfil.getPerCodigo());
			
			getSession().createQuery(hql.toString());
		} catch (Exception e) {
			LOGGER.error("Erro na tentativa de deletar as funcionalidades por Perfil.", e);
			throw new IntegrationException("Erro na tentativa de deletar as funcionalidades por Perfil.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.UsuarioDao#buscarPerfilPorId(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	@SuppressWarnings("unchecked")
	public Perfil buscarPerfilPorId(final Perfil perfil) throws BusinessException, IntegrationException {
		try {
			LOGGER.info("Obtendo perfil por ID.");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from Perfil F where F.perCodigo = " + perfil.getPerCodigo());
			
			List<Perfil> lista = getHibernateTemplate().findByValueBean(hql.toString(), new Perfil());
			if (lista != null && lista.size() == 1) {
				return lista.get(0);
			}
			return null;
		} catch (Exception e) {
			LOGGER.error("Erro ao obter perfil por ID.", e);
			throw new IntegrationException("Erro ao obter perfil por ID.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.UsuarioDao#listaUsuarios(br.com.guedes.sistemacomercial.model.Usuario)
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarUsuariosPorCriterios(final Usuario usuario) throws BusinessException, IntegrationException {
		try {
			LOGGER.info("Obtendo lista de usuários.");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from Usuario U where U.usuStatus = 'A' ");
			if (usuario.getUsuCodigo() != null) {
				hql.append(" and U.usuCodigo = " + usuario.getUsuCodigo());
			}
			if (usuario.getUsuLogin() != null) {
				hql.append(" and U.usuLogin = " + usuario.getUsuLogin());
			}			
			
			return getHibernateTemplate().findByValueBean(hql.toString(), new Usuario());
		} catch (Exception e) {
			LOGGER.error("Erro ao obter lista de usuários.", e);
			throw new IntegrationException("Erro ao obter lista de usuários.", e);
		}
	}
	
	/*
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Perfil> listaPerfilsNotExistsUsuario(final Usuario usuario) throws BusinessException, IntegrationException {
		try {
			LOGGER.info("Obtendo lista de funcionalidades por perfil.");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from Perfil P where not exists (select UP.perfil.perCodigo from UsuarioPerfil UP ");
			hql.append("where UP.perfil.perCodigo = P.perCodigo and UP.usuario.usuCodigo = " + usuario.getUsuCodigo() + ")");
			
			return getHibernateTemplate().findByValueBean(hql.toString(), new Funcionalidade());
		} catch (Exception e) {
			LOGGER.error("Erro ao obter lista de perfis que não existe no usuário.", e);
			throw new IntegrationException("Erro ao obter lista de perfis que não existe no usuário.", e);
		}
	}
}
