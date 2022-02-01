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

	public Categorie(int noCategorie, String libelle, List<ArticleVendu> articles) {
		this.articles = new ArrayList();
		setNoCategorie(noCategorie);
		setLibelle(libelle);
		setArticles(articles);
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
	
	//M�thode d'ajout � la liste
	public void addToArticles(ArticleVendu article) {
		this.articles.add(article);
	}

}
