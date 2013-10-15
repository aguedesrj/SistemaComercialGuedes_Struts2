package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_TIPO_CONTATO")
public class TipoContato implements Serializable {

	private static final long serialVersionUID = 8024878744147760888L;

	@Id
	@Column(name="TPC_CODIGO")
	private Integer tpcCodigo;
	
	@Column(name="TPC_DESCRICAO", length=50)
	private String tpcDescricao;

	public Integer getTpcCodigo() {
		return tpcCodigo;
	}

	public void setTpcCodigo(Integer tpcCodigo) {
		this.tpcCodigo = tpcCodigo;
	}

	public String getTpcDescricao() {
		return tpcDescricao;
	}

	public void setTpcDescricao(String tpcDescricao) {
		this.tpcDescricao = tpcDescricao;
	}
}
