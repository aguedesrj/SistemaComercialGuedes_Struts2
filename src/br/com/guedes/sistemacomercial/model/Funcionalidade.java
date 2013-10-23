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
@Table(name="TBL_FUNCIONALIDADE")
@SequenceGenerator(name="SEQUENCE_FUNCIONALIDADE", sequenceName = "GEN_TBL_FUNCIONALIDADE_ID", allocationSize=1)
public class Funcionalidade implements Serializable {

	private static final long serialVersionUID = 6645656494101056564L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_FUNCIONALIDADE")
	@Column(name="FUN_CODIGO")
	private Integer funCodigo;
	
	@Column(name="FUN_DESCRICAO", length=120, nullable=false)
	private String funDescricao;

	public Integer getFunCodigo() {
		return funCodigo;
	}

	public void setFunCodigo(Integer funCodigo) {
		this.funCodigo = funCodigo;
	}

	public String getFunDescricao() {
		return funDescricao;
	}

	public void setFunDescricao(String funDescricao) {
		this.funDescricao = funDescricao;
	}
}
