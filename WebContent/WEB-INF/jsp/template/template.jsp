<!DOCTYPE html>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
	<link rel="shortcut icon" href="../resources/img/faviconLogo.jpg" type="image/jpg" />
	<sx:head/> 
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"> 
	
	<title><tiles:insertAttribute name="title"/></title>
	<!-- CSS -->
	<link href="../resources/bootstrap-3.0.3-dist/dist/css/bootstrap.css" rel="stylesheet" type="text/css"/>
	<link href="../resources/js/jquery/css/blitzer/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css"/>
	<link href="../resources/css/tablecloth.css" rel="stylesheet" type="text/css"/>
	<link href="../resources/css/prettify.css" rel="stylesheet" type="text/css"/> 
	<link href="../resources/css/multi-select.css" rel="stylesheet" type="text/css"/>
	<link href="../resources/js/jquery.jqGrid-4.5.4/css/ui.jqgrid.css" rel="stylesheet" type="text/css"/>	
	<link href="../resources/css/bootstrap-dialog.css" rel="stylesheet" type="text/css"/>
	
	<!-- JAVASCRIPT -->
	<script src="../resources/js/jquery/jquery-1.9.1.js"></script>
	<script src="../resources/js/Highcharts-3.0.7/js/highcharts.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.core.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.mouse.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.button.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.draggable.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.position.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.resizable.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.dialog.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.effect.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.menu.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.datepicker.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/minified/i18n/jquery.ui.datepicker-pt-BR.min.js"></script>
	<script src="../resources/js/jquery/development-bundle/ui/jquery.ui.effect-clip.js"></script>
	<script src="../resources/js/jquery.jqGrid-4.5.4/js/i18n/grid.locale-pt-br.js" type="text/javascript"></script>
	<script src="../resources/js/jquery.jqGrid-4.5.4/js/jquery.jqGrid.min.js" type="text/javascript"></script>	
	<script src="../resources/js/jquery/jquery.metadata.js"></script>
	<script src="../resources/js/jquery/jquery.tablesorter.min.js"></script>
	<script src="../resources/js/jquery/jquery.tablecloth.js"></script>
	<script src="../resources/js/jquery/jquery.jclock.js"></script>
	<script src="../resources/js/jquery/jquery.maskedinput.js"></script>
	<script src="../resources/js/jquery/jquery.limit-1.2.source.js"></script>
	<script src="../resources/js/jquery/jquery.alphanumeric.js"></script>
	<script src="../resources/js/jquery/jquery.maskMoney.js"></script>	
	<script src="../resources/js/jquery/jquery.multi-select.js"></script>
	<script src="../resources/js/jquery/jquery.dataTables.js"></script>
	<script src="../resources/js/run_prettify.js"></script>	
	<script src="../resources/js/bootstrap-dialog.js"></script>	
	<script src="../resources/js/util.js"></script>	
	<script src="../resources/bootstrap-3.0.3-dist/dist/js/bootstrap.min.js"></script>	
	<script src="../resources/js/bootbox.js"></script>	
</head>
<body>
	<div id="tilesMenu"><tiles:insertAttribute name="menu"/></div>
	<div id="tilesMensagem" style="padding-top: 25px;"><tiles:insertAttribute name="mensagem"/></div>
	<div id="tilesConteudo"><tiles:insertAttribute name="conteudo"/></div>	
	<div id="tilesRodape"><tiles:insertAttribute name="rodape"/></div>
</body>
</html>
