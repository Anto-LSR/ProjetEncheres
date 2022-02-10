<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profil Utilisateur</title>
</head>
<body>
	<%@include file="headers/headervide.jsp"%>
	<main>
		<div class="container">
			<div class="profile__container">


				<ul>
					<li><span class="fieldname">Pseudo :</span>
						<p>${utilisateur.pseudo}</p></li>
					<br>
					<li><span class="fieldname">Nom :</span>
						<p>${utilisateur.nom}</p></li>
					<li><span class="fieldname">Prenom :</span>
						<p>${utilisateur.prenom}</p></li>
					<li><span class="fieldname">Email :</span>
						<p>${utilisateur.email}</p></li>
					<li><span class="fieldname">Téléphone :</span>
						<p>${utilisateur.telephone}</p></li>




				</ul>
				<div class="updateBtn__container">
					<a href="${pageContext.request.contextPath }/"><button
							class="updateBtn">Retour</button></a>
				</div>
			</div>
		</div>
	</main>

</body>
</html>