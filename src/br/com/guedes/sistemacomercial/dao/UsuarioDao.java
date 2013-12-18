package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import br.com.guedes.sistemacomercial.model.Funcionalidade;
import br.com.guedes.sistemacomercial.model.Perfil;
import br.com.guedes.sistemacomercial.model.PerfilFuncionalidade;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.model.UsuarioPerfil;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface UsuarioDao {

	/**
	 * Efetuar Login do Usu�rio.
	 * 
	 * @param login String
	 * @param senha String
	 * @return Usuario
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public Usuario efetuarLogin(final String login, final String senha) throws BusinessException, IntegrationException;
	
	/**
	 * Lista de perfis por usu�rio.
	 * 
	 * @param usuario Usuario
	 * @return List<UsuarioPerfil>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<UsuarioPerfil> listaPerfisPorUsuario(final Usuario usuario) throws BusinessException, IntegrationException;
	
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
	 * Lista as funcionalidade que n�o existe no perfil informado.
	 * 
	 * @param perfil Perfil
	 * @return List<Funcionalidade>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<Funcionalidade> listaFuncionalidadesNotExistsPerfil(final Perfil perfil) throws BusinessException, IntegrationException;
	
	/**
	 * Deletar todas as funcionalidades por Perfil.
	 * 
	 * @param perfil Perfil
	 * @throws IntegrationException
	 */
	public void deletarFuncionalidadesPorPerfil(final Perfil perfil) throws IntegrationException;
	
	/**
	 * Buscar perfil por ID.
	 * 
	 * @param perfil Perfil
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public Perfil buscarPerfilPorId(final Perfil perfil) throws BusinessException, IntegrationException;	
	
	/**
	 * Verifica se o Perfil est� sendo usado para algum Usu�rio.
	 * 
	 * @param perfil Perfil
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public boolean isPerfilUsadoUsuario(final Perfil perfil) throws BusinessException, IntegrationException;	
	
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
	 * Lista os perfis que n�o existe no usu�rio informado.
	 * 
	 * @param usuario Usuario
	 * @return List<Perfil>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<Perfil> listaPerfilsNotExistsUsuario(final Usuario usuario) throws BusinessException, IntegrationException;
}
