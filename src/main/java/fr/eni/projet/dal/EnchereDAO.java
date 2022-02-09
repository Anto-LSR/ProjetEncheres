package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;

public interface EnchereDAO {

	public void insertEnchere(Enchere enchere);

	public List<Enchere> selectAlleEncheres();

	public Enchere selectbyNumArticle(Enchere enchere);

	public List<Enchere> selectByUtilisateur(Utilisateur utilisateur);
	
	public Enchere selectMaxByUser(Enchere enchere);

}
