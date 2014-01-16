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
@Table(name="VW_VENDA")
public class VW_Venda implements Serializable {

	private static final long serialVersionUID = 5001600577905619320L;

	@Id
	@Column(name="VEN_CODIGO")
	private Integer venCodigo;
	
	@Column(name="USU_CODIGO")
	private Integer usuCodigo;	
	
	@Column(name="PES_NOME")
	private String pesNome;
	
	@Column(name="VEN_PORCENTODESCONTO")
	private BigDecimal venPorcentoDesconto;
	
	@Column(name="FPG_CODIGO")
	private Integer fpgCodigo;
	
	@Column(name="FPG_DESCRICAO")
	private String fpgDescricao;	
	
	@Column(name="VEN_VALORCOMISSAO")
	private BigDecimal venValorComissao;
	
	@Column(name="VEN_DATAINICIO")
	private Calendar venDataInicio;
	
	@Column(name="VEN_DATAFIM")
	private Calendar venDataFim;

	@Column(name="VEN_CPFCLIENTE")
	private String venCpfCliente;
	
	@Column(name="MVX_CODIGO")
	private Integer mvxCodigo;
	
	@Column(name="VEN_STATUS")
	private String venStatus;

	public Integer getVenCodigo() {
		return venCodigo;
	}

	public void setVenCodigo(Integer venCodigo) {
		this.venCodigo = venCodigo;
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

	public BigDecimal getVenPorcentoDesconto() {
		return venPorcentoDesconto;
	}

	public void setVenPorcentoDesconto(BigDecimal venPorcentoDesconto) {
		this.venPorcentoDesconto = venPorcentoDesconto;
	}

	public Integer getFpgCodigo() {
		return fpgCodigo;
	}

	public void setFpgCodigo(Integer fpgCodigo) {
		this.fpgCodigo = fpgCodigo;
	}

	public String getFpgDescricao() {
		return fpgDescricao;
	}

	public void setFpgDescricao(String fpgDescricao) {
		this.fpgDescricao = fpgDescricao;
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

	public Integer getMvxCodigo() {
		return mvxCodigo;
	}

	public void setMvxCodigo(Integer mvxCodigo) {
		this.mvxCodigo = mvxCodigo;
	}

	public String getVenStatus() {
		return venStatus;
	}

	public void setVenStatus(String venStatus) {
		this.venStatus = venStatus;
	}

	public String getVenCpfCliente() {
		return venCpfCliente;
	}

	public void setVenCpfCliente(String venCpfCliente) {
		this.venCpfCliente = venCpfCliente;
	}
}
