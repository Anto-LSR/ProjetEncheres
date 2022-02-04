<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<form class="venteForm__container">
					<div class="input__container">
						<label for="article">Article:</label> <input type="text"
							id="article" name="article">
					</div>
					<div class="input__container">
						<label for="categorie">Catégorie:</label> <select name="categorie"
							id="pet-select">
							<option value="">--Choisir une catégorie--</option>
							<c:forEach items="${categories}" var="cat">
								<option name="categorie">${cat.libelle }</option>
							</c:forEach>
						</select>
					</div>
					<div class="input__container">
						<label for="description">Description:</label> <input type="text"
							id="description" name="description">
					</div>
					<div class="input__container">
						<label for="photo">Photo de l'article</label> <input type="file"
							id="photo" name="photo">
					</div>
					<div class="input__container">
						<label for="prixInitial">Prix initial:</label> <input
							type="number" id="prixInitial" name="prixInitial">
					</div>
					<div class="input__container">
						<label for="debEnchere">Début de l'enchère:</label> <input
							type="date" id="debEnchere" name="debEnchere">
					</div>
					<div class="input__container">
						<label for="finEnchere">Fin de l'enchère:</label> <input
							type="date" id="finEnchere" name="finEnchere">
					</div>
					<div class="retraitDiv">
						<h3>Retrait :</h3>
						<label for="rue">Rue : </label> <input type="text" name="rue"
							id="rue"> <label for="codepostal">Code postal : </label>
						<input type="text" name="codepostal" id="codepostal"> <label
							for="ville">Ville </label> <input type="text" name="ville"
							id="ville">

					</div>
					<div class="buttons__container">
						<button type="submit" class="venteButtons">Créer</button>
						<a href="${pageContext.request.contextPath }/"><button
								type="button" class="venteButtons">Annuler</button></a>
					</div>
				</form>
			</div>
		</main>
	</div>
</body>
</html>

