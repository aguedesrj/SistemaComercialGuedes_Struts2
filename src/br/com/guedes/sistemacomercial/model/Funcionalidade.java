package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_FUNCIONALIDADE")
public class Funcionalidade implements Serializable {

	private static final long serialVersionUID = 4445526944624094763L;

	@Id
	@Column(name="FUN_CODIGO")
	private Integer funCodigo;
	
	@Column(name="FUN_DESCRICAO", length=120, nullable=false)
	private String funDescricao;
	
	@Transient
	private List<AcaoFuncionalidade> listaAcaoFuncionalidade;

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

	public List<AcaoFuncionalidade> getListaAcaoFuncionalidade() {
		return listaAcaoFuncionalidade;
	}

	public void setListaAcaoFuncionalidade(
			List<AcaoFuncionalidade> listaAcaoFuncionalidade) {
		this.listaAcaoFuncionalidade = listaAcaoFuncionalidade;
	}
}
