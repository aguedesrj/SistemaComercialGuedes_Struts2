package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;
import java.util.List;

public class PerfilVO implements Serializable {

	private static final long serialVersionUID = -2145592693459125735L;
	
	private Integer perCodigo;
	private String perNome;
	private List<FuncionalidadeVO> listaFuncionalidades;
	
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

	public List<FuncionalidadeVO> getListaFuncionalidades() {
		return listaFuncionalidades;
	}

	public void setListaFuncionalidades(List<FuncionalidadeVO> listaFuncionalidades) {
		this.listaFuncionalidades = listaFuncionalidades;
	}
}
