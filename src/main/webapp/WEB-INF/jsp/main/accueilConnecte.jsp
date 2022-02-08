<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta charset="UTF-8" />
<title></title>
</head>
<body>
	<div class="container">
		<main>
			<div class="form__container">




				<form class="search__div" method="post"
					action="<%=request.getContextPath()%>/accueil">
					<div class="filter__div">
						<h3>Filtres :</h3>
						<div class="radiomenu">
							<input type="radio" id="radioAchats" name="choice" value="Achats"
								checked /> <label for="Achats">Achats</label>
						</div>

						<div class="checkboxmenu">
							<div>
								<input type="checkbox" id="enchouvertes" name="enchouvertes"
									checked /> <label for="enchouvertes">Enchères ouvertes</label>
							</div>
							<div>
								<input type="checkbox" id="enchencours" name="enchencours" /> <label
									for="enchencours">Mes enchères en cours</label>
							</div>
							<div>
								<input type="checkbox" id="enchremportees" name="enchremportees" />
								<label for="enchremportees">Mes enchères remportées</label>
							</div>
						</div>

						<div class="radiomenu">
							<input type="radio" id="radioVentes" name="choice" value="Ventes"
								checked /> <label for="Ventes">Ventes</label>
						</div>

						<div class="checkboxmenu">
							<div>
								<input type="checkbox" id="ventesencours" name="ventesencours"
									checked /> <label for="ventesencours">Mes ventes en
									cours</label>
							</div>
							<div>
								<input type="checkbox" id="ventesnondebutees"
									name="ventesnondebutees" /> <label for="ventesnondebutees">Ventes
									non débutées</label>
							</div>
							<div>
								<input type="checkbox" id="ventesTerminees"
									name="ventesTerminees" /> <label for="ventesTerminees">Ventes
									terminées</label>
							</div>
						</div>
					</div>

					<div class="searchForm">
						<div class="filterDiv">
							<label for="categories">Catégories : </label> <select
								id="categories" name="categories">
								<option name="categorie">Toutes</option>
								<c:forEach items="${categories}" var="cat">
									<option name="categorie">${cat.libelle }</option>
								</c:forEach>
							</select>
						</div>
						<input type="text" placeholder="Le nom de l'article contient..."
							name="recherche" />
						<button type="submit" class="searchBtn">Rechercher</button>
					</div>
				</form>





			</div>
			<div class="articles__container">
				<c:forEach items="${liste}" var="art">


					<div class="article">
						<div class="img__container">
							<img class="imageArticle"
								src="${pageContext.request.contextPath }/assets/img/box.png" />
						</div>

						<div class="articleInfo">
							<ul>
								<a href="${pageContext.request.contextPath }/article?noArticle=${art.noArticle}"> <li class="titreArticle">${art.nomArticle}</li></a>

								<li id="priceLi">Prix:<span id="priceSpan">${art.prixInitial}</span><img
									src="${pageContext.request.contextPath }/assets/img/coin.png"></li>
								<li>Classement:</li>
								<li>Début : ${art.formattedDateDebutEnchere()}</li>
								<li>Fin : ${art.formattedDateFinEnchere()}</li>
								<li>Retrait:</li>
								<li>Vendeur: ${art.utilisateurVendeur.nom }</li>
							</ul>
						</div>
					</div>
				</c:forEach>
			</div>
		</main>
	</div>
	<script src="${pageContext.request.contextPath }/js/script.js"></script>
	<c:if test="${ventesEnCours != null }">
		<script>
			ventesEnCours.checked = true;
		</script>
	</c:if>

	<c:if test="${ventesNonDebutees != null }">
		<script>
			ventesNonDebutees.checked = true;
		</script>
	</c:if>

	<c:if test="${ventesTerminees != null }">
		<script>
			ventesTerminees.checked = true;
		</script>
	</c:if>

	<c:if test="${encheresOuvertes != null }">
		<script>
			enchOuverte.checked = true;
		</script>
	</c:if>

	<c:if test="${encheresEnCours != null }">
		<script>
		enchEnCours.checked = true;
		</script>
	</c:if>

	<c:if test="${encheresRemportees != null }">
		<script>
			enchRemportees.checked = true;
		</script>
	</c:if>
	
	
	<script>
	let radioA = document.getElementById('radioAchats');
	let radioV = document.getElementById('radioVentes');
	if('${choice}' == 'Ventes'){
		radioV.checked = true

	} else if ('${choice}' == 'Achats'){
		radioA.checked = true

	}
	</script>
	
	
	
</body>
</html>
