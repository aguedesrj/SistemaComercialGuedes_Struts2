<%@ taglib prefix="s" uri="/struts-tags" %>

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
