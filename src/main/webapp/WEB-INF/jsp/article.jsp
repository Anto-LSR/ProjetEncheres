<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Article</title>
</head>
<body>
	<%@include file="headers/headervide.jsp"%>
	<div class="container">
		<main>
		<div class="search__div">

				<div class="visuel_article_container">
					<h1>${article.NomArticle }</h1>

					<ul>
						<li><span class="fielname">Meilleure offre : </span></li>
						<li><span class="fielname">Mise à prix :</span></li>
						<li><span class="fielname">Fin de l'enchère :</span></li>
						<li><span class="fielname">Retrait :</span></li>
						<li><span class="fielname">Vendeur :</span></li>
					</ul>

					<div>
						<div class="updateBtn__container">
							<a href="${pageContext.request.contextPath }/"><button
									class="updateBtn">Annuler la vente</button></a>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>