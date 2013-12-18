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
	    	<s:hidden id="usuCodigo" name="usuario.usuCodigo"></s:hidden>
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

<!-- DIV do detalhamento -->
<div id="modalDetalhe" class="modal fade">
	<div class="modal-dialog" style="width: 450px;">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="container">
					<table style="width: 370px; margin-top: 15px;">
						<tr>
							<td>
								<label for="spanPerNome" cssClass="control-label">Nome</label><br>
								<span id="spanPerNome"></span>							
							</td>
							<td>
								<label for="spanUsuLogin" cssClass="control-label">Login</label><br>
								<span id="spanUsuLogin"></span>							
							</td>							
						</tr>
						<tr>
							<td>
								<label for="spanPesDataCadastro" cssClass="control-label">Data inclusão</label><br>
								<span id="spanPesDataCadastro"></span>							
							</td>
							<td>
								<label for="spanPesDataAlteracao" cssClass="control-label">Data alteração</label><br>
								<span id="spanPesDataAlteracao"></span>							
							</td>							
						</tr>						
						<tr>
							<td colspan="2">
								<label for="spanPerfis" cssClass="control-label">Perfis:</label><br>
								<span id="spanPerfis"></span>							
							</td>
						</tr>
					</table>
		         </div>
			</div>
			<!-- dialog buttons -->
			<div class="modal-footer">
				<button id="btnFechar" type="button" class="btn btn-primary">Fechar</button>
			</div>
		</div>
	</div>
</div>
