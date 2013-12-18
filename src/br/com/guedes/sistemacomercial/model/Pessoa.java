package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.util.Calendar;

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
@Table(name="TBL_PESSOA")
@SequenceGenerator(name="SEQUENCE_PESSOA", sequenceName = "GEN_TBL_PESSOA_ID", allocationSize=1)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = -270858427934393319L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PESSOA")
	@Column(name="PES_CODIGO")
	private Integer pesCodigo;
	
	@Column(name="PES_NOME", length=120, nullable=false)
	private String pesNome;	

	@Column(name="PES_DATACADASTRO", nullable=false)
	private Calendar pesDataCadastro;
	
	@Column(name="PES_DATAALTERACAO")
	private Calendar pesDataAlteracao;		
	
	@OneToOne
	@JoinColumn(name="USU_CODIGO")
	private Usuario usuario;

	public Integer getPesCodigo() {
		return pesCodigo;
	}

	public void setPesCodigo(Integer pesCodigo) {
		this.pesCodigo = pesCodigo;
	}

	public String getPesNome() {
		return pesNome;
	}

	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}

	public Calendar getPesDataCadastro() {
		return pesDataCadastro;
	}

	public void setPesDataCadastro(Calendar pesDataCadastro) {
		this.pesDataCadastro = pesDataCadastro;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getPesDataAlteracao() {
		return pesDataAlteracao;
	}

	public void setPesDataAlteracao(Calendar pesDataAlteracao) {
		this.pesDataAlteracao = pesDataAlteracao;
	}
}
