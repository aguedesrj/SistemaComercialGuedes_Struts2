package br.com.guedes.sistemacomercial.controller;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SistemaComercialGuedesInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 9190792855898970519L;

	@Override
	public String intercept(final ActionInvocation invocation) throws Exception {

//		Map<String, Object> session = invocation.getInvocationContext().getSession();
//		Usuario usuario = (Usuario) session.get(Constantes.KEY_USUARIO_SESSION);
//		
//		if (usuario == null) {
//			
//			return Action.ERROR;
//		}
		
		// retorna ao método que foi de origem.
		return invocation.invoke();
		
		/*
		HttpRequest request = ServletActionContext.getRequest();
		
		String teste = ServletActionContext.getActionMapping().etRequest();

		LogSeguranca logSeguranca = new LogSeguranca();
		logSeguranca.setLogDtHrAcesso(Calendar.getInstance());
		
		Acao acao = new Acao();
		acao.setAcaCodigo("A05");
		
		Funcionalidade funcionalidade = new Funcionalidade();
		funcionalidade.setFunCodigo("F003");
		
		logSeguranca.setAcao(acao);
		logSeguranca.setFuncionalidade(funcionalidade);
		logSeguranca.setUsuario(usuario);
		
		logSegurancaFacade.inserir(null);
		*/

	}
	
	@Override  
	public void destroy() {  
	          
	}  
	  
	@Override  
	public void init() {  
	              
	}	
}
