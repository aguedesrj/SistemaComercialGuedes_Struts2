package br.com.guedes.sistemacomercial.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemacomercial.facade.CategoriaFacade;
import br.com.guedes.sistemacomercial.facade.ProdutoFacade;
import br.com.guedes.sistemacomercial.facade.UsuarioFacade;
import br.com.guedes.sistemacomercial.model.Categoria;
import br.com.guedes.sistemacomercial.model.Fornecedor;
import br.com.guedes.sistemacomercial.model.Produto;
import br.com.guedes.sistemacomercial.model.VW_Produto;
import br.com.guedes.sistemacomercial.model.ValorVendaProduto;
import br.com.guedes.sistemacomercial.util.Util;
import br.com.guedes.sistemacomercial.vo.ProdutoVO;
import br.com.guedes.sistemacomercial.vo.ProdutoVWVO;
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
	//private static final String SESSION_LISTA_VALOR_PRODUTO  = "SESSION_LISTA_VALOR_PRODUTO";
	//private static final String SESSION_IS_ALTERACAO_PRODUTO = "SESSION_IS_ALTERACAO_PRODUTO";
	private static final Logger LOGGER = Logger.getLogger(ProdutoAction.class);

	private String mensagemUsuario;
	private ProdutoVO produto = new ProdutoVO();
	private List<ProdutoVO> listaProduto;
	
	
	
	
	
	//private Produto produto;
	private Fornecedor fornecedor;
	private Categoria categoria;
	private List<Categoria> listaCategoria = new ArrayList<Categoria>();
	private List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
	private List<ValoresProdutoVO> listaValoresProdutoVO = new ArrayList<ValoresProdutoVO>();
	private ValoresProdutoVO valoresProdutoVO = new ValoresProdutoVO();
	private List<ProdutoVWVO> listaProdutoView = new ArrayList<ProdutoVWVO>();
	
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
    			getProduto().setProDataCadastro(Util.converterCalendarParaString(produto.getProDataCadastro()));
    			getProduto().setProDataAlteracao(Util.converterCalendarParaString(produto.getProDataAlteracao()));
    			getProduto().setProNome(produto.getProNome());
    			getProduto().setProCodigoBarras(produto.getProCodigoBarras());
    			//getProduto().setForNome(produto.getProCodigoBarras());
    			if (produto.getCategoria() != null) {
    				getProduto().setCatDescricao(produto.getCategoria().getCatDescricao());
    			}
    			getProduto().setProQuantidadeMinima(produto.getProQuantidadeMinima());
    			getProduto().setProQuantidadeMaxima(produto.getProQuantidadeMaxima());
    			getProduto().setProObs(produto.getProObs());
    			getProduto().setListaValoresProduto(new ArrayList<ValoresProdutoVO>());
    			for (ValorVendaProduto valorVendaProduto: produto.getListaValorVendaProduto()) {
    				ValoresProdutoVO valoresProdutoVO = new ValoresProdutoVO();
    				
    				//valoresProdutoVO.setVrpImpostoICMS(Util.converterBigDecimalParaStringDecimal(valorVendaProduto.getVvpValorProduto()));
    				valoresProdutoVO.setVvpValorProduto(Util.converterBigDecimalParaStringDecimal(valorVendaProduto.getVvpValorProduto()));
    				valoresProdutoVO.setVvpDataCadastro(Util.converterCalendarParaString(valorVendaProduto.getVvpDataCadastro()));
    				getProduto().getListaValoresProduto().add(valoresProdutoVO);
    			}
    		}
    		return SUCCESS;
			
		} catch (Exception e) {
			LOGGER.error("Erro ao detalhar Produto.", e);
			setMensagemUsuario("Erro ao detalhar Produto.");
			return ERROR;
		}
	}
	
	
	
	
	
	
	
	
//	/**
//	 * 
//	 * @return String
//	 */
//    public String iniciarCadastro() {
//    	
//    	try {
//    		// remove da session a lista de valores do produto.
//    		this.getRequest().getSession().removeAttribute(SESSION_LISTA_VALOR_PRODUTO);
//    		
//    		// obter lista de categorias.
//    		setListaCategoria(categoriaFacade.listaCategoria());
//    		
//    		return SUCCESS;
//			
//		} catch (Exception e) {
//			
//			addActionError("Erro ao iniciar cadastro do Produto.");
//			return ERROR;
//		}
//    }
    
	/**
	 * 
	 * @return String
	 */
//    public String iniciarAlteracao() {
//    	
//    	try {
//    		// remove da session a lista de valores do produto.
//    		this.getRequest().getSession().removeAttribute(SESSION_LISTA_VALOR_PRODUTO);
//    		
//    		// obter o Produto.
//    		setProduto(produtoFacade.obterProdutoPorId(getProduto().getProCodigo()));
//    		
//    		setCategoria(produto.getCategoria());
//    		//setFornecedor(produto.getCategoria());
//    		
//    		// obter lista de categorias.
//    		setListaCategoria(categoriaFacade.listaCategoria());
//    		
//    		// lista de valores do Produto.
//    		preencherValoresProdutoParaAlterar();
//    		
//    		// seta a lista de valores na session.
//    		this.getRequest().getSession().setAttribute(SESSION_LISTA_VALOR_PRODUTO, getListaValoresProdutoVO());
//    		this.getRequest().getSession().setAttribute(SESSION_IS_ALTERACAO_PRODUTO, SESSION_IS_ALTERACAO_PRODUTO);
//    		
//    		return SUCCESS;
//			
//		} catch (Exception e) {
//			
//			addActionError("Erro ao iniciar alteração do Produto.");
//			return ERROR;
//		}
//    }  
    
//    @SuppressWarnings("unchecked")
//	public String buscarListaValoresProduto() {
//    	
//    	try {
//    		
//    		// buscar da session a lista de valores do Produto.
//    		setListaValoresProdutoVO((List<ValoresProdutoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_VALOR_PRODUTO));
//    		
//    		return SUCCESS;
//			
//		} catch (Exception e) {
//			
//			addActionError("Erro ao iniciar alteração do Produto.");
//			return ERROR;
//		}
//    }     
    
//    @SuppressWarnings("unchecked")
//	public String salvar() {
//    	
//    	try {
//    		
//    		setListaValoresProdutoVO((List<ValoresProdutoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_VALOR_PRODUTO));
//    		
////    		if (getFornecedor() != null && getFornecedor().getPessoa().getPesCodigo() != 0) {
////    			produto.setFornecedor(getFornecedor());
////    		}
//    		
//    		if (getCategoria() != null && getCategoria().getCatCodigo() != 0) {
//    			produto.setCategoria(getCategoria());
//    		}    		
//    		
//    		produto.setProDataCadastro(Calendar.getInstance());
//    		
//    		produto.setUsuario((Usuario) this.getRequest().getSession().getAttribute(Constantes.KEY_USUARIO_SESSION));
//    		
//    		// valida campos.
//    		
//    		// obter os valores do Produto na session.
//    		preencherValoresProdutoParaSalvar();
//    		
//    		// verifica se está alterando o Produto.
//    		if (this.getRequest().getSession().getAttribute(SESSION_IS_ALTERACAO_PRODUTO) == null) {
//    			getProduto().setProCodigo(null);
//    		}
//    		
//    		// salvar.
//    		produtoFacade.salvar(getProduto());
//    		
//    		// remove da session a lista de valores do produto.
//    		this.getRequest().getSession().removeAttribute(SESSION_LISTA_VALOR_PRODUTO);
//    		this.getRequest().getSession().removeAttribute(SESSION_IS_ALTERACAO_PRODUTO);
//    		
//    		return SUCCESS;
//			
//		} catch (Exception e) {
//			
//			LOGGER.error("Erro ao gravar o Produto.", e);
//			setMensagemUsuario("Erro ao gravar o Produto.");
//			return ERROR;
//		}
//    }    
    
//    @SuppressWarnings("unchecked")
//	public String incluirValorProduto() {
//    	
//    	try {
//    		
//    		setListaValoresProdutoVO((List<ValoresProdutoVO>) this.getRequest().getSession().getAttribute(SESSION_LISTA_VALOR_PRODUTO));
//    		if (getListaValoresProdutoVO() == null) {
//    			setListaValoresProdutoVO(new ArrayList<ValoresProdutoVO>());
//    		}
//    		
//    		getValoresProdutoVO().setVvpDataCadastro(Util.obterDataHoraAtual());
//    		getListaValoresProdutoVO().add(getValoresProdutoVO());
//    		
//    		this.getRequest().getSession().setAttribute(SESSION_LISTA_VALOR_PRODUTO, getListaValoresProdutoVO());
//    		
//    		return SUCCESS;
//    		
//		} catch (Exception e) {
//			
//			LOGGER.error("Erro ao incluir valor ao Produto.", e);
//			setMensagemUsuario("Erro ao incluir valor ao Produto.");
//			return ERROR;
//		}
//    }
    
//	/**
//	 * 
//	 * @return String
//	 */
//    public String iniciarPesquisa() {
//    	
//    	setProduto(null);
//    	return SUCCESS;
//    }    
    
   
    
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

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public List<Fornecedor> getListaFornecedor() {
		return listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
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

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<ProdutoVWVO> getListaProdutoView() {
		return listaProdutoView;
	}

	public void setListaProdutoView(List<ProdutoVWVO> listaProdutoView) {
		this.listaProdutoView = listaProdutoView;
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
