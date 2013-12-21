$(document).ready(function() {
	var itensSelecionados = [];
	
	// exibir carregando.
	$("#loading").css("display", "block");
	
	// foco no campo nome.
	$("#pesNome").focus();
	
	// selecionar todos.
	$('#select-all').click(function(){
		$('#selectPerfis').multiSelect('select_all');
		return false;
	});	
	
	$('#deselect-all').click(function(){
		$('#selectPerfis').multiSelect('deselect_all');
		return false;
	});
	
	// se estiver alterando, buscar os perfis.
	if ($('#usuCodigo').val() > 0) {
		$.ajax({
			url: 'SistemaComercialGuedes/Usuario/CarregaListaPerfisAlteracao?usuario.usuCodigo='+$('#usuCodigo').val(),
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
					$('#selectPerfis').multiSelect('deselect_all');
					$("#selectPerfis").html(options);
					$('#selectPerfis').multiSelect();					
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
	} else {
		// obter a lista de perfis.
		callListaPerfis();
	}
	
    // Salvar.
	$("#btnSalvar").button().click(function() {
		if (validarForm()) {
			// ir no servidor...
			$.ajax({
				url: 'SistemaComercialGuedes/Usuario/SalvaUsuario',
				data: $('#formUsuario').serialize(),
				type: 'POST',
				cache: false,
				dataType: "json",
				beforeSend: function(){
					$("#loading").css("display", "block");
					$("#divMensagemErro").css("display", "none");
				},
				success: function(data, status, request){
					if (status == "success" && data.mensagemUsuario == null) {
						// prepara form.
						preparaFormNovaCadastro();
                    	$("#divMensagemSucesso").css("display", "block");
                    	$("#spanMsgSuccess").show().html("Usuário cadastrado com sucesso!");						
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
    });	
});

function callListaPerfis() {
	$.ajax({
		url: 'SistemaComercialGuedes/Usuario/ListaPerfis',
		type: 'POST',
		cache: false,
		dataType: "json",
		beforeSend: function(){
		},
		success: function(data, status, request){ 
			if (status == "success" && data.mensagemUsuario == null) {
				// preencher lista.
				var options = '';
				for (var i = 0; data.listaSelecionados.length > i; i++) {
					options += "<option id='"+data.listaSelecionados[i].genCodigo+"' value='"+data.listaSelecionados[i].genCodigo+"' style='font-size: 12px;'>"+data.listaSelecionados[i].genDescricao+"</option>";
				}  
				$("#selectPerfis").html(options);
				$('#selectPerfis').multiSelect();
				$('#selectPerfis').multiSelect('deselect_all');
			} else {
				BootstrapDialog.alert(data.mensagemUsuario);
			}
		},
		complete : function () {
			$("#loading").css("display", "none");
		},
		error: function (request, error) {
			BootstrapDialog.alert("Sistema indisponível no momento.");
		}
	});	
}

function preparaFormNovaCadastro() {
	var pesNome  = $("#pesNome"),
        usuLogin = $("#usuLogin"),
        usuSenha = $("#usuSenha"),
        usuConfirmaSenha = $("#usuConfirmaSenha"),
        itensSelecionados = $("#itensSelecionados");
	
	// muda a cor da borda.
	pesNome.css("border", "1px solid #cccccc");
	usuLogin.css("border", "1px solid #cccccc");
	usuSenha.css("border", "1px solid #cccccc");
	usuConfirmaSenha.css("border", "1px solid #cccccc");
	
	// limpa campos
	pesNome.val("");
	usuLogin.val("");
	usuSenha.val("");
	usuConfirmaSenha.val("");
	itensSelecionados.val("");
	
	// lista os Perfis.
	callListaPerfis();
	
	// foco no campo nome.
	pesNome.focus();	
}

function validarForm() {
	
	$("#divMensagemErro").css("display", "none");
	$("#divMensagemSucesso").css("display", "none");
	
	itensSelecionados = [];
	var isValidos = false;
	var pesNome  = $("#pesNome"),
	    usuLogin = $("#usuLogin"),
	    usuSenha = $("#usuSenha"),
	    usuConfirmaSenha = $("#usuConfirmaSenha");	
	
	pesNome.css("border", "1px solid #cccccc");
	usuLogin.css("border", "1px solid #cccccc");
	usuSenha.css("border", "1px solid #cccccc");
	usuConfirmaSenha.css("border", "1px solid #cccccc");
	
	// Validar campo nome.
	if (pesNome.val().trim() == "") {
		pesNome.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	// Validar campo login.
	if (usuLogin.val().trim() == "") {
		usuLogin.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	// Validar campo senha.
	if (usuSenha.val().trim() == "") {
		usuSenha.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	// Validar campo confirma senha.
	if (usuConfirmaSenha.val().trim() == "") {
		usuConfirmaSenha.css("border", "1px solid #ff4500");
		isValidos = true;
	}	
	
	// obter os perfis selecionadas.
	$('#selectPerfis :selected' ).each(function(i, selected) {
		itensSelecionados[i] = $(selected).val();
	});
	
	// valida perfis.
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