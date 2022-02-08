package fr.eni.projet.bo;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import fr.eni.projet.helpers.Tools;

public class ArticleVendu {
	private int noArticle;
	private String NomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int prixInitial;
	private int prixVente;
	private Utilisateur utilisateurVendeur;
	private Utilisateur utilisateurAcheteur;
	private Categorie categorie;
	private Retrait retrait;
	private List<Enchere> encheres = null;

	// constructeurs
	public ArticleVendu() {
		this.encheres = new ArrayList<>();
		setDateDebutEncheres(dateDebutEncheres);
	}

	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Utilisateur utilisateurVendeur, Categorie categorie) {
		this.encheres = new ArrayList<>();
		setNomArticle(nomArticle);
		setDescription(description);
		setDateDebutEncheres(dateDebutEncheres);
		setDateFinEncheres(dateFinEncheres);
		setUtilisateurAcheteur(utilisateurAcheteur);
		setCategorie(categorie);
	}

	// Getters et Setters
	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return NomArticle;
	}

	public void setNomArticle(String nomArticle) {
		NomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public Utilisateur getUtilisateurAcheteur() {
		return utilisateurAcheteur;
	}

	public void setUtilisateurAcheteur(Utilisateur utilisateurAcheteur) {
		this.utilisateurAcheteur = utilisateurAcheteur;
	}

	public Utilisateur getUtilisateurVendeur() {
		return utilisateurVendeur;
	}

	public void setUtilisateurVendeur(Utilisateur utilisateurVendeur) {
		this.utilisateurVendeur = utilisateurVendeur;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Retrait getRetrait() {
		return retrait;
	}

	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	// Add List
	public void addToEncheres(Enchere enchere) {
		this.encheres.add(enchere);
	}
	
	
	public String formattedDateDebutEnchere() {
		return Tools.convertFormatDate(dateDebutEncheres);
	}
	
	public String formattedDateFinEnchere() {
		return Tools.convertFormatDate(dateFinEncheres);
	}
}