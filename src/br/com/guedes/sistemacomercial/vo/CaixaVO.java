package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;

public class CaixaVO implements Serializable {

	private static final long serialVersionUID = -4383605655735092979L;

	private Integer caiCodigo;
	private String caiDescricao;
	private String caiObs;
	
	public Integer getCaiCodigo() {
		return caiCodigo;
	}
	public void setCaiCodigo(Integer caiCodigo) {
		this.caiCodigo = caiCodigo;
	}
	
	public String getCaiDescricao() {
		return caiDescricao;
	}
	
	public void setCaiDescricao(String caiDescricao) {
		this.caiDescricao = caiDescricao;
	}
	
	public String getCaiObs() {
		return caiObs;
	}
	
	public void setCaiObs(String caiObs) {
		this.caiObs = caiObs;
	}
}
