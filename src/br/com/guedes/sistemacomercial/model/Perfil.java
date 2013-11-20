package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_PERFIL")
@SequenceGenerator(name="SEQUENCE_PERFIL", sequenceName = "GEN_TBL_PERFIL_ID", allocationSize=1)
public class Perfil implements Serializable {

	private static final long serialVersionUID = 7878383982954619343L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PERFIL")
	@Column(name="PER_CODIGO")
	private Integer perCodigo;
	
	@Column(name="PER_NOME", length=80, nullable=false)
	private String perNome;
	
	@OneToMany(mappedBy = "perfil")
	private Set<PerfilFuncionalidade> listaPerfilFuncionalidade = new HashSet<PerfilFuncionalidade>();
	
	public Integer getPerCodigo() {
		return perCodigo;
	}

	public void setPerCodigo(Integer perCodigo) {
		this.perCodigo = perCodigo;
	}

	public String getPerNome() {
		return perNome;
	}

	public void setPerNome(String perNome) {
		this.perNome = perNome;
	}

	public Set<PerfilFuncionalidade> getListaPerfilFuncionalidade() {
		return listaPerfilFuncionalidade;
	}

	public void setListaPerfilFuncionalidade(
			Set<PerfilFuncionalidade> listaPerfilFuncionalidade) {
		this.listaPerfilFuncionalidade = listaPerfilFuncionalidade;
	}
}
