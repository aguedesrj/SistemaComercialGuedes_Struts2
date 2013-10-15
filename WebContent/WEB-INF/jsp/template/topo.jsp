
	<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<%@taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
	
	<jsp:useBean id="data" class="java.util.Date"/>
	
	<span style="padding-right: 5px;">
		
		<fmt:formatDate value="${data}" dateStyle="full"/>
		
	</span>

	<div class="jclock"></div>
	