package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.Categorie;

public interface CategorieDAO {

	public int insertCategorie(Categorie categorie);

	public void updateCategorie(Categorie categorie);

	public void deleteCategorie(Categorie categorie);

	public List<Categorie> selectAllCategorie();

	public Categorie selectByLibelle(Categorie categorie);
}
