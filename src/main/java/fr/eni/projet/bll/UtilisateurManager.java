package fr.eni.projet.bll;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Utilisateur> selectAllUsers(){
		List<Utilisateur> users = new ArrayList();
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
	
	
	
	
	
	
}
