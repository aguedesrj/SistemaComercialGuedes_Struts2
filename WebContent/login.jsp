<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>::: Sistema Comercial Guedes :::</title>
	
	<!-- JavaScript -->
	<script src="../static-guedes/js/jquery/jquery-1.9.1.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.core.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.mouse.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.button.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.draggable.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.position.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.resizable.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.dialog.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.effect.js"></script>
	<script src="../static-guedes/js/jquery/ui.panel.min.js"></script>
	<script src="../static-guedes/js/login.js"></script>
	
	<!-- CSS -->
	<link rel="stylesheet" href="../static-guedes/js/jquery/css/blitzer/jquery-ui-1.10.3.custom.css">
	<link rel="stylesheet" href="../static-guedes/js/jquery/development-bundle/demos/demos.css">
	<link rel="stylesheet" href="../static-guedes/css/ui.panel.css">
	<link rel="stylesheet" href="../static-guedes/css/principal.css">	
</head> 
<body>
	<s:if test="hasActionMessages()">
		<div id="divMensagemSucesso" class="ui-widget" style="width: 500px;">
			<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
				<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
				<s:actionmessage/></p>
			</div>
		</div>	
	</s:if>
	<div id="divMensagemSucesso" class="ui-widget" style="width: 500px; display: none;">
		<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
			<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
			<span id="spanMsgSuccess"></span></p>
		</div>
	</div>
	<div id="divMensagemErro" class="ui-widget" style="width: 500px; display: none;">
		<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
			<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
			<span id="spanMsgError"></span></p>
		</div>
	</div>
	<div id="loading" align="center" style="visibility: hidden; margin-top: 30px;">
		<img alt="" src="../static-guedes/img/loading.gif">
	</div>
	<div align="center">
		<div id="panelCenter_1" class="centralPanel" style="width: 300px; margin-top: 20px;">
			<h3>Sistema Comercial</h3>
			<div>
				<s:form namespace="Usuario" id="formLogin" name="formLogin" theme="simple">
					<fieldset>
						<s:textfield name="usuario.usuLogin" id="usuLogin" size="20" maxlength="15" theme="simple" required="true" placeholder="Login" cssClass="text ui-widget-content ui-corner-all"></s:textfield>
						<s:password name="usuario.usuSenha" id="usuSenha" size="15" maxlength="10" theme="simple" required="true" placeholder="Senha" cssClass="text ui-widget-content ui-corner-all"></s:password>
						<input type="button" id="efetuar-login" value="Efetuar Login">
					</fieldset>
				</s:form>				
			</div>
		</div>
	</div>
</body>
</html>