package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_VENDA")
public class Venda implements Serializable {

	private static final long serialVersionUID = 8167044229418061536L;

	@Id
	@Column(name="VEN_CODIGO")
	private Integer venCodigo;
	
	@Column(name="VEN_PORCENTODESCONTO")
	private BigDecimal venPorcentoDesconto;
	
	@Column(name="VEN_VALORCOMISSAO")
	private BigDecimal venValorComissao;
	
	@Column(name="VEN_DATAINICIO", nullable=false)
	private Calendar venDataInicio;	

	@Column(name="VEN_DATAFIM")
	private Calendar venDataFim;
	
	@Column(name="VEN_STATUS")
	private Calendar venStatus;	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USU_CODIGO", nullable=false)
	private Usuario usuario;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="FPG_CODIGO")
	private FormaPagamento formaPagamento;	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="MVX_CODIGO", nullable=false)
	private MovimentoCaixa movimentoCaixa;
	
	@OneToMany(mappedBy="venda", targetEntity=ItensVenda.class, fetch = FetchType.EAGER)
	@JoinColumn(name="VEN_CODIGO", insertable=true, updatable=true)
	@Cascade(CascadeType.ALL)	
	@Fetch(FetchMode.SUBSELECT)
	private List<ItensVenda> listaItensVenda;	

	public Integer getVenCodigo() {
		return venCodigo;
	}

	public void setVenCodigo(Integer venCodigo) {
		this.venCodigo = venCodigo;
	}

	public BigDecimal getVenPorcentoDesconto() {
		return venPorcentoDesconto;
	}

	public void setVenPorcentoDesconto(BigDecimal venPorcentoDesconto) {
		this.venPorcentoDesconto = venPorcentoDesconto;
	}

	public BigDecimal getVenValorComissao() {
		return venValorComissao;
	}

	public void setVenValorComissao(BigDecimal venValorComissao) {
		this.venValorComissao = venValorComissao;
	}

	public Calendar getVenDataInicio() {
		return venDataInicio;
	}

	public void setVenDataInicio(Calendar venDataInicio) {
		this.venDataInicio = venDataInicio;
	}

	public Calendar getVenDataFim() {
		return venDataFim;
	}

	public void setVenDataFim(Calendar venDataFim) {
		this.venDataFim = venDataFim;
	}

	public Calendar getVenStatus() {
		return venStatus;
	}

	public void setVenStatus(Calendar venStatus) {
		this.venStatus = venStatus;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public MovimentoCaixa getMovimentoCaixa() {
		return movimentoCaixa;
	}

	public void setMovimentoCaixa(MovimentoCaixa movimentoCaixa) {
		this.movimentoCaixa = movimentoCaixa;
	}

	public List<ItensVenda> getListaItensVenda() {
		return listaItensVenda;
	}

	public void setListaItensVenda(List<ItensVenda> listaItensVenda) {
		this.listaItensVenda = listaItensVenda;
	}
}
