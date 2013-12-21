$(document).ready(function() {
	
	var itensSelecionados = [];
	
	// selecionar todos.
	$('#select-all').click(function(){
		$('#selectFuncionalidades').multiSelect('select_all');
		return false;
	});	
	
	$('#deselect-all').click(function(){
		$('#selectFuncionalidades').multiSelect('deselect_all');
		return false;
	});	
	
    // Botão Novo Perfil.
    $("#btnNovoPerfil").button().click(function() {
    	// buscar todos as funcionalidades.
    	$.ajax({
    		url: 'SistemaComercialGuedes/Usuario/ListaFuncionalidades',
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
    				// preencher lista.
    				var options = '';
    				for (var i = 0; data.listaDisponiveis.length > i; i++) {
    					options += "<option id='"+data.listaDisponiveis[i].genCodigo+"' value='"+data.listaDisponiveis[i].genCodigo+"' style='font-size: 10px;'>"+data.listaDisponiveis[i].genDescricao+"</option>";
    				}  
    				$("#selectFuncionalidades").html("");
    				$("#selectFuncionalidades").html(options);
    				$('#selectFuncionalidades').multiSelect();
    		    	$("#perNome").css("border", "1px solid #cccccc");
    		        // Limpar campos.
    		        $("#perNome").val("");
    		        //$('#selectFuncionalidades').multiSelect('deselect_all');
    		        // exibir modal.
    		    	$("#modalManutencaoPerfil").modal({ // wire up the actual modal functionality and show the dialog
    			   		 "backdrop" : "static",
    			   		 "keyboard" : true,
    			   		 "show" : true // ensure the modal is shown immediately
    		    	});
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
						// atualiza a lista de perfis.
						callListaPerfis();
						$("#modalManutencaoPerfil").modal('hide');
                    	$("#divMensagemSucesso").css("display", "block");
                    	$("#spanMsgSuccess").show().html("Perfil cadastrado com sucesso!");						
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
	
    // Fechar modal do detalhe.
	$("#btnFechar").button().click(function() {	
		$("#modalDetalhe").modal('hide');
	});
	
    // Botão Cancelar, fechar modal do detalhe.
	$("#btnCancelar").button().click(function() {	
		$("#modalDetalhe").modal('hide');
	});	
	
	// obter a lista de perfis.
	callListaPerfis();
});

function callListaPerfis() {
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
				exibirListaPerfis(data.listaSelecionados);
			} else {
				$("#divMensagemErro").css("display", "block");
				$("#spanMsgError").show().html(data.mensagemUsuario);  					
			}
		},
		complete : function () {
			$("#loading").css("display", "none");
		},
		error: function (request, error) {
			$("#divMensagemErro").css("display", "block");
			$("#spanMsgError").show().html("Sistema indisponível no momento.");  
		}
	});	
}

function exibirListaPerfis(lista) {
	
	$("#tabelaPerfis").jqGrid("clearGridData", true);
	
    // Tabela de Perfis
	$("#tabelaPerfis").jqGrid({
		datatype: 'local',
	    colNames:['Nome do Perfil','','','','genCodigo'],
	    colModel:[
	        {name:'genDescricao',index:'genDescricao', width:400,sortable:true, classes: 'genDescricao', align:'center'},
            {name:'detalhar',index:'detalhar', width:50,sortable:true, classes: 'detalhar'},
            {name:'alterar',index:'alterar', width:50,sortable:true, classes: 'alterar'},
            {name:'deletar',index:'deletar', width:50,sortable:true, classes: 'deletar'},
	        {name:'genCodigo',index:'genCodigo', width:50,sortable:true, key:true, classes: 'genCodigo',hidden: true}
	    ],
        gridComplete: function(){
            var ids = $("#tabelaPerfis").jqGrid('getDataIDs');
            for(var i=0;i < ids.length;i++){
                var valor = ids[i];
                btnDetalhar = "<div align='center' style='margin-top: 3px; margin-bottom: 5px;'><img width='19px' height='16px' title='Detalhar produto' alt='Detalhar produto' style='cursor: pointer' src='../resources/img/detalhe.png' onclick='javascript:detalhar("+valor+");'></div>";
                btnAlterar  = "<div align='center' style='margin-top: 3px; margin-bottom: 5px;'><img width='16px' height='16px' title='Alterar produto' alt='Alterar produto' style='cursor: pointer' src='../resources/img/edit.png' onclick='javascript:alterar("+valor+");'></div>";
                btnDeletar  = "<div align='center' style='margin-top: 3px; margin-bottom: 5px;'><img width='16px' height='16px' title='Deletar produto' alt='Deletar produto' style='cursor: pointer' src='../resources/img/delete.png' onclick='javascript:deletar("+valor+");'></div>";
                $("#tabelaPerfis").jqGrid('setRowData',ids[i],{alterar:btnAlterar,detalhar:btnDetalhar,deletar:btnDeletar});
            }   
        },	    
	    multiselect: false,
	    rowNum:50,
	    scroll:true,
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

function detalhar(genCodigo) {
	$.ajax({
		url: 'SistemaComercialGuedes/Usuario/ListaFuncionalidadesPorPerfil?perfil.perCodigo='+genCodigo,
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
				exibirModalDetalhe(data);
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
}

function exibirModalDetalhe(data) {
	// exibir nome do Perfil.
	$("#spanPerNome").html(data.perfil.perNome);
	
	// exibir as funcionalidades.
	$("#spanFuncionalidade").html("");
	for (var i = 0; data.listaSelecionados.length > i; i++) {
		$("#spanFuncionalidade").append(data.listaSelecionados[i].genDescricao+"<br>");
	}
	
    // exibir modal.
	$("#modalDetalhe").modal({ // wire up the actual modal functionality and show the dialog
   		 "backdrop" : "static",
   		 "keyboard" : true,
   		 "show" : true // ensure the modal is shown immediately
	});		
}

function alterar(genCodigo) {
	$.ajax({
		url: 'SistemaComercialGuedes/Usuario/ExibeAlteracao?perfil.perCodigo='+genCodigo,
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
				// preencher lista.
				var options = '';
				for (var i = 0; data.listaSelecionados.length > i; i++) {
					options += "<option selected id='"+data.listaSelecionados[i].genCodigo+"' value='"+data.listaSelecionados[i].genCodigo+"' style='font-size: 10px;'>"+data.listaSelecionados[i].genDescricao+"</option>";
				}  
				for (var i = 0; data.listaDisponiveis.length > i; i++) {
					options += "<option id='"+data.listaDisponiveis[i].genCodigo+"' value='"+data.listaDisponiveis[i].genCodigo+"' style='font-size: 10px;'>"+data.listaDisponiveis[i].genDescricao+"</option>";
				} 
				$('#selectFuncionalidades').multiSelect('deselect_all');
				$("#selectFuncionalidades").html(options);
				$('#selectFuncionalidades').multiSelect();					
				
				// seta os valores.
				$("#perCodigo").val(data.perfil.perCodigo);
				$("#perNome").val(data.perfil.perNome);
				
		    	// exibir modal.
		    	$("#modalManutencaoPerfil").modal({ // wire up the actual modal functionality and show the dialog
			   		 "backdrop" : "static",
			   		 "keyboard" : true,
			   		 "show" : true // ensure the modal is shown immediately
		    	});
			} else {
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
}

function deletar(genCodigo) {
	BootstrapDialog.show({
		title: 'Sistema Comercial Guedes',
        message: 'Deseja excluir o perfil?',
        closable: false,
        buttons: [{
            label: 'SIM',
            action: function(dialogRef){
            	dialogRef.close();
            	callDeletar(genCodigo);
            }
        }, {
            label: 'NÃO',
            action: function(dialogRef){
                dialogRef.close();
            }
        }]
    });	
}

/*
 * 
 */
function callDeletar(genCodigo) {
	$.ajax({
		url: 'SistemaComercialGuedes/Usuario/DeletaPerfil?perfil.perCodigo='+genCodigo,
		type: 'POST',
		cache: false,
		dataType: "json",
		beforeSend: function(){
			$("#loading").css("display", "block");
		},
		success: function(data, status, request){
			$("#loading").css("display", "none");
			if (status == "success") {
				BootstrapDialog.show({
					title: 'Sistema Comercial Guedes',
		            message: data.mensagemUsuario
		        });				
				// obter a lista de perfis.
				callListaPerfis();
			} else {
				BootstrapDialog.show({
					title: 'Sistema Comercial Guedes',
		            message: data.mensagemUsuario
		        });						
			}
		},
		error: function (request, error) {
			$("#loading").css("display", "none");
			BootstrapDialog.show({
				title: 'Sistema Comercial Guedes',
	            message: "Sistema indisponível no momento."
	        });				
		}
	});	
}

function validarFormPerfil() {
	
	$("#divMensagemErro").css("display", "none");
	$("#divMensagemSucesso").css("display", "none");
	
	itensSelecionados = [];
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
		itensSelecionados[i] = $(selected).val();
	});
	
	// valida funcionalidades.
	if (itensSelecionados.length == 0) {
		isValidos = true;
	}
	
	if (isValidos) {
		$("#itensSelecionados").val("");
		$("#divMensagemErro").css("display", "block");
		$("#spanMsgError").show().html("O(s) campo(s) em vermelho(s) é obrigatório.");			
		return false;		
	} else {
		$("#itensSelecionados").val(itensSelecionados);
	}
	
	return true;	
}
