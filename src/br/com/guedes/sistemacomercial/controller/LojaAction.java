package br.com.guedes.sistemacomercial.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemacomercial.facade.LojaFacade;
import br.com.guedes.sistemacomercial.model.Caixa;
import br.com.guedes.sistemacomercial.model.Loja;
import br.com.guedes.sistemacomercial.vo.CaixaVO;
import br.com.guedes.sistemacomercial.vo.LojaVO;

/**
 * Action Loja.
 * 
 * @author AndréLessa
 *
 */
@Controller
@Scope("request")
public class LojaAction extends BaseAction {

	private static final long serialVersionUID = 2251583501532473222L;
	private static final Logger LOGGER = Logger.getLogger(LojaAction.class);
	private static final String SESSION_LISTA_CAIXA  = "SESSION_LISTA_CAIXA";

	private String mensagemUsuario;
	private LojaVO loja = new LojaVO();
	private List<CaixaVO> listaCaixas;
	private CaixaVO caixa = new CaixaVO();
	
	@Autowired
	private LojaFacade lojaFacade;
	
	/**
	 * 
	 * @return String
	 */
	public String manutencaoLoja() {
		try {
    		// remove da session a lista de caixa.
    		this.getRequest().getSession().removeAttribute(SESSION_LISTA_CAIXA);
			Loja loja = lojaFacade.buscarLoja();
			if (loja != null) {
				getLoja().setLojCodigo(loja.getLojCodigo());
				getLoja().setLojNome(loja.getLojNome());
				// lista de caixas.
				listaCaixas = new ArrayList<CaixaVO>();
				for (Caixa caixa: loja.getListaCaixa()) {
					CaixaVO caixaVO = new CaixaVO();
					caixaVO.setCaiCodigo(caixa.getCaiCodigo());
					caixaVO.setCaiDescricao(caixa.getCaiDescricao());
					caixaVO.setCaiObs(caixa.getCaiObs());
					listaCaixas.add(caixaVO);
				}
			}
			return SUCCESS;
		} catch (Exception e) {
			LOGGER.fatal(e.getMessage(), e);
			return ERROR;
		}
	}
	
	/**
	 * 
	 * @return String
	 */
	public String salvar() {
		try {
			// validar dados.
			
			// seta os dados.
			Loja loja = new Loja();
			loja.setLojCodigo(getLoja().getLojCodigo());
			loja.setLojNome(getLoja().getLojNome().trim());
			loja.setListaCaixa(new ArrayList<Caixa>());
			// lista de caixa.
			for (CaixaVO caixaVO: getListaCaixas()) {
				Caixa caixa = new Caixa();
				caixa.setCaiCodigo(caixaVO.getCaiCodigo());
				caixa.setCaiDescricao(caixaVO.getCaiDescricao().trim());
				caixa.setCaiObs(caixaVO.getCaiObs().trim());
				loja.getListaCaixa().add(caixa);
			}
			// salvando.
			lojaFacade.salvar(loja);
			return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("Erro ao gravar a Loja.");
			return ERROR;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String incluirCaixa() {
		try {
	  		setListaCaixas((List<CaixaVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_CAIXA));
	  		if (getListaCaixas() == null) {
	  			setListaCaixas(new ArrayList<CaixaVO>());
	  		}
	  		getListaCaixas().add(getCaixa());
	  		this.getRequest().getSession().setAttribute(SESSION_LISTA_CAIXA, getListaCaixas());
	  		return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Erro ao incluir caixa.", e);
			setMensagemUsuario("Erro ao incluir caixa.");
			return ERROR;
		}
	}	
	
	public String getMensagemUsuario() {
		return mensagemUsuario;
	}
	
	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}
	
	public LojaVO getLoja() {
		return loja;
	}
	
	public void setLoja(LojaVO loja) {
		this.loja = loja;
	}
	
	public List<CaixaVO> getListaCaixas() {
		return listaCaixas;
	}
	
	public void setListaCaixas(List<CaixaVO> listaCaixas) {
		this.listaCaixas = listaCaixas;
	}
	
	public CaixaVO getCaixa() {
		return caixa;
	}
	
	public void setCaixa(CaixaVO caixa) {
		this.caixa = caixa;
	}
}
