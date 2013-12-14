<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../resources/js/pages/manutencaoPerfil.js"></script>

<div class="container">
	<div class="page-header">
    	<h1>Manutenção Perfil do Usuário</h1>
    </div>
    <div class="row" style="margin-top: 15px; margin-left: 0px;">
		<button id="btnNovoPerfil" type="button" class="btn btn-primary">Novo Perfil</button>
	</div>    
	<div class="page-header">
    	<h1>Listagem Perfil do Usuário</h1>
    </div>
	<div class="row" style="margin-top: 15px; margin-left: 0px;">
		<div style="float:left;">
			<table width="100%" id="tabelaPerfis" class="tablehead" style="background:#CCC;"></table>
		</div>
	</div>    	
</div>

<!-- DIV do formulário -->
<div id="modalManutencaoPerfil" class="modal fade">
	<div class="modal-dialog" style="width: 500px;">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="container" style="width: 370px;">
					<form id="formManutencaoPerfil" name="formManutencaoPerfil">
						<s:hidden id="perCodigo" name="perfil.perCodigo"></s:hidden>
						<s:hidden id="funcSelecionados" name="funcSelecionados"></s:hidden> 
						<div class="row" style="margin-top: 15px;">
							<div class="col-lg-5">
								<label for="vrpImpostoICMS" cssClass="control-label">Nome do Perfil</label>
								<s:textfield name="perfil.perNome" id="perNome" maxlength="80" theme="simple" required="true" cssStyle="width: 250px;" cssClass="form-control"></s:textfield>						
							</div>						
						</div>
						<div class="row" style="margin-top: 15px;">
							<div class="col-lg-5">
								<a href='#' id='select-all'>Selecionar todos</a>
								<a href='#' id='deselect-all'>Desmarcar todos</a>
								<select id='selectFuncionalidades' multiple='multiple' name="selectFuncionalidades">
								</select>							
							</div>						
						</div>
					</form>					
		         </div>
			</div>
			<!-- dialog buttons -->
			<div class="modal-footer">
				<button id="btnSalvar" type="button" class="btn btn-primary">Salvar</button>
			</div>
		</div>
	</div>
</div>

<!-- DIV do detalhamento -->
<div id="modalDetalhe" class="modal fade">
	<div class="modal-dialog" style="width: 500px;">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="container" style="width: 370px;">
					<div class="row" style="margin-top: 15px;">
						<div class="col-lg-4">
							<label for="perNome" cssClass="control-label">Nome do Perfil</label>
							<span id="spanPerNome"></span>
						</div>						
					</div>
					<div class="row" style="margin-top: 15px;">
						<div class="col-lg-7">
							<label for="spanFuncionalidade" cssClass="control-label">Funcionalidades:</label>
							<span id="spanFuncionalidade"></span>							
						</div>						
					</div>
		         </div>
			</div>
			<!-- dialog buttons -->
			<div class="modal-footer">
				<button id="btnFechar" type="button" class="btn btn-primary">Fechar</button>
			</div>
		</div>
	</div>
</div>
