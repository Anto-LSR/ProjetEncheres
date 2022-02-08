//******************************************************* */
//VERIFICATION DES INPUTS SUR NOUVELLE VENTE 

let article = document.getElementById("article");
let description = document.getElementById("description")
let prixInitial = document.getElementById("prixInitial")

let rueField = document.getElementById("rue");
let codePostalField = document.getElementById("codepostal");
let villeField = document.getElementById("ville");

let fin = document.getElementById("finEnchere")
let debut = document.getElementById("debEnchere")

let confirmBtn = document.getElementById("confirmBtn");

let page = document.querySelector(".container");

let categorie = document.getElementById("categorie")

page.addEventListener("click", () => {
	verifInput();
});

page.addEventListener("keydown", () => {
	verifInput();
});
let error = false;
function verifInput() {
	error = false;
	verifPrixInitial()
	verifDesc()
	verifArticle()
	verifRueField();
	verifCodePostalField();
	verifVilleField();
	verifFin()
	verifDebut()
	verifCate()

	if (!error) {
		confirmBtn.removeAttribute("disabled", true);


		rueField.classList.remove("inputError");
		codePostalField.classList.remove("inputError");
		villeField.classList.remove("inputError");
	}
}

function verifCate(){
	if (categorie.value == ""){
		categorie.classList.add("inputError")
		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	}else {
		categorie.classList.remove("inputError")
	}
}

function verifDebut(){
	if (debut.value == ""){
		debut.classList.add("inputError")
		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	}else {
		debut.classList.remove("inputError")
	}
}

function verifFin(){
	if (fin.value == ""){
		fin.classList.add("inputError")
		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	}else {
		fin.classList.remove("inputError")
	}
}


function verifArticle(){
	if(article.value.length ==0){
		article.classList.add("inputError")
		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	}else {
		article.classList.remove("inputError")
	}
}

function verifDesc(){
	if(description.value.length ==0){
		description.classList.add("inputError")
		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	}else {
		description.classList.remove("inputError")
	}
}


function verifPrixInitial(){
	if(prixInitial.value.length ==0){
		prixInitial.classList.add("inputError")
		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	}else {
		prixInitial.classList.remove("inputError")
	}
}

function verifRueField() {
	if (rueField.value.length == 0) {
		rueField.classList.add("inputError");

		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	} else {
		rueField.classList.remove("inputError");
	}
}

function verifCodePostalField() {
	if (codePostalField.value.length == 0) {
		codePostalField.classList.add("inputError");

		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	} else {
		codePostalField.classList.remove("inputError");
	}
}

function verifVilleField() {
	if (villeField.value.length == 0) {
		villeField.classList.add("inputError");

		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	} else {
		villeField.classList.remove("inputError");
	}
}

confirmBtn.addEventListener('click', () => {
	verifInput()
})




