package br.com.guedes.sistemacomercial.dao;

import br.com.guedes.sistemacomercial.model.Loja;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface LojaDao {
	
	/**
	 * Obter dados da única loja.
	 * 
	 * @return Loja
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public Loja buscarLoja() throws BusinessException, IntegrationException;
}
