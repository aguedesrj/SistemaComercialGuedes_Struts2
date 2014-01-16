package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;
import java.util.List;

import br.com.guedes.sistemacomercial.model.Caixa;

public class LojaVO implements Serializable {

	private static final long serialVersionUID = -7211541614493143225L;

	private Integer lojCodigo;
	private String lojNome;
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
	
	public List<Caixa> getListaCaixa() {
		return listaCaixa;
	}
	
	public void setListaCaixa(List<Caixa> listaCaixa) {
		this.listaCaixa = listaCaixa;
	}
}
