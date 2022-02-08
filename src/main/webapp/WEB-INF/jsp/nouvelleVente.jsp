<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	errorPage="error.jsp"
	isErrorPage = "false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Nouvelle Vente</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css">
</head><%@include file="headers/headervide.jsp"%>
<body>
	<div class="container">
		<main>
			<div class="venteContainer">
				<h3>Nouvelle vente</h3>
				<form class="venteForm__container" method="post"
					action="${pageContext.request.contextPath }/nouvellevente">
					<c:if test="${nomNull != null }">
						<p class="error">${nomNull }</p>
					</c:if>
					<div class="input__container">
						<label for="article">Article:</label> <input type="text"
							id="article" name="article">
					</div>
					<c:if test="${cateNull != null }">
					<p class="error">${cateNull }</p>
					</c:if>
					<div class="input__container">
						<label for="categorie">Catégorie:</label> <select name="categorie"
							id="categorie">
							<option value="">--Choisir une catégorie--</option>
							<c:forEach items="${categories}" var="cat">
								<option name="categorie">${cat.libelle }</option>
							</c:forEach>
						</select>
					</div>
					<c:if test="${descNull != null }">
						<p class="error">${descNull }</p>
					</c:if>
					<div class="input__container">
						<label for="description">Description:</label> <textarea 
							id="description" name="description"></textarea>
					</div>
					<div class="input__container">
						 <label for="photo">Photo de l'article</label><!-- <input type="file"
							id="photo" name="photo"> -->
						
							<button type="button" id="uploadBtn">UPLOADER</button>
							
					</div>
					<c:if test="${prixNull != null }">
						<p class="error">${prixNull }</p>
					</c:if>
					<div class="input__container">
						<label for="prixInitial">Prix initial:</label> <input
							type="number" id="prixInitial" name="prixInitial">
					</div>
					<c:if test="${dateDebutNull != null }">
						<p class="error">${dateDebutNull }</p>
					</c:if>
					<div class="input__container">
						<label for="debEnchere">Début de l'enchère:</label> <input
							type="date" id="debEnchere" name="debEnchere" min="${today}"
							value="${today}">
					</div>
					<c:if test="${debutAfterFin != null}">
						<p class="error">${debutAfterFin}</p>
					</c:if>

					<c:if test="${debutBeforeToday != null}">
						<p class="error">${debutBeforeToday}</p>
					</c:if>

					<c:if test="${finBeforeToday != null}">
						<p class="error">${finBeforeToday}</p>
					</c:if>

					<c:if test="${dateFinNull != null }">
						<p class="error">${dateFinNull }</p>
					</c:if>
					<div class="input__container">
						<label for="finEnchere">Fin de l'enchère:</label> <input
							type="date" id="finEnchere" name="finEnchere" min="${tomorrow}"
							value="${tomorrow}">
					</div>

						<h3>Retrait :</h3>
					<div class="retraitDiv">

						<div class="input__container">
							<label for="rue">Rue : </label> <input type="text" name="rue"
								id="rue" value="${utilisateur.rue }">
						</div>
						<div class="input__container">
							<label for="codepostal">Code postal : </label> <input type="text"
								name="codepostal" id="codepostal"
								value="${utilisateur.codePostal }">
						</div>
						<div class="input__container">
							<label for="ville">Ville </label> <input type="text" name="ville"
								id="ville" value="${utilisateur.ville }">
						</div>

					</div>
					<div class="buttons__container">
						<button type="submit" class="venteButtons "id="confirmBtn">Créer</button>
						<a href="${pageContext.request.contextPath }/"><button
								type="button" class="venteButtons" >Annuler</button></a>
					</div>
				</form>
			</div>
		</main>
	</div>
	<script src="${pageContext.request.contextPath }/js/nouvelleVente.js"></script>
</body>
</html>

