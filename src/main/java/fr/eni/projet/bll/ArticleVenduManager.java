package fr.eni.projet.bll;

import java.time.LocalDate;
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
	
	public List<InputError> verifDate (ArticleVendu article) {
		List<InputError> errors = new ArrayList<>();
		
		if (article.getDateDebutEncheres().isAfter(article.getDateFinEncheres())){
			InputError err = new InputError("debutAfterFin", "La date de début ne peut être inferieure à la date de fin");
			errors.add(err);
		}
		if (article.getDateDebutEncheres().isBefore(LocalDate.now())) {
			InputError err = new InputError("debutBeforeToday", "La date de début ne peut être antérieure à la date du jour");
			errors.add(err);
		}
		if (article.getDateFinEncheres().isBefore(LocalDate.now())) {
			InputError err = new InputError("finBeforeToday", "La date de fin ne peut être antérieure à la date du jour");
			errors.add(err);
		}
		if(article.getNomArticle().trim().isBlank() | article.getNomArticle() == null) {
			InputError err = new InputError("nomNull", "Le nom de l'article doit être renseigné");
			errors.add(err);
		}
		
		if (article.getDescription().trim().isBlank() | article.getDescription() == null) {
			InputError err = new InputError("descNull", "La description de l'article doit être renseignée");
			errors.add(err);
		}
		
		if(article.getPrixInitial() == 0) {
			InputError err = new InputError("prixNull", "Le prix de l'article doit être renseigné");
			errors.add(err);
		}
		
		if(article.getDateDebutEncheres() == null) {
			InputError err = new InputError("dateDebutNull", "La date du début de l'enchère doit être renseignée");
			errors.add(err);
		}
		
		if(article.getDateFinEncheres() == null) {
			InputError err = new InputError("dateFinNull", "La date de fin de l'enchère doit être renseignée");
			errors.add(err);
		}
	return errors;
	}
	
	public List<ArticleVendu> selectByFiltres(String categorie, String recherche, String choice, String ventesEnCours,
			String ventesNonDebutees, String ventesTerminees, String encheresOuvertes, String encheresEnCours,
			String encheresRemportees, Utilisateur utilisateur){
		List<ArticleVendu> articles = new ArrayList<>();
		ArticleVenduDAO av = DAOFactory.createNewArticleVenduImpl();
		articles = av.selectByFiltres(categorie, recherche, choice, ventesEnCours, ventesNonDebutees, ventesTerminees, encheresOuvertes, encheresEnCours, encheresRemportees, utilisateur);
		
		return articles;
	
	}
	
	public List<ArticleVendu> selectByFiltresDeconnecte(String categorie, String recherche){
		List<ArticleVendu> articles = new ArrayList<>();
		ArticleVenduDAO av = DAOFactory.createNewArticleVenduImpl();
		articles = av.selectByFiltresDeconnecte(categorie, recherche);
		
		return articles;
	
	}
	
}