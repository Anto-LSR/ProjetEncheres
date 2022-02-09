package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.Utilisateur;

public interface UtilisateurDAO {
	public int insertUser(Utilisateur utilisateur);
	public Utilisateur selectUserById(Utilisateur utilisateur);
	public List<Utilisateur>selectAllUsers();
	public Utilisateur updateUser(Utilisateur utilisateur); 
	public void deleteUser(Utilisateur utilisateur);
	public Utilisateur selectByLogin(Utilisateur utilisateur);
	public void updateCredit (int newSolde, int userId);
}
