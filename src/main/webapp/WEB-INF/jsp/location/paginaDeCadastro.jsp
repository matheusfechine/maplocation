<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<body id="location">
	<div id="spin"></div>
	<div class="container-fluid">
		<h1>Página de Cadastro de Locations</h1>
		<section>
		<c:out value="${sucesso}"></c:out>
			<form action="<c:url value='/location/cadastra'/>" method="post">
				<div>Nome: <input type="text" size="10" id="latitude" name="location.name"/></div>
				<div>Latitude: <input type="text" size="10" id="latitude" name="location.latitude"/></div>
				<div>Longitude: <input type="text" size="10" id="longitude" name="location.longitude"/></div>
				<div>Tag: 
					<select id="selectTags">
						<c:forEach var="tag" items="${tags}">
							<option value="${tag.id}">${tag.name}</option>
						</c:forEach>
					</select>
				</div>
				<div><input id="btnAdicionar" type="button" value="Adicionar"></div>
				<div>
					<table id="tabelaDeTags">
						<thead>
							<tr>
								<th>Id</th>
								<th>Name</th>
								<th>Acao</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div><input type="submit" value="Salvar"></div>
			</form>
		</section>
	</div>
	
	<script type='text/javascript' src='${ctx}/javascripts/location/paginaCadastro.js'></script>

</body>
</html>
