package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_FORMA_PAGAMENTO")
public class FormaPagamento implements Serializable {

	private static final long serialVersionUID = -474513536764796598L;

	@Id
	@Column(name="FPG_CODIGO")
	private Integer fpgCodigo;
	
	@Column(name="FPG_DESCRICAO", length=100, nullable=false)
	private String fpgDescricao;

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
}
