package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemacomercial.dao.CategoriaDao;
import br.com.guedes.sistemacomercial.model.Categoria;
import br.com.guedes.sistemacomercial.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CategoriaFacadeImpl implements CategoriaFacade {

	@Autowired(required=true)
	private CategoriaDao categoriaDao;
	
	/*
	 * (non-Javadoc)
	 * @see br.com.sistemacomercial.guedes.facade.CategoriaFacade#listaCategoria()
	 */
	public List<Categoria> listaCategoria() throws IntegrationException {
		
		return categoriaDao.listaCategoria();
	}

	public CategoriaDao getCategoriaDao() {
		return categoriaDao;
	}

	public void setCategoriaDao(CategoriaDao categoriaDao) {
		this.categoriaDao = categoriaDao;
	}
}
