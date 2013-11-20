$(document).ready(function() {
	
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
    
    // Botão gravar Produto.
    $("#btnGravar").button().click(function() {
            
            // Validar campos.
            if (isCamposFormularioValidos()) {                
                    $.ajax({
                            url: 'SistemaComercialGuedes/Produto/Salva',
                            data: $('#formProduto').serialize(),
                            type: 'POST',
                            cache: false,
                            dataType: "json",
                            beforeSend: function(){
                                    $("#loading").css("visibility", "visible");
                            },
                            success: function(data, status, request){
                                    $("#loading").css("visibility", "hidden");
                                    if (status == "success" && data.mensagemUsuario == null) {
                                            limparCampos();
                                            
                                            $("#spanMsgSuccess").show().html("Produto cadastrado com sucesso!");
                                            runEffectMsgSuccess();
                                            
                                            $("#proNome").focus();
                                            $("#tabelaValores").jqGrid("clearGridData", true);
                                            $("#proCodigo").val("");
                                    } else {
                                            $("#spanMsgError").show().html(data.mensagemUsuario);
                                            runEffectMsgError();                                                
                                    }
                            },
                            error: function (request, error) {
                                    $("#loading").css("visibility", "hidden");
                                    $("#spanMsgError").show().html("Sistema indisponível no momento.");
                                    runEffectMsgError();                                        
                            }
                    });                        
            }
    }); 
    
    // Botão inserir valores ao Produto.
    $("#btnExibirInserirValor").button().click(function() {
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
							vrpImpostoICMS : data.valoresProdutoVO.vrpImpostoICMS,
							vrpImpostoIPI : data.valoresProdutoVO.vrpImpostoIPI,
							vrpImpostoISS : data.valoresProdutoVO.vrpImpostoISS,
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
	
    
    
//    // Tabela de Valores do Produto
//$("#tabelaValores").jqGrid({
//             datatype: 'xmlstring',
//             datastr : '',
//    height: 100,
//    colNames:['Data Cadastro','ICMS(%)','IPI(%)','ISS(%)','Valor de Venda', 'vvpCodigo'],
//    colModel:[
//        {name:'vvpDataCadastro',index:'vvpDataCadastro', width:120,sortable:true, key:true, classes: 'vvpDataCadastro', align:'center'},
//        {name:'vrpImpostoICMS',index:'vrpImpostoICMS', width:110,sortable:true, key:true, classes: 'vrpImpostoICMS'},
//        {name:'vrpImpostoIPI',index:'vrpImpostoIPI', width:110,sortable:false, key:true, classes: 'vrpImpostoIPI'},
//        {name:'vrpImpostoISS',index:'vrpImpostoISS', width:110,sortable:false, key:true, classes: 'vrpImpostoISS'},
//        {name:'vvpValorProduto',index:'vvpValorProduto', width:110,sortable:true, key:true, classes: 'vvpValorProduto', align:'right'},
//        {name:'vvpCodigo',index:'vvpCodigo', width:180,sortable:true, key:true, classes: 'vvpCodigo',hidden: true}
//    ],
//    multiselect: false,
//    rowNum:10,
//    rowList:[10,20,30],
//    sortname: 'id'
//});
//
//// remover o button de felhar tabela.
//$(".ui-jqgrid-titlebar-close").hide();
//
//// verifica se está em alteração do Produto.
//    $.ajax({
//            url: 'SistemaComercialGuedes/Produto/BuscaListaValoresProduto',
//            data: $('#formProduto').serialize(),
//            type: 'POST',
//            cache: false,
//            dataType: "json",
//            beforeSend: function(){
//                    $("#loading").css("visibility", "visible");
//            },
//            success: function(data, status, request){
//                    $("#loading").css("visibility", "hidden");
//                    if (status == "success" && data.mensagemUsuario == undefined && data.listaValoresProdutoVO.length > 0) {
//                            for (var i=0; data.listaValoresProdutoVO.length > i; i++) {
//                                    $("#tabelaValores").addRowData(data.listaValoresProdutoVO[i].vvpCodigo, {
//                                            vvpDataCadastro: data.listaValoresProdutoVO[i].vvpDataCadastro,
//                                            vrpImpostoICMS : data.listaValoresProdutoVO[i].vrpImpostoICMS,
//                                            vrpImpostoIPI : data.listaValoresProdutoVO[i].vrpImpostoIPI,
//                                            vrpImpostoISS : data.listaValoresProdutoVO[i].vrpImpostoISS,
//                                            vvpValorProduto: data.listaValoresProdutoVO[i].vvpValorProduto
//                                    });                                                
//                            }
//                    } else {
//                            $("#spanMsgError").show().html(data.mensagemUsuario);
//                            runEffectMsgError();                                                
//                    }
//            },
//            error: function (request, error) {
//                    $("#loading").css("visibility", "hidden");
//                    $("#spanMsgError").show().html("Sistema indisponível no momento.");
//                    runEffectMsgError();                                        
//            }
//    });            
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
		$("#divMensagemErro").css("display", "block");
		$("#spanMsgError").show().html("O(s) campo(s) em vermelho(s) é obrigatório.");	
		return false;
	}
	
	return true;
}