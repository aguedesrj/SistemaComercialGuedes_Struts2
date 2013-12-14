package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;

public class GenericVO implements Serializable {

	private static final long serialVersionUID = 708079200928080529L;
	
	private Integer genCodigo;
	private String genDescricao;
	
	public Integer getGenCodigo() {
		return genCodigo;
	}
	
	public void setGenCodigo(Integer genCodigo) {
		this.genCodigo = genCodigo;
	}
	
	public String getGenDescricao() {
		return genDescricao;
	}
	
	public void setGenDescricao(String genDescricao) {
		this.genDescricao = genDescricao;
	}
}
