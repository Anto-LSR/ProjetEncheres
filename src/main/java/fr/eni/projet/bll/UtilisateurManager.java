package fr.eni.projet.bll;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.UtilisateurDAO;

public class UtilisateurManager {
	private static UtilisateurManager instance;

	private UtilisateurManager() {

	}

	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	public int insertUser(Utilisateur utilisateur) {
		UtilisateurDAO ud = DAOFactory.createNewUtilisateurImpl();
		int noUtilisateur = ud.insertUser(utilisateur);
		return noUtilisateur;
	}

	public Utilisateur selectUserById(Utilisateur utilisateurrecherche) {
		UtilisateurDAO ud = DAOFactory.createNewUtilisateurImpl();
		Utilisateur utilisateur = ud.selectUserById(utilisateurrecherche);
		return utilisateur;
	}

	public List<Utilisateur> selectAllUsers() {

		List<Utilisateur> users = new ArrayList<>();
		UtilisateurDAO ud = DAOFactory.createNewUtilisateurImpl();
		users = ud.selectAllUsers();
		return users;
	}

	public void updateUser(Utilisateur utilisateur) {
		UtilisateurDAO ud = DAOFactory.createNewUtilisateurImpl();
		ud.updateUser(utilisateur);
	}

	public void deleteUSer(Utilisateur utilisateur) {
		UtilisateurDAO ud = DAOFactory.createNewUtilisateurImpl();
		ud.deleteUser(utilisateur);
	}


	public List<InputError> verifUser(Utilisateur utilisateur) { // <-----------VERIFIER CONDITIONS ERREURS
		List<InputError> errors = new ArrayList<>();
		if (isAlphaNumeric(utilisateur.getPseudo().trim())) {

			InputError err = new InputError("alphaError", "Le pseudo doit être composé de caractère alpha-numériques.");
			errors.add(err);
		} else if (utilisateur.getPseudo().isBlank()) { // <------------CA DOIT ETRE CA
			InputError err = new InputError("pseudoNull", "Le champ Pseudo doit être rempli");
			errors.add(err);
			System.out.println("coucou");
		} else if (utilisateur.getNom().trim() == null) {
			InputError err = new InputError("nomNull", "Le champ Nom doit être rempli");
			errors.add(err);
		} else if (utilisateur.getPrenom().trim() == null) {
			InputError err = new InputError("prenomNull", "Le champ Prénom doit être rempli");
			errors.add(err);
		} else if (utilisateur.getEmail().trim() == null) {
			InputError err = new InputError("emailNull", "Le champ E-Mail doit être rempli");
			errors.add(err);
		} else if (utilisateur.getRue().trim() == null) {
			InputError err = new InputError("rueNull", "Le champ Rue doit être rempli");
			errors.add(err);

		} else if (utilisateur.getCodePostal().trim() == null) {
			InputError err = new InputError("codePostalNull", "Le champ Code Postal doit être rempli");
			errors.add(err);
		} else if (utilisateur.getVille().trim() == null) {
			InputError err = new InputError("villeNull", "Le champ Ville doit être rempli");
			errors.add(err);
		} else if (utilisateur.getMotDePasse().trim() == null) {
			InputError err = new InputError("passNull", "Le mot de passe doit être rempli");
			errors.add(err);
		}

		return errors;
	}

	public boolean isAlphaNumeric(String str) {
		return str != null && str.matches("^[a-zA-Z0-9]*$");
	}



}
