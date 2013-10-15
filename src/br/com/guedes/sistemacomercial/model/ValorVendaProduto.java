package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_VALOR_VENDA_PRODUTO")
@SequenceGenerator(name="SEQUENCE_VALOR_VENDA_PRODUTO", sequenceName = "GEN_TBL_VALOR_VENDA_PRODUTO_ID", allocationSize=1)
public class ValorVendaProduto implements Serializable {

	private static final long serialVersionUID = 8629833511108643850L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_VALOR_VENDA_PRODUTO")
	@Column(name="VVP_CODIGO")
	private Integer vvpCodigo;
	
	@OneToOne
	@JoinColumn(name="PRO_CODIGO", nullable=false)
	private Produto produto;
	
	@Column(name="VVP_VALORPRODUTO", nullable=false)
	private BigDecimal vvpValorProduto;
	
	@Column(name="VVP_DATACADASTRO", nullable=false)
	private Calendar vvpDataCadastro;

	public Integer getVvpCodigo() {
		return vvpCodigo;
	}

	public void setVvpCodigo(Integer vvpCodigo) {
		this.vvpCodigo = vvpCodigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getVvpValorProduto() {
		return vvpValorProduto;
	}

	public void setVvpValorProduto(BigDecimal vvpValorProduto) {
		this.vvpValorProduto = vvpValorProduto;
	}

	public Calendar getVvpDataCadastro() {
		return vvpDataCadastro;
	}

	public void setVvpDataCadastro(Calendar vvpDataCadastro) {
		this.vvpDataCadastro = vvpDataCadastro;
	}
}
