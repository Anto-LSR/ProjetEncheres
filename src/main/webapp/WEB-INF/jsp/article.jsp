<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<div class="visuel_article_container">
			<h1>${article.nomArticle}</h1>
			
			<ul>
			<li><span class="fielname">Meilleure offre :</span> </li>
			<li><span class="fielname">Mise à prix :</span> <p> ${article.prixInitial}</p></li>
			<li><span class="fielname">Fin de l'enchère :</span> <p>${article.dateFinEncheres}</p></li>
			<li><span class="fielname">Retrait :</span> <p>${article.retrait}</p> </li>
			<li><span class="fielname">Vendeur :</span><p>${article.utilisateurVendeur}</li>
			</ul>
			
			<div>
			<div class="updateBtn__container">
				<a href="${pageContext.request.contextPath }/"><button class="updateBtn">Annuler la vente</button></a>
				</div>
				<div class="updateBtn__container">
				<a href="${pageContext.request.contextPath }/"><button class="updateBtn">Back</button></a>
				</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>