package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="TBL_FORNECEDOR")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = -8243840447348888755L;

	@Id
	@Column(name="PRO_CODIGO")
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
}
