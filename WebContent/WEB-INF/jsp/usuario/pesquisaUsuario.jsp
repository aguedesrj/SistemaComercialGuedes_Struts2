<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../resources/js/pages/usuario/pesquisaUsuario.js"></script>

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Pesquisar Usuário</h3>
        </div>
	    <s:form namespace="Usuario" id="formUsuario" name="formUsuario" theme="simple">
			<div class="panel-body">
		    	<button id="btnPesquisar" type="button" class="btn btn-primary">Pesquisar</button>
		    	<button style="margin-left: 10px;" id="btnNovo" type="button" class="btn btn-primary">Novo</button>
			</div>
		</s:form>
	</div>
	
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Listagem Usuário</h3>
        </div>
		<div class="panel-body">
	    	<table width="100%" id="tabelaUsuarios" class="tablehead" style="background:#CCC;"></table>
		</div>
	</div>   	
</div>
