package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
@Entity
@Table(name="TBL_USUARIO")
@SequenceGenerator(name="SEQUENCE_USUARIO", sequenceName = "GEN_TBL_USUARIO_ID", allocationSize=1)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 8879365077684758158L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_USUARIO")
	@Column(name="USU_CODIGO")
	private Integer usuCodigo;
	
	@Column(name="USU_LOGIN", length=50)
	private String usuLogin;
	
	@Column(name="USU_SENHA", length=20)
	private String usuSenha;
	
	@Column(name="USU_STATUS", length=1)
	private String usuStatus;	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PES_CODIGO")
	private Pessoa pessoa;
	
	@OneToMany(mappedBy = "usuario", fetch=FetchType.EAGER)
	private Set<UsuarioPerfil> listaUsuarioPerfil = new HashSet<UsuarioPerfil>();	

	public Integer getUsuCodigo() {
		return usuCodigo;
	}

	public void setUsuCodigo(Integer usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	public String getUsuLogin() {
		return usuLogin;
	}

	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}

	public String getUsuSenha() {
		return usuSenha;
	}

	public void setUsuSenha(String usuSenha) {
		this.usuSenha = usuSenha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Set<UsuarioPerfil> getListaUsuarioPerfil() {
		return listaUsuarioPerfil;
	}

	public void setListaUsuarioPerfil(Set<UsuarioPerfil> listaUsuarioPerfil) {
		this.listaUsuarioPerfil = listaUsuarioPerfil;
	}

	public String getUsuStatus() {
		return usuStatus;
	}

	public void setUsuStatus(String usuStatus) {
		this.usuStatus = usuStatus;
	}
}
