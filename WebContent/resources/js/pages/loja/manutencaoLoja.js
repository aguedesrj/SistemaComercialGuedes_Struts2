﻿$(document).ready(function() {
	
	$("#proNome").focus();
	
    // inserir márcara nos campos.
    $("#proPesoLiquido").maskMoney();
    $("#proPesoBruto").maskMoney();
    $("#vrpImpostoICMS").maskMoney();
    $("#vrpImpostoIPI").maskMoney();
    $("#vrpImpostoISS").maskMoney();
    $("#vvpValorProduto").maskMoney();
    
    $('#proObs').limit('10');
    $('#proQuantidadeMaxima').numeric();
    $('#proQuantidadeMinima').numeric();
    
    $("tabelaValores").tablecloth({
    	theme: "paper",
        striped: true,
        sortable: true,
        condensed: true
    }); 
    
    // Fechar modal do detalhe.
	$("#btnCancelar").button().click(function() {	
		$("#modalValoresProduto").modal('hide');
	});	
	
    // Botão gravar Produto.
    $("#btnSalvar").button().click(function() {
    	// Validar campos.
        if (isCamposFormularioValidos()) {                
        	$.ajax({
        		url: 'SistemaComercialGuedes/Produto/Salva',
                data: $('#formProduto').serialize(),
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
                    	limparCampos();
                    	$("#divMensagemSucesso").css("display", "block");
                    	$("#spanMsgSuccess").show().html("Produto cadastrado com sucesso!");
                        $("#proNome").focus();
                        $("#tabelaValores").jqGrid("clearGridData", true);
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
    }); 
    
    // Botão inserir valores ao Produto.
    $("#btnExibirInserirValor").button().click(function() {
    	$("#vvpValorProduto").css("border", "1px solid #cccccc");
        // Limpar campos.
        $("#vrpImpostoICMS").val("");
        $("#vrpImpostoIPI").val("");
        $("#vrpImpostoISS").val("");
        $("#vvpValorProduto").val("");
        // exibir modal.
    	$("#modalValoresProduto").modal({ // wire up the actual modal functionality and show the dialog
	   		 "backdrop" : "static",
	   		 "keyboard" : true,
	   		 "show" : true // ensure the modal is shown immediately
    	});
    });
    
    // Inserir Valores.
	$("#btnInserir").button().click(function() {
		if (isCamposValoresValidos()) {
			// ir no servidor...
			$.ajax({
				url: 'SistemaComercialGuedes/Produto/IncluiValorProduto',
				data: $('#formDialogValoresProduto').serialize(),
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
						$("#tabelaValores").addRowData(0, {
							vvpDataCadastro : data.valoresProdutoVO.vvpDataCadastro,
							vrpImpostoICMS  : data.valoresProdutoVO.vrpImpostoICMS,
							vrpImpostoIPI   : data.valoresProdutoVO.vrpImpostoIPI,
							vrpImpostoISS   : data.valoresProdutoVO.vrpImpostoISS,
							vvpValorProduto : data.valoresProdutoVO.vvpValorProduto
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
	
    // Tabela de Valores do Produto
	$("#tabelaValores").jqGrid({
		datatype: 'xmlstring',
	    datastr : '',
	    height: 100,
	    colNames:['Data Cadastro','ICMS(%)','IPI(%)','ISS(%)','Valor Venda', '', 'vvpCodigo'],
	    colModel:[
	        {name:'vvpDataCadastro',index:'vvpDataCadastro', width:140,sortable:true, key:true, classes: 'vvpDataCadastro', align:'center'},
	        {name:'vrpImpostoICMS',index:'vrpImpostoICMS', width:100,sortable:true, key:true, classes: 'vrpImpostoICMS'},
	        {name:'vrpImpostoIPI',index:'vrpImpostoIPI', width:100,sortable:false, key:true, classes: 'vrpImpostoIPI'},
	        {name:'vrpImpostoISS',index:'vrpImpostoISS', width:100,sortable:false, key:true, classes: 'vrpImpostoISS'},
	        {name:'vvpValorProduto',index:'vvpValorProduto', width:120,sortable:true, key:true, classes: 'vvpValorProduto', align:'right'},
	        {name:'deletar',index:'deletar', width:50, sortable:true, classes: 'deletar'},
	        {name:'vvpCodigo',index:'vvpCodigo', width:180,sortable:true, key:true, classes: 'vvpCodigo',hidden: true}
	    ],
	    multiselect: false,
	    rowNum:10,
	    rowList:[10,20,30],
	    sortname: 'id'
	});

	// remover o button de felhar tabela.
	$(".ui-jqgrid-titlebar-close").hide();

	// verifica se está em alteração do Produto.
	if ($("#proCodigo").val() != null && $("#proCodigo").val() > 0) {
	    $.ajax({
	    	url: 'SistemaComercialGuedes/Produto/BuscaListaValoresProduto',
	        data: $('#formProduto').serialize(),
	        type: 'POST',
	        cache: false,
	        dataType: "json",
	        beforeSend: function(){
	        	$("#loading").css("visibility", "visible");
	        },
	        success: function(data, status, request){
	        	$("#loading").css("visibility", "hidden");
	            if (status == "success" && data.mensagemUsuario == undefined && data.listaValoresProdutoVO.length > 0) {
	            	for (var i=0; data.listaValoresProdutoVO.length > i; i++) {
	            		$("#tabelaValores").addRowData(data.listaValoresProdutoVO[i].vvpCodigo, {
	            			vvpDataCadastro: data.listaValoresProdutoVO[i].vvpDataCadastro,
	                        vrpImpostoICMS : data.listaValoresProdutoVO[i].vrpImpostoICMS,
	                        vrpImpostoIPI : data.listaValoresProdutoVO[i].vrpImpostoIPI,
	                        vrpImpostoISS : data.listaValoresProdutoVO[i].vrpImpostoISS,
	                        vvpValorProduto: data.listaValoresProdutoVO[i].vvpValorProduto
	            		});                                                
	            	}
	            } else {
	                                                
	            }
	        },
	        error: function (request, error) {
				$("#loading").css("display", "none");
				$("#divMensagemErro").css("display", "block");
				$("#spanMsgError").show().html("Não foi possível carregar os valores.");	                                      
	        }
	    });		
	}
});

function isCamposValoresValidos() {
	
	// Valores dos campos.
	var vrpImpostoICMS  = $("#vrpImpostoICMS"), 
		vrpImpostoIPI   = $("#vrpImpostoIPI"), 
		vrpImpostoISS   = $("#vrpImpostoISS"), 
		vvpValorProduto = $("#vvpValorProduto");
	
	vvpValorProduto.css("border", "1px solid #cccccc");
	
	// Validar campo Valor de Venda 
	if (vvpValorProduto.val().trim() == "" || vvpValorProduto.val().trim() == "0.00") {
		
		vvpValorProduto.css("border", "1px solid #ff4500");
		return false;
	}
	
	return true;
}

function isCamposFormularioValidos() {
	
	var isValidos = false;
	var proNome = $("#proNome"), 
		proQuantidadeMinima = $("#proQuantidadeMinima"),
		proQuantidadeMaxima = $("#proQuantidadeMaxima");

	proNome.css("border", "1px solid #cccccc");
	proQuantidadeMinima.css("border", "1px solid #cccccc");
	proQuantidadeMaxima.css("border", "1px solid #cccccc");
	
	// Validar campo nome.
	if (proNome.val().trim() == "") {
		proNome.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	// Validar campo quantidade minima.
	if (proQuantidadeMinima.val().trim() == "") {
		proQuantidadeMinima.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	// Validar campo quantidade máxima.
	if (proQuantidadeMaxima.val().trim() == "") {
		proQuantidadeMaxima.css("border", "1px solid #ff4500");
		isValidos = true;
	}	
	
	if (isValidos) {
		$("#divMensagemErro").css("display", "block");
		$("#spanMsgError").show().html("O(s) campo(s) em vermelho(s) é obrigatório.");			
		return false;		
	}
	
	return true;	
}

function limparCampos() {
	$("#proNome").val("");
	$("#proCodigoBarras").val("");
	$("#fornecedor").val("0");
	$("#categoria").val("0");
	$("#proQuantidadeMinima").val("");
	$("#proQuantidadeMaxima").val("");
	$("#proObs").val("");
}