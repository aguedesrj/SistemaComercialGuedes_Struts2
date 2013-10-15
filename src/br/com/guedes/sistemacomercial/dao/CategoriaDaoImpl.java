package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import br.com.guedes.sistemacomercial.model.Categoria;
import br.com.guedes.sistemacomercial.util.IntegrationException;

@Repository
public class CategoriaDaoImpl extends HibernateDaoSupport implements CategoriaDao {

	private static final Logger LOGGER = Logger.getLogger(CategoriaDaoImpl.class);
	
	/*
	 * (non-Javadoc)
	 * @see br.com.sistemacomercial.guedes.dao.CategoriaDao#listaCategoria()
	 */
	@SuppressWarnings("unchecked")
	public List<Categoria> listaCategoria() throws IntegrationException {
		
		try {
			
			return getHibernateTemplate().findByValueBean(new StringBuilder("from Categoria ").toString(), Categoria.class);
			
		} catch (Exception e) {
			LOGGER.fatal("Erro ao buscar lista Categoriaa.", e);
			e.printStackTrace();
			throw new IntegrationException("Erro ao buscar lista de Categorias.");
		}
	}
}
