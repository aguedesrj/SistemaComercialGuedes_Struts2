<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../static-guedes/js/pages/produto/cadastroProduto.js"></script>

<div id="panelCenter_1" class="centralPanel">
	<h3>Dados do Produto</h3>
	<div>
		<s:form namespace="Produto" id="formProduto" name="formProduto" theme="simple">
			<s:hidden id="proCodigo" name="produto.proCodigo"></s:hidden>
			<fieldset>
				<table>
					<tr>
						<td style="width: 50%;">
							<s:label for="proNome">Nome do Produto</s:label>
							<s:textfield name="produto.proNome" id="proNome" maxlength="120" theme="simple" required="true" cssClass="text ui-widget-content ui-corner-all" cssStyle="width: 250px;"></s:textfield>
						</td>
						<td>
							<s:label for="proNome">Código de Barras</s:label>
							<s:textfield name="produto.proCodigoBarras" id="proCodigoBarras" maxlength="50" theme="simple" required="true" cssClass="text ui-widget-content ui-corner-all" cssStyle="width: 120px;"></s:textfield>
						</td>								
						<!--  
						<td>
							<s:label for="proModelo">Foto do Produto</s:label>
							<div id="divImagens">
								<s:a href="javascript:exibirTelaFoto()"><img src="../static-guedes/img/ic_ferramenta.gif" border="0"></img></s:a>
								<s:a href="javascript:excluirFoto()"><img src="../static-guedes/img/ic_apagar.gif" border="0"></img></s:a>
							</div>								
						</td>
						-->								
					</tr>
					<tr>
						<td style="height: 40px;">
							<s:label for="forCodigo">Fornecedor</s:label>
							<s:select name="fornecedor.pessoa.pesCodigo" list="listaFornecedor" id="forCodigo" headerKey="0" headerValue=":: Selecione Fornecedor ::" theme="simple" listValue="pesNome" listKey="pesCodigo" cssClass="text ui-widget-content ui-corner-all"></s:select>
						</td>
						<td>
							<s:label for="catCodigo">Categoria</s:label>
							<s:select name="categoria.catCodigo" list="listaCategoria" id="catCodigo" headerKey="0" headerValue=":: Selecione Categoria ::" theme="simple" listValue="catDescricao" listKey="catCodigo" cssClass="select ui-widget-content ui-corner-all"></s:select>
						</td>
					</tr>
					<tr>
						<td>
							<s:label for="proQuantidadeMinima">Quantidade Mínima</s:label>
							<s:textfield name="produto.proQuantidadeMinima" id="proQuantidadeMinima" maxlength="7" theme="simple" required="true" cssStyle="width: 70px;" cssClass="text ui-widget-content ui-corner-all"></s:textfield>								
						</td>
						<td>
							<s:label for="proQuantidadeMaxima">Quantidade Máxima</s:label>
							<s:textfield name="produto.proQuantidadeMaxima" id="proQuantidadeMaxima" maxlength="7" theme="simple" required="true" cssStyle="width: 70px;" cssClass="text ui-widget-content ui-corner-all"></s:textfield>								
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<s:label for="proObs">Observações</s:label>
							<s:textarea name="produto.proObs" id="proObs" cols="90" theme="simple" cssClass="textarea ui-widget-content ui-corner-all"></s:textarea>								
						</td>
					</tr>																												
				</table>			
			</fieldset>
		</s:form>				
	</div>
</div>

<div id="panelCenter_2" class="centralPanel" style="margin-top: 20px;">
	<h3>Valores do Produto</h3>
	<div>
		<fieldset>
			<div style="width:650px; float:left;">
				<table id="tabelaValores" class="scroll"></table>
			</div>
		</fieldset>
		<div style="padding-top: 10px;">
			<input type="button" id="btnInserirValor" value="Inserir Valor"/>		
		</div>		
	</div>
</div>

<fieldset>
	<input type="button" id="btnGravar" value="Gravar Produto">
</fieldset>	

<!-- DIV do formulário Valores do Produto -->
<div id="dialog-form" title="Inserir Valores ao Produto">
	<form id="formDialogValoresProduto">
		<fieldset>
			<table style="width: 100%;">
				<tr>
					<td style="width: 50%; height: 45px;">
						<label for="vrpImpostoICMS">ICMS(%)</label>
						<s:textfield name="valoresProdutoVO.vrpImpostoICMS" id="vrpImpostoICMS" maxlength="6" theme="simple" required="true" cssStyle="width: 80px;" cssClass="text ui-widget-content ui-corner-all"></s:textfield>
					</td>
					<td>
						<label for="email">IPI(%)</label>
						<s:textfield name="valoresProdutoVO.vrpImpostoIPI" id="vrpImpostoIPI" maxlength="6" theme="simple" required="true" cssStyle="width: 80px;" cssClass="text ui-widget-content ui-corner-all"></s:textfield>					
					</td>								
				</tr>
				<tr style="height: 50px;">		
					<td>
						<label for="password">ISS(%)</label>
						<s:textfield name="valoresProdutoVO.vrpImpostoICMS" id="vrpImpostoISS" maxlength="6" theme="simple" required="true" cssStyle="width: 80px;" cssClass="text ui-widget-content ui-corner-all"></s:textfield>					
					</td>
					<td>
						<label for="password">Valor de Venda</label>
						<s:textfield name="valoresProdutoVO.vvpValorProduto" id="vvpValorProduto" maxlength="9" theme="simple" required="true" cssStyle="width: 100px;" cssClass="text ui-widget-content ui-corner-all"></s:textfield>						
					</td>								
				</tr>
			</table>		
		</fieldset>
	</form>
</div>
