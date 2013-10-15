package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemacomercial.model.Produto;
import br.com.guedes.sistemacomercial.model.VW_Produto;
import br.com.guedes.sistemacomercial.model.ValorVendaProduto;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

/**
 * 
 * @author AndréLessa
 *
 */
public class ProdutoDaoImpl extends HibernateDaoSupport implements ProdutoDao {

	private static final Logger LOGGER = Logger.getLogger(ProdutoDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.ProdutoDao#salvar(br.com.guedes.sistemacomercial.model.Produto)
	 */
	public void salvar(final Produto produto) throws IntegrationException, BusinessException {
		try {
			LOGGER.info("Salvando o Produto...");
			getHibernateTemplate().saveOrUpdate(produto);
		} catch (Exception e) {
			LOGGER.error("Erro ao gravar Produto.", e);
			throw new IntegrationException("Erro ao gravar Produto.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.ProdutoDao#pesquisar(br.com.guedes.sistemacomercial.model.Produto)
	 */
	@SuppressWarnings("unchecked")
	public List<VW_Produto> pesquisar(final Produto produto) throws IntegrationException {
		try {
			LOGGER.info("Pesquisando Produto...");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from VW_Produto where proCodigo > 0 ");
			
			// Codigo
			if (produto.getProCodigo() != null) {
				hql.append(" and proCodigo = :proCodigo ");
			}
			
			// Nome
			if (produto.getProNome() != null) {
				hql.append(" and upper(proNome) like upper('%" + produto.getProNome() + "%')");
			}
			
			return getHibernateTemplate().findByValueBean(hql.toString(), produto);
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Produto.", e);
			throw new IntegrationException("Erro ao pesquisar Produto.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.ProdutoDao#obterProduto(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public Produto obterProduto(final Integer proCodigo) throws IntegrationException, BusinessException {
		try {
			LOGGER.info("Obtendo dados do Produto...");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from Produto where proCodigo = " + proCodigo);
			
			Produto produto = new Produto();
			List<Produto> listaProduto = getHibernateTemplate().findByValueBean(hql.toString(), produto);
			if (listaProduto != null && !listaProduto.isEmpty()) {
				return listaProduto.get(0);
			}
			throw new IntegrationException("Erro ao obter Produto.");
		} catch (Exception e) {
			LOGGER.error("Erro ao obter Produto.", e);
			throw new IntegrationException("Erro ao obter Produto.", e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.ProdutoDao#obterValoresVendaPorProduto(java.lang.Integer)
	 */
	public List<ValorVendaProduto> obterValoresVendaPorProduto(final Integer proCodigo) throws IntegrationException, BusinessException {
		return null;
	}
}
