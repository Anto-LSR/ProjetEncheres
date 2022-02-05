<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
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
          <div class="search__div">
            <div class="filter__div">
              <h3>Filtres :</h3>
              <div class="radiomenu">
                <input
                  type="radio"
                  id="radioAchats"
                  name="choice"
                  value="Achats"
                  checked
                />
                <label for="Achats">Achats</label>
              </div>

              <div class="checkboxmenu">
                <div>
                  <input
                    type="checkbox"
                    id="enchouvertes"
                    name="enchouvertes"
                    checked
                  />
                  <label for="enchouvertes">Enchères ouvertes</label>
                </div>
                <div>
                  <input type="checkbox" id="enchencours" name="enchencours" />
                  <label for="enchencours">Mes enchères en cours</label>
                </div>
                <div>
                  <input
                    type="checkbox"
                    id="enchremportees"
                    name="enchremportees"
                  />
                  <label for="enchremportees">Mes enchères remportées</label>
                </div>
              </div>

              <div class="radiomenu">
                <input
                  type="radio"
                  id="radioVentes"
                  name="choice"
                  value="Ventes"
                  checked
                />
                <label for="Ventes">Ventes</label>
              </div>

              <div class="checkboxmenu">
                <div>
                  <input
                    type="checkbox"
                    id="ventesencours"
                    name="ventesencours"
                    checked
                  />
                  <label for="ventesencours">Mes ventes en cours</label>
                </div>
                <div>
                  <input
                    type="checkbox"
                    id="ventesnondebutees"
                    name="ventesnondebutees"
                  />
                  <label for="ventesnondebutees">Ventes non débutées</label>
                </div>
                <div>
                  <input
                    type="checkbox"
                    id="ventesTerminees"
                    name="ventesTerminees"
                  />
                  <label for="ventesTerminees">Ventes terminées</label>
                </div>
              </div>
            </div>

            <form class="searchForm">
              <div class="filterDiv">
                <label for="categories">Catégories : </label>
                <select id="categories">
                  <c:forEach items="${categories}" var="cat">
                    <option name="categorie">${cat.libelle }</option>
                  </c:forEach>
                </select>
              </div>
              <input
                type="text"
                placeholder="Le nom de l'article contient..."
              />
              <button type="submit" class="searchBtn">Rechercher</button>
            </form>
          </div>
          <div class="articles__container">
            <div class="article">
              <div class="img__container">
                <img
                  class="imageArticle"
                  src="${pageContext.request.contextPath }/assets/img/box.png"
                />
              </div>

              <div class="articleInfo">
                <ul>
                  <li class="titreArticle">Nom de l'article</li>

                  <li id="priceLi">Prix:<span id="priceSpan">45</span><img src="${pageContext.request.contextPath }/assets/img/coin.png"></li>
                  <li>Classement:</li>
                  <li>Fin de l'enchère:</li>
                  <li>Retrait:</li>
                  <li>Vendeur:</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
    <script src="${pageContext.request.contextPath }/js/script.js"></script>
  </body>
</html>
