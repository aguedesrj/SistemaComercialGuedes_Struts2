package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_PRODUTO")
@SequenceGenerator(name="SEQUENCE_PRODUTO", sequenceName = "GEN_TBL_PRODUTO_ID", allocationSize=1)
public class Produto implements Serializable {

	private static final long serialVersionUID = -4440944985791120185L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCE_PRODUTO")
	@Column(name="PRO_CODIGO")
	private Integer proCodigo;
	
	@Column(name="PRO_NOME", length=120, nullable=false)
	private String proNome;	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USU_CODIGO", nullable=false)
	private Usuario usuario;
	
	@Column(name="PRO_QUANTIDADEMINIMA")
	private Integer proQuantidadeMinima;
	
	@Column(name="PRO_QUANTIDADEMAXIMA")
	private Integer proQuantidadeMaxima;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CAT_CODIGO")
	private Categoria categoria;
	
//	@OneToOne
//	@JoinColumn(name="FOR_CODIGO")
//	private Fornecedor fornecedor;
	
	@Column(name="PRO_OBS", length=255)
	private String proObs;	
	
	@Column(name="PRO_CODIGOBARRAS", length=50)
	private String proCodigoBarras;
	
	@Column(name="PRO_FOTO")
	private byte[] proFoto;	
	
	@Column(name="PRO_DATACADASTRO", nullable=false)
	private Calendar proDataCadastro;
	
	@Column(name="PRO_DATAALTERACAO")
	private Calendar proDataAlteracao;	
	
	@OneToMany(mappedBy="produto", targetEntity=ValorVendaProduto.class, fetch = FetchType.EAGER)
	@JoinColumn(name="PRO_CODIGO", insertable=true, updatable=true)
	@Cascade(CascadeType.ALL)	
	@Fetch(FetchMode.SUBSELECT)
	private List<ValorVendaProduto> listaValorVendaProduto;

	public Integer getProCodigo() {
		return proCodigo;
	}

	public void setProCodigo(Integer proCodigo) {
		this.proCodigo = proCodigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getProQuantidadeMinima() {
		return proQuantidadeMinima;
	}

	public void setProQuantidadeMinima(Integer proQuantidadeMinima) {
		this.proQuantidadeMinima = proQuantidadeMinima;
	}

	public Integer getProQuantidadeMaxima() {
		return proQuantidadeMaxima;
	}

	public void setProQuantidadeMaxima(Integer proQuantidadeMaxima) {
		this.proQuantidadeMaxima = proQuantidadeMaxima;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

//	public Fornecedor getFornecedor() {
//		return fornecedor;
//	}
//
//	public void setFornecedor(Fornecedor fornecedor) {
//		this.fornecedor = fornecedor;
//	}

	public String getProNome() {
		return proNome;
	}

	public void setProNome(String proNome) {
		this.proNome = proNome;
	}

	public String getProObs() {
		return proObs;
	}

	public void setProObs(String proObs) {
		this.proObs = proObs;
	}

	public String getProCodigoBarras() {
		return proCodigoBarras;
	}

	public void setProCodigoBarras(String proCodigoBarras) {
		this.proCodigoBarras = proCodigoBarras;
	}

	public byte[] getProFoto() {
		return proFoto;
	}

	public void setProFoto(byte[] proFoto) {
		this.proFoto = proFoto;
	}

	public List<ValorVendaProduto> getListaValorVendaProduto() {
		return listaValorVendaProduto;
	}

	public void setListaValorVendaProduto(
			List<ValorVendaProduto> listaValorVendaProduto) {
		this.listaValorVendaProduto = listaValorVendaProduto;
	}

	public Calendar getProDataCadastro() {
		return proDataCadastro;
	}

	public void setProDataCadastro(Calendar proDataCadastro) {
		this.proDataCadastro = proDataCadastro;
	}

	public Calendar getProDataAlteracao() {
		return proDataAlteracao;
	}

	public void setProDataAlteracao(Calendar proDataAlteracao) {
		this.proDataAlteracao = proDataAlteracao;
	}
}
