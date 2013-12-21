$(document).ready(function() {
	
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
				$("#loading").css("display", "block");
				$("#divMensagemErro").css("display", "none");
				$("#divMensagemSucesso").css("display", "none");
			},
			success: function(data, status, request){ 
				$("#loading").css("display", "none");
				if (status == "success" && data.mensagemUsuario == null) {
					// atualiza lista na tabela.
					atualizarTabelaProduto(data.listaProdutoView);
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
	});	    
});

function atualizarTabelaProduto(listaProdutoView) {
	
	$("#tabelaProdutos").jqGrid("clearGridData", true);
	
	// Tabela dos Produtos
    $("#tabelaProdutos").jqGrid({
    	datatype: 'local',
        colNames:['Data Cadastro','Produto','Codigo Barras','Preço Venda','','','proCodigo'],
        colModel:[
            {name:'proDataCadastro',index:'proDataCadastro', width:150,sortable:true, classes: 'proDataCadastro', align:'center'},
            {name:'proNome',index:'proNome', width:300,sortable:true, classes: 'proNome'},
            {name:'proCodigoBarras',index:'proCodigoBarras', width:100,sortable:false, classes: 'proCodigoBarras'},
            {name:'vvpValorProduto',index:'vvpValorProduto', width:100,sortable:true, classes: 'vvpValorProduto', align:'right'},
            {name:'detalhar',index:'detalhar', width:50,sortable:true, classes: 'detalhar'},
            {name:'alterar',index:'alterar', width:50,sortable:true, classes: 'alterar'},            
            {name:'proCodigo',index:'proCodigo', sortable:true, key:true, classes: 'proCodigo',hidden: true}
        ],
        gridComplete: function(){
            var ids = $("#tabelaProdutos").jqGrid('getDataIDs');
            for(var i=0;i < ids.length;i++){
                var valor = ids[i];
                btnDetalhar = "<div align='center' style='margin-top: 3px; margin-bottom: 5px;'><img width='19px' height='16px' title='Detalhar produto' alt='Detalhar produto' style='cursor: pointer' src='../resources/img/detalhe.png' onclick='javascript:detalhar("+valor+");'></div>";
                btnAlterar  = "<div align='center' style='margin-top: 3px; margin-bottom: 5px;'><img width='16px' height='16px' title='Alterar produto' alt='Alterar produto' style='cursor: pointer' src='../resources/img/edit.png' onclick='javascript:alterar("+valor+");'></div>";
                btnDeletar  = "<div align='center' style='margin-top: 3px; margin-bottom: 5px;'><img width='16px' height='16px' title='Deletar produto' alt='Deletar produto' style='cursor: pointer' src='../resources/img/delete.png' onclick='javascript:deletar("+valor+");'></div>";                
                $("#tabelaProdutos").jqGrid('setRowData',ids[i],{alterar:btnAlterar,detalhar:btnDetalhar,deletar:btnDeletar});
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

function detalhar(proCodigo) {
	
}

function alterar(proCodigo) {
	$("#proCodigo").val(proCodigo);
	$("#formProduto").attr("action", "InicioAlteracao");
	$("#formProduto").submit();	
}
