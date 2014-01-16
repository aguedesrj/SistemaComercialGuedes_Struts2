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
	    <s:form namespace="Venda" id="formVenda" name="formVenda" theme="simple" cssStyle="margin-left: 15px;">
			<div class="row" style="margin-top: 10px;">
				<div class="col-lg-2">
					<s:label for="dataInicio" cssClass="control-label">Data início</s:label>
					<s:textfield name="venda.venDataInicio" id="venDataInicio" maxlength="10" theme="simple" required="true" cssClass="form-control" cssStyle="width: 120px;"/>
				</div>
				<div class="col-lg-2">
					<s:label for="dataFim" cssClass="control-label">Data fim</s:label>
					<s:textfield name="venda.venDataFim" id="venDataFim" maxlength="10" theme="simple" required="true" cssClass="form-control" cssStyle="width: 120px;"/>
				</div>		    		
			</div>
		</s:form>
		<div class="panel-body">
	    	<button id="btnPesquisar" type="button" class="btn btn-primary">Pesquisar</button>  	
		</div>			
	</div>
	
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Gráfico</h3>
        </div>
		<div class="panel-body">
			<!-- inserir gráfico -->
			<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		</div>
	</div>   	
</div>