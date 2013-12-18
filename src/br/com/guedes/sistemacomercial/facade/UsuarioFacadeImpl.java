package br.com.guedes.sistemacomercial.facade;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemacomercial.dao.UsuarioDao;
import br.com.guedes.sistemacomercial.model.Funcionalidade;
import br.com.guedes.sistemacomercial.model.Perfil;
import br.com.guedes.sistemacomercial.model.PerfilFuncionalidade;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.model.UsuarioPerfil;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class UsuarioFacadeImpl extends HibernateDaoSupport implements UsuarioFacade {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired  
    private SessionFactory sessionFactory;	
	
	/*
	 * (non-Javadoc)
	 * @see br.com.sistemacomercial.guedes.facade.UsuarioFacade#efetuarLogin(java.lang.String, java.lang.String)
	 */
	public Usuario efetuarLogin(final String login, final String senha) throws BusinessException, IntegrationException {
		return usuarioDao.efetuarLogin(login, senha);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#salvarUsuario(br.com.guedes.sistemacomercial.model.Usuario)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegrationException.class)
	public void salvarUsuario(final Usuario usuario) throws IntegrationException {
		try {
			// verifica se já existe algum Usuário com o mesmo login.
			Usuario usuarioCond = new Usuario();
			usuarioCond.setUsuLogin(usuario.getUsuLogin());
			List<Usuario> lista = usuarioDao.buscarUsuariosPorCriterios(usuarioCond);
			if (lista != null && !lista.isEmpty()) {
				throw new BusinessException("Login já está sendo utilizado.");
			}
			sessionFactory.getCurrentSession().saveOrUpdate(usuario.getPessoa());
			sessionFactory.getCurrentSession().saveOrUpdate(usuario);
			List<UsuarioPerfil> listaUsuarioPerfil = usuarioDao.listaPerfisPorUsuario(usuario);
			for (UsuarioPerfil usuarioPerfil: listaUsuarioPerfil) {
				sessionFactory.getCurrentSession().delete(usuarioPerfil);
			}
			sessionFactory.getCurrentSession().flush();
			for (UsuarioPerfil usuarioPerfil: usuario.getListaUsuarioPerfil()) {
				sessionFactory.getCurrentSession().saveOrUpdate(usuarioPerfil);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException("Não foi possível salvar o Usuário.");
		}		
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#salvarPerfil(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegrationException.class)
	public void salvarPerfil(final Perfil perfil) throws IntegrationException {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(perfil);
			List<PerfilFuncionalidade> lista = usuarioDao.listaFuncionalidadesPorPerfil(perfil);
			for (PerfilFuncionalidade perfilFuncionalidade: lista) {
				sessionFactory.getCurrentSession().delete(perfilFuncionalidade);
			}
			sessionFactory.getCurrentSession().flush();
			for (PerfilFuncionalidade perfilFuncionalidade: perfil.getListaPerfilFuncionalidade()) {
				sessionFactory.getCurrentSession().saveOrUpdate(perfilFuncionalidade);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException("Não foi possível salvar o Perfil.");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#listaPerfil()
	 */
	@SuppressWarnings("unchecked")
	public List<Perfil> listaPerfil() throws BusinessException, IntegrationException {
		try {
			return (ArrayList<Perfil>) getHibernateTemplate().find("from Perfil");
		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException("Não foi possível listar os Perfis.");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#buscarPerfilPorId(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	public Perfil buscarPerfilPorId(final Perfil perfil) throws BusinessException, IntegrationException {
		return usuarioDao.buscarPerfilPorId(perfil);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#deletarPerfil(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegrationException.class)
	public void deletarPerfil(Perfil perfil) throws BusinessException, IntegrationException {
		perfil = usuarioDao.buscarPerfilPorId(perfil);
		if (perfil == null) {
			throw new BusinessException("Perfil não encontrado na base de dados.");
		}
		if (usuarioDao.isPerfilUsadoUsuario(perfil)) {
			throw new BusinessException("O Perfil está sendo usuado em algum Usuário.");
		}
		getHibernateTemplate().delete(perfil);	
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#listaFuncionalidade()
	 */
	@SuppressWarnings("unchecked")
	public List<Funcionalidade> listaFuncionalidade() throws BusinessException, IntegrationException {
		try {
			return (ArrayList<Funcionalidade>) getHibernateTemplate().find("from Funcionalidade");
		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException("Não foi possível listar as Funcionalidades.");
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#listaFuncionalidadesPorPerfil(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	public List<PerfilFuncionalidade> listaFuncionalidadesPorPerfil(final Perfil perfil) throws BusinessException, IntegrationException {
		return usuarioDao.listaFuncionalidadesPorPerfil(perfil);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#listaFuncionalidadesNotExistsPerfil(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	public List<Funcionalidade> listaFuncionalidadesNotExistsPerfil(final Perfil perfil) throws BusinessException, IntegrationException {
		return usuarioDao.listaFuncionalidadesNotExistsPerfil(perfil);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#listaUsuarios(br.com.guedes.sistemacomercial.model.Usuario)
	 */
	public List<Usuario> buscarUsuariosPorCriterios(final Usuario usuario) throws BusinessException, IntegrationException {
		return usuarioDao.buscarUsuariosPorCriterios(usuario);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#deletarUsuario(br.com.guedes.sistemacomercial.model.Usuario)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegrationException.class)
	public void deletarUsuario(Usuario usuario) throws BusinessException, IntegrationException {
		Usuario usuarioUpdate = usuarioDao.buscarUsuariosPorCriterios(usuario).get(0);
		if (usuario == null) {
			throw new BusinessException("Usuário não encontrado na base de dados.");
		}
		usuarioUpdate.setUsuStatus(usuario.getUsuStatus());
		getHibernateTemplate().saveOrUpdate(usuarioUpdate);
		getHibernateTemplate().flush();
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#listaPerfilsNotExistsUsuario(br.com.guedes.sistemacomercial.model.Usuario)
	 */
	public List<Perfil> listaPerfilsNotExistsUsuario(final Usuario usuario) throws BusinessException, IntegrationException {
		return usuarioDao.listaPerfilsNotExistsUsuario(usuario);
	}

	/**
	 * @return the usuarioDao
	 */
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	/**
	 * @param usuarioDao the usuarioDao to set
	 */
	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
}
