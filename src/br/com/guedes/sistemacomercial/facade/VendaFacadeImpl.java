package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemacomercial.dao.VendaDao;
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
	
	public List<Venda> pesquisarVendaPorCriterios(final Venda venda) throws BusinessException, IntegrationException {
		return getVendaDao().pesquisarVendaPorCriterios(venda);
	}

	public VendaDao getVendaDao() {
		return vendaDao;
	}

	public void setVendaDao(VendaDao vendaDao) {
		this.vendaDao = vendaDao;
	}
}
