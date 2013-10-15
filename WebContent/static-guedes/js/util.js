
function runEffectMsgSuccess() {
	$( "#divMensagemSucesso" ).show( "clip", {}, 500, callbackMsgSuccess );
};

function callbackMsgSuccess() {
	setTimeout(function() {
		$( "#divMensagemSucesso:visible" ).removeAttr( "style" ).fadeOut();
	}, 3000 );
};

function runEffectMsgError() {
	$( "#divMensagemErro" ).show( "clip", {}, 500, callbackMsgError );
};

function callbackMsgError() {
	setTimeout(function() {
		$( "#divMensagemErro:visible" ).removeAttr( "style" ).fadeOut();
	}, 3000 );
};

