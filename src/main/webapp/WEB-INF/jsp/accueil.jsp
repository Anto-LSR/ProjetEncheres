 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
<c:if test="${connected == true }">
<%@include file="headers/headerConnecte.jsp"%>
<%@include file="main/accueilConnecte.jsp"%>
</c:if>

<c:if test="${connected == false }">
<%@include file="headers/headerDeconnecte.jsp"%>
	<%@include file="main/accueilDeconnecte.jsp"%>
</c:if>
	
	
</body>
</html>