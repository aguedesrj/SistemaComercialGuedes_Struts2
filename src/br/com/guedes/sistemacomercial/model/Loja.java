package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
