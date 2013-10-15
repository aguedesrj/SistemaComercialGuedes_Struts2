package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import br.com.guedes.sistemacomercial.model.Produto;
import br.com.guedes.sistemacomercial.model.VW_Produto;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface ProdutoFacade {

	/**
	 * Salvar Produto.
	 * 
	 * @param produto Produto
	 * @throws IntegrationException
	 * @throws BusinessException
	 */
	public void salvar(final Produto produto) throws IntegrationException, BusinessException;
	
	/**
	 * Pesquisar Produto.
	 * 
	 * @param produto VW_Produto
	 * @throws IntegrationException
	 */
	public List<VW_Produto> pesquisar(final Produto produto) throws IntegrationException;
	
	/**
	 * Obter Produto.
	 * 
	 * @param proCodigo Integer
	 * @throws IntegrationException
	 * @throws BusinessException
	 */
	public Produto obterProduto(final Integer proCodigo) throws IntegrationException, BusinessException;	
}
