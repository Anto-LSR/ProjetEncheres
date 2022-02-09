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
	<c:if test="${connected == true }">
		<%@include file="headers/headerConnecte.jsp"%>
	</c:if>

	<c:if test="${connected == false }">
		<%@include file="headers/headerDeconnecte.jsp"%>
	</c:if>

	<div class="container">
		<main>
			<div class="search__div">

				<div class="visuel_article_container">

					<h1>${article.nomArticle}</h1>



					<ul>
						<li class="fielname">Description : <span>
								${article.description}</span></li>
						<li class="fielname">Categorie : <span>
								${article.categorie.libelle}</span></li>
						<li class="fielname">Meilleure offre : <span>
								${article.prixVente}</span> points
						</li>
						<li class="fielname">Mise à prix : <span>
								${article.prixInitial}</span> points
						</li>
						<li class="fielname">Fin de l'enchère : <span>
								${Fin}</span></li>

						<c:if test="${connected == true}">
							<li class="fielname">Retrait : <span>
									${article.retrait.rue}</span></li>
							<li><span>${article.retrait.codePostal}</span></li>
							<li><span>${article.retrait.ville}</span></li>
							<li class="fielname">Vendeur : <span>
									${article.utilisateurVendeur.nom}</span></li>
						</c:if>

						<c:if test="${utilisateur.credit > article.prixVente && connected == true && article.utilisateurVendeur.noUtilisateur != utilisateur.noUtilisateur}">
							<form class="encherir"
								action="${pageContext.request.contextPath }/article?noArticle=${article.noArticle}"
								method="post">
								<li class="fielname">Ma proposition : <input type="number"
									id="proposition" name="proposition" min="${article.prixVente}"
									max="${utilisateur.credit}" value="${article.prixVente}"></li>
								<button type="submit">Enchérir</button>

							</form>
						</c:if>
						<c:if test="${utilisateur.credit < article.prixVente && connected == true &&  article.utilisateurVendeur.noUtilisateur != utilisateur.noUtilisateur}">
							<p class="error">Vous n'avez pas assez de crédit pour
								enchérir</p>
						</c:if>
						<c:if test="${ article.utilisateurVendeur.noUtilisateur == utilisateur.noUtilisateur && article.dateDebutEncheres > today}">
						<button type="submit">Annuler la vente</button>
						</c:if>

					</ul>

					<div>
						<div class="updateBtn__container">
							<a href="${pageContext.request.contextPath }/"><button
									class="updateBtn">Retour</button></a>
						</div>
					</div>
				</div>
			</div>
		</main>
	</div>
</body>
</html>