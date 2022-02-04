<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css">

<title></title>
</head>
<body>
	<header>

		<a href="${pageContext.request.contextPath }/">
			<h1>Encheres.org</h1>
		</a>
			<div class="wallet">
				<img src="${pageContext.request.contextPath }/assets/img/coin.png">${utilisateur.credit }
			</div>
		<ul>
			<li><a href="${pageContext.request.contextPath }/nouvellevente">Vendre
					un article</a></li>
			<li><a href="${pageContext.request.contextPath }/monprofil">Mon
					profil</a></li>

			<li><a href="${pageContext.request.contextPath }/deconnection">Deconnexion</a></li>
		</ul>
	</header>
</body>
</html>