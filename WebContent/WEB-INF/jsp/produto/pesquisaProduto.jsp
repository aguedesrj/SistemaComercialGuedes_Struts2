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
