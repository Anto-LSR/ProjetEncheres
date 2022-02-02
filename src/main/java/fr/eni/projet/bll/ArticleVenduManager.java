package fr.eni.projet.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.ArticleVendu;

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

	public List<ArticleVendu> selectArticleByCategorie(ArticleVendu articleByCategorie) {

		List<ArticleVendu> articles = new ArrayList<>();
		ArticleVenduDAO av = DAOFactory.createNewArticleVenduImpl();
		articles = av.selectArticleByCategorie(articleByCategorie);
		return articles;

	}

}