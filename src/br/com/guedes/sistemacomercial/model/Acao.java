package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_ACAO")
public class Acao implements Serializable {

	private static final long serialVersionUID = -1990699466685579870L;

	@Id
	@Column(name="ACA_CODIGO")
	private Integer acaCodigo;
	
	@Column(name="ACA_DESCRICAO", length=80, nullable=false)
	private String acaDescricao;

	public Integer getAcaCodigo() {
		return acaCodigo;
	}

	public void setAcaCodigo(Integer acaCodigo) {
		this.acaCodigo = acaCodigo;
	}

	public String getAcaDescricao() {
		return acaDescricao;
	}

	public void setAcaDescricao(String acaDescricao) {
		this.acaDescricao = acaDescricao;
	}

}
