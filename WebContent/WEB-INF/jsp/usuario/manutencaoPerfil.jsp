<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.selectable.js"></script>
<script src="../static-guedes/js/pages/usuario/manutencaoPerfil.js"></script>

<style>
	#feedback { font-size: 1.4em; }
	#selectable .ui-selecting { background: #FECA40; }
	#selectable .ui-selected { background: #F39814; color: white; }
	#selectable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
	#selectable li { margin: 3px; padding: 0.4em; font-size: 1.4em; height: 18px; }
</style>

<div id="panelCenter_1" class="centralPanel">
	<h3>Manutenção do Perfil Usuário</h3>
	<div>
		<fieldset>
			<s:form namespace="ManutencaoPerfil" id="formManutencaoPerfil" name="formManutencaoPerfil" theme="simple">
				<fieldset>
					<table>
						<tr>
							<td><input type="button" id="btnNovoPerfil" value="Novo Perfil"></td>
						</tr>																												
					</table>			
				</fieldset>
			</s:form>		
			<div id="divTabelaPerfis" style="padding-top: 10px; display: none;">
				<table id="tabelaPerfis" class="scroll"></table>
			</div>
		</fieldset>		
	</div>
</div>

<!-- DIV do formulário Perfis -->
<div id="dialog-form" title="Inserir Perfil">
	<form id="formDialogPerfil">
		<s:hidden id="perfisSelecionados" name="perfisSelecionados"></s:hidden>
		<fieldset>
			<table style="width: 100%;">
				<tr>
					<td style="height: 45px;">
						<label for="nomePerfil">Nome do Perfil</label>
						<s:textfield name="perNome" id="perNome" maxlength="80" theme="simple" required="true" cssStyle="width: 250px;" cssClass="text ui-widget-content ui-corner-all"></s:textfield>
					</td>								
				</tr>
				<tr>		
					<td>
						<label for="listaFuncionalidades">Selecione as funcionalidades</label>
						<ol id="selectable" style="font-size: 8px;">
							<s:iterator var="func" value="listaFuncionalidade">
								<input type="hidden" value="<s:property value="%{funCodigo}"/>" id="funCodigo<s:property value="%{funCodigo}"/>"/>
								<li id="<s:property value="%{funCodigo}"/>" class="ui-widget-content"><s:property value="%{funDescricao}"/></li>
							</s:iterator>
						</ol>
					</td>								
				</tr>
			</table>		
		</fieldset>
	</form>
</div>
