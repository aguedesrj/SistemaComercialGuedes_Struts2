package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import br.com.guedes.sistemacomercial.model.TipoContato;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface ContatoDao {

	/**
	 * Lista dos tipo de contatos.
	 * 
	 * @return List<TipoContato>
	 * @throws IntegrationException
	 */
	public List<TipoContato> listaTipoContatos() throws IntegrationException;
}
