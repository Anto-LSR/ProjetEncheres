<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<title>Inscription</title>
</head>
<body>
	<%@include file="headers/headervide.jsp"%>


	<div class="container">
		<main>
			<div class="form__container">

				<form method="post"
					action="<%=request.getContextPath()%>/inscription">

					<div class="input__container">
						<label for="identifiant">Identifiant:</label> <input type=text
							id="indentifiant" name="identifiant">
					</div>

					<div class="input__container">

						<label for="motdepasse">Mot de passe:</label> <input
							type="password" id="motdepasse" name="motdepasse">
					</div>
					<div class="connection__container">
						<div class="buttons__container">
							<button type="submit">Connexion</button>
						</div>
						<div class="loginoption__container">
							<div>
								<input type="checkbox" id="sesouvenir" name="sesouvenir">
								<label for="sesouvenir">Se souvenir de moi</label>
							</div>

							<div>
								<a href="#">Mot de passe oublié</a>
							</div>
						</div>
					</div>

					<div class="loginButtons__container">
						<button  type="submit">Créer un compte</button>
					</div>


				</form>
			</div>

		</main>
	</div>
</body>
</html>