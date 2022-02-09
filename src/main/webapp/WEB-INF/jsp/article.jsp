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

					<h1>${article.nomArticle}</h1>



					<ul>
					
						<li class="fielname">Description : <span> ${article.description}</span></li>
						<li class="fielname">Description : <span> ${article.categorie.libelle}</span></li>
						<li class="fielname">Meilleure offre : <span> ${article.prixVente}</span> points</li>
						<li class="fielname">Mise à prix : <span> ${article.prixInitial}</span> points</li>
						<li class="fielname">Fin de l'enchère : <span> ${article.dateFinEncheres}</span></li>
						<li class="fielname">Retrait : <span> ${article.retrait.rue}</span></li>
						<li><span>${article.retrait.codePostal}</span></li>
						<li><span>${article.retrait.ville}</span></li>
						<li class="fielname">Vendeur : <span> ${article.utilisateurVendeur.nom}</span></li>
						
						<c:if test = "${utilisateur.credit > article.prixVente}">
						<form class="encherir" action ="${pageContext.request.contextPath }/article"  method="post">
						<li class="fielname">Ma proposition : <span><input type="number" id="proposition" name="proposition"
       						min="${article.prixVente}" max="${utilisateur.credit}" value="${article.prixVente}" > </span></li>
      					</form>
       					</c:if>
       					<c:if test = "${utilisateur.credit < article.prixVente}">
       					<p class="error"> Vous n'avez pas assez de crédit pour enchérir </p> 
       					</c:if>
       					
       					    					
       					
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