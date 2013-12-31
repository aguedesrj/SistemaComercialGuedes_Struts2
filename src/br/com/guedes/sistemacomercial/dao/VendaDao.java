package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import br.com.guedes.sistemacomercial.model.Venda;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface VendaDao {

	/**
	 * Obter lista de venda por crit�rios.
	 * 
	 * @param venda Venda
	 * @return List<Venda>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<Venda> pesquisarVendaPorCriterios(final Venda venda) throws BusinessException, IntegrationException;
}
