//******************************************************* */
//VERIFICATION DES INPUTS SUR INSCRIPTION 

let phoneField = document.getElementById("telephone");
let pseudoField = document.getElementById("pseudo");
let nomField = document.getElementById("nom");
let prenomField = document.getElementById("prenom");
let mailField = document.getElementById("mail");
let rueField = document.getElementById("rue");
let codePostalField = document.getElementById("codepostal");
let villeField = document.getElementById("ville");
let password = document.getElementById("motdepasse");
let confirmation = document.getElementById("confirmation");

let confirmBtn = document.getElementById("confirmBtn");

let page = document.querySelector(".container");

page.addEventListener("click", () => {
	verifInput();
});

page.addEventListener("keydown", () => {
	verifInput();
	console.log("cocu");
});
let error = false;
function verifInput() {
	error = false;
	verifPseudoField();
	verifNomField();
	verifPrenomField();
	verifMailField();
	verifRueField();
	verifCodePostalField();
	verifVilleField();
	verifPassword();
	verifEmptyPassword();
	if (!error) {
		confirmBtn.removeAttribute("disabled", true);
		pseudoField.classList.remove("inputError");
		nomField.classList.remove("inputError");
		prenomField.classList.remove("inputError");
		mailField.classList.remove("inputError");
		rueField.classList.remove("inputError");
		codePostalField.classList.remove("inputError");
		villeField.classList.remove("inputError");
	}
}

function verifPseudoField() {
	if (pseudoField.value.length == 0) {
		pseudoField.classList.add("inputError");

		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	} else {
		pseudoField.classList.remove("inputError");
	}
}

function verifNomField() {
	if (nomField.value.length == 0) {
		nomField.classList.add("inputError");

		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	} else {
		nomField.classList.remove("inputError");
	}
}

function verifPrenomField() {
	if (prenomField.value.length == 0) {
		prenomField.classList.add("inputError");

		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	} else {
		prenomField.classList.remove("inputError");
	}
}

function verifMailField() {
	if (mailField.value.length == 0) {
		mailField.classList.add("inputError");

		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	} else {
		mailField.classList.remove("inputError");
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

function verifPassword() {
	if (password.value != confirmation.value) {
		confirmation.classList.add("inputError");
		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	} else {
		confirmation.classList.remove("inputError");
	}
}

function verifEmptyPassword() {
	if (password.value.length == 0) {
		password.classList.add("inputError");

		confirmBtn.setAttribute("disabled", true);
		return (error = true);
	} else {
		password.classList.remove("inputError");
	}

}


