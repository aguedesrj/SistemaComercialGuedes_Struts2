<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../static-guedes/js/pages/produto/pesquisaProduto.js"></script>

<div id="panelCenter_1" class="centralPanel">
	<h3>Pesquisar Produto</h3>
	<div>
		<fieldset>
			<s:form namespace="Produto" id="formProduto" name="formProduto" theme="simple">
				<s:hidden id="proCodigo" name="produto.proCodigo"></s:hidden>
				<fieldset>
					<table>
						<tr>
							<td style="width: 50%;">
								<s:label for="proNome">Nome do Produto</s:label>
								<s:textfield name="produto.proNome" id="proNome" maxlength="120" theme="simple" required="true" cssClass="text ui-widget-content ui-corner-all" cssStyle="width: 250px;"></s:textfield>
							</td>
						</tr>
						<tr>
							<td><input type="button" id="btnPesquisar" value="Pesquisar"></td>
						</tr>																												
					</table>			
				</fieldset>
			</s:form>		
			<div id="divTabelaProdutos" style="padding-top: 10px; display: none;">
				<table id="tabelaProdutos" class="scroll"></table>
			</div>
		</fieldset>		
	</div>
</div>