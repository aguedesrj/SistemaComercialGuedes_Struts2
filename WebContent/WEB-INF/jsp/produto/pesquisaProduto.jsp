<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../resources/js/pages/pesquisaProduto.js"></script>	

<div class="container">
	<div class="page-header">
    	<h1>Cadastro Produto</h1>
    </div>
    <s:form namespace="Produto" id="formProduto" name="formProduto" theme="simple" cssStyle="margin-left: 15px;">
		<div class="row" style="margin-top: 15px;">
			<div class="col-lg-5">
				<s:label for="proNome" cssClass="control-label">Nome do Produto</s:label>
				<s:textfield name="produto.proNome" id="proNome" maxlength="120" theme="simple" required="true" cssClass="form-control" cssStyle="width: 250px;"/>
			</div>
		</div>    
    </s:form>
	<div class="row" style="margin-top: 15px; margin-left: 0px;">
		<button id="btnPesquisar" type="button" class="btn btn-primary">Pesquisar</button>
	</div>    
	<div class="row" style="margin-top: 15px; margin-left: 0px;">
		<div style="width:650px; float:left;">
			<table id="tabelaProdutos" cellspacing="1" cellpadding="3" class="tablehead" style="background:#CCC;"></table>
		</div>
	</div>
</div>