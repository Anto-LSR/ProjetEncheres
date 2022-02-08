//GESTION FILTRES ACCUEIL CONNECTE

let radioAchat = document.getElementById('radioAchats');
let radioVente = document.getElementById('radioVentes');

let enchEnCours = document.getElementById('enchencours');
let enchOuverte = document.getElementById('enchouvertes');
let enchRemportees = document.getElementById('enchremportees');

let ventesEnCours = document.getElementById('ventesencours');
let ventesNonDebutees = document.getElementById('ventesnondebutees')
let ventesTerminees = document.getElementById('ventesTerminees')


enchEnCours.setAttribute("disabled", true)
enchEnCours.checked = false;
enchOuverte.setAttribute("disabled", true)
enchOuverte.checked = false;
enchRemportees.setAttribute("disabled", true);
enchRemportees.checked = false;


ventesEnCours.checked = false;

ventesNonDebutees.checked = false;

ventesTerminees.checked = false;


radioAchat.addEventListener('click', () => {
    ventesEnCours.setAttribute("disabled", true)
    ventesEnCours.checked = false;
    ventesNonDebutees.setAttribute("disabled", true)
    ventesNonDebutees.checked = false;
    ventesTerminees.setAttribute("disabled", true);
    ventesTerminees.checked = false;

    enchEnCours.removeAttribute("disabled", true)
    enchEnCours.checked=false;
    enchOuverte.removeAttribute("disabled", true)
    enchOuverte.checked = false;
    enchRemportees.removeAttribute("disabled", true)
    enchRemportees.checked = false;
})

radioVente.addEventListener('click', () => {
    enchEnCours.setAttribute("disabled", true)
    enchEnCours.checked = false;
    enchOuverte.setAttribute("disabled", true)
    enchOuverte.checked = false;
    enchRemportees.setAttribute("disabled", true);
    enchRemportees.checked = false;

    ventesEnCours.removeAttribute("disabled", true)
    ventesEnCours.checked = false;
    ventesNonDebutees.removeAttribute("disabled", true)
    ventesNonDebutees.checked = false;
    ventesTerminees.removeAttribute("disabled", true)
    ventesTerminees.checked = false;
})


