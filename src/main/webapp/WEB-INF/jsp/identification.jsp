<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./css/style.css">
<title>Inscription</title>
</head>
<body>
	<%@include file="headers/headervide.jsp"%>


	<div class="container">
		<main>
			<div class="form__container login">

				<form method="post"
					action="<%=request.getContextPath()%>/identification">

					<c:if test="${loginError != null }">

						<p class="loginError">${loginError}</p>
					</c:if>
					<c:if test="${success != null }">
						<p class="success">${success}</p>
					</c:if>
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



				</form>
				<div class="loginButtons__container">
					<a href="<%=request.getContextPath()%>/inscription">
						<button>Créer un compte</button>
					</a>
				</div>
			</div>

		</main>
	</div>
</body>
</html>