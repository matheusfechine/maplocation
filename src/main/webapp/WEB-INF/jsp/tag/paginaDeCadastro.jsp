<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<body id="location">
	<div id="spin"></div>
	<div class="container-fluid">
		<h1>Página de Cadastro de Tags</h1>
		<section>
		<c:out value="${sucesso}"></c:out>
			<form action="<c:url value='/tag/cadastra'/>" method="post">
				<div>Nome: <input type="text" size="10" id="latitude" name="tag.name"></div>
				<div><input type="submit" value="Salvar"></div>
			</form>
		</section>
	</div>

<%-- 	<script type='text/javascript' src='${ctx}/javascripts/utils/spin.js'></script> --%>
	<script type='text/javascript' src='${ctx}/javascripts/tag/paginaCadastro.js'></script>
</body>
</html>