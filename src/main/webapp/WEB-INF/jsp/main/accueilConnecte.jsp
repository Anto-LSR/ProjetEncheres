<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
	<div class="container">
		<main>
			<div class="form__container">
				<h3>Filtres :</h3>
				<div class="radiomenu">
					<input type="radio" id="radioAchats" name="choice" value="Achats"
						checked> <label for="Achats">Achats</label>
				</div>

				<div class="checkboxmenu">
					<div>
						<input type="checkbox" id="enchouvertes" name="enchouvertes"
							checked> <label for="enchouvertes">ench�res
							ouvertes</label>
					</div>
					<div>
						<input type="checkbox" id="enchencours" name="enchencours">
						<label for="enchencours">mes ench�res en cours</label>
					</div>
					<div>
						<input type="checkbox" id="enchremportees" name="enchremportees">
						<label for="enchremportees">mes ench�res remport�es</label>
					</div>

				</div>



				<div class="radiomenu">
					<input type="radio" id="radioVentes" name="choice" value="Ventes"
						checked> <label for="Ventes">Ventes</label>
				</div>

				<div class="checkboxmenu">
					<div>
						<input type="checkbox" id="ventesencours" name="ventesencours"
							checked> <label for="ventesencours">mes ventes en
							cours</label>
					</div>
					<div>
						<input type="checkbox" id="ventesnondebutees"
							name="ventesnondebutees"> <label for="ventesnondebutees">ventes
							non d�but�es</label>
					</div>
					<div>
						<input type="checkbox" id="ventesTerminees" name="ventesTerminees">
						<label for="ventesTerminees">ventes termin�es</label>
					</div>

				</div>



				<form class="searchForm">
					<div class="filterDiv">
						<label for="categories">Cat�ories : </label> <select
							id="categories">
							<c:forEach items="${categories}" var="cat">
								<option name="categorie">${cat.libelle }</option>

							</c:forEach>
						</select>
					</div>
					<input type="text" placeholder="Le nom de l'article contient...">
					<button type="submit" class="searchBtn">Rechercher</button>
				</form>
				<div class="article">

					<img class="imageArticle"
						src="${pageContext.request.contextPath }/assets/img/pcPortable.jpg">


					<div class=articleInfo>

						<ul>
							<li class=titreArticle>Nom de l'article</li>

							<li>Prix:</li>
							<li>Classement:</li>
							<li>Fin de l'ench�re:</li>
							<li>Retrait:</li>
							<li>Vendeur:</li>
						</ul>


					</div>

				</div>
			</div>
		</main>
	</div>
	<script src="${pageContext.request.contextPath }/js/script.js"></script>
</body>
</html>