<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="../resources/js/pages/loja/manutencaoLoja.js"></script>	

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Manutenção Loja</h3>
        </div>
        <s:form namespace="Loja" id="formLoja" name="formLoja" theme="simple" cssStyle="margin-left: 15px;">
        	<span style="margin-top: 10px; font-size: 12px; font-style: italic;">* campos obrigatórios</span>
			<s:hidden id="lojCodigo" name="loja.lojCodigo"></s:hidden>
			<div class="row">
				<div class="col-lg-6">
					<s:label for="lojNome" cssClass="control-label">Nome</s:label>
					<s:textfield name="loja.lojNome" id="lojNome" maxlength="100" theme="simple" required="true" cssClass="form-control" cssStyle="width: 300px;"/>
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-lg-5">
					<s:label for="lojNome" cssClass="control-label">Tabela com os Caixas</s:label><br>
					<div style="width:650px; float:left;">
						<table id="tabelaCaixas" cellspacing="1" cellpadding="3" class="tablehead" style="background:#CCC;"></table>
					</div>							
				</div>						
			</div>        
		</s:form>
		<div class="panel-body">
	    	<button id="btnSalvar" type="button" class="btn btn-primary">Salvar</button>
	    	<button style="margin-left: 10px;" id="btnInserirCaixa" type="button" class="btn btn-primary">Inserir Caixa</button>	    	
		</div>					
	</div>
</div>

<!-- DIV do modal Caixa -->
<div id="modalCaixa" class="modal fade">
	<div class="modal-dialog" style="width: 410px;">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="panel panel-default" style="margin-top: 30px;">
			    	<div class="panel-heading">
			        	<h3 class="panel-title">Caixa</h3>
			        </div>
   					<form id="formCaixa" style="margin-left: 15px;">
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-5">
								<s:label for="caiDescricao" cssClass="control-label">Descrição</s:label>
								<s:textfield name="caixa.caiDescricao" id="caiDescricao" maxlength="20" theme="simple" required="true" cssClass="form-control" cssStyle="width: 100px;"/>								
							</div>						
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-5">
								<s:label for="lojNome" cssClass="control-label">Observação</s:label>
								<s:textarea name="caixa.caiObs" id="caiObs" cols="90" theme="simple" cssClass="form-control"></s:textarea>
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
