package fr.eni.projet.bll;

public class InputError {
	private String nom;
	private String description;	
	
	public InputError(String nom, String description) {
		setNom(nom);
		setDescription(description);
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}


