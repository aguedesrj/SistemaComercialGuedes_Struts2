package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.guedes.sistemacomercial.model.TipoContato;
import br.com.guedes.sistemacomercial.util.IntegrationException;

@Repository
public class ContatoDaoImpl extends HibernateDaoSupport implements ContatoDao {

	private static final Logger LOGGER = Logger.getLogger(ContatoDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.sistemacomercial.guedes.dao.ContatoDao#listaTipoContatos()
	 */
	@SuppressWarnings("unchecked")
	public List<TipoContato> listaTipoContatos() throws IntegrationException {
		
		try {
			
			return getHibernateTemplate().findByValueBean(new StringBuilder("from TipoContato ").toString(), TipoContato.class);
			
		} catch (Exception e) {
			LOGGER.fatal("Erro ao buscar lista de tipos de contatos.", e);
			e.printStackTrace();
			throw new IntegrationException("Erro ao buscar lista de tipos de contatos.");
		}
	}
}
