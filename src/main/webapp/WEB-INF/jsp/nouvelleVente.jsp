<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nouvelle Vente</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/style.css">

</head>

<%@include file="headers/headervide.jsp"%>

<body>
	<div class="container">
		<main>
			<h3>Nouvelle vente</h3>
			<div class="input__container">
				<label for="article">Article:</label> <input type="text"
					id="article" name="article">
			</div>

			<div class="input__container">
				<label for="categorie">Catégorie:</label> <select name="categorie"
					id="pet-select">
					<option value="">--Choisir une catégorie--</option>

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
				<label for="prixInitial">Prix initial:</label> <input type="number"
					id="prixInitial" name="prixInitial">
			</div>

			<div class="input__container">
				<label for="debEnchere">Début de l'enchère:</label> <input
					type="date" id="debEnchere" name="debEnchere">
			</div>

			<div class="input__container">
				<label for="finEnchere">Fin de l'enchère:</label> <input type="date"
					id="finEnchere" name="finEnchere">
			</div>
			<div>
				<p>Retrait:</p>
			</div>

			<div class="buttons__container">
				<button type="submit">Créer</button>

				<button type="button">Annuler</button>
			</div>
		</main>
	</div>
</body>
</html>