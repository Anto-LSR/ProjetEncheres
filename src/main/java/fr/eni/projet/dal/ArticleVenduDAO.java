package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Utilisateur;

public interface ArticleVenduDAO {
	public int insertArticle (ArticleVendu articleVendu);
	public List<ArticleVendu> selectAllArticles();
	public List<ArticleVendu> selectByLetter(String recherche);
	public ArticleVendu selectByName (ArticleVendu articleByName);
	public List<ArticleVendu> selectArticleByCategorie(ArticleVendu articleByCategorie);
	public List<ArticleVendu> selectByUtilisateur();
	public List<ArticleVendu> selectByDateFin();
	public List<ArticleVendu> selectByMesVentesEnCours(Utilisateur utilisateur);
	
}
