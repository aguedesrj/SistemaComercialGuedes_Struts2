package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import br.com.guedes.sistemacomercial.model.Venda;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface VendaFacade {

	/**
	 * Obter lista de venda por critérios.
	 * 
	 * @param venda Venda
	 * @return List<Venda>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<Venda> pesquisarVendaPorCriterios(final Venda venda) throws BusinessException, IntegrationException;	
}
