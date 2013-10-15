<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="hasActionMessages()">
	<div id="divMensagemSucesso" class="ui-widget" style="width: 500px;">
		<div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
			<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
			<s:actionmessage/></p>
		</div>
	</div>	
</s:if>
<div id="divMensagemSucesso" class="ui-state-highlight ui-corner-all" style="display: none;">
	<div style="padding: 0.4em;">
		<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
		<span id="spanMsgSuccess"></span></p>
	</div>
</div>
<div id="divMensagemErro" class="ui-state-highlight ui-corner-all" style="display: none;">
	<div style="padding: 0.4em;">
		<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
		<span id="spanMsgError"></span></p>
	</div>
</div>
<div id="loading" align="center" style="visibility: hidden; margin-top: 30px;">
	<img alt="" src="../static-guedes/img/loading.gif">
</div>
