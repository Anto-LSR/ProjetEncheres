package fr.eni.projet.dal;

import fr.eni.projet.dal.jdbcImplement.ArticleVenduImpl;
import fr.eni.projet.dal.jdbcImplement.CategorieImpl;
import fr.eni.projet.dal.jdbcImplement.UtilisateurImpl;

public class DAOFactory {
	public static UtilisateurDAO createNewUtilisateurImpl() {
		return new UtilisateurImpl();
	}
	
	public static ArticleVenduDAO createNewArticleVenduImpl() {
		return new ArticleVenduImpl();
	}
	
	
	public static CategorieDAO createNewCategorieImpl() {
		return new CategorieImpl();
	}
	
	
}


