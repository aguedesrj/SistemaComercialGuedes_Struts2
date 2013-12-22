package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemacomercial.dao.ProdutoDao;
import br.com.guedes.sistemacomercial.model.Produto;
import br.com.guedes.sistemacomercial.model.VW_Produto;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class ProdutoFacadeImpl implements ProdutoFacade {

	@Autowired(required=true)
	private ProdutoDao produtoDao;
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.ProdutoFacade#salvar(br.com.guedes.sistemacomercial.model.Produto)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(final Produto produto) throws IntegrationException, BusinessException {
		//produtoDao.salvar(produto);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.ProdutoFacade#pesquisarProdutoPorCriterios(br.com.guedes.sistemacomercial.model.Produto)
	 */
	public List<VW_Produto> pesquisarProdutoPorCriterios(final Produto produto) throws IntegrationException {
		return produtoDao.pesquisarProdutoPorCriterios(produto);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.ProdutoFacade#obterProdutoPorId(java.lang.Integer)
	 */
	public Produto obterProdutoPorId(final Integer proCodigo) throws IntegrationException, BusinessException {
		return produtoDao.obterProdutoPorId(proCodigo);
	}

	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}

	public void setProdutoDao(ProdutoDao produtoDao) {
		this.produtoDao = produtoDao;
	}
}
