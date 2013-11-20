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
	public Usuario efetuarLogin(final String login, final String senha) throws BusinessException {
		return usuarioDao.efetuarLogin(login, senha);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#salvarPerfil(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegrationException.class)
	public void salvarPerfil(final Perfil perfil) throws IntegrationException {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(perfil);
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
	 * @see br.com.guedes.sistemacomercial.facade.UsuarioFacade#deletarPerfil(br.com.guedes.sistemacomercial.model.Perfil)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = IntegrationException.class)
	public void deletarPerfil(final Perfil perfil) throws BusinessException, IntegrationException {
		try {
			getHibernateTemplate().delete(perfil);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException("Não foi possível deletar o Perfi.");
		}		
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
