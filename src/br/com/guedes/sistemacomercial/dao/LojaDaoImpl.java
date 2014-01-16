package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.guedes.sistemacomercial.model.Loja;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.IntegrationException;

@Repository
public class LojaDaoImpl extends HibernateDaoSupport implements LojaDao {

	private static final Logger LOGGER = Logger.getLogger(LojaDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.guedes.sistemacomercial.dao.LojaDao#buscarLoja()
	 */
	@SuppressWarnings("unchecked")
	public Loja buscarLoja() throws BusinessException, IntegrationException {
		try {
			LOGGER.info("Obtendo dados da Loja.");
			StringBuilder hql = new StringBuilder();
			
			hql.append("from Loja");
			
			List<Loja> lista = getHibernateTemplate().findByValueBean(hql.toString(), new Loja());
			if (lista != null && lista.size() == 1) {
				return lista.get(0);
			}
			return null;
		} catch (Exception e) {
			LOGGER.error("Erro ao obter dados da Loja.", e);
			throw new IntegrationException("Erro ao obter dados da Loja.", e);
		}
	}
}
