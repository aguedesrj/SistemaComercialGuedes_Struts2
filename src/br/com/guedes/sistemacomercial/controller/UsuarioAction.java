package br.com.guedes.sistemacomercial.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemacomercial.facade.UsuarioFacade;
import br.com.guedes.sistemacomercial.model.Funcionalidade;
import br.com.guedes.sistemacomercial.model.Perfil;
import br.com.guedes.sistemacomercial.model.PerfilFuncionalidade;
import br.com.guedes.sistemacomercial.model.PerfilFuncionalidadePk;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.Constantes;
import br.com.guedes.sistemacomercial.vo.GenericVO;
import br.com.guedes.sistemacomercial.vo.PerfilVO;

/**
 * Action usuário.
 * 
 * @author AndréLessa
 *
 */
@Controller
@Scope("request")
public class UsuarioAction extends BaseAction {

	private static final long serialVersionUID = 6197016604313350773L;
	
	private Usuario usuario;
	private String mensagemUsuario;
	private PerfilVO perfil = new PerfilVO();
	private String funcSelecionados;
	private List<GenericVO> listaSelecionados;
	private List<GenericVO> listaDisponiveis;	
	
	@Autowired
	private UsuarioFacade usuarioFacade;
	
	/**
	 * Exibir tela de login.
	 * 
	 * @return String
	 */
    public String exibirTelaLogin() {
    	
    	return SUCCESS;
    }
    
    public String efetuarLogin() {
    	
		//this.validarCamposLogin();
		
		if (hasActionErrors()) {
			return ERROR;
		}
		
		try {
			
			usuario = usuarioFacade.efetuarLogin(usuario.getUsuLogin().trim(), usuario.getUsuSenha().trim());
			
			// Abrir sessão.
			this.getRequest().getSession().setAttribute(Constantes.KEY_USUARIO_SESSION, usuario);
			
			return SUCCESS;				
		} catch (BusinessException e) {
			LOG.error(e.getMessage(), e);
			setMensagemUsuario(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("Serviço de Login indisponível.");
			return ERROR;
		}
    }
    
    public String exibirTelaHome() {
    	
    	return SUCCESS;
    }  
    
    /**
     * 
     * @return String
     */
    public String exibirManutencaoPerfil() {

    	return SUCCESS;
    }   
    
    /**
     * Listar todas as funcionalidades disponíveis.
     * 
     * @return String
     */
    public String listarFuncionalidades() {
    	try {
    		setListaDisponiveis(new ArrayList<GenericVO>());
			// lista de funcionalidades.
    		List<Funcionalidade> lista = usuarioFacade.listaFuncionalidade();
    		for (Funcionalidade funcionalidade: lista) {
    			GenericVO genericVO = new GenericVO(); 
    			genericVO.setGenCodigo(funcionalidade.getFunCodigo());
    			genericVO.setGenDescricao(funcionalidade.getFunDescricao());
    			
    			getListaDisponiveis().add(genericVO);
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("Não foi possível listar as Funcionalidades.");
			return ERROR;
		}    	
    } 
    
    public String listarPerfis() {
    	try {
    		// lista de pefils.
    		List<Perfil> lista = usuarioFacade.listaPerfil();
    		setListaSelecionados(new ArrayList<GenericVO>());
    		for (Perfil perfil: lista) {
    			GenericVO genericVO = new GenericVO();
    			genericVO.setGenCodigo(perfil.getPerCodigo());
    			genericVO.setGenDescricao(perfil.getPerNome());
    			getListaSelecionados().add(genericVO);
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("Não foi possível listar os Perfis.");
			return ERROR;
		}    	
    }
    
    public String listarFuncionalidadesPorPerfil() {
    	try {
    		Perfil perfil = new Perfil();
    		perfil.setPerCodigo(getPerfil().getPerCodigo());
    		List<PerfilFuncionalidade> lista = usuarioFacade.listaFuncionalidadesPorPerfil(perfil);
    		if (lista != null && !lista.isEmpty()) {
    			getPerfil().setPerCodigo(lista.get(0).getPerfil().getPerCodigo());
    			getPerfil().setPerNome(lista.get(0).getPerfil().getPerNome());
    			setListaSelecionados(new ArrayList<GenericVO>());
        		for (PerfilFuncionalidade perfilFuncionalidade: lista) {
        			GenericVO funcionalidadeVO = new GenericVO();
        			funcionalidadeVO.setGenCodigo(perfilFuncionalidade.getFuncionalidade().getFunCodigo());
        			funcionalidadeVO.setGenDescricao(perfilFuncionalidade.getFuncionalidade().getFunDescricao());
        			getListaSelecionados().add(funcionalidadeVO);
        		}
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("Não foi possível detalhar o Perfil.");
			return ERROR;
		}     	
    }
    
    /**
     * 
     * @return String
     */
    public String exibirAlteracao() {
	 	try {
    		Perfil perfil = new Perfil();
    		perfil.setPerCodigo(getPerfil().getPerCodigo());
    		// obter a lista de funcionalidades selecionados.
    		List<PerfilFuncionalidade> listaPerfilFuncionalidade = usuarioFacade.listaFuncionalidadesPorPerfil(perfil);
    		if (listaPerfilFuncionalidade != null && !listaPerfilFuncionalidade.isEmpty()) {
    			getPerfil().setPerCodigo(listaPerfilFuncionalidade.get(0).getPerfil().getPerCodigo());
    			getPerfil().setPerNome(listaPerfilFuncionalidade.get(0).getPerfil().getPerNome());
    			setListaSelecionados(new ArrayList<GenericVO>());
        		for (PerfilFuncionalidade perfilFuncionalidade: listaPerfilFuncionalidade) {
        			GenericVO funcionalidadeVO = new GenericVO();
        			funcionalidadeVO.setGenCodigo(perfilFuncionalidade.getFuncionalidade().getFunCodigo());
        			funcionalidadeVO.setGenDescricao(perfilFuncionalidade.getFuncionalidade().getFunDescricao());
        			getListaSelecionados().add(funcionalidadeVO);
        		}
    		}    		
    		// obter a lista de funcionalidades disponíveis.
	 		setListaDisponiveis(new ArrayList<GenericVO>());
				// lista de funcionalidades.
	 		List<Funcionalidade> listaFuncionalidade = usuarioFacade.listaFuncionalidadesNotExistsPerfil(perfil);
	 		for (Funcionalidade funcionalidade: listaFuncionalidade) {
	 			GenericVO genericVO = new GenericVO(); 
	 			genericVO.setGenCodigo(funcionalidade.getFunCodigo());
	 			genericVO.setGenDescricao(funcionalidade.getFunDescricao());
	 			
	 			getListaDisponiveis().add(genericVO);
	 		}
	 		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("Não foi possível iniciar a alteração.");
			return ERROR;
		}
	}
    
    public String efetuarlogout() {
    	
    	this.getRequest().getSession().removeAttribute(Constantes.KEY_USUARIO_SESSION);
    	
    	addActionMessage("Logout feito com sucesso.");
    	
    	return SUCCESS;
    }
    
    /**
     * Salvar Perfil.
     */
    public String salvarPerfil() {
    	try {

    		// validar campos.
    		
    		//
    		Perfil perfil = new Perfil();
    		perfil.setPerCodigo(getPerfil().getPerCodigo());
    		perfil.setPerNome(getPerfil().getPerNome());
    		
    		// lista de funcionalidades selecionadas.
    		String[] array = getFuncSelecionados().split(",");
    		for (int i = 0; array.length > i; i++) {
        		PerfilFuncionalidade perfilFuncionalidade = new PerfilFuncionalidade();
        		PerfilFuncionalidadePk perfilFuncionalidadePk = new PerfilFuncionalidadePk();
        		Funcionalidade funcionalidade = new Funcionalidade();
        		
    			funcionalidade.setFunCodigo(Integer.valueOf(array[i]));
    			
        		perfilFuncionalidadePk.setFuncionalidade(funcionalidade);
        		perfilFuncionalidadePk.setPerfil(perfil);
        		perfilFuncionalidade.setPerfil(perfil);
        		perfilFuncionalidade.setFuncionalidade(funcionalidade);
        		
        		perfil.getListaPerfilFuncionalidade().add(perfilFuncionalidade);
    		}
    		
    		// salvar perfil.
    		usuarioFacade.salvarPerfil(perfil);
    		
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("Não foi possível salvar o Perfil.");
			return ERROR;
		}    	
    }
    
    /**
     * Deletar Perfil.
     */
    public String deletarPerfil() {
    	try {

    		Perfil perfil = new Perfil();
    		perfil.setPerCodigo(getPerfil().getPerCodigo());
    		
    		// deletar perfil.
    		usuarioFacade.deletarPerfil(perfil);
    		setMensagemUsuario("Perfil excluído com sucesso.");
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("Não foi possível excluir o Perfil.");
			}
			return ERROR;
		}    	
    }    

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public void setMensagemUsuario(String mensagemUsuario) {
		this.mensagemUsuario = mensagemUsuario;
	}

	public String getFuncSelecionados() {
		return funcSelecionados;
	}

	public void setFuncSelecionados(String funcSelecionados) {
		this.funcSelecionados = funcSelecionados;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}
	
	public List<GenericVO> getListaSelecionados() {
		return listaSelecionados;
	}

	public void setListaSelecionados(List<GenericVO> listaSelecionados) {
		this.listaSelecionados = listaSelecionados;
	}

	public List<GenericVO> getListaDisponiveis() {
		return listaDisponiveis;
	}

	public void setListaDisponiveis(List<GenericVO> listaDisponiveis) {
		this.listaDisponiveis = listaDisponiveis;
	}
}
