package br.com.guedes.sistemacomercial.dao;

import java.util.List;

import br.com.guedes.sistemacomercial.model.Categoria;
import br.com.guedes.sistemacomercial.util.IntegrationException;

public interface CategoriaDao {

	/**
	 * Listar as categorias.
	 * 
	 * @return List<Categoria>
	 * @throws IntegrationException
	 */
	public List<Categoria> listaCategoria() throws IntegrationException;
}
