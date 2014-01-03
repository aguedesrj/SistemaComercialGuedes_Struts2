package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemacomercial.dao.VendaDao;
import br.com.guedes.sistemacomercial.model.ItensVenda;
import br.com.guedes.sistemacomercial.model.VW_Venda;
import br.com.guedes.sistemacomercial.model.Venda;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class VendaFacadeImpl implements VendaFacade {
	
	private static final Logger LOGGER = Logger.getLogger(VendaFacadeImpl.class);

	@Autowired(required=true)
	private VendaDao vendaDao;
	
	@Autowired  
    private SessionFactory sessionFactory;	
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.VendaFacade#pesquisarVendaPorCriterios(br.com.guedes.sistemacomercial.model.Venda)
	 */
	public List<VW_Venda> pesquisarVendaPorCriterios(final Venda venda) throws BusinessException, IntegrationException {
		return getVendaDao().pesquisarVendaPorCriterios(venda);
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.VendaFacade#obterVendasPorPeriodo(java.lang.String, java.lang.String)
	 */
	public List<ItensVenda> obterVendasPorPeriodo(final String dataInicio, final String dataFim) throws BusinessException, IntegrationException {
		LOGGER.info("Obtendo vendas por período.");
		return getVendaDao().obterVendasPorPeriodo(dataInicio, dataFim);
	}

	public VendaDao getVendaDao() {
		return vendaDao;
	}

	public void setVendaDao(VendaDao vendaDao) {
		this.vendaDao = vendaDao;
	}
}
