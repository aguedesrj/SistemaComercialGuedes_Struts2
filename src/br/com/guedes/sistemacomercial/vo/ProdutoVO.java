package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;
import java.util.List;

public class ProdutoVO implements Serializable {

	private static final long serialVersionUID = -3467922302791462526L;

	private Integer proCodigo;
	private String proNome;
	private String proCodigoBarras;
	private String proDataCadastro;
	private String proDataAlteracao;
	private String vvpValorProduto;
	private Integer proQuantidadeMinima;
	private Integer proQuantidadeMaxima;
	private String proObs;
	private GenericVO categoria = new GenericVO();
	private GenericVO fornecedor = new GenericVO();
	private List<ValoresProdutoVO> listaValoresProduto;
	
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
	
	public String getProDataAlteracao() {
		return proDataAlteracao;
	}
	
	public void setProDataAlteracao(String proDataAlteracao) {
		this.proDataAlteracao = proDataAlteracao;
	}
	
	public String getVvpValorProduto() {
		return vvpValorProduto;
	}
	
	public void setVvpValorProduto(String vvpValorProduto) {
		this.vvpValorProduto = vvpValorProduto;
	}
	
	public Integer getProQuantidadeMinima() {
		return proQuantidadeMinima;
	}
	
	public void setProQuantidadeMinima(Integer proQuantidadeMinima) {
		this.proQuantidadeMinima = proQuantidadeMinima;
	}
	
	public Integer getProQuantidadeMaxima() {
		return proQuantidadeMaxima;
	}
	
	public void setProQuantidadeMaxima(Integer proQuantidadeMaxima) {
		this.proQuantidadeMaxima = proQuantidadeMaxima;
	}
	
	public String getProObs() {
		return proObs;
	}
	
	public void setProObs(String proObs) {
		this.proObs = proObs;
	}

	public GenericVO getCategoria() {
		return categoria;
	}

	public void setCategoria(GenericVO categoria) {
		this.categoria = categoria;
	}

	public GenericVO getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(GenericVO fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<ValoresProdutoVO> getListaValoresProduto() {
		return listaValoresProduto;
	}

	public void setListaValoresProduto(List<ValoresProdutoVO> listaValoresProduto) {
		this.listaValoresProduto = listaValoresProduto;
	}
}
