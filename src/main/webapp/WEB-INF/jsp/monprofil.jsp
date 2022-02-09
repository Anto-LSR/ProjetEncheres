<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 
<title></title>
</head>
<body>
	<%@include file="headers/headervide.jsp"%>
	<div class="container">
		<main>
			<div class="profile__container">

				<h1>Mon profil</h1>
				<ul>
					<li><span class="fieldname">Pseudo :</span><p> ${utilisateur.pseudo}</p></li>
					<br>
					<li><span class="fieldname">Nom :</span><p>${utilisateur.nom}</p></li>
					<li><span class="fieldname">Prenom :</span><p>${utilisateur.prenom}</p></li>
					<li><span class="fieldname">Email :</span><p>${utilisateur.email}</p></li>
					<li><span class="fieldname">Téléphone :</span><p>${utilisateur.telephone}</p></li>
					<li><span class="fieldname">Rue :</span><p>${utilisateur.rue}</p></li>
					<li><span class="fieldname">Code postal :</span><p>${utilisateur.codePostal}</p></li>
					<li><span class="fieldname">Ville :</span><p>${utilisateur.ville}</p></li>
					
					
					

				</ul>
				<div class="updateBtn__container">
				<a href="${pageContext.request.contextPath }/editerprofil"><button class="updateBtn">Modifier</button></a>
				</div>
			</div>

		</main>


	</div>
</body>
</html>