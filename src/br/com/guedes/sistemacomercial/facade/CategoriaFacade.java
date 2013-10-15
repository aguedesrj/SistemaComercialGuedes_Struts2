package br.com.guedes.sistemacomercial.facade;

import java.util.List;

import br.com.guedes.sistemacomercial.model.Categoria;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface CategoriaFacade {

	/**
	 * Lista de Categorias.
	 * 
	 * @return List<Categoria>
	 * @throws IntegrationException
	 */
	public List<Categoria> listaCategoria() throws IntegrationException;
}
