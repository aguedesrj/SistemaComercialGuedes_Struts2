package br.com.guedes.sistemacomercial.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemacomercial.facade.VendaFacade;
import br.com.guedes.sistemacomercial.model.ItensVenda;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.Util;
import br.com.guedes.sistemacomercial.vo.VendaVO;

/**
 * Action Produto.
 * 
 * @author AndréLessa
 *
 */
@Controller
@Scope("request")
public class VendaAction extends BaseAction {

	private static final long serialVersionUID = 3327850756656678644L;
	private static final Logger LOGGER = Logger.getLogger(VendaAction.class);

	private String mensagemUsuario;
	private VendaVO venda = new VendaVO();
	private List<VendaVO> listaVendas;
	
	@Autowired
	private VendaFacade vendaFacade;	
	
	public String iniciarPequisaGraficoHome() {
		
		Calendar dataInicio = Calendar.getInstance();
		Calendar dataFim = Calendar.getInstance();
		dataInicio.add(Calendar.DATE, -16);
		dataFim.add(Calendar.DATE, 1);
		
		getVenda().setVenDataInicio(Util.converterCalendarParaString(dataInicio, Util.simpleDateFormatData));
		getVenda().setVenDataFim(Util.converterCalendarParaString(dataFim, Util.simpleDateFormatData));
		
		return SUCCESS;
	}
	
    public String executarPesquisaGraficoPorPeriodo() {
    	try {
    		LOGGER.info("Action executando pesquisa de gráfico em vendas.");
    		
    		List<ItensVenda> listaItensVenda = vendaFacade.obterVendasPorPeriodo(getVenda().getVenDataInicio(), getVenda().getVenDataFim());
    		setListaVendas(new ArrayList<VendaVO>());
    		for (ItensVenda itensVenda: listaItensVenda) {
    			VendaVO vendaVO = new VendaVO();
    			vendaVO.setItvQuantidade(itensVenda.getItvQuantidade());
    			vendaVO.setVenDataFim(Util.converterCalendarParaString(itensVenda.getVenda().getVenDataFim(), Util.simpleDateFormatDataHora));
    			
    			getListaVendas().add(vendaVO);
    		}
    		
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("Não foi efetuar a pesquisa.");
			}
			return ERROR;
		} 
    }

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public VendaVO getVenda() {
		return venda;
	}

	public void setVenda(VendaVO venda) {
		this.venda = venda;
	}

	public List<VendaVO> getListaVendas() {
		return listaVendas;
	}

	public void setListaVendas(List<VendaVO> listaVendas) {
		this.listaVendas = listaVendas;
	}
}
