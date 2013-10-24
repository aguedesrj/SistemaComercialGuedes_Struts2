package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PerfilFuncionalidadePk implements Serializable {

	private static final long serialVersionUID = 159265092530302465L;

	@ManyToOne(fetch = FetchType.EAGER)    
	@JoinColumn(name="PER_CODIGO")   
	private Perfil perfil;	

	@ManyToOne(fetch = FetchType.EAGER)    
	@JoinColumn(name="FUN_CODIGO")   
	private Funcionalidade funcionalidade;

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
}
