package fr.eni.projet.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleVenduDAO;
import fr.eni.projet.dal.DAOFactory;

public class ArticleVenduManager {

	private static ArticleVenduManager instance;

	private ArticleVenduManager() {

	}

	
	public static ArticleVenduManager getInstance() {
		if (instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}

	public int insertArticle(ArticleVendu articleVendu) {
		ArticleVenduDAO av = DAOFactory.createNewArticleVenduImpl();
		int noArticle = av.insertArticle(articleVendu);
		return noArticle;
	}

	public List<ArticleVendu> selectAllArticles() {

		List<ArticleVendu> articles = new ArrayList<>();
		ArticleVenduDAO av = DAOFactory.createNewArticleVenduImpl();
		articles = av.selectAllArticles();
		return articles;

	}

	public List<ArticleVendu> selectByLetter(String recherche) {
		List<ArticleVendu> articles = new ArrayList<>();
		ArticleVenduDAO av = DAOFactory.createNewArticleVenduImpl();
		articles = av.selectByLetter(recherche);
		return articles;

	}

	public ArticleVendu selectByName(ArticleVendu articleByName) {
		ArticleVenduDAO av = DAOFactory.createNewArticleVenduImpl();
		ArticleVendu articlevendu = av.selectByName(articleByName);
		return articlevendu;

	}

	public List<ArticleVendu> selectArticleByCategorie(Categorie categorie) {

		List<ArticleVendu> articles = new ArrayList<>();
		ArticleVenduDAO av = DAOFactory.createNewArticleVenduImpl();
		articles = av.selectArticleByCategorie(categorie);
		return articles;

	}
	
	public List<ArticleVendu> selectByDateFin(){
		List<ArticleVendu> articles = new ArrayList<>();
		ArticleVenduDAO av = DAOFactory.createNewArticleVenduImpl();
		articles = av.selectByDateFin();
		return articles;
	}
	
	
	public List<ArticleVendu> selectByUtilisateur(Utilisateur utilisateur){
		List<ArticleVendu> articles = new ArrayList<>();
		ArticleVenduDAO av = DAOFactory.createNewArticleVenduImpl();
		articles = av.selectByUtilisateur(utilisateur);
		return articles;
		
	}
}