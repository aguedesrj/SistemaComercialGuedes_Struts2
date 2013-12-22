package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemacomercial.dao.ProdutoDao;
import br.com.guedes.sistemacomercial.model.Produto;
import br.com.guedes.sistemacomercial.model.VW_Produto;
import br.com.guedes.sistemacomercial.model.ValorVendaProduto;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class ProdutoFacadeImpl implements ProdutoFacade {
	
	private static final Logger LOGGER = Logger.getLogger(ProdutoFacadeImpl.class);

	@Autowired(required=true)
	private ProdutoDao produtoDao;
	
	@Autowired  
    private SessionFactory sessionFactory;	
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.ProdutoFacade#salvar(br.com.guedes.sistemacomercial.model.Produto)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void salvar(final Produto produto) throws IntegrationException, BusinessException {
		try {
			// verifica se já existe algum Produto com o mesmo nome.
			Produto produtoCond = new Produto();
			produtoCond.setProNome(produto.getProNome());
			// verifica se está sendo alterado.
			if (produto.getProCodigo() == null || produto.getProCodigo() == 0) {
				List<VW_Produto> lista = produtoDao.pesquisarProdutoPorCriterios(produtoCond);
				if (lista != null && !lista.isEmpty()) {
					throw new BusinessException("Produto já está cadastrado.");
				}
			}
			sessionFactory.getCurrentSession().saveOrUpdate(produto);
			for (ValorVendaProduto valorVendaProduto: produto.getListaValorVendaProduto()) {
				sessionFactory.getCurrentSession().saveOrUpdate(valorVendaProduto);
			}
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) {
			LOGGER.error(e);
			if (e instanceof BusinessException) {
				throw new BusinessException(e.getMessage());
			} else {
				throw new IntegrationException("Não foi possível salvar o Produto.");
			}
		}
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
