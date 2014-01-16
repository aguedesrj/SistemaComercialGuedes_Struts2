package br.com.guedes.sistemacomercial.facade;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemacomercial.dao.LojaDao;
import br.com.guedes.sistemacomercial.model.Caixa;
import br.com.guedes.sistemacomercial.model.Loja;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class LojaFacadeImpl extends HibernateDaoSupport implements LojaFacade {
	
	private static final Logger LOGGER = Logger.getLogger(LojaFacadeImpl.class);

	@Autowired
	private LojaDao lojaDao;
	
	@Autowired  
    private SessionFactory sessionFactory;		
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.facade.LojaFacade#buscarLoja()
	 */
	public Loja buscarLoja() throws BusinessException, IntegrationException {
		return getLojaDao().buscarLoja();
	}
	
	public void salvar(final Loja loja) throws IntegrationException {
		try {
			// salvar os caixas.
			for (Caixa caixa: loja.getListaCaixa()) {
				sessionFactory.getCurrentSession().saveOrUpdate(caixa);
			}
			sessionFactory.getCurrentSession().flush();
			// salvar dados da Loja
			sessionFactory.getCurrentSession().saveOrUpdate(loja);
			sessionFactory.getCurrentSession().flush();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new IntegrationException("Não foi possível salvar a Loja.");
		}
	}

	public LojaDao getLojaDao() {
		return lojaDao;
	}

	public void setLojaDao(LojaDao lojaDao) {
		this.lojaDao = lojaDao;
	}
}
