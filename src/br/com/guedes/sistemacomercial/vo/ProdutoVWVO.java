package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;

public class ProdutoVWVO implements Serializable {

	private static final long serialVersionUID = 5720180980601077663L;
	
	private Integer proCodigo;
	private String proNome;
	private String proCodigoBarras;
	private String proDataCadastro;
	private String vvpValorProduto;
	
	public Integer getProCodigo() {
		return proCodigo;
	}
	
	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}
	
	public String getProNome() {
		return proNome;
	}
	
	public void setProNome(String proNome) {
		this.proNome = proNome;
	}
	
	public String getProCodigoBarras() {
		return proCodigoBarras;
	}
	
	public void setProCodigoBarras(String proCodigoBarras) {
		this.proCodigoBarras = proCodigoBarras;
	}
	
	public String getProDataCadastro() {
		return proDataCadastro;
	}
	
	public void setProDataCadastro(String proDataCadastro) {
		this.proDataCadastro = proDataCadastro;
	}
	
	public String getVvpValorProduto() {
		return vvpValorProduto;
	}
	
	public void setVvpValorProduto(String vvpValorProduto) {
		this.vvpValorProduto = vvpValorProduto;
	}
}
