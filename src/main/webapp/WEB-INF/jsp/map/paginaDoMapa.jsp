<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<body id="location">
	<div id="spin"></div>
	<div class="container-fluid">
		<h1>Mapa</h1>
		<section>
		<div><select id="selectLocation">
				<c:forEach var="location" items="${locations}">
					<option value="">Selecione...</option>
					<option value="${location.id}">${location.name}</option>
				</c:forEach>
			</select>
		</div>
		 <div id="googleMap" style="width:500px;height:380px;"></div>
		</section>
	</div>
	<script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyCtdXeyfrMDi3GQysGjK-zxVDNQr7V8sh4&sensor=false"></script>
	<script type='text/javascript' src='${ctx}/javascripts/map/paginaDoMapa.js'></script>
</body>
</html>