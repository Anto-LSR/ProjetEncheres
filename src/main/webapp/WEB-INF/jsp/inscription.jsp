<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css" >
<title>Inscription</title>
</head>
<body>
	<%@include file="headers/headervide.jsp"%>


	<div class="container">
		<main>
			<div class="form__container">
			<h3>Créer un compte</h3>
			<form method="post" action="<%=request.getContextPath() %>/inscription">
				
				<div class="input__container">
				<label for="pseudo">Pseudo:</label>
				<input type=text id="pseudo" name="pseudo">
				</div>
				<div class="input__container">
				<label for="nom">Nom:</label>
				<input type="text" id="nom" name="nom">
				</div>
				<div class="input__container">
				<label for="prenom">Prénom:</label>
				<input type="text" id="prenom" name="prenom">
				</div>
				<div class="input__container">
				<label for="mail">Email:</label>
				<input type="email" id="mail" name="mail">
				</div>
				<div class="input__container">
				<label for="nom">Telephone:</label>
				<input type="text" id="telephone" name="telephone">
				</div>
				<div class="input__container">
				<label for="rue">Rue:</label>
				<input type="text" id="rue" name="rue">
					</div>
				<div class="input__container">
				<label for="nom">Code postal:</label>
				<input type="text" id="codepostal" name="codepostal">
				</div>
				<div class="input__container">
				<label for="nom">Ville:</label>
				<input type="text" id="ville" name="ville">
				</div>
				<div class="input__container">
				<label for="motdepasse">Mot de passe:</label>
				<input type="password" id="motdepasse" name="motdepasse">
				</div>
				<div class="input__container">
				<label for="confirmation">Confirmation:</label>
				<input type="password" id="confirmation" name="confirmation">
				</div>
				<div class="buttons__container">
				<button type="submit">Créer</button>
				<button>Annuler</button>
				</div>
			</form>
			</div>

		</main>
	</div>
</body>
</html>