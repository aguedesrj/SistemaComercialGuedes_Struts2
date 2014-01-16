package br.com.guedes.sistemacomercial.facade;

import br.com.guedes.sistemacomercial.model.Loja;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface LojaFacade {

	/**
	 * Obter dados da única loja.
	 * 
	 * @return Loja
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public Loja buscarLoja() throws BusinessException, IntegrationException;
	
	/**
	 * Salvar loja.
	 * 
	 * @param loja Loja
	 * @throws IntegrationException
	 */
	public void salvar(final Loja loja) throws IntegrationException;
}
