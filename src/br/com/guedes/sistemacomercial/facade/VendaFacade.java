package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import br.com.guedes.sistemacomercial.model.ItensVenda;
import br.com.guedes.sistemacomercial.model.VW_Venda;
import br.com.guedes.sistemacomercial.model.Venda;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface VendaFacade {

	/**
	 * Obter lista de venda por crit�rios.
	 * 
	 * @param venda Venda
	 * @return List<VW_Venda>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<VW_Venda> pesquisarVendaPorCriterios(final Venda venda) throws BusinessException, IntegrationException;	
	
	/**
	 * obter Vendas por per�odo, para ser exibida em gr�fico.
	 * 
	 * @param dataInicio String
	 * @param dataFim String
	 * @return List<ItensVenda>
	 * @throws BusinessException
	 * @throws IntegrationException
	 */
	public List<ItensVenda> obterVendasPorPeriodo(final String dataInicio, final String dataFim) throws BusinessException, IntegrationException;	
}
