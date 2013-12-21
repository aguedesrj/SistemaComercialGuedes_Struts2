<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../resources/js/pages/produto/manutencaoProduto.js"></script>	

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Manutenção Produto</h3>
        </div>
        <s:form namespace="Produto" id="formProduto" name="formProduto" theme="simple" cssStyle="margin-left: 15px;">
        	<span style="margin-top: 10px; font-size: 12px; font-style: italic;">* campos obrigatórios</span>
			<s:hidden id="proCodigo" name="produto.proCodigo"></s:hidden>
			<div class="row">
				<div class="col-lg-6">
					<s:label for="proNome" cssClass="control-label">Nome do Produto</s:label>
					<s:textfield name="produto.proNome" id="proNome" maxlength="120" theme="simple" required="true" cssClass="form-control" cssStyle="width: 300px;"/>
				</div>
				<div class="col-lg-6">
					<s:label for="proNome" cssClass="control-label">Código de Barras</s:label>
					<s:textfield name="produto.proCodigoBarras" id="proCodigoBarras" maxlength="50" theme="simple" required="true" cssClass="form-control" cssStyle="width: 200px;"/>
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-lg-6">
					<s:label for="forCodigo" cssClass="control-label">Fornecedor</s:label>
					<s:select name="fornecedor.pessoa.pesCodigo" list="listaFornecedor" id="forCodigo" headerKey="0" headerValue=":: Selecione Fornecedor ::" theme="simple" listValue="pesNome" listKey="pesCodigo" cssStyle="width: 350px;" cssClass="form-control"></s:select>
				</div>
				<div class="col-lg-6">
					<s:label for="catCodigo" cssClass="control-label">Categoria</s:label>
					<s:select name="categoria.catCodigo" list="listaCategoria" id="catCodigo" headerKey="0" headerValue=":: Selecione Categoria ::" theme="simple" listValue="catDescricao" listKey="catCodigo" cssStyle="width: 350px;" cssClass="form-control"></s:select>	
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-lg-6">
					<s:label for="proQuantidadeMinima" cssClass="control-label">Quantidade Mínima</s:label>
					<s:textfield name="produto.proQuantidadeMinima" id="proQuantidadeMinima" maxlength="7" theme="simple" required="true" cssStyle="width: 80px;" cssClass="form-control"></s:textfield>				
				</div>
				<div class="col-lg-6">
					<s:label for="proQuantidadeMaxima" cssClass="control-label">Quantidade Máxima</s:label>
					<s:textfield name="produto.proQuantidadeMaxima" id="proQuantidadeMaxima" maxlength="7" theme="simple" required="true" cssStyle="width: 80px;" cssClass="form-control"></s:textfield>					
				</div>
			</div>	
			<div class="row" style="margin-top: 10px;">
				<div class="col-lg-6">
					<s:label for="proObs" cssClass="control-label">Observações</s:label>
					<s:textarea name="produto.proObs" id="proObs" cols="90" theme="simple" cssClass="form-control"></s:textarea>				
				</div>
			</div>									
			<div class="row" style="margin-top: 10px;">
				<div class="col-lg-5">
					<s:label for="proNome" cssClass="control-label">Tabela com valores do Produto</s:label><br>
					<div style="width:650px; float:left;">
						<table id="tabelaValores" cellspacing="1" cellpadding="3" class="tablehead" style="background:#CCC;"></table>
					</div>							
				</div>						
			</div>        
		</s:form>
		<div class="panel-body">
	    	<button id="btnSalvar" type="button" class="btn btn-primary">Salvar</button>
	    	<button style="margin-left: 10px;" id="btnExibirInserirValor" type="button" class="btn btn-primary">Inserir Valor</button>	    	
		</div>					
	</div>
</div>

<!-- DIV do formulário Valores do Produto -->
<div id="modalValoresProduto" class="modal fade">
	<div class="modal-dialog" style="width: 410px;">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="panel panel-default" style="margin-top: 30px;">
			    	<div class="panel-heading">
			        	<h3 class="panel-title">Valores do Produto</h3>
			        </div>
   					<form id="formDialogValoresProduto" style="margin-left: 15px;">
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-5">
								<label for="vrpImpostoICMS" cssClass="control-label">ICMS</label>
								<div class="input-group">
									<span class="input-group-addon">%</span>
								  	<s:textfield name="valoresProdutoVO.vrpImpostoICMS" id="vrpImpostoICMS" maxlength="6" theme="simple" required="true" cssStyle="width: 80px;" cssClass="form-control"></s:textfield>	
								</div>								
							</div>
							<div class="col-lg-5">
								<label for="vrpImpostoIPI" cssClass="control-label">IPI</label>
								<div class="input-group">
									<span class="input-group-addon">%</span>
								  	<s:textfield name="valoresProdutoVO.vrpImpostoIPI" id="vrpImpostoIPI" maxlength="6" theme="simple" required="true" cssStyle="width: 80px;" cssClass="form-control"></s:textfield>
								</div>								
							</div>						
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-5">
								<label for="vrpImpostoISS" cssClass="control-label">ISS</label>
								<div class="input-group">
									<span class="input-group-addon">%</span>
								  	<s:textfield name="valoresProdutoVO.vrpImpostoICMS" id="vrpImpostoISS" maxlength="6" theme="simple" required="true" cssStyle="width: 80px;" cssClass="form-control"></s:textfield>	
								</div>								
							</div>
							<div class="col-lg-5">
								<label for="vvpValorProduto" cssClass="control-label">Valor de Venda</label>
								<div class="input-group">
									<span class="input-group-addon">R$</span>
								  	<s:textfield name="valoresProdutoVO.vvpValorProduto" id="vvpValorProduto" maxlength="10" theme="simple" required="true" cssStyle="width: 100px;" cssClass="form-control"></s:textfield>
								</div>								
							</div>						
						</div>
					</form>
					<div class="panel-body" style="margin-top: 10px;">
				    	<button id="btnInserir" type="button" class="btn btn-primary">Inserir</button>
				    	<button id="btnCancelar" type="button" class="btn btn-primary" style="margin-left: 10px;">Cancelar</button>	    	
					</div>									        
			    </div>				
			</div>
		</div>
	</div>
</div>
