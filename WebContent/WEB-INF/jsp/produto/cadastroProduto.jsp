<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../resources/js/pages/cadastroProduto.js"></script>	

<div class="container">
	<div class="page-header">
    	<h1>Cadastro Produto</h1>
    </div>
	<s:form namespace="Produto" id="formProduto" name="formProduto" theme="simple" cssStyle="margin-left: 15px;">
		<s:hidden id="proCodigo" name="produto.proCodigo"></s:hidden>    
		<div class="row" style="margin-top: 15px;">
			<div class="col-lg-5">
				<s:label for="proNome" cssClass="control-label">Nome do Produto</s:label>
				<s:textfield name="produto.proNome" id="proNome" maxlength="120" theme="simple" required="true" cssClass="form-control" cssStyle="width: 250px;"/>
			</div>
			<div class="col-lg-5">
				<s:label for="proNome" cssClass="control-label">Código de Barras</s:label>
				<s:textfield name="produto.proCodigoBarras" id="proCodigoBarras" maxlength="50" theme="simple" required="true" cssClass="form-control" cssStyle="width: 120px;"/>
			</div>
		</div>
		<div class="row" style="margin-top: 15px;">
			<div class="col-lg-5">
				<div class="control-group combo">	
					<s:label for="forCodigo" cssClass="control-label">Fornecedor</s:label>
					<s:select name="fornecedor.pessoa.pesCodigo" list="listaFornecedor" id="forCodigo" headerKey="0" headerValue=":: Selecione Fornecedor ::" theme="simple" listValue="pesNome" listKey="pesCodigo" cssClass="form-control"></s:select>		
				</div>
			</div>
			<div class="col-lg-5">	
				<s:label for="catCodigo" cssClass="control-label">Categoria</s:label>
				<s:select name="categoria.catCodigo" list="listaCategoria" id="catCodigo" headerKey="0" headerValue=":: Selecione Categoria ::" theme="simple" listValue="catDescricao" listKey="catCodigo" cssClass="form-control"></s:select>		
			</div>		
		</div>	
		<div class="row" style="margin-top: 15px;">
			<div class="col-lg-5">	
				<s:label for="proQuantidadeMinima" cssClass="control-label">Quantidade Mínima</s:label>
				<s:textfield name="produto.proQuantidadeMinima" id="proQuantidadeMinima" maxlength="7" theme="simple" required="true" cssStyle="width: 70px;" cssClass="form-control"></s:textfield>		
			</div>
			<div class="col-lg-5">	
				<s:label for="proQuantidadeMaxima" cssClass="control-label">Quantidade Máxima</s:label>
				<s:textfield name="produto.proQuantidadeMaxima" id="proQuantidadeMaxima" maxlength="7" theme="simple" required="true" cssStyle="width: 70px;" cssClass="form-control"></s:textfield>		
			</div>		
		</div>
		<div class="row" style="margin-top: 15px;">
			<div class="col-lg-5">
				<s:label for="proObs" cssClass="control-label">Observações</s:label>
				<s:textarea name="produto.proObs" id="proObs" cols="90" theme="simple" cssClass="form-control"></s:textarea>		
			</div>		
		</div>
	</s:form>
	<div class="page-header">
    	<h1>Valores do Produto</h1>
    </div>	
	<div class="row" style="margin-top: 15px; margin-left: 0px;">
		<div style="width:650px; float:left;">
			<table id="tabelaValores" cellspacing="1" cellpadding="3" class="tablehead" style="background:#CCC;"></table>
		</div>
	</div>
	<div class="row" style="margin-top: 15px; margin-left: 0px;">
		<button id="btnGravar" type="button" class="btn btn-primary">Gravar</button>
		<button id="btnExibirInserirValor" type="button" class="btn btn-primary">Inserir Valor</button>
	</div>								
</div>

<!-- DIV do formulário Valores do Produto -->
<div id="modalValoresProduto" class="modal fade">
	<div class="modal-dialog" style="width: 410px;">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="container" style="width: 350px;">
					<div class="page-header">
   						<h3>Valores do Produto</h3>
   					</div>
   					<form id="formDialogValoresProduto" style="margin-left: 15px;">
						<div class="row" style="margin-top: 15px;">
							<div class="col-lg-5">
								<label for="vrpImpostoICMS" cssClass="control-label">ICMS(%)</label>
								<s:textfield name="valoresProdutoVO.vrpImpostoICMS" id="vrpImpostoICMS" maxlength="6" theme="simple" required="true" cssStyle="width: 80px;" cssClass="form-control"></s:textfield>						
							</div>
							<div class="col-lg-5">
								<label for="vrpImpostoIPI" cssClass="control-label">IPI(%)</label>
								<s:textfield name="valoresProdutoVO.vrpImpostoIPI" id="vrpImpostoIPI" maxlength="6" theme="simple" required="true" cssStyle="width: 80px;" cssClass="form-control"></s:textfield>						
							</div>						
						</div>
						<div class="row" style="margin-top: 15px;">
							<div class="col-lg-5">
								<label for="vrpImpostoISS" cssClass="control-label">ISS(%)</label>
								<s:textfield name="valoresProdutoVO.vrpImpostoICMS" id="vrpImpostoISS" maxlength="6" theme="simple" required="true" cssStyle="width: 80px;" cssClass="form-control"></s:textfield>							
							</div>
							<div class="col-lg-5">
								<label for="vvpValorProduto" cssClass="control-label">Valor de Venda</label>
								<s:textfield name="valoresProdutoVO.vvpValorProduto" id="vvpValorProduto" maxlength="9" theme="simple" required="true" cssStyle="width: 100px;" cssClass="form-control"></s:textfield>							
							</div>						
						</div>
					</form>					
		         </div>
			</div>
			<!-- dialog buttons -->
			<div class="modal-footer">
				<button id="btnInserir" type="button" class="btn btn-primary">Inserir</button>
			</div>
		</div>
	</div>
	
</div>
