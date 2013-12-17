package br.com.guedes.sistemacomercial.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.guedes.sistemacomercial.facade.UsuarioFacade;
import br.com.guedes.sistemacomercial.model.Funcionalidade;
import br.com.guedes.sistemacomercial.model.Perfil;
import br.com.guedes.sistemacomercial.model.PerfilFuncionalidade;
import br.com.guedes.sistemacomercial.model.PerfilFuncionalidadePk;
import br.com.guedes.sistemacomercial.model.Pessoa;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.model.UsuarioPerfil;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.Constantes;
import br.com.guedes.sistemacomercial.util.Util;
import br.com.guedes.sistemacomercial.vo.GenericVO;
import br.com.guedes.sistemacomercial.vo.PerfilVO;
import br.com.guedes.sistemacomercial.vo.UsuarioVO;

/**
 * Action usu�rio.
 * 
 * @author Andr�Lessa
 *
 */
@Controller
@Scope("request")
public class UsuarioAction extends BaseAction {

	private static final long serialVersionUID = 6197016604313350773L;
	
	private String mensagemUsuario;
	private PerfilVO perfil = new PerfilVO();
	private String itensSelecionados;
	private List<UsuarioVO> listaUsuarios;
	private List<GenericVO> listaSelecionados;
	private List<GenericVO> listaDisponiveis;
	private UsuarioVO usuario = new UsuarioVO();
	
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
			
			Usuario usuario = new Usuario();
			usuario.setUsuLogin(getUsuario().getUsuLogin());
			usuario.setUsuSenha(getUsuario().getUsuSenha());
			
			usuario = usuarioFacade.efetuarLogin(usuario.getUsuLogin().trim(), usuario.getUsuSenha().trim());
			
			// Abrir sess�o.
			this.getRequest().getSession().setAttribute(Constantes.KEY_USUARIO_SESSION, usuario);
			
			return SUCCESS;				
		} catch (BusinessException e) {
			LOG.error(e.getMessage(), e);
			setMensagemUsuario(e.getMessage());
			return ERROR;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("Servi�o de Login indispon�vel.");
			return ERROR;
		}
    }
    
    public String exibirTelaHome() {
    	
    	return SUCCESS;
    }  
    
    public String exibirPesquisarUsuario() {
    	
    	return SUCCESS;
    }
    
    public String exibirManutencaoUsuario() {
    	
    	return SUCCESS;
    }  
    
    /**
     * Salvar Usu�rio.
     */
    public String salvarUsuario() {
    	try {

    		// validar campos.
    		
    		// dados
    		Pessoa pessoa = new Pessoa();
    		Usuario usuario = new Usuario();
    		pessoa.setPesCodigo(getUsuario().getPesCodigo());
    		pessoa.setPesNome(getUsuario().getPesNome());
    		// verifica se est� incluindo novo Usu�rio
    		if (pessoa.getPesCodigo() == null) {
    			pessoa.setPesDataCadastro(new LocalDateTime());
    		} else {
    			pessoa.setPesDataAlteracao(new LocalDateTime());
    		}
    		usuario.setUsuCodigo(getUsuario().getUsuCodigo());
    		usuario.setUsuLogin(getUsuario().getUsuLogin());
    		usuario.setUsuSenha(getUsuario().getUsuSenha());
    		usuario.setPessoa(pessoa);
    		// lista de perfis selecionadas.
    		String[] array = getItensSelecionados().split(",");
    		for (int i = 0; array.length > i; i++) {
    			UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
        		Perfil perfil = new Perfil();
        		
        		perfil.setPerCodigo(Integer.valueOf(array[i]));
        		usuarioPerfil.setPerfil(perfil);
        		usuarioPerfil.setUsuario(usuario);       		
        		usuario.getListaUsuarioPerfil().add(usuarioPerfil);
    		}
    		
    		// salvar.
    		usuarioFacade.salvarUsuario(usuario);
    		
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("N�o foi poss�vel salvar o Usu�rio.");
			}
			return ERROR;
		}    	
    }    
    
    /**
     * 
     * @return String
     */
    public String exibirManutencaoPerfil() {

    	return SUCCESS;
    }   
    
    public String listarUsuarios() {
    	try {
    		Usuario usuario = new Usuario();
    		usuario.setUsuCodigo(getUsuario().getUsuCodigo());
    		
    		List<Usuario> lista = usuarioFacade.buscarUsuariosPorCriterios(usuario);
    		setListaUsuarios(new ArrayList<UsuarioVO>());
    		
    		for (Usuario usuarioLista: lista) {
    			UsuarioVO usuarioVO = new UsuarioVO();
    			usuarioVO.setPesCodigo(usuarioLista.getPessoa().getPesCodigo());
    			usuarioVO.setPesNome(usuarioLista.getPessoa().getPesNome());
    			usuarioVO.setUsuCodigo(usuarioLista.getUsuCodigo());
    			usuarioVO.setUsuLogin(usuarioLista.getUsuLogin());
    			usuarioVO.setUsuSenha(usuarioLista.getUsuSenha());
    			usuarioVO.setUsuConfirmaSenha(usuarioLista.getUsuSenha());
    			
    			getListaUsuarios().add(usuarioVO);
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("N�o foi poss�vel listar os Usu�rios.");
			return ERROR;
		}    	
    }  
    
    public String deletarUsuario() {
    	try {

    		Usuario usuario = new Usuario();
    		usuario.setUsuCodigo(getUsuario().getUsuCodigo());
    		usuario.setUsuStatus("I");
    		
    		// deletar.
    		usuarioFacade.deletarUsuario(usuario);
    		setMensagemUsuario("Usu�rio exclu�do com sucesso.");
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("N�o foi poss�vel excluir o Usu�rio.");
			}
			return ERROR;
		}      	
    }
    
    /**
     * 
     * @return String
     */
    public String detalharUsuario() {
    	try {

    		Usuario usuario = new Usuario();
    		usuario.setUsuCodigo(getUsuario().getUsuCodigo());
    		
    		List<Usuario> lista = usuarioFacade.buscarUsuariosPorCriterios(usuario);
    		if (lista != null && lista.size() == 1) {
    			usuario = lista.get(0);
    			getUsuario().setPesCodigo(usuario.getPessoa().getPesCodigo());
    			getUsuario().setPesDataAlteracao(Util.dateTimeFormatter.print(usuario.getPessoa().getPesDataAlteracao()));
    			getUsuario().setPesDataCadastro(Util.dateTimeFormatter.print(usuario.getPessoa().getPesDataCadastro()));
    			getUsuario().setPesNome(usuario.getPessoa().getPesNome());
    			getUsuario().setUsuCodigo(usuario.getUsuCodigo());
    			getUsuario().setUsuLogin(usuario.getUsuLogin());
    			getUsuario().setListaPerfil(new ArrayList<PerfilVO>());
    			for (UsuarioPerfil usuarioPerfil: usuario.getListaUsuarioPerfil()) {
    				PerfilVO perfilVO = new PerfilVO();
    				perfilVO.setPerCodigo(usuarioPerfil.getPerfil().getPerCodigo());
    				perfilVO.setPerNome(usuarioPerfil.getPerfil().getPerNome());
    				getUsuario().getListaPerfil().add(perfilVO);
    			}
    		} else {
    			setMensagemUsuario("Usu�rio n�o encontrado.");
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("N�o foi poss�vel detalhar o Usu�rio.");
			}
			return ERROR;
		}
    }
    
    public String exibirAlteracaoUsuario() {
    	try {

    		Usuario usuario = new Usuario();
    		usuario.setUsuCodigo(getUsuario().getUsuCodigo());
    		
    		List<Usuario> lista = usuarioFacade.buscarUsuariosPorCriterios(usuario);
    		if (lista != null && lista.size() == 1) {
    			usuario = lista.get(0);
    			getUsuario().setPesCodigo(usuario.getPessoa().getPesCodigo());
    			getUsuario().setPesNome(usuario.getPessoa().getPesNome());
    			getUsuario().setUsuCodigo(usuario.getUsuCodigo());
    			getUsuario().setUsuLogin(usuario.getUsuLogin());
    			getUsuario().setUsuSenha(usuario.getUsuSenha());
    			getUsuario().setUsuConfirmaSenha(usuario.getUsuSenha());
    			setListaSelecionados(new ArrayList<GenericVO>());
    			for (UsuarioPerfil usuarioPerfil: usuario.getListaUsuarioPerfil()) {
    				GenericVO genericVO = new GenericVO();
        			genericVO.setGenCodigo(usuarioPerfil.getPerfil().getPerCodigo());
        			genericVO.setGenDescricao(usuarioPerfil.getPerfil().getPerNome());
        			getListaSelecionados().add(genericVO);
    			}
    		} else {
    			setMensagemUsuario("Usu�rio n�o encontrado.");
    		}
    		
    		// obter a lista de perfis dispon�veis.
	 		setListaDisponiveis(new ArrayList<GenericVO>());
	 		List<Perfil> listaPerfil = usuarioFacade.listaPerfilsNotExistsUsuario(usuario);
	 		for (Perfil perfil: listaPerfil) {
	 			GenericVO genericVO = new GenericVO(); 
	 			genericVO.setGenCodigo(perfil.getPerCodigo());
	 			genericVO.setGenDescricao(perfil.getPerNome());
	 			
	 			getListaDisponiveis().add(genericVO);
	 		}
    		
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("N�o foi poss�vel iniciar a altera��o do Usu�rio.");
			}
			return ERROR;
		}   	
    }
    
    /**
     * Listar todas as funcionalidades dispon�veis.
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
			setMensagemUsuario("N�o foi poss�vel listar as Funcionalidades.");
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
			setMensagemUsuario("N�o foi poss�vel listar os Perfis.");
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
        			GenericVO genericVO = new GenericVO();
        			genericVO.setGenCodigo(perfilFuncionalidade.getFuncionalidade().getFunCodigo());
        			genericVO.setGenDescricao(perfilFuncionalidade.getFuncionalidade().getFunDescricao());
        			getListaSelecionados().add(genericVO);
        		}
    		}
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			setMensagemUsuario("N�o foi poss�vel detalhar o Perfil.");
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
        			GenericVO genericVO = new GenericVO();
        			genericVO.setGenCodigo(perfilFuncionalidade.getFuncionalidade().getFunCodigo());
        			genericVO.setGenDescricao(perfilFuncionalidade.getFuncionalidade().getFunDescricao());
        			getListaSelecionados().add(genericVO);
        		}
    		}    		
    		// obter a lista de funcionalidades dispon�veis.
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
			setMensagemUsuario("N�o foi poss�vel iniciar a altera��o.");
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
    		String[] array = getItensSelecionados().split(",");
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
			setMensagemUsuario("N�o foi poss�vel salvar o Perfil.");
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
    		setMensagemUsuario("Perfil exclu�do com sucesso.");
    		return SUCCESS;
		} catch (Exception e) {
			LOG.fatal(e.getMessage(), e);
			if (e instanceof BusinessException) {
				setMensagemUsuario(e.getMessage());
			} else {
				setMensagemUsuario("N�o foi poss�vel excluir o Perfil.");
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

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

	public String getItensSelecionados() {
		return itensSelecionados;
	}

	public void setItensSelecionados(String itensSelecionados) {
		this.itensSelecionados = itensSelecionados;
	}

	public List<UsuarioVO> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<UsuarioVO> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
}
