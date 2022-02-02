<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<%@include file="headers/headervide.jsp"%>
	<div class="container">
		<main>
			<div class="form__container">

				<h3>Filtres :</h3>
				<form>
					<label for="categories">Caétgories : </label> <select
						id="categories">
						<option>Catégorie 1</option>

					</select> <input type="text" placeholder="Le nom de l'article contient...">
					<button type="submit">Rechercher</button>
				</form>


			</div>
		</main>

	</div>
</body>
</html>