package br.com.guedes.sistemacomercial.facade;

import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.util.BusinessException;

public interface UsuarioFacade {

	/**
	 * Efetuar Login do Usu�rio.
	 * 
	 * @param login String
	 * @param senha String
	 * @return Usuario
	 * @throws BusinessException
	 */
	public Usuario efetuarLogin(final String login, final String senha) throws BusinessException;
}
