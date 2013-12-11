$(document).ready(function() {
	
	var funcSelecionados = [];
	$('#selectFuncionalidades').multiSelect();	
	
	// selecionar todos.
	$('#select-all').click(function(){
		$('#selectFuncionalidades').multiSelect('#select_all');
		return false;
	});	
	
	$('#deselect_all').click(function(){
		$('#selectFuncionalidades').multiSelect('#deselect_all');
		return false;
	});	
	
    // Botão Novo Perfil.
    $("#btnNovoPerfil").button().click(function() {
    	$("#perNome").css("border", "1px solid #cccccc");
        // Limpar campos.
        $("#perNome").val("");
        // exibir modal.
    	$("#modalManutencaoPerfil").modal({ // wire up the actual modal functionality and show the dialog
	   		 "backdrop" : "static",
	   		 "keyboard" : true,
	   		 "show" : true // ensure the modal is shown immediately
    	});
    	$("#perNome").focus();
    });	
    
    // Salvar.
	$("#btnSalvar").button().click(function() {
		if (validarFormPerfil()) {
			// ir no servidor...
			$.ajax({
				url: 'SistemaComercialGuedes/Usuario/SalvaPerfil',
				data: $('#formManutencaoPerfil').serialize(),
				type: 'POST',
				cache: false,
				dataType: "json",
				beforeSend: function(){
					$("#loading").css("display", "block");
					$("#divMensagemErro").css("display", "none");
				},
				success: function(data, status, request){
					$("#loading").css("display", "none");
					if (status == "success" && data.mensagemUsuario == null) {
						$("#tabelaPerfis").addRowData(0, {
							perCodigo : data.listaPerfil.perCodigo,
							perNome   : data.listaPerfil.perNome
						});
						$("#modalValoresProduto").modal('hide'); 
					} else {
						$("#divMensagemErro").css("display", "block");
						$("#spanMsgError").show().html(data.mensagemUsuario);						
					}
				},
				error: function (request, error) {
					$("#loading").css("display", "none");
					$("#divMensagemErro").css("display", "block");
					$("#spanMsgError").show().html("Sistema indisponível no momento.");
				}        
			});                                
		}			
    });
	
	$.ajax({
		url: 'SistemaComercialGuedes/Usuario/ListaPerfis',
		type: 'POST',
		cache: false,
		dataType: "json",
		beforeSend: function(){
			$("#loading").css("display", "block");
			$("#divMensagemErro").css("display", "none");
			$("#divMensagemSucesso").css("display", "none");
		},
		success: function(data, status, request){ 
			if (status == "success" && data.mensagemUsuario == null) {
				// atualiza lista na tabela.
				exibirListaPerfis(data.listaPerfil);
			} else {
				$("#loading").css("display", "none");
				$("#divMensagemErro").css("display", "block");
				$("#spanMsgError").show().html(data.mensagemUsuario);  					
			}
		},
		complete : function () {
			$("#loading").css("display", "none");
		},
		error: function (request, error) {
			$("#loading").css("display", "none");
			$("#divMensagemErro").css("display", "block");
			$("#spanMsgError").show().html("Sistema indisponível no momento.");  
		}
	});		
});

function exibirListaPerfis(lista) {
	
	$("#tabelaPerfis").jqGrid("clearGridData", true);
	
    // Tabela de Perfis
	$("#tabelaPerfis").jqGrid({
		datatype: 'local',
	    colNames:['Nome do Perfil','Detalhes','Alterar','perCodigo'],
	    colModel:[
	        {name:'perNome',index:'perNome', width:400,sortable:true, classes: 'perNome', align:'center'},
            {name:'detalhar',index:'detalhar', width:60,sortable:true, classes: 'detalhar'},
            {name:'alterar',index:'alterar', width:60,sortable:true, classes: 'alterar'},	        
	        {name:'perCodigo',index:'perCodigo', width:180,sortable:true, key:true, classes: 'perCodigo',hidden: true}
	    ],
        gridComplete: function(){
            var ids = $("#tabelaPerfis").jqGrid('getDataIDs');
            for(var i=0;i < ids.length;i++){
                var valor = ids[i];
                btnDetalhar = "<div align='center' style='margin-top: 3px;'><img width='19px' height='16px' alt='Detalhar produto' src='../resources/img/ic_ferramenta.gif' onclick='javascript:detalhar("+valor+");'></div>";
                btnAlterar  = "<div align='center' style='margin-top: 3px;'><img width='16px' height='16px' alt='Alterar produto'  src='../resources/img/ic_sbox_editar.gif' onclick='javascript:alterar("+valor+");'></div>";
                $("#tabelaPerfis").jqGrid('setRowData',ids[i],{alterar:btnAlterar,detalhar:btnDetalhar});
            }   
        },	    
	    multiselect: false,
	    rowNum:50,
	    rowList:[10,20,30],
        onSelectRow: function (id) {
    		//alert(id); 		
    	},        
        sortname: 'id'
	});

	// remover o button de felhar tabela.
	$(".ui-jqgrid-titlebar-close").hide();	
	
    $("#tabelaPerfis").jqGrid('setGridParam',{datatype: 'local',data:lista}).trigger("reloadGrid"); 	
}

function detalhar(perCodigo) {
	
}

function alterar(perCodigo) {

}

function validarFormPerfil() {
	
	funcSelecionados = [];
	var isValidos = false;
	var perNome = $("#perNome");	
	
	perNome.css("border", "1px solid #cccccc");
	
	// Validar campo nome.
	if (perNome.val().trim() == "") {
		perNome.css("border", "1px solid #ff4500");
		isValidos = true;
	}	
	
	// obter as funcionalidades selecionadas.
	$('#selectFuncionalidades :selected' ).each(function(i, selected) {
		funcSelecionados[i] = $(selected).val();
	});
	
	// valida funcionalidades.
	if (funcSelecionados.length == 0) {
		isValidos = true;
	}
	
	if (isValidos) {
		$("#funcSelecionados").val("");
		$("#divMensagemErro").css("display", "block");
		$("#spanMsgError").show().html("O(s) campo(s) em vermelho(s) é obrigatório.");			
		return false;		
	} else {
		$("#funcSelecionados").val(funcSelecionados);
	}
	
	return true;	
}
