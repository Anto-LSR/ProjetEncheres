<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./css/style.css">
<title>Inscription</title>
</head>
<body>
	<%@include file="headers/headervide.jsp"%>


	<div class="container">
		<main>

			<div class="form__container">
				<h3>Mon Profil</h3>
				<form method="post"
					action="<%=request.getContextPath()%>/editerprofil"
					autocomplete="off">
					<div class="sidesContainer">
						<div class="leftSide">
							<input autocomplete="false" name="hidden" type="text"
								style="display: none;">
							<c:if test="${pseudoTaken != null }">
								<p class="error">${pseudoTaken}</p>
							</c:if>

							<c:if test="${pseudoNull != null }">
								<p class="error">${pseudoNull}</p>
							</c:if>
							<div class="input__container">
								<label for="pseudo">Pseudo:</label> <input type=text id="pseudo"
									name="pseudo" value="${utilisateur.pseudo}">
							</div>



							<c:if test="${nomNull != null }">
								<p class="error">${nomNull}</p>
							</c:if>
							<div class="input__container">
								<label for="nom">Nom:</label> <input type="text" id="nom"
									name="nom" value="${utilisateur.nom}">
							</div>
							<c:if test="${prenomNull != null }">
								<p class="error">${prenomNull}</p>
							</c:if>
							<div class="input__container">
								<label for="prenom">Prénom:</label> <input type="text"
									id="prenom" name="prenom" value="${utilisateur.prenom}">
							</div>

							<c:if test="${mailTaken != null }">
								<p class="error">${mailTaken}</p>
							</c:if>
							<c:if test="${mailNull != null }">
								<p class="error">${mailNull}</p>
							</c:if>
							<div class="input__container">
								<label for="mail">Email:</label> <input type="email" id="mail"
									name="mail" value="${utilisateur.email}">
							</div>


							<div class="input__container">
								<label for="telephone">Telephone:</label> <input type="text"
									id="telephone" value="${utilisateur.getTelephone()}"
									autocomplete="false" name="telephone" maxlength="10">
							</div>
						</div>
						<div class="leftSide">
							<c:if test="${rueNull != null }">
								<p class="error">${rueNull}</p>
							</c:if>
							<div class="input__container">
								<label for="rue">Rue:</label> <input type="text" id="rue"
									name="rue" value="${utilisateur.rue}">
							</div>

							<c:if test="${codePostalNull != null }">
								<p class="error">${codePostalNull}</p>
							</c:if>
							<div class="input__container">
								<label for="codepostal">Code postal:</label> <input type="text"
									id="codepostal" name="codepostal"
									value="${utilisateur.codePostal}" autocomplete="false" min="0"
									maxlength="5">
							</div>
							<c:if test="${villeNull != null }">
								<p class="error">${codePostalNull}</p>
							</c:if>
							<div class="input__container">
								<label for="ville">Ville:</label> <input type="text" id="ville"
									name="ville" value="${utilisateur.ville}">
							</div>

							<div class="input__container">
								<label for="motdepasse">Mot de passe:</label> <input
									type="password" id="motdepasse" name="motdepasse">
							</div>
							<div class="input__container">
								<label for="confirmation">Confirmation:</label> <input
									type="password" id="confirmation" name="confirmation">
							</div>
							<c:if test="${passError != null}">
								<p class="error">${passError}</p>
							</c:if>
						</div>
					</div>

					<div class="profilButtons__container">
						<button id="confirmBtn" type="submit">Enregistrer</button>
						<a href="${pageContext.request.contextPath }/supprimer"><button
								type="button">Supprimer mon compte</button></a> <a
							href="${pageContext.request.contextPath }/"><button
								type="button">Retour</button></a>
					</div>

				</form>
			</div>

		</main>
	</div>
	<script src="${pageContext.request.contextPath }/js/modifprofil.js"></script>
</body>
</html>