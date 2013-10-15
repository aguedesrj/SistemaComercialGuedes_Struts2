package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_USUARIO")
@SequenceGenerator(name="SEQUENCE_USUARIO", sequenceName = "GEN_TBL_USUARIO_ID", allocationSize=1)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 8879365077684758158L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_USUARIO")
	@Column(name="USU_CODIGO")
	private Integer pesCodigo;
	
	@Column(name="USU_LOGIN", length=50)
	private String usuLogin;
	
	@Column(name="USU_SENHA", length=20)
	private String usuSenha;
	
	@OneToOne
	@JoinColumn(name="PES_CODIGO")
	private Pessoa pessoa;

	public Integer getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(Integer pesCodigo) {
		this.pesCodigo = pesCodigo;
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
}
