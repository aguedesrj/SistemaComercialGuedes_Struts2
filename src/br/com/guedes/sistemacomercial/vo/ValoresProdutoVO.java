package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;

public class ValoresProdutoVO implements Serializable {

	private static final long serialVersionUID = 8930151155081886580L;
	private Integer vvpCodigo;
	private String vrpImpostoICMS;
	private String vrpImpostoIPI;
	private String vrpImpostoISS;
	private String vvpValorProduto;
	private String vvpDataCadastro;
	
	public String getVrpImpostoICMS() {
		return vrpImpostoICMS;
	}
	
	public void setVrpImpostoICMS(String vrpImpostoICMS) {
		this.vrpImpostoICMS = vrpImpostoICMS;
	}
	
	public String getVrpImpostoIPI() {
		return vrpImpostoIPI;
	}
	
	public void setVrpImpostoIPI(String vrpImpostoIPI) {
		this.vrpImpostoIPI = vrpImpostoIPI;
	}
	
	public String getVrpImpostoISS() {
		return vrpImpostoISS;
	}
	
	public void setVrpImpostoISS(String vrpImpostoISS) {
		this.vrpImpostoISS = vrpImpostoISS;
	}

	public Integer getVvpCodigo() {
		return vvpCodigo;
	}

	public void setVvpCodigo(Integer vvpCodigo) {
		this.vvpCodigo = vvpCodigo;
	}

	public String getVvpValorProduto() {
		return vvpValorProduto;
	}

	public void setVvpValorProduto(String vvpValorProduto) {
		this.vvpValorProduto = vvpValorProduto;
	}

	public String getVvpDataCadastro() {
		return vvpDataCadastro;
	}

	public void setVvpDataCadastro(String vvpDataCadastro) {
		this.vvpDataCadastro = vvpDataCadastro;
	}
}
