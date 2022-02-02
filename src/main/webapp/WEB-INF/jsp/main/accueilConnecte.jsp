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

				<div>
					<input type="radio" id="huey" name="Choice" value="Achats" checked>
					<label for="Achats">Achats</label>
				</div>



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
					<input type="checkbox" id="enchremport�s" name="enchremport�s">
					<label for="enchremport�s">mes ench�res remport�es</label>
				</div>


				<div>
					<input type="radio" id="Ventes" name="Choice" value="Ventes"
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
						non d�but�es</label>
				</div>

				<div>
					<input type="checkbox" id="ventestermin�es" name="ventestermin�es">
					<label for="ventestermin�es">ventes termin�es</label>
				</div>


				<form class="searchForm">
					<div class="filterDiv">
						<label for="categories">Cat�gories : </label> <select
							id="categories">
							<option>Cat�gorie 1</option>

						</select>
					</div>
					<input type="text" placeholder="Le nom de l'article contient...">
					<button type="submit" class="searchBtn">Rechercher</button>
				</form>


			</div>
		</main>

	</div>
</body>
</html>