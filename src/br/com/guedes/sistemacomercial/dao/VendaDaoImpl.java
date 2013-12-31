package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.guedes.sistemacomercial.model.Venda;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

/**
 * 
 * @author AndréLessa
 *
 */
public class VendaDaoImpl extends HibernateDaoSupport implements VendaDao {

	private static final Logger LOGGER = Logger.getLogger(VendaDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.VendaDao#pesquisarVendaPorCriterios(br.com.guedes.sistemacomercial.model.Venda)
	 */
	@SuppressWarnings("unchecked")
	public List<Venda> pesquisarVendaPorCriterios(final Venda venda) throws BusinessException, IntegrationException {
		try {
			LOGGER.info("Pesquisando Vendas...");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from Venda where venCodigo > 0 ");
			
			// Codigo
			if (venda.getVenCodigo() != null) {
				hql.append(" and proCodigo = " + venda.getVenCodigo());
			}
			
			List<Venda> lista = getHibernateTemplate().findByValueBean(hql.toString(), venda);
			if (lista == null || lista.isEmpty()) {
				throw new BusinessException("Nenhuma venda encontrada.");
			}
			return lista;
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Produto.", e);
			if (e instanceof BusinessException) {
				throw new BusinessException(e);
			}
			throw new IntegrationException("Erro ao pesquisar Produto.", e);
		}
	}
}
