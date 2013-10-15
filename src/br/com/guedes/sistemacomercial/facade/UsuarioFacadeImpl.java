package br.com.guedes.sistemacomercial.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemacomercial.dao.UsuarioDao;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.util.BusinessException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class UsuarioFacadeImpl implements UsuarioFacade {

	@Autowired(required=true)
	private UsuarioDao usuarioDao;
	
	/*
	 * (non-Javadoc)
	 * @see br.com.sistemacomercial.guedes.facade.UsuarioFacade#efetuarLogin(java.lang.String, java.lang.String)
	 */
	public Usuario efetuarLogin(final String login, final String senha) throws BusinessException {
		return usuarioDao.efetuarLogin(login, senha);
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
