<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>

<c:set var="ctx">${pageContext.request.contextPath}</c:set>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<decorator:title>Map Location</decorator:title>
		<%@include file="/decorators/css.jsp"%>
		<%@include file="/decorators/javascript.jsp"%>
		<decorator:head></decorator:head>
	</head>
	<body>
		
		<%@include file="/decorators/header.jsp"%>
		<div>
			<decorator:body></decorator:body>
		</div>
	</body>
</html>