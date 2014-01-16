package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_LOJA")
public class Loja implements Serializable {

	private static final long serialVersionUID = 3238411251926656436L;

	@Id
	@Column(name="LOJ_CODIGO")
	private Integer lojCodigo;
	
	@Column(name="LOJ_NOME", length=100, nullable=false)
	private String lojNome;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USU_CODIGO", nullable=false)
	private Usuario usuario;
	
	@Transient
	private List<Caixa> listaCaixa;
	
	public Integer getLojCodigo() {
		return lojCodigo;
	}

	public void setLojCodigo(Integer lojCodigo) {
		this.lojCodigo = lojCodigo;
	}

	public String getLojNome() {
		return lojNome;
	}

	public void setLojNome(String lojNome) {
		this.lojNome = lojNome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Caixa> getListaCaixa() {
		return listaCaixa;
	}

	public void setListaCaixa(List<Caixa> listaCaixa) {
		this.listaCaixa = listaCaixa;
	}
}
