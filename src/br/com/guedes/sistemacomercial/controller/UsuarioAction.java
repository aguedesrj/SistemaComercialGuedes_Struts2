package br.com.guedes.sistemacomercial.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.xml.sax.XMLReader;

import br.com.guedes.sistemacomercial.facade.UsuarioFacade;
import br.com.guedes.sistemacomercial.model.Usuario;
import br.com.guedes.sistemacomercial.util.BusinessException;
import br.com.guedes.sistemacomercial.util.Constantes;

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
	
	@Autowired
	private UsuarioFacade usuarioFacade;
	
	/**
	 * Exibir tela de login.
	 * 
	 * @return String
	 */
    public String exibirTelaLogin() {
    	
    	try {
			
    		URL url = new URL("https://wwws.bradescoseguros.com.br/SEGU-AutenticacaoFacebook/service/AutenticadorWebService?WSDL");
    		//https://wwws.bradescoseguros.com.br/SEGU-AutenticacaoFacebook/service/AutenticadorWebService?WSDL
    		//https://wwws.bradescoseguros.com.br/MOBI-PortalDoSegurado/segurado/login.do?email=flavia.ferrao@bradescoseguros.com.br&senha=123456
			URLConnection conn = url.openConnection();
 
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			System.out.println(br.readLine().toString());
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				System.out.println(inputLine);
			}
			
			br.close();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
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
    
    public String efetuarlogout() {
    	
    	this.getRequest().getSession().removeAttribute(Constantes.KEY_USUARIO_SESSION);
    	
    	addActionMessage("Logout feito com sucesso.");
    	
    	return SUCCESS;
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
}
