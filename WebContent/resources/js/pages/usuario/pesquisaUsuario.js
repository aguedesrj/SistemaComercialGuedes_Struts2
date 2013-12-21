$(document).ready(function() {
	
    // Botão pesquisar usuário.
    $("#btnPesquisar").button().click(function() {
    	callListaUsuarios();
    });	
	
    // Botão novo usuário.
    $("#btnNovo").button().click(function() {
		$("#formUsuario").attr("action", "ManutencaoUsuario");
		$("#formUsuario").submit();    	
    });
    
    // Fechar modal do detalhe.
	$("#btnFechar").button().click(function() {	
		$("#modalDetalhe").modal('hide');
	});    
});

function callListaUsuarios() {
	$.ajax({
		url: 'SistemaComercialGuedes/Usuario/ListaUsuarios',
		type: 'POST',
		cache: false,
		dataType: "json",
		beforeSend: function(){
			$("#loading").css("display", "block");
			$("#divMensagemErro").css("display", "none");
			$("#divMensagemSucesso").css("display", "none");
		},
		success: function(data, status, request){
			$("#loading").css("display", "none");
			if (status == "success" && data.mensagemUsuario == null) {
				// atualiza lista na tabela.
				carregaTabela(data.listaUsuarios);
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

function carregaTabela(lista) {
	
	$("#tabelaUsuarios").jqGrid("clearGridData", true);
	
    // Tabela
	$("#tabelaUsuarios").jqGrid({
		datatype: 'local',
	    colNames:['Nome do Usuário','Login','','','','usuCodigo'],
	    colModel:[
	        {name:'pesNome',index:'pesNome', width:400, sortable:true, classes: 'pesNome', align:'center'},
	        {name:'usuLogin',index:'usuLogin', width:200, sortable:true, classes: 'usuLogin', align:'center'},
            {name:'detalhar',index:'detalhar', width:50, sortable:true, classes: 'detalhar'},
            {name:'alterar',index:'alterar', width:50, sortable:true, classes: 'alterar'},
            {name:'deletar',index:'deletar', width:50, sortable:true, classes: 'deletar'},
	        {name:'usuCodigo',index:'usuCodigo', width:50, sortable:true, key:true, classes: 'usuCodigo',hidden: true}
	    ],
        gridComplete: function(){
            var ids = $("#tabelaUsuarios").jqGrid('getDataIDs');
            for(var i=0;i < ids.length;i++){
                var valor = ids[i];
                btnDetalhar = "<div align='center' style='margin-top: 3px; margin-bottom: 5px;'><img width='19px' height='16px' title='Detalhar produto' alt='Detalhar usuário' style='cursor: pointer' src='../resources/img/detalhe.png' onclick='javascript:detalhar("+valor+");'></div>";
                btnAlterar  = "<div align='center' style='margin-top: 3px; margin-bottom: 5px;'><img width='16px' height='16px' title='Alterar produto' alt='Alterar usuário' style='cursor: pointer' src='../resources/img/edit.png' onclick='javascript:alterar("+valor+");'></div>";
                btnDeletar  = "<div align='center' style='margin-top: 3px; margin-bottom: 5px;'><img width='16px' height='16px' title='Deletar produto' alt='Deletar usuário' style='cursor: pointer' src='../resources/img/delete.png' onclick='javascript:deletar("+valor+");'></div>";
                $("#tabelaUsuarios").jqGrid('setRowData',ids[i],{alterar:btnAlterar,detalhar:btnDetalhar,deletar:btnDeletar});
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
	
    $("#tabelaUsuarios").jqGrid('setGridParam',{datatype: 'local',data:lista}).trigger("reloadGrid"); 	
}

function detalhar(usuCodigo) {
	$.ajax({
		url: 'SistemaComercialGuedes/Usuario/DetalhaUsuario?usuario.usuCodigo='+usuCodigo,
		type: 'POST',
		cache: false,
		dataType: "json",
		beforeSend: function(){
			$("#loading").css("display", "block");
			$("#divMensagemErro").css("display", "none");
			$("#divMensagemSucesso").css("display", "none");
		},
		success: function(data, status, request){ 
			$("#loading").css("display", "none");
			if (status == "success" && data.mensagemUsuario == null) {
				exibirModalDetalhe(data);
			} else {
				$("#loading").css("display", "none");
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

function alterar(usuCodigo) {
	// seta o código.
	$("#usuCodigo").val(usuCodigo);
	
	$("#formUsuario").attr("action", "ExibeAlteracaoUsuario");
	$("#formUsuario").submit();	
}

function exibirModalDetalhe(data) {
	$("#spanPerNome").html(data.usuario.pesNome);
	$("#spanUsuLogin").html(data.usuario.usuLogin);
	$("#spanPesDataCadastro").html(data.usuario.pesDataCadastro);
	$("#spanPesDataAlteracao").html(data.usuario.pesDataAlteracao);
	
	// exibir os perfis.
	$("#spanPerfis").html("");
	for (var i = 0; data.usuario.listaPerfil.length > i; i++) {
		$("#spanPerfis").append(data.usuario.listaPerfil[i].perNome+"<br>");
	}
	
    // exibir modal.
	$("#modalDetalhe").modal({ // wire up the actual modal functionality and show the dialog
   		 "backdrop" : "static",
   		 "keyboard" : true,
   		 "show" : true // ensure the modal is shown immediately
	});		
}

function deletar(usuCodigo) {
	BootstrapDialog.show({
		title: 'Sistema Comercial Guedes',
        message: 'Deseja excluir o perfil?',
        closable: false,
        buttons: [{
            label: 'SIM',
            action: function(dialogRef){
            	dialogRef.close();
            	callDeletar(usuCodigo);
            }
        }, {
            label: 'NÃO',
            action: function(dialogRef){
                dialogRef.close();
            }
        }]
    });	
}

function callDeletar(usuCodigo) {
	$.ajax({
		url: 'SistemaComercialGuedes/Usuario/DeletaUsuario?usuario.usuCodigo='+usuCodigo,
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
				// obter a lista de usuários.
				callListaUsuarios();
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
