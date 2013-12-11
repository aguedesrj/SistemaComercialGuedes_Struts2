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
	private List<Funcionalidade> listaFuncionalidade = new ArrayList<Funcionalidade>();
	private List<PerfilVO> listaPerfil = new ArrayList<PerfilVO>();
	private PerfilVO perfil = new PerfilVO();
	private String funcSelecionados;
	
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
    	try {
			// lista de funcionalidades.
    		setListaFuncionalidade(usuarioFacade.listaFuncionalidade());
    		return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
    }
    
    public String listarPerfis() {
    	try {
    		// lista de pefils.
    		List<Perfil> lista = usuarioFacade.listaPerfil();
    		for (Perfil perfil: lista) {
    			PerfilVO perfilVO = new PerfilVO();
    			perfilVO.setPerCodigo(perfil.getPerCodigo());
    			perfilVO.setPerNome(perfil.getPerNome());
    			getListaPerfil().add(perfilVO);
    		}
    		return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
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
    		//Set<PerfilFuncionalidade> listaPerfilFuncionalidade = new HashSet<PerfilFuncionalidade>();
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
        		
        		//perfilFuncionalidade.setFuncionalidade(funcionalidade);
        		//perfilFuncionalidade.setPerfil(perfil);
        		perfil.getListaPerfilFuncionalidade().add(perfilFuncionalidade);
    		}
    		
    		// salvar perfil.
    		usuarioFacade.salvarPerfil(perfil);
    		
    		return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
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

	public List<Funcionalidade> getListaFuncionalidade() {
		return listaFuncionalidade;
	}

	public void setListaFuncionalidade(List<Funcionalidade> listaFuncionalidade) {
		this.listaFuncionalidade = listaFuncionalidade;
	}

	public List<PerfilVO> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(List<PerfilVO> listaPerfil) {
		this.listaPerfil = listaPerfil;
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
}
