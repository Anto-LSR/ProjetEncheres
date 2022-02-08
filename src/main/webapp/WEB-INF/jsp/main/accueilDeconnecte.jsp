<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<div class="form__container dcHome">

				<!-- <h3>Filtres :</h3> -->
				<form class="searchFormDC" method="post" action="<%=request.getContextPath()%>/accueil">
					<div class="filterDiv">
						<label for="categories">Catégories : </label> <select
							id="categories" name="categories">
							<option name="categories">Toutes</option>
							<c:forEach items="${categories}" var="cat">
								<option name="categorie">${cat.libelle}</option>

							</c:forEach>

						</select>
					</div>
					<input type="text" placeholder="Le nom de l'article contient..." name="recherche">
					<button type="submit" class="searchBtn">Rechercher</button>
				</form>


			</div>
			<div class="articles__container">
			 <c:forEach items="${liste}" var="art">
            
            
            <div class="article">
              <div class="img__container">
                <img
                  class="imageArticle"
                  src="${pageContext.request.contextPath }/assets/img/box.png"
                />
              </div>

              <div class="articleInfo">
                <ul>
                  <li class="titreArticle">${art.nomArticle}</li>

                  <li id="priceLi">Prix:<span id="priceSpan">${art.prixVente}</span><img src="${pageContext.request.contextPath }/assets/img/coin.png"></li>
                  <li>Classement:</li>
                  <li>Fin de l'enchère: ${art.formattedDateFinEnchere()}</li>
                  <li>Retrait:</li>
                  <li>Vendeur: ${art.utilisateurVendeur.nom }</li>
                </ul>
              </div>
            </div>
            </c:forEach>
			</div>
		</main>

	</div>
</body>
</html>