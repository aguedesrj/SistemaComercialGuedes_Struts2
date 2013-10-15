package br.com.guedes.sistemacomercial.dao;

import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.util.BusinessException;

public interface UsuarioDao {

	/**
	 * Efetuar Login do Usuário.
	 * 
	 * @param login String
	 * @param senha String
	 * @return Usuario
	 * @throws BusinessException
	 */
	public Usuario efetuarLogin(final String login, final String senha) throws BusinessException;
}
