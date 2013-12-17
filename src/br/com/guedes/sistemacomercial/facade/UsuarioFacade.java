package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import br.com.guedes.sistemacomercial.model.Funcionalidade;
import br.com.guedes.sistemacomercial.model.Perfil;
import br.com.guedes.sistemacomercial.model.PerfilFuncionalidade;
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
	 * Salvar Usuário.
	 * 
	 * @param usuario Usuario
	 * @throws IntegrationException
	 */
	public void salvarUsuario(final Usuario usuario) throws IntegrationException;	
	
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
	public void deletarPerfil(Perfil perfil) throws BusinessException, IntegrationException;	
	
	/**
	 * Lista de Funcionalidades.
	 * 
	 * @return List<Funcionalidade>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<Funcionalidade> listaFuncionalidade() throws BusinessException, IntegrationException;	
	
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
	
	/**
	 * Lista de Usuarios.
	 * 
	 * @param usuario Usuario
	 * @return List<Usuario>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<Usuario> buscarUsuariosPorCriterios(final Usuario usuario) throws BusinessException, IntegrationException;	
	
	/**
	 * Deletar Usuario.
	 * 
	 * @param usuario Usuario
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public void deletarUsuario(Usuario usuario) throws BusinessException, IntegrationException;	
	
	/**
	 * Lista os perfis que não existe no usuário informado.
	 * 
	 * @param usuario Usuario
	 * @return List<Perfil>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<Perfil> listaPerfilsNotExistsUsuario(final Usuario usuario) throws BusinessException, IntegrationException;
}
