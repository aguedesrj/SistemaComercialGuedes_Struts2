<%@ taglib prefix="s"  uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- JavaScript -->
<script src="../static-guedes/js/menu.js"></script>

<!-- CSS -->
<link rel="stylesheet" href="../static-guedes/css/menu.css">

<div>
	<ul id="menu">
		<li><a href="../Usuario/Home">Home</a></li>
		<li>
			<a href="#">Produto</a>
			<ul>
				<li><a href="../Produto/InicioCadastro">Cadastrar</a></li>
				<li><a href="../Produto/InicioPesquisa">Pesquisar</a></li>
			</ul>
		</li>
		<li><a href="#">Vendas</a></li>
		<li><a href="../Usuario/logout">Sair</a></li>
	</ul>
</div>