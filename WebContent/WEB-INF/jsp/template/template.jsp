<!DOCTYPE html>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
	<link rel="shortcut icon" href="../static-guedes/img/faviconLogo.jpg" type="image/jpg" />
	<sx:head/> 
	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"> 
	
	<title><tiles:insertAttribute name="title"/></title>
	
	<!-- JavaScript -->
	<script src="../static-guedes/js/jquery/jquery-1.9.1.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.core.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.widget.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.mouse.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.button.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.draggable.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.position.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.resizable.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.dialog.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.effect.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.menu.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.datepicker.js"></script>
	<script src="../static-guedes/js/jquery/development-bundle/ui/jquery.ui.effect-clip.js"></script>
	<script src="../static-guedes/js/jquery/ui.panel.min.js"></script>
	<script src="../static-guedes/js/jquery/jquery.jclock.js"></script>
	<script src="../static-guedes/js/jquery/jquery.maskedinput.js"></script>
	<script src="../static-guedes/js/jquery/jquery.limit-1.2.source.js"></script>
	<script src="../static-guedes/js/jquery/jquery.alphanumeric.js"></script>
	<script src="../static-guedes/js/jquery/jquery.maskMoney.js"></script>	
	<script src="../static-guedes/js/util.js"></script>
	
	<!-- JavaScript - jquery.jqGrid-4.5.2 -->
	<script src="../static-guedes/js/jquery.jqGrid-4.5.2/js/i18n/grid.locale-pt-br.js"></script>
	<script src="../static-guedes/js/jquery.jqGrid-4.5.2/js/jquery.jqGrid.min.js"></script>
	<script src="../static-guedes/js/jquery.jqGrid-4.5.2/plugins/ui.multiselect.js" type="text/javascript"></script>
	<script src="../static-guedes/js/jquery.jqGrid-4.5.2/plugins/jquery.tablednd.js" type="text/javascript"></script>
	<script src="../static-guedes/js/jquery.jqGrid-4.5.2/plugins/jquery.contextmenu.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" media="screen" href="../static-guedes/js/jquery.jqGrid-4.5.2/plugins/ui.multiselect.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../static-guedes/js/jquery.jqGrid-4.5.2/css/ui.jqgrid.css">
	<script type="text/javascript">
		$.jgrid.no_legacy_api = true;
		$.jgrid.useJSON = true;
	</script>	
	
	<!-- CSS -->
	<link rel="stylesheet" href="../static-guedes/js/jquery/css/blitzer/jquery-ui-1.10.3.custom.css">
	<link rel="stylesheet" href="../static-guedes/js/jquery/development-bundle/demos/demos.css">
	<link rel="stylesheet" href="../static-guedes/css/ui.panel.css">
	
	



	
</head>
<body>
	<div align="center">
		<table id="tableTemplate" style="width: 900px;">
				<tr>
					<td colspan="2">
						<div id="tilesTopo"><tiles:insertAttribute name="topo"/></div>
					</td>
				</tr>
				<tr>
					<td style="width: 20%"></td>				
					<td>
						<div id="tilesMensagem"><tiles:insertAttribute name="mensagem"/></div>
					</td>
				</tr>				
				<tr>
					<td style="width: 20%">
						<div id="tilesMenu"><tiles:insertAttribute name="menu"/></div>
					</td>
					<td>
						<div id="tilesConteudo"><tiles:insertAttribute name="conteudo"/></div>
					</td>					
				</tr>
				<tr>
					<td colspan="2">
						<div id="tilesRodape"><tiles:insertAttribute name="rodape"/></div>
					</td>
				</tr>
		</table>
	</div>
</body>
</html>
