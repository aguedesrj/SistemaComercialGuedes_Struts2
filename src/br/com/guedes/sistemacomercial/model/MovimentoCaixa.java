package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

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
@Table(name="TBL_MOVIMENTO_CAIXA")
public class MovimentoCaixa implements Serializable {

	private static final long serialVersionUID = 6902349145212788646L;

	@Id
	@Column(name="MVX_CODIGO")
	private Integer mvxCodigo;
	
	@Column(name="MVX_DATAABERTURA", nullable=false)
	private Calendar mvxDataAbertura;	

	@Column(name="MVX_DATAFECHAMENTO", nullable=false)
	private Calendar mvxDataFechamento;
	
	@Column(name="MVX_VALORABERTURA")
	private BigDecimal mvxValorAbertura;	

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="USU_CODIGO", nullable=false)
	private Usuario usuario;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="LOJ_CODIGO", nullable=false)
	private Loja loja;

	public Integer getMvxCodigo() {
		return mvxCodigo;
	}

	public void setMvxCodigo(Integer mvxCodigo) {
		this.mvxCodigo = mvxCodigo;
	}

	public Calendar getMvxDataAbertura() {
		return mvxDataAbertura;
	}

	public void setMvxDataAbertura(Calendar mvxDataAbertura) {
		this.mvxDataAbertura = mvxDataAbertura;
	}

	public Calendar getMvxDataFechamento() {
		return mvxDataFechamento;
	}

	public void setMvxDataFechamento(Calendar mvxDataFechamento) {
		this.mvxDataFechamento = mvxDataFechamento;
	}

	public BigDecimal getMvxValorAbertura() {
		return mvxValorAbertura;
	}

	public void setMvxValorAbertura(BigDecimal mvxValorAbertura) {
		this.mvxValorAbertura = mvxValorAbertura;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}	
}
