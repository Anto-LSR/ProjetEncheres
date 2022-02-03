<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
    <div class="container">
        <main>
            <div class="form__container">
                <h3>Filtres :</h3>
                <div class="radiomenu">
                    <input type="radio" id="radioAchats" name="choice" value="Achats" checked>
                    <label for="Achats">Achats</label>
                </div>
                <div>
                    <input type="checkbox" id="enchouvertes" name="enchouvertes"
                        checked> <label for="enchouvertes">enchères
                        ouvertes</label>
                </div>
                <div>
                    <input type="checkbox" id="enchencours" name="enchencours">
                    <label for="enchencours">mes enchères en cours</label>
                </div>
                <div>
                    <input type="checkbox" id="enchremportees" name="enchremportees">
                    <label for="enchremportees">mes enchères remportées</label>
                </div>
                <div class="radiomenu">
                    <input type="radio" id="radioVentes" name="choice" value="Ventes"
                        checked> <label for="Ventes">Ventes</label>
                </div>
                <div>
                    <input type="checkbox" id="ventesencours" name="ventesencours"
                        checked> <label for="ventesencours">mes ventes en
                        cours</label>
                </div>
                <div>
                    <input type="checkbox" id="ventesnondebutees"
                        name="ventesnondebutees"> <label for="ventesnondebutees">ventes
                        non débutées</label>
                </div>
                <div>
                    <input type="checkbox" id="ventesTerminees" name="ventesTerminees">
                    <label for="ventesTerminees">ventes termin�es</label>
                </div>
                <form class="searchForm">
                    <div class="filterDiv">
                        <label for="categories">Catégories : </label> <select
                            id="categories">
                            <option>Catégorie 1</option>
                        </select>
                    </div>
                    <input type="text" placeholder="Le nom de l'article contient...">
                    <button type="submit" class="searchBtn">Rechercher</button>
                </form>
            </div>
        </main>
    </div>
    <script src="${pageContext.request.contextPath }/js/script.js"></script>
</body>
</html>