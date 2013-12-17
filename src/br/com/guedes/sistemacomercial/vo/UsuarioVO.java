package br.com.guedes.sistemacomercial.vo;

import java.io.Serializable;
import java.util.List;

public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = -3971155095466191243L;

	private Integer usuCodigo;
	private Integer pesCodigo;
	private String usuLogin;
	private String usuSenha;
	private String usuConfirmaSenha;
	private String pesNome;
	private String pesDataCadastro;
	private String pesDataAlteracao;
	private List<PerfilVO> listaPerfil;
	
	public Integer getUsuCodigo() {
		return usuCodigo;
	}
	
	public void setUsuCodigo(Integer usuCodigo) {
		this.usuCodigo = usuCodigo;
	}
	
	public Integer getPesCodigo() {
		return pesCodigo;
	}
	
	public void setPesCodigo(Integer pesCodigo) {
		this.pesCodigo = pesCodigo;
	}
	
	public String getUsuLogin() {
		return usuLogin;
	}
	
	public void setUsuLogin(String usuLogin) {
		this.usuLogin = usuLogin;
	}
	
	public String getUsuSenha() {
		return usuSenha;
	}
	
	public void setUsuSenha(String usuSenha) {
		this.usuSenha = usuSenha;
	}
	
	public String getUsuConfirmaSenha() {
		return usuConfirmaSenha;
	}
	
	public void setUsuConfirmaSenha(String usuConfirmaSenha) {
		this.usuConfirmaSenha = usuConfirmaSenha;
	}
	
	public String getPesNome() {
		return pesNome;
	}
	
	public void setPesNome(String pesNome) {
		this.pesNome = pesNome;
	}

	public String getPesDataCadastro() {
		return pesDataCadastro;
	}

	public void setPesDataCadastro(String pesDataCadastro) {
		this.pesDataCadastro = pesDataCadastro;
	}

	public String getPesDataAlteracao() {
		return pesDataAlteracao;
	}

	public void setPesDataAlteracao(String pesDataAlteracao) {
		this.pesDataAlteracao = pesDataAlteracao;
	}

	public List<PerfilVO> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<PerfilVO> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}
}
