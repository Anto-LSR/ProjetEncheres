package fr.eni.projet.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> articles = null;
	
	//Constructeurs
	public Categorie() {
		this.articles = new ArrayList();
	}

	public Categorie(String libelle) {
		this.articles = new ArrayList();
		setLibelle(libelle);
	}

	
	//Getters/setters
	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleVendu> articles) {
		this.articles = articles;
	}
	
	//Méthode d'ajout à la liste
	public void addToArticles(ArticleVendu article) {
		this.articles.add(article);
	}

}
