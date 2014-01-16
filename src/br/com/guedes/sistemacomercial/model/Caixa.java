package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="TBL_CAIXA")
@SequenceGenerator(name="SEQUENCE_CAIXA", sequenceName = "GEN_TBL_CAIXA_ID", allocationSize=1)
public class Caixa implements Serializable {

	private static final long serialVersionUID = -511396479771807256L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_CAIXA")
	@Column(name="CAI_CODIGO")
	private Integer caiCodigo;
	
	@Column(name="CAI_DESCRICAO", length=20, nullable=false)
	private String caiDescricao;	
	
	@Column(name="CAI_OBS", length=255)
	private String caiObs;	
	
	@Column(name="CAI_STATUS", length=1, nullable=false)
	private String carStatus;		
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="LOJ_CODIGO", nullable=false)
	private Loja loja;

	public Integer getCaiCodigo() {
		return caiCodigo;
	}

	public void setCaiCodigo(Integer caiCodigo) {
		this.caiCodigo = caiCodigo;
	}

	public String getCaiDescricao() {
		return caiDescricao;
	}

	public void setCaiDescricao(String caiDescricao) {
		this.caiDescricao = caiDescricao;
	}

	public String getCaiObs() {
		return caiObs;
	}

	public void setCaiObs(String caiObs) {
		this.caiObs = caiObs;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}	
}
