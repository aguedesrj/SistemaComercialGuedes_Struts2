
$(function(){
    $('#panelCenter_1').panel({
        collapsible:false
    });
});

$(document).ready(function() {
	
	$("#usuLogin").focus();
	
	$('#efetuar-login').click(function() {
		// valida campos.
		if (isCamposValidos()) {
			
			$("#loading").css("visibility", "visible");
			$.ajax({
				url: 'SistemaComercialGuedes/Usuario/Login',
				data: $('#formLogin').serialize(),
				type: 'POST',
				cache: false,
				dataType: "json",
				beforeSend: function(){
					$("#divMensagemErro").css("display", "none");
				},
				success: function(data, status, request){ 
					if (status == "success" && data.mensagemUsuario == null) {
						$("#formLogin").attr("action", "Home");
						$("#formLogin").submit();
					} else {
						$("#loading").css("visibility", "hidden");
						$("#divMensagemErro").css("display", "block");
						$("#spanMsgError").show().html(data.mensagemUsuario);					
					}
				},
				error: function (request, error) {
					$("#loading").css("visibility", "hidden");
					$("#divMensagemErro").css("display", "block");
					$("#spanMsgError").show().html("Sistema indisponível no momento.");
				}
			});
		}
	});
});

/*
 * Validar os campos.
 */
function isCamposValidos() {
	
	var usuLogin = $("#usuLogin"), 
		usuSenha = $("#usuSenha");	
	
	usuLogin.removeClass("ui-state-error");
	usuSenha.removeClass("ui-state-error");
	
	// Validar campo login.
	if (usuLogin.val().trim() == "") {
		
		usuLogin.addClass("ui-state-error");
		$("#divMensagemErro").css("display", "block");
		$("#spanMsgError").show().html("O campo login deve ser informado.");		
		return false;
	}
	
	// Validar campo senha.
	if (usuSenha.val().trim() == "") {
		
		usuSenha.addClass("ui-state-error");
		$("#divMensagemErro").css("display", "block");
		$("#spanMsgError").show().html("O campo senha deve ser informado.");		
		return false;
	}	
	
	return true;
}

