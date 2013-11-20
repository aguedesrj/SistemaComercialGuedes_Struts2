<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>::: Sistema Comercial Guedes :::</title>
	<link href="../resources/bootstrap/css/bootstrap.css" rel="stylesheet" />
	<script src="../resources/js/jquery-1.9.1.js"></script>
</head>
<body>
	<script src="../resources/js/pages/login.js"></script>	
	<!-- Mensagem ao Usuário. -->
	<div class="container" style="width: 500px; margin-top: 10px; height: 25px;">
		<s:if test="hasActionMessages()">
			<div class="alert alert-success">
	        	<s:actionmessage/>
	      	</div>		
		</s:if>
		<s:if test="hasActionErrors()">
			<div class="alert alert-danger">
	        	<s:actionerror/>
	      	</div>		
		</s:if>	
		<div id="divMensagemSucesso" class="alert alert-success" style="display: none;">
        	<span id="spanMsgSuccess"></span>
      	</div>
		<div id="divMensagemErro" class="alert alert-danger" style="display: none;">
        	<span id="spanMsgError"></span>
      	</div>
      	<div id="loading" align="center" style="display: none;">
      		<img alt="" src="../resources/img/loading.gif">
      	</div>
	</div>
	<!-- FIM Mensagem ao Usuário. -->
	<s:form namespace="Usuario" id="formLogin" name="formLogin" theme="simple">
		<div class="container" style="width: 350px; margin-top: 50px;">
	    	<div class="panel panel-default">
	        	<div class="panel-heading">
	            	<h3 class="panel-title" style="font-weight: bold;">Efetuar Login</h3>
	            </div>
	            <div class="panel-body">
					<s:textfield name="usuario.usuLogin" id="usuLogin" size="20" maxlength="15" theme="simple" required="true" placeholder="Login" cssClass="form-control"></s:textfield>
					<s:password cssStyle="margin-top: 10px;" name="usuario.usuSenha" id="usuSenha" size="15" maxlength="10" theme="simple" required="true" placeholder="Senha" cssClass="form-control"></s:password>	            
	            	<div style="margin-top: 15px;">
	              		<button id="btnEntrar" type="button" class="btn btn-primary">Entrar</button>
	              	</div>
	            </div>
	         </div>
         </div>
	</s:form>
</body>
</html>