package fr.eni.projet.dal;

import fr.eni.projet.dal.jdbcImplement.ArticleVenduImpl;
import fr.eni.projet.dal.jdbcImplement.CategorieImpl;
import fr.eni.projet.dal.jdbcImplement.EnchereImpl;
import fr.eni.projet.dal.jdbcImplement.RetraitImpl;
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

	public static RetraitDAO createNewRetraitImpl() {

		return new RetraitImpl();
	}
	
	public static EnchereDAO createNewEnchereImpl() {
		return new EnchereImpl();
	}

}
