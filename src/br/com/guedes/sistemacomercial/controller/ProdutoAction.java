package br.com.guedes.sistemacomercial.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemacomercial.facade.CategoriaFacade;
import br.com.guedes.sistemacomercial.facade.ProdutoFacade;
import br.com.guedes.sistemacomercial.facade.UsuarioFacade;
import br.com.guedes.sistemacomercial.model.Categoria;
import br.com.guedes.sistemacomercial.model.Produto;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.model.VW_Produto;
import br.com.guedes.sistemacomercial.model.ValorVendaProduto;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.Constantes;
import br.com.guedes.sistemacomercial.util.Util;
import br.com.guedes.sistemacomercial.vo.GenericVO;
import br.com.guedes.sistemacomercial.vo.ProdutoVO;
import br.com.guedes.sistemacomercial.vo.ValoresProdutoVO;

/**
 * Action Produto.
 * 
 * @author AndréLessa
 *
 */
@Controller
@Scope("request")
public class ProdutoAction extends BaseAction {

	private static final long serialVersionUID = 4544489506705367837L;
	private static final String SESSION_LISTA_VALOR_PRODUTO  = "SESSION_LISTA_VALOR_PRODUTO";
	private static final Logger LOGGER = Logger.getLogger(ProdutoAction.class);

	private String mensagemUsuario;
	private ProdutoVO produto = new ProdutoVO();
	private ValoresProdutoVO valoresProdutoVO = new ValoresProdutoVO();
	private List<ProdutoVO> listaProduto;
	private List<GenericVO> listaCategoria;
	private List<GenericVO> listaFornecedor;
	private List<ValoresProdutoVO> listaValoresProdutoVO;
	
	@Autowired
	private UsuarioFacade usuarioFacade;
	
	@Autowired
	private CategoriaFacade categoriaFacade;	
	
	@Autowired
	private ProdutoFacade produtoFacade;	
	
    public String iniciarPesquisa() {
    	
    	return SUCCESS;
    }  
	
	public String executarPesquisa() {
    	
    	try {
    		Produto produto = new Produto();
    		produto.setProNome(getProduto().getProNome());

    		List<VW_Produto> listaProduto = produtoFacade.pesquisarProdutoPorCriterios(produto);
    		if (listaProduto == null || listaProduto.isEmpty()) {
    			setMensagemUsuario("Produto não encontrado.");
    			return ERROR;
    		} else {
    			// popular lista Produtos.
    			setListaProduto(new ArrayList<ProdutoVO>());
    			for (VW_Produto vwProduto: listaProduto) {
    				ProdutoVO produtoVO = new ProdutoVO();
    				
    				produtoVO.setProCodigo(vwProduto.getProCodigo());
    				produtoVO.setProNome(vwProduto.getProNome());
    				produtoVO.setProCodigoBarras(vwProduto.getProCodigoBarras());
    				produtoVO.setProDataCadastro(Util.converterCalendarParaString(vwProduto.getProDataCadastro()));
    				produtoVO.setVvpValorProduto(Util.converterBigDecimalParaStringDecimal(vwProduto.getVvpValorProduto()));
    				
    				getListaProduto().add(produtoVO);
    			}
    		}
    		return SUCCESS;
			
		} catch (Exception e) {
			LOGGER.error("Erro ao pesquisar Produto.", e);
			setMensagemUsuario("Erro ao pesquisar Produto.");
			return ERROR;
		}
    } 	
	
	public String detalhar() {

    	try {
    		Produto produto = produtoFacade.obterProdutoPorId(getProduto().getProCodigo());
    		if (produto == null) {
    			setMensagemUsuario("Produto não encontrado.");
    			return ERROR;
    		} else {
    			// seta dados do Produto.
    			preencherProduto(produto);
    		}
    		return SUCCESS;
			
		} catch (Exception e) {
			LOGGER.error("Erro ao detalhar Produto.", e);
			setMensagemUsuario("Erro ao detalhar Produto.");
			return ERROR;
		}
	}
	
	/**
	 * 
	 * @return String
	 */
    public String iniciarManutencao() {
    	
    	try {
    		// remove da session a lista de valores do produto.
    		this.getRequest().getSession().removeAttribute(SESSION_LISTA_VALOR_PRODUTO);
    		
    		// seta as lista de Categoria e Fornecedor.
    		preencherListaCategoriaFornecedor();
    		
    		return SUCCESS;
		} catch (Exception e) {
			addActionError("Erro ao iniciar manutenção do Produto.");
			return ERROR;
		}
    }
    
	@SuppressWarnings("unchecked")
	public String incluirValorProduto() {
		try {
  		
	  		setListaValoresProdutoVO((List<ValoresProdutoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_VALOR_PRODUTO));
	  		if (getListaValoresProdutoVO() == null) {
	  			setListaValoresProdutoVO(new ArrayList<ValoresProdutoVO>());
	  		}
	  		
	  		getValoresProdutoVO().setVvpDataCadastro(Util.obterDataHoraAtual());
	  		getListaValoresProdutoVO().add(getValoresProdutoVO());
	  		
	  		this.getRequest().getSession().setAttribute(SESSION_LISTA_VALOR_PRODUTO, getListaValoresProdutoVO());
	  		
	  		return SUCCESS;
  		
		} catch (Exception e) {
			
			LOGGER.error("Erro ao incluir valor ao Produto.", e);
			setMensagemUsuario("Erro ao incluir valor ao Produto.");
			return ERROR;
		}
	}    
	
	@SuppressWarnings("unchecked")
	public String salvar() {
  	
		try {
			// validação dos dados.
			
			// seta os dados.
			Produto produto = new Produto();
			produto.setProCodigo(getProduto().getProCodigo());
			if (produto.getProCodigo() != null && produto.getProCodigo() > 0) {
				produto = produtoFacade.obterProdutoPorId(produto.getProCodigo());
				produto.setProDataAlteracao(Calendar.getInstance());
			} else {
				produto.setProDataCadastro(Calendar.getInstance());
			}
			produto.setProNome(getProduto().getProNome().trim());
			produto.setProQuantidadeMinima(getProduto().getProQuantidadeMinima());
			produto.setProQuantidadeMaxima(getProduto().getProQuantidadeMaxima());
	  		if (getProduto().getCategoria() != null && getProduto().getCategoria().getGenCodigo() > 0) {
	  			produto.setCategoria(new Categoria());
	  			produto.getCategoria().setCatCodigo(getProduto().getCategoria().getGenCodigo());
	  		}			
//	  		if (getProduto().getFornecedor() != null && getProduto().getFornecedor().getGenCodigo() > 0) {
//	  			produto.setfCategoria(new Categoria());
//	  			produto.getCategoria().setCatCodigo(getProduto().getCategoria().getGenCodigo());
//	  		}	
	  		produto.setProObs(getProduto().getProObs().trim());
	  		produto.setProCodigoBarras(getProduto().getProCodigoBarras().trim());
	  		setListaValoresProdutoVO((List<ValoresProdutoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_VALOR_PRODUTO));
	  		if (getListaValoresProdutoVO() != null && getListaValoresProdutoVO().size() > 0) {
	  			produto.setListaValorVendaProduto(new ArrayList<ValorVendaProduto>());
	  			for (ValoresProdutoVO valoresProdutoVO: getListaValoresProdutoVO()) {
	  				ValorVendaProduto valorVendaProduto = new ValorVendaProduto();
	  				valorVendaProduto.setProduto(produto);
	  				valorVendaProduto.setVvpCodigo(valoresProdutoVO.getVvpCodigo());
	  				valorVendaProduto.setVvpDataCadastro(Util.converterStringParaCalendar(valoresProdutoVO.getVvpDataCadastro()));
	  				valorVendaProduto.setVvpValorProduto(Util.converterDecimalStringParaBigDecimal(valoresProdutoVO.getVvpValorProduto()));
	  				
	  				produto.getListaValorVendaProduto().add(valorVendaProduto);
	  			}
	  		}
	  		produto.setUsuario((Usuario) this.getRequest().getSession().getAttribute(Constantes.KEY_USUARIO_SESSION));
	  		
	  		// salvar.
	  		produtoFacade.salvar(produto);
	  		
	  		// remove da session a lista de valores do produto.
	  		this.getRequest().getSession().removeAttribute(SESSION_LISTA_VALOR_PRODUTO);
	  		
	  		return SUCCESS;
			
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("Erro ao gravar o Produto.");
			}
			return ERROR;
		}
	}	
    
	/**
	 * 
	 * @return String
	 */
	public String iniciarAlteracao() {
    	
    	try {
    		// remove da session a lista de valores do produto.
    		this.getRequest().getSession().removeAttribute(SESSION_LISTA_VALOR_PRODUTO);
    		
    		Produto produto = produtoFacade.obterProdutoPorId(getProduto().getProCodigo());
    		if (produto == null) {
    			setMensagemUsuario("Produto não encontrado.");
    			return ERROR;
    		}
    		
			// seta dados do Produto.
			preencherProduto(produto);
			
    		// seta a lista de valores na session.
    		this.getRequest().getSession().setAttribute(SESSION_LISTA_VALOR_PRODUTO, getProduto().getListaValoresProduto());
    		
    		// seta as lista de Categoria e Fornecedor.
    		preencherListaCategoriaFornecedor();
    		
    		return SUCCESS;
			
		} catch (Exception e) {
			
			addActionError("Erro ao iniciar alteração do Produto.");
			return ERROR;
		}
    }  
	
	private void preencherProduto(Produto produto) throws Exception {
		getProduto().setProDataCadastro(Util.converterCalendarParaString(produto.getProDataCadastro()));
		getProduto().setProDataAlteracao(Util.converterCalendarParaString(produto.getProDataAlteracao()));
		getProduto().setProNome(produto.getProNome());
		getProduto().setProCodigoBarras(produto.getProCodigoBarras());
		//getProduto().setForNome(produto.getProCodigoBarras());
		if (produto.getCategoria() != null) {
			getProduto().setCategoria(new GenericVO());
			getProduto().getCategoria().setGenCodigo(produto.getCategoria().getCatCodigo());
			getProduto().getCategoria().setGenDescricao(produto.getCategoria().getCatDescricao());
		}
		getProduto().setProQuantidadeMinima(produto.getProQuantidadeMinima());
		getProduto().setProQuantidadeMaxima(produto.getProQuantidadeMaxima());
		getProduto().setProObs(produto.getProObs());
		getProduto().setListaValoresProduto(new ArrayList<ValoresProdutoVO>());
		for (ValorVendaProduto valorVendaProduto: produto.getListaValorVendaProduto()) {
			ValoresProdutoVO valoresProdutoVO = new ValoresProdutoVO();
			
			valoresProdutoVO.setVvpCodigo(valorVendaProduto.getVvpCodigo());
			//valoresProdutoVO.setVrpImpostoICMS(Util.converterBigDecimalParaStringDecimal(valorVendaProduto.getVvpValorProduto()));
			valoresProdutoVO.setVvpValorProduto(Util.converterBigDecimalParaStringDecimal(valorVendaProduto.getVvpValorProduto()));
			valoresProdutoVO.setVvpDataCadastro(Util.converterCalendarParaString(valorVendaProduto.getVvpDataCadastro()));
			getProduto().getListaValoresProduto().add(valoresProdutoVO);
		}
	}
	
	private void preencherListaCategoriaFornecedor() throws Exception {
		// obter lista de Categorias.
		setListaCategoria(new ArrayList<GenericVO>());
		List<Categoria> listaCategorias = categoriaFacade.listaCategoria();
		if (listaCategorias != null) {
			for (Categoria categoria: listaCategorias) {
				GenericVO genericVO = new GenericVO();
				genericVO.setGenCodigo(categoria.getCatCodigo());
				genericVO.setGenDescricao(categoria.getCatDescricao());
				getListaCategoria().add(genericVO);
			}
		}
		// obter lista de Fornecedores.
		setListaFornecedor(new ArrayList<GenericVO>());
	}
    
    @SuppressWarnings("unchecked")
	public String buscarListaValoresProduto() {
    	
    	try {
    		
    		// buscar da session a lista de valores do Produto.
    		setListaValoresProdutoVO((List<ValoresProdutoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_VALOR_PRODUTO));
    		
    		return SUCCESS;
			
		} catch (Exception e) {
			
			setMensagemUsuario("Erro ao carregar a lista de valores para alteração do Produto.");
			return ERROR;
		}
    }     
    
    
    

  
    
   
    
//    private void preencherValoresProdutoParaSalvar() throws Exception {
//    	getProduto().setListaValorVendaProduto(new ArrayList<ValorVendaProduto>());
//    	for (ValoresProdutoVO valoresProdutoVO: getListaValoresProdutoVO()) {
//    		ValorVendaProduto valorVendaProduto = new  ValorVendaProduto();
//    		valorVendaProduto.setProduto(getProduto());
//    		valorVendaProduto.setVvpCodigo(valoresProdutoVO.getVvpCodigo());
//    		valorVendaProduto.setVvpDataCadastro(Util.converterStringParaCalendar(valoresProdutoVO.getVvpDataCadastro()));
//    		valorVendaProduto.setVvpValorProduto(Util.converterDecimalStringParaBigDecimal(valoresProdutoVO.getVvpValorProduto()));
//    		
//    		getProduto().getListaValorVendaProduto().add(valorVendaProduto);
//    	}
//    }
//    
//    private void preencherValoresProdutoParaAlterar() throws Exception {
//    	setListaValoresProdutoVO(new ArrayList<ValoresProdutoVO>());
//    	for (ValorVendaProduto valorVendaProduto: getProduto().getListaValorVendaProduto()) {
//    		ValoresProdutoVO valoresProdutoVO = new  ValoresProdutoVO();
//    		valoresProdutoVO.setVvpCodigo(valorVendaProduto.getVvpCodigo());
//    		valoresProdutoVO.setVvpDataCadastro(Util.converterCalendarParaString(valorVendaProduto.getVvpDataCadastro()));
//    		valoresProdutoVO.setVvpValorProduto(Util.converterBigDecimalParaStringDecimal(valorVendaProduto.getVvpValorProduto()));
//    		
//    		getListaValoresProdutoVO().add(valoresProdutoVO);
//    	}
//    }    

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public List<GenericVO> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<GenericVO> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public List<GenericVO> getListaFornecedor() {
		return listaFornecedor;
	}

	public void setListaFornecedor(List<GenericVO> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public List<ValoresProdutoVO> getListaValoresProdutoVO() {
		return listaValoresProdutoVO;
	}

	public void setListaValoresProdutoVO(
			List<ValoresProdutoVO> listaValoresProdutoVO) {
		this.listaValoresProdutoVO = listaValoresProdutoVO;
	}

	public ValoresProdutoVO getValoresProdutoVO() {
		return valoresProdutoVO;
	}

	public void setValoresProdutoVO(ValoresProdutoVO valoresProdutoVO) {
		this.valoresProdutoVO = valoresProdutoVO;
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	public List<ProdutoVO> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<ProdutoVO> listaProduto) {
		this.listaProduto = listaProduto;
	}
}
