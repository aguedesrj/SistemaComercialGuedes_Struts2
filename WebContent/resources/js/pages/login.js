$(document).ready(function() {
	$("#usuLogin").focus();
	$('#btnEntrar').click(function() {
		// valida campos.
		if (isCamposValidos()) {
			$.ajax({
				url: 'SistemaComercialGuedes/Usuario/Login',
				data: $('#formLogin').serialize(),
				type: 'POST',
				cache: false,
				dataType: "json",
				beforeSend: function(){
					$("#loading").css("display", "block");
					$("#divMensagemErro").css("display", "none");
				},
				success: function(data, status, request){ 
					if (status == "success" && data.mensagemUsuario == null) {
						$("#formLogin").attr("action", "Home");
						$("#formLogin").submit();
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
	});
});

/*
 * Validar os campos.
 */
function isCamposValidos() {
	
	var isValidos = false;
	var usuLogin = $("#usuLogin"), 
		usuSenha = $("#usuSenha");	

	usuLogin.css("border", "1px solid #cccccc");
	usuSenha.css("border", "1px solid #cccccc");
	
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
	
	if (isValidos) {
		$("#divMensagemErro").css("display", "block");
		$("#spanMsgError").show().html("O(s) campo(s) em vermelho(s) é obrigatório.");			
		return false;		
	}
	
	return true;
}

