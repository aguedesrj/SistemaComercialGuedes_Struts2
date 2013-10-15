$(document).ready(function() {
	
    $('#panelCenter_1').panel({
        collapsible:false
    });
    
    $('#panelCenter_2').panel({
        collapsible:false
    });	
	
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
	
	// Modal Valores do Produto
	$("#dialog-form").dialog({
		autoOpen: false,
		height: 230,
		width: 300,
		modal: true,
		buttons: {
			"Inserir Valores": function() {
				if (isCamposValoresValidos()) {
					// ir no servidor...
					$.ajax({
						url: 'SistemaComercialGuedes/Produto/IncluiValorProduto',
						data: $('#formDialogValoresProduto').serialize(),
						type: 'POST',
						cache: false,
						dataType: "json",
						beforeSend: function(){
							$("#loadingValoresProduto").css("visibility", "visible");
						},
						success: function(data, status, request){ 
							$("#loadingValoresProduto").css("visibility", "invisible");
							if (status == "success" && data.mensagemUsuario == null) {
									$("#tabelaValores").addRowData(0, {
									vvpDataCadastro   : data.valoresProdutoVO.vvpDataCadastro,
									vrpImpostoICMS : data.valoresProdutoVO.vrpImpostoICMS,
									vrpImpostoIPI  : data.valoresProdutoVO.vrpImpostoIPI,
									vrpImpostoISS  : data.valoresProdutoVO.vrpImpostoISS,
									vvpValorProduto  : data.valoresProdutoVO.vvpValorProduto
								});
			 
								$("#dialog-form").dialog("close");
							} else {
//								document.getElementById('lblMsgErro').innerHTML = data[0].mensagem;
//								document.getElementById('msgErro').style.display = 'block';
							}
						},
						error: function (request, error) {
							$("#loadingValoresProduto").css("visibility", "invisible");
						}	
					});				
				}
			},
			"Cancelar": function() {
				$("#dialog-form").dialog( "close" );
			}
		},
		close: function() {
			//allFields.val("").removeClass( "ui-state-error" );
		}
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
	$("#btnInserirValor").button().click(function() {
		// Limpar campos.
		$("#vrpImpostoICMS").val("");
		$("#vrpImpostoIPI").val("");
		$("#vrpImpostoISS").val("");
		$("#vvpValorProduto").val("");
		
		// Abrir Modal
		$( "#dialog-form" ).dialog( "open" );
	});	
	
	// Tabela de Valores do Produto
    $("#tabelaValores").jqGrid({
 		datatype: 'xmlstring',
 		datastr : '',
        height: 100,
        colNames:['Data Cadastro','ICMS(%)','IPI(%)','ISS(%)','Valor de Venda', 'vvpCodigo'],
        colModel:[
            {name:'vvpDataCadastro',index:'vvpDataCadastro', width:120,sortable:true, key:true, classes: 'vvpDataCadastro', align:'center'},
            {name:'vrpImpostoICMS',index:'vrpImpostoICMS', width:110,sortable:true, key:true, classes: 'vrpImpostoICMS'},
            {name:'vrpImpostoIPI',index:'vrpImpostoIPI', width:110,sortable:false, key:true, classes: 'vrpImpostoIPI'},
            {name:'vrpImpostoISS',index:'vrpImpostoISS', width:110,sortable:false, key:true, classes: 'vrpImpostoISS'},
            {name:'vvpValorProduto',index:'vvpValorProduto', width:110,sortable:true, key:true, classes: 'vvpValorProduto', align:'right'},
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
						vrpImpostoIPI  : data.listaValoresProdutoVO[i].vrpImpostoIPI,
						vrpImpostoISS  : data.listaValoresProdutoVO[i].vrpImpostoISS,
						vvpValorProduto: data.listaValoresProdutoVO[i].vvpValorProduto
					});						
				}
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
    
});

function isCamposValoresValidos() {
	
	// Valores dos campos.
	var vrpImpostoICMS = $("#vrpImpostoICMS"), 
		vrpImpostoIPI  = $("#vrpImpostoIPI"), 
		vrpImpostoISS  = $("#vrpImpostoISS"), 
		vvpValorProduto  = $("#vvpValorProduto");
	
	//vrpImpostoICMS.removeClass("ui-state-error");
	//vrpImpostoIPI.removeClass("ui-state-error");
	//vrpImpostoISS.removeClass("ui-state-error");
	vvpValorProduto.removeClass("ui-state-error");
	
	// Validar campo Valor de Venda 
	if (vvpValorProduto.val().trim() == "" || vvpValorProduto.val().trim() == "0.00") {
		
		vvpValorProduto.addClass("ui-state-error");
		return false;
	}
	
	return true;
}

/*
 * Validar os campos.
 */
function isCamposFormularioValidos() {
	
	var isCamposValidos = true;
	var proNome = $("#proNome"), 
		proQuantidadeMinima = $("#proQuantidadeMinima"),
		proQuantidadeMaxima = $("#proQuantidadeMaxima");	
	
	proNome.removeClass("ui-state-error");
	proQuantidadeMinima.removeClass("ui-state-error");
	proQuantidadeMaxima.removeClass("ui-state-error");
	
	// Validar campo proNome.
	if (proNome.val().trim() == "") {
		
		proNome.addClass("ui-state-error");
		isCamposValidos = false;
	}
	
	// Validar campo proQuantidadeMinima.
	if (proQuantidadeMinima.val().trim() == "") {
		
		proQuantidadeMinima.addClass("ui-state-error");
		isCamposValidos = false;
	}	
	
	// Validar campo proQuantidadeMaxima.
	if (proQuantidadeMaxima.val().trim() == "") {
		
		proQuantidadeMaxima.addClass("ui-state-error");
		isCamposValidos = false;
	}	
	
	if (!isCamposValidos) {
		
		$("#divMensagemErro").css("display", "block");
		$("#spanMsgError").show().html("Verificar os campos obrigatórios.");		
		return isCamposValidos;		
	}
	
	return isCamposValidos;
}

function limparCampos() {
	$("#proNome").val("");
	$("#proCodigoBarras").val("");
	$("#forCodigo").val("0");
	$("#catCodigo").val("0");
	$("#proQuantidadeMinima").val("");
	$("#proQuantidadeMaxima").val("");
	$("#proObs").val("");
}

