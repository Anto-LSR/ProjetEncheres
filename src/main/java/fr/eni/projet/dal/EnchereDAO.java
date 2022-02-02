package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.Enchere;

public interface EnchereDAO {

	public int insertEnchere (Enchere enchere);
	public List<Enchere> selectAlleEncheres();
	public List<Enchere> selectByCategorie();
	public List<Enchere> selectByUtilisateur();
	public List<Enchere> selectByDateFin();
	public List<Enchere> selectByDateDebut();
	public Enchere selectbyNumArticle (Enchere enchere);
	public Enchere selectByName(Enchere enchere);
	
	
}
