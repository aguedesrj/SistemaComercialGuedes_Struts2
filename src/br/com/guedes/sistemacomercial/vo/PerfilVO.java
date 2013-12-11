package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;

public class PerfilVO implements Serializable {

	private static final long serialVersionUID = -2145592693459125735L;
	
	private Integer perCodigo;
	private String perNome;
	
	public Integer getPerCodigo() {
		return perCodigo;
	}
	
	public void setPerCodigo(Integer perCodigo) {
		this.perCodigo = perCodigo;
	}
	
	public String getPerNome() {
		return perNome;
	}
	
	public void setPerNome(String perNome) {
		this.perNome = perNome;
	}
}
