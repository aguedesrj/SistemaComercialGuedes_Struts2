package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@IdClass(PerfilFuncionalidadePk.class)
@Table(name="TBL_PERFIL_FUNCIONALIDADE")
public class PerfilFuncionalidade implements Serializable {

	private static final long serialVersionUID = -4983423953462811676L;
	
	@Id
    private Perfil perfil;

    @Id
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
