package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="VW_PRODUTO")
public class VW_Produto implements Serializable {

	private static final long serialVersionUID = -1817787357282614279L;

	@Id
	@Column(name="PRO_CODIGO")
	private Integer proCodigo;
	
	@Column(name="PRO_NOME", length=120, nullable=false)
	private String proNome;	
	
	@Column(name="PRO_CODIGOBARRAS", length=50)
	private String proCodigoBarras;
	
	@Column(name="PRO_DATACADASTRO", nullable=false)
	private Calendar proDataCadastro;
	
	@Column(name="VVP_VALORPRODUTO", nullable=false)
	private BigDecimal vvpValorProduto;

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

	public Calendar getProDataCadastro() {
		return proDataCadastro;
	}

	public void setProDataCadastro(Calendar proDataCadastro) {
		this.proDataCadastro = proDataCadastro;
	}

	public BigDecimal getVvpValorProduto() {
		return vvpValorProduto;
	}

	public void setVvpValorProduto(BigDecimal vvpValorProduto) {
		this.vvpValorProduto = vvpValorProduto;
	}	
}
