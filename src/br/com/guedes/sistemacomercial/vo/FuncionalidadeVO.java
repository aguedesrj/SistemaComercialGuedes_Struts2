package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;

public class FuncionalidadeVO implements Serializable {

	private static final long serialVersionUID = -6696216524682762568L;
	
	private Integer funCodigo;
	private String funDescricao;
	
	public Integer getFunCodigo() {
		return funCodigo;
	}
	
	public void setFunCodigo(Integer funCodigo) {
		this.funCodigo = funCodigo;
	}
	
	public String getFunDescricao() {
		return funDescricao;
	}
	
	public void setFunDescricao(String funDescricao) {
		this.funDescricao = funDescricao;
	}
}
