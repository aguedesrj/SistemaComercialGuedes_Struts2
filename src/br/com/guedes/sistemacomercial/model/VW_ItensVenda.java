package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="VW_ITENS_VENDA")
public class VW_ItensVenda implements Serializable {

	private static final long serialVersionUID = 7511236225345949218L;

	@Id
	@Column(name="ITV_CODIGO")
	private Integer itvCodigo;
	
	@Column(name="VVP_CODIGO")
	private Integer vvpCodigo;	
	
	@Column(name="VEN_CODIGO")
	private Integer venCodigo;
	
	@Column(name="ITV_QUANTIDADE")
	private Integer itvQuantidade;
	
	@Column(name="USU_CODIGO")
	private Integer usuCodigo;
	
	@Column(name="PES_NOME")
	private String pesNome;	
	
	@Column(name="ITV_CODIGO_PRODUTO_LIDO")
	private String itvCodigoProdutoLido;
	
	@Column(name="PRO_CODIGO")
	private Integer proCodigo;
	
	@Column(name="PRO_NOME")
	private String proNome;
	
	@Column(name="ITV_STATUS")
	private Integer ivtStatus;
	
	@Column(name="VVP_VALORPRODUTO")
	private BigDecimal vvpValorProduto;

	public Integer getItvCodigo() {
		return itvCodigo;
	}

	public void setItvCodigo(Integer itvCodigo) {
		this.itvCodigo = itvCodigo;
	}

	public Integer getVvpCodigo() {
		return vvpCodigo;
	}

	public void setVvpCodigo(Integer vvpCodigo) {
		this.vvpCodigo = vvpCodigo;
	}

	public Integer getVenCodigo() {
		return venCodigo;
	}

	public void setVenCodigo(Integer venCodigo) {
		this.venCodigo = venCodigo;
	}

	public Integer getItvQuantidade() {
		return itvQuantidade;
	}

	public void setItvQuantidade(Integer itvQuantidade) {
		this.itvQuantidade = itvQuantidade;
	}

	public Integer getUsuCodigo() {
		return usuCodigo;
	}

	public void setUsuCodigo(Integer usuCodigo) {
		this.usuCodigo = usuCodigo;
	}

	public String getPesNome() {
		return pesNome;
	}

	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}

	public String getItvCodigoProdutoLido() {
		return itvCodigoProdutoLido;
	}

	public void setItvCodigoProdutoLido(String itvCodigoProdutoLido) {
		this.itvCodigoProdutoLido = itvCodigoProdutoLido;
	}

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

	public Integer getIvtStatus() {
		return ivtStatus;
	}

	public void setIvtStatus(Integer ivtStatus) {
		this.ivtStatus = ivtStatus;
	}

	public BigDecimal getVvpValorProduto() {
		return vvpValorProduto;
	}

	public void setVvpValorProduto(BigDecimal vvpValorProduto) {
		this.vvpValorProduto = vvpValorProduto;
	}
}
