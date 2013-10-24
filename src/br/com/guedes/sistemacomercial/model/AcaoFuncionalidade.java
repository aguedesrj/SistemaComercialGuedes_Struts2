package br.com.guedes.sistemacomercial.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Embeddable 
@Table(name="TBL_ACAO_FUNCIONALIDADE")
public class AcaoFuncionalidade implements Serializable {

	private static final long serialVersionUID = 7070005188977668054L;

	@Id
	private AcaoFuncionalidadePk acaolFuncionalidadePk;

	public AcaoFuncionalidadePk getAcaolFuncionalidadePk() {
		return acaolFuncionalidadePk;
	}

	public void setAcaolFuncionalidadePk(AcaoFuncionalidadePk acaolFuncionalidadePk) {
		this.acaolFuncionalidadePk = acaolFuncionalidadePk;
	}
}
