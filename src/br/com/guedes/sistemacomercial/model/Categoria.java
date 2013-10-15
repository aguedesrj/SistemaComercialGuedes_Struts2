package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_CATEGORIA")
@SequenceGenerator(name="SEQUENCE_CATEGORIA", sequenceName = "GEN_TBL_CATEGORIA_ID", allocationSize=1)
public class Categoria implements Serializable {

	private static final long serialVersionUID = 8024878744147760888L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_CATEGORIA")
	@Column(name="CAT_CODIGO")
	private Integer catCodigo;
	
	@Column(name="CAT_DESCRICAO", length=100)
	private String catDescricao;

	public Integer getCatCodigo() {
		return catCodigo;
	}

	public void setCatCodigo(Integer catCodigo) {
		this.catCodigo = catCodigo;
	}

	public String getCatDescricao() {
		return catDescricao;
	}

	public void setCatDescricao(String catDescricao) {
		this.catDescricao = catDescricao;
	}
}
