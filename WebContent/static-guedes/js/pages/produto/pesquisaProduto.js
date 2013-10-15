$(document).ready(function() {
	
    $('#panelCenter_1').panel({
        collapsible:false
    });
    
    $("#proNome").focus();
    
	// Botão pesquisa Produto.
	$("#btnPesquisar").button().click(function() {
		$.ajax({
			url: 'SistemaComercialGuedes/Produto/Pesquisa',
			data: $('#formProduto').serialize(),
			type: 'POST',
			cache: false,
			dataType: "json",
			beforeSend: function(){
				$("#loading").css("visibility", "visible");
			},
			success: function(data, status, request){ 
				if (status == "success" && data.mensagemUsuario == null) {
					// atualiza lista na tabela.
					atualizarTabelaProduto(data.listaProdutoView);
				} else {
					$("#divTabelaProdutos").css("display", "none");
					$("#spanMsgError").show().html(data.mensagemUsuario);
					runEffectMsgError();					
				}
			},
			complete : function () {
				$("#loading").css("visibility", "hidden");
			},
			error: function (request, error) {
				$("#spanMsgError").show().html("Sistema indisponível no momento.");
				runEffectMsgError();
			}
		});			
	});	    
});

function atualizarTabelaProduto(listaProdutoView) {
	
	$("#divTabelaProdutos").css("display", "block");
	$("#tabelaProdutos").jqGrid("clearGridData", true);
	
	// Tabela dos Produtos
    $("#tabelaProdutos").jqGrid({
    	datatype: 'local',
    	datastr : '',
        colNames:['Data Cadastro','Produto','Codigo Barras','Preço de Venda','Detalhes','Alterar','proCodigo'],
        colModel:[
            {name:'proDataCadastro',index:'proDataCadastro', width:120,sortable:true, classes: 'proDataCadastro', align:'center'},
            {name:'proNome',index:'proNome', width:300,sortable:true, classes: 'proNome'},
            {name:'proCodigoBarras',index:'proCodigoBarras', width:100,sortable:false, classes: 'proCodigoBarras'},
            {name:'vvpValorProduto',index:'vvpValorProduto', width:100,sortable:true, classes: 'vvpValorProduto', align:'right'},
            {name:'detalhar',index:'detalhar', width:60,sortable:true, classes: 'detalhar'},
            {name:'alterar',index:'alterar', width:60,sortable:true, classes: 'alterar'},            
            {name:'proCodigo',index:'proCodigo', sortable:true, key:true, classes: 'proCodigo',hidden: true}
        ],
        gridComplete: function(){
            var ids = $("#tabelaProdutos").jqGrid('getDataIDs');
            for(var i=0;i < ids.length;i++){
                var valor = ids[i];
                btnDetalhar = "<div align='center' style='margin-top: 3px;'><img width='19px' height='16px' alt='Detalhar produto' src='../static-guedes/img/ic_ferramenta.gif' onclick='javascript:detalharProduto("+valor+");'></div>";
                btnAlterar  = "<div align='center' style='margin-top: 3px;'><img width='16px' height='16px' alt='Alterar produto' src='../static-guedes/img/ic_sbox_editar.gif' onclick='javascript:alterarProduto("+valor+");'></div>";
                $("#tabelaProdutos").jqGrid('setRowData',ids[i],{alterar:btnAlterar,detalhar:btnDetalhar});
            }   
        },        
        multiselect: false,
        rowNum:10,
        rowList:[10,20,30],
        onSelectRow: function (id) 
    	{
    		//alert(id); 		
    	},        
        sortname: 'id'
    });
    
    // remover o button de felhar tabela.
    $(".ui-jqgrid-titlebar-close").hide();
    
    $("#tabelaProdutos").jqGrid('setGridParam',{datatype: 'local',data:listaProdutoView}).trigger("reloadGrid");    
}

function detalharProduto(proCodigo) {
	
}

function alterarProduto(proCodigo) {
	$("#proCodigo").val(proCodigo);
	$("#formProduto").attr("action", "InicioAlteracao");
	$("#formProduto").submit();	
}
