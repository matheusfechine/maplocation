<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<body id="location">
	<div id="spin"></div>
	<div class="container-fluid">
		<h1>Página de Listagem de Locations</h1>
		<c:out value="${sucesso}"></c:out>
		<section>
		<table>
					<thead>
						<tr>
							<th>Id</th>
							<th>Nome</th>
							<th>Latitude</th>
							<th>Longitude</th>
							<th>Criado Em</th>
							<th>Editar</th>
							<th>Excluir</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${locations}" var="location">
						<tr>
							<td>${location.id}</td>
							<td>${location.name}</td>
							<td>${location.latitude}</td>
							<td>${location.longitude}</td>
							<td>${location.created}</td>
							<td><a href='<c:url value='/location/paginaAtualizacao/${location.id}'/>'>Editar</a></td>
							<td><a href='<c:url value='/location/exclui/${location.id}'/>'>Excluir</a></td>
							
						</tr>
					</c:forEach>
					</tbody>
				</table>
		</section>
	</div>
	<script type='text/javascript' src='${ctx}/javascripts/location/paginaListagem.js'></script>
</body>
</html>