package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.guedes.sistemacomercial.dao.ContatoDao;
import br.com.guedes.sistemacomercial.model.TipoContato;
import br.com.guedes.sistemacomercial.util.IntegrationException;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ContatoFacadeImpl implements ContatoFacade {

	@Autowired(required=true)
	private ContatoDao contatoDao;
	
	/*
	 * (non-Javadoc)
	 * @see br.com.sistemacomercial.guedes.facade.ContatoFacade#listaTipoContatos()
	 */
	public List<TipoContato> listaTipoContatos() throws IntegrationException {
		
		return contatoDao.listaTipoContatos();
	}
	
	public ContatoDao getContatoDao() {
		return contatoDao;
	}

	public void setContatoDao(ContatoDao contatoDao) {
		this.contatoDao = contatoDao;
	}
}
