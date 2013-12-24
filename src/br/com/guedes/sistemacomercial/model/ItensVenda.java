package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_ITENS_VENDA")
public class ItensVenda implements Serializable {

	private static final long serialVersionUID = -7310457355369293992L;

	@Id
	@Column(name="ITV_CODIGO")
	private Integer itvCodigo;
	
	@Column(name="ITV_QUANTIDADE", nullable=false)
	private Integer itvQuantidade;
	
	@Column(name="ITV_STATUS", nullable=false)
	private Integer itvStatus;	
	
	@Column(name="ITV_CODIGO_PRODUTO_LIDO", nullable=false)
	private Integer itvCodigoProdutoLido;	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USU_CODIGO")
	private Usuario usuario;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="VVP_CODIGO", nullable=false)
	private ValorVendaProduto valorVendaProduto;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="VEN_CODIGO", nullable=false)
	private Venda venda;

	public Integer getItvCodigo() {
		return itvCodigo;
	}

	public void setItvCodigo(Integer itvCodigo) {
		this.itvCodigo = itvCodigo;
	}

	public Integer getItvQuantidade() {
		return itvQuantidade;
	}

	public void setItvQuantidade(Integer itvQuantidade) {
		this.itvQuantidade = itvQuantidade;
	}

	public Integer getItvStatus() {
		return itvStatus;
	}

	public void setItvStatus(Integer itvStatus) {
		this.itvStatus = itvStatus;
	}

	public Integer getItvCodigoProdutoLido() {
		return itvCodigoProdutoLido;
	}

	public void setItvCodigoProdutoLido(Integer itvCodigoProdutoLido) {
		this.itvCodigoProdutoLido = itvCodigoProdutoLido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ValorVendaProduto getValorVendaProduto() {
		return valorVendaProduto;
	}

	public void setValorVendaProduto(ValorVendaProduto valorVendaProduto) {
		this.valorVendaProduto = valorVendaProduto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}	
}
