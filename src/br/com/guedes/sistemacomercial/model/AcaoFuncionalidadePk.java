package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AcaoFuncionalidadePk implements Serializable {

	private static final long serialVersionUID = 845571290533582109L;
	
	@ManyToOne(fetch = FetchType.EAGER)    
	@JoinColumn(name="ACA_CODIGO")   
	private Acao acao;	

	@ManyToOne(fetch = FetchType.EAGER)    
	@JoinColumn(name="FUN_CODIGO")   
	private Funcionalidade funcionalidade;

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
}
