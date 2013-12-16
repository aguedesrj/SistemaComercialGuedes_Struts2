package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@IdClass(UsuarioPerfilPk.class)
@Table(name="TBL_USUARIO_PERFIL")
public class UsuarioPerfil implements Serializable {

	private static final long serialVersionUID = 7539332918373226052L;

	@Id
    private Usuario usuario;

	@Id
    private Perfil perfil;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}
