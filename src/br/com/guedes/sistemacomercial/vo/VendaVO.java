package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;

public class VendaVO implements Serializable {

	private static final long serialVersionUID = 8144663550649191338L;

	private Integer venCodigo;
	private String pesNome;
	private String fpgDescricao;
	private String venDataInicio;
	private String venDataFim;
	private Integer itvQuantidade;
	
	public Integer getVenCodigo() {
		return venCodigo;
	}
	
	public void setVenCodigo(Integer venCodigo) {
		this.venCodigo = venCodigo;
	}
	
	public String getPesNome() {
		return pesNome;
	}
	
	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}
	
	public String getFpgDescricao() {
		return fpgDescricao;
	}
	
	public void setFpgDescricao(String fpgDescricao) {
		this.fpgDescricao = fpgDescricao;
	}
	
	public String getVenDataInicio() {
		return venDataInicio;
	}
	
	public void setVenDataInicio(String venDataInicio) {
		this.venDataInicio = venDataInicio;
	}
	
	public String getVenDataFim() {
		return venDataFim;
	}
	
	public void setVenDataFim(String venDataFim) {
		this.venDataFim = venDataFim;
	}
	
	public Integer getItvQuantidade() {
		return itvQuantidade;
	}
	
	public void setItvQuantidade(Integer itvQuantidade) {
		this.itvQuantidade = itvQuantidade;
	}
}
