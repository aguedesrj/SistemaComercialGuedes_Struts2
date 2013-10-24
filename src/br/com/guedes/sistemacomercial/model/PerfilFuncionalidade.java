package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Embeddable 
@Table(name="TBL_PERFIL_FUNCIONALIDADE")
public class PerfilFuncionalidade implements Serializable {

	private static final long serialVersionUID = -4983423953462811676L;
	
	@Id
	private PerfilFuncionalidadePk perfilFuncionalidadePk;

	public PerfilFuncionalidadePk getPerfilFuncionalidadePk() {
		return perfilFuncionalidadePk;
	}

	public void setPerfilFuncionalidadePk(
			PerfilFuncionalidadePk perfilFuncionalidadePk) {
		this.perfilFuncionalidadePk = perfilFuncionalidadePk;
	}
}
