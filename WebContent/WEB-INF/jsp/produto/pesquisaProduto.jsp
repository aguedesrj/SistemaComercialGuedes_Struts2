<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../resources/js/pages/produto/pesquisaProduto.js"></script>	

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Pesquisar Produto</h3>
        </div>
	    <s:form namespace="Produto" id="formProduto" name="formProduto" theme="simple" cssStyle="margin-left: 15px; margin-top: 15px;">
	    	<div class="row">
				<div class="col-lg-5">
					<s:label for="proNome" cssClass="control-label">Nome do Produto</s:label>
					<s:textfield name="produto.proNome" id="proNome" maxlength="120" theme="simple" cssClass="form-control" cssStyle="width: 250px;"/>
				</div>
			</div>	    	
		</s:form>
		<div class="panel-body">
	    	<button id="btnPesquisar" type="button" class="btn btn-primary">Pesquisar</button>
	    	<button style="margin-left: 10px;" id="btnNovo" type="button" class="btn btn-primary">Novo</button>
		</div>		
	</div>
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Listagem Produto</h3>
        </div>
		<div class="panel-body">
	    	<table width="100%" id="tabelaProdutos" class="tablehead" style="background:#CCC;"></table>
		</div>
	</div>   	
</div>

<!-- DIV do detalhamento -->
<div id="modalDetalhe" class="modal fade">
	<div class="modal-dialog" style="width: 400px;">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="panel panel-default" style="margin-top: 30px;">
			    	<div class="panel-heading">
			        	<h3 class="panel-title">Detalhe</h3>
			        </div>
   					<form id="formDialogProduto" style="margin-left: 15px;">
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<label for="spanProDataCadastro" cssClass="control-label">Data de cadastro</label><br>
								<span id="spanProDataCadastro"></span>									
							</div>
							<div class="col-lg-6">
								<label for="spanProDataAlteracao" cssClass="control-label">Data de alteração</label><br>
								<span id="spanProDataAlteracao"></span>									
							</div>						
						</div>   					
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<label for="spanProNome" cssClass="control-label">Nome</label><br>
								<span id="spanProNome"></span>									
							</div>
							<div class="col-lg-6">
								<label for="spanProCodigoBarras" cssClass="control-label">Código de Barras</label><br>
								<span id="spanProCodigoBarras"></span>									
							</div>						
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<label for="spanForNome" cssClass="control-label">Fornecedor</label><br>
								<span id="spanForNome"></span>								
							</div>
							<div class="col-lg-6">
								<label for="spanCatDescricao" cssClass="control-label">Categoria</label><br>
								<span id="spanCatDescricao"></span>								
							</div>						
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<label for="spanProQuantidadeMinima" cssClass="control-label">Quantidade Mínima</label><br>
								<span id="spanProQuantidadeMinima"></span>							
							</div>
							<div class="col-lg-6">
								<label for="spanProQuantidadeMaxima" cssClass="control-label">Quantidade Máxima</label><br>
								<span id="spanProQuantidadeMaxima"></span>								
							</div>												
						</div>	
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<label for="spanProObs" cssClass="control-label">Observações</label><br>
								<span id="spanProObs"></span>							
							</div>											
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-8">
								<label for="spanValoresProduto" cssClass="control-label">Tabela com valores do Produto</label><br>
								<span id="spanValoresProduto"></span>							
							</div>					
						</div>																	
					</form>
					<div class="panel-body" style="margin-top: 10px;">
				    	<button id="btnFechar" type="button" class="btn btn-primary">Fechar</button>	    	
					</div>									        
			    </div>				
			</div>
		</div>
	</div>
</div>
