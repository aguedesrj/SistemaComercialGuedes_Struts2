package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import br.com.guedes.sistemacomercial.model.Produto;
import br.com.guedes.sistemacomercial.model.VW_Produto;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface ProdutoDao {

	/**
	 * Pesquisar Produto.
	 * 
	 * @param produto VW_Produto
	 * @throws IntegrationException
	 */
	public List<VW_Produto> pesquisarProdutoPorCriterios(final Produto produto) throws IntegrationException;
	
	/**
	 * Obter Produto.
	 * 
	 * @param proCodigo Integer
	 * @throws IntegrationException
	 * @throws BusinessException
	 */
	public Produto obterProdutoPorId(final Integer proCodigo) throws IntegrationException, BusinessException;
}
