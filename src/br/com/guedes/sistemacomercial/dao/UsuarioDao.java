package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import br.com.guedes.sistemacomercial.model.Funcionalidade;
import br.com.guedes.sistemacomercial.model.Perfil;
import br.com.guedes.sistemacomercial.model.PerfilFuncionalidade;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

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
	
	/**
	 * Lista de funcionalidades por perfil.
	 * 
	 * @param perfil Perfil
	 * @return List<PerfilFuncionalidade>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<PerfilFuncionalidade> listaFuncionalidadesPorPerfil(final Perfil perfil) throws BusinessException, IntegrationException;
	
	/**
	 * Lista as funcionalidade que não existe no perfil informado.
	 * 
	 * @param perfil Perfil
	 * @return List<Funcionalidade>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<Funcionalidade> listaFuncionalidadesNotExistsPerfil(final Perfil perfil) throws BusinessException, IntegrationException;
}
