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
				<form class="searchFormDC">
					<div class="filterDiv">
						<label for="categories">Catégories : </label> <select
							id="categories">
							<option name="categorie">toutes les catégories</option>
							<c:forEach items="${categories}" var="cat">
								<option name="categorie">${cat.libelle}</option>

							</c:forEach>

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