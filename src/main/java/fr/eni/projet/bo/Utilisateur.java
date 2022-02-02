package fr.eni.projet.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	private int noUtilisateur, credit;
	private String pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse;
	private boolean administrateur;
	private List<Enchere> encheres = null;
	private List<ArticleVendu> achats = null;
	private List<ArticleVendu> ventes = null;

	public Utilisateur() {
		this.ventes = new ArrayList();
		this.achats = new ArrayList();
		this.encheres = new ArrayList();

	}

	public Utilisateur(int credit, String pseudo, String nom, String prenom, String email, String rue,
			String codePostal, String ville, String motDePasse, boolean administrateur) {
		this.ventes = new ArrayList();
		this.achats = new ArrayList();
		this.encheres = new ArrayList();

		setCredit(credit);
		setPseudo(pseudo);
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		setRue(rue);
		setCodePostal(codePostal);
		setVille(ville);
		setMotDePasse(motDePasse);
		setAdministrateur(administrateur);
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	public List<Enchere> getEncheres() {
		return encheres;
	}

	public void setEncheres(List<Enchere> encheres) {
		this.encheres = encheres;
	}

	public List<ArticleVendu> getArticlesAchat() {
		return achats;
	}

	public void setArticlesAchat(List<ArticleVendu> achats) {
		this.achats = achats;
	}

	public List<ArticleVendu> getArticleVente() {
		return ventes;
	}

	public void setArticleVente(List<ArticleVendu> ventes) {
		this.ventes = ventes;
	}

	public void addToEncheres(Enchere enchere) {
		this.encheres.add(enchere);
	}

	public void addToArticleVente(ArticleVendu articlevendu) {
		this.ventes.add(articlevendu);
	}

	public void addToArticleAchat(ArticleVendu articlevendu) {
		this.achats.add(articlevendu);
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", credit=" + credit + ", pseudo=" + pseudo + ", nom="
				+ nom + ", prenom=" + prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue
				+ ", codePostal=" + codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", administrateur="
				+ administrateur + ", encheres=" + encheres + ", achats=" + achats + ", ventes=" + ventes + "]";
	}
	
	
}
