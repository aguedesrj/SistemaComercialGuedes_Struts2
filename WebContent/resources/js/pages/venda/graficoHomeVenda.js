$(document).ready(function() {
	
	$("#venDataInicio").datepicker();
	$("#venDataFim").datepicker();
	
	// buscar as vendas.
	$.ajax({
		url: 'SistemaComercialGuedes/Venda/ExecutaPesquisaGraficoPorPeriodo',
		data: $('#formVenda').serialize(),
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
				carregaGrafico(data);
				$("#loading").css("display", "none");
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
});

function carregaGrafico(data) {
	var valueCategories = [];
	var valueSeries = [];
	
	for (var i = 0; data.listaVendas.length > i; i++) {
		valueCategories.push(data.listaVendas[i].venDataFim);
		valueSeries.push(data.listaVendas[i].itvQuantidade);
	}
	
	$('#container').highcharts({
        title: {
            text: 'Vendas efetuadas',
            x: -20 //center
        },
        subtitle: {
            text: 'Período: ' + $("#venDataInicio").val() + ' até ' + $("#venDataFim").val(),
            x: -20
        },
        xAxis: {
            categories: valueCategories
        },
        yAxis: {
            title: {
                text: 'Quantidade'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: ''
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'Produto',
            data: valueSeries
        }]
    });	
}