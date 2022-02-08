package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Utilisateur;

public interface ArticleVenduDAO {
	public int insertArticle(ArticleVendu articleVendu);

	public List<ArticleVendu> selectAllArticles();

	public List<ArticleVendu> selectByLetter(String recherche);

	public ArticleVendu selectByName(ArticleVendu articleByName);
	
	public ArticleVendu selectById(int id);

	public List<ArticleVendu> selectArticleByCategorie(Categorie categorie);

	public List<ArticleVendu> selectByUtilisateur(Utilisateur utilisateur);

	public List<ArticleVendu> selectByDateFin();

	public List<ArticleVendu> selectByMesVentesEnCours(Utilisateur utilisateur);

	public List<ArticleVendu> selectByFiltres(String categorie, String recherche, String choice, String ventesEnCours,
			String ventesNonDebutees, String ventesTerminees, String encheresOuvertes, String encheresEnCours,
			String encheresRemportees, Utilisateur utilisateur);
	public List<ArticleVendu> selectByFiltresDeconnecte(String categorie, String recherche);
	
	public ArticleVendu selectByDetails(int id);
}

