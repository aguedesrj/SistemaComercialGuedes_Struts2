package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import br.com.guedes.sistemacomercial.model.Funcionalidade;
import br.com.guedes.sistemacomercial.model.Perfil;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface UsuarioFacade {

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
	 * Salvar Perfil do Usuário.
	 * 
	 * @param perfil Perfil
	 * @throws IntegrationException
	 */
	public void salvarPerfil(final Perfil perfil) throws IntegrationException;
	
	/**
	 * Lista de Perfis.
	 * 
	 * @param perfil Perfil
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<Perfil> listaPerfil() throws BusinessException, IntegrationException;
	
	/**
	 * Deletar Perfil.
	 * 
	 * @param perfil Perfil
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public void deletarPerfil(final Perfil perfil) throws BusinessException, IntegrationException;	
	
	/**
	 * Lista de Funcionalidades.
	 * 
	 * @return List<Funcionalidade>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<Funcionalidade> listaFuncionalidade() throws BusinessException, IntegrationException;	
}
