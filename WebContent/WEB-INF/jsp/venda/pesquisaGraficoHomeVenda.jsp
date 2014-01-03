<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../resources/js/pages/venda/graficoHomeVenda.js"></script>

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Pesquisar Vendas</h3>
        </div>
	    <s:form namespace="Venda" id="formVenda" name="formVenda" theme="simple">
			<div class="panel-body">
		    	<p>Data in�cio:	<s:textfield name="venda.venDataInicio" id="venDataInicio" maxlength="10" theme="simple" required="true" cssClass="form-control" cssStyle="width: 120px;"/></p>
		    	<p>Data fim: <s:textfield name="venda.venDataFim" id="venDataFim" maxlength="10" theme="simple" required="true" cssClass="form-control" cssStyle="width: 120px;"/></p>
		    	<button id="btnPesquisar" type="button" class="btn btn-primary">Pesquisar</button>
			</div>
		</s:form>
	</div>
	
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Gr�fico</h3>
        </div>
		<div class="panel-body">
			<!-- inserir gr�fico -->
			<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		</div>
	</div>   	
</div>