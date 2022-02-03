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
				<h3>Mon Profil</h3>
				<form method="post"
					action="<%=request.getContextPath()%>/editerprofil"
					autocomplete="off">
					<input autocomplete="false" name="hidden" type="text"
						style="display: none;">
					<c:if test="${pseudoTaken != null }">
						<p class="error">${pseudoTaken}</p>
					</c:if>
					<div class="input__container">
						<label for="pseudo">Pseudo:</label> <input type=text id="pseudo"
							name="pseudo" value="${utilisateur.pseudo}">
					</div>
					<div class="input__container">
						<label for="nom">Nom:</label> <input type="text" id="nom"
							name="nom" value="${utilisateur.nom}">
					</div>
					<div class="input__container">
						<label for="prenom">Prénom:</label> <input type="text" id="prenom"
							name="prenom" value="${utilisateur.prenom}">
					</div>
					<c:if test="${mailTaken != null }">
						<p class="error">${mailTaken}</p>
					</c:if>
					<div class="input__container">
						<label for="mail">Email:</label> <input type="email" id="mail"
							name="mail" value="${utilisateur.email}">
					</div>
					<div class="input__container">
						<label for="telephone">Telephone:</label> <input type="text"
							id="telephone" value="${utilisateur.getTelephone()}"
							autocomplete="false" name="telephone">
					</div>
					<div class="input__container">
						<label for="rue">Rue:</label> <input type="text" id="rue"
							name="rue" value="${utilisateur.rue}">
					</div>
					<div class="input__container">
						<label for="codepostal">Code postal:</label> <input type="text"
							id="codepostal" name="codepostal"
							value="${utilisateur.codePostal}" autocomplete="false">
					</div>
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
					<div class="profilButtons__container">
						<button type="submit">Enregistrer</button>
						<a href="${pageContext.request.contextPath }/supprimer"><button
								type="button">Supprimer mon compte</button></a> <a
							href="${pageContext.request.contextPath }/"><button
								type="button">Retour</button></a>
					</div>
				</form>
			</div>

		</main>
	</div>
</body>
</html>