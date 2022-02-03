package fr.eni.projet.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.bo.Categorie;

import fr.eni.projet.dal.CategorieDAO;
import fr.eni.projet.dal.DAOFactory;

public class CategorieManager {
	private static CategorieManager instance;

	private CategorieManager() {

	}

	public static CategorieManager getInstance() {

		if (instance == null) {
			instance = new CategorieManager();

		}

		return instance;

	}

	public int insertCategorie(Categorie categorie) {
		CategorieDAO cg = DAOFactory.createNewCategorieImpl();
		int noCategorie = cg.insertCategorie(categorie);

		return noCategorie;

	}

	public void updateCategorie(Categorie categorie) {
		CategorieDAO cg = DAOFactory.createNewCategorieImpl();
		cg.updateCategorie(categorie);

	}

	public void deleteCategorie(Categorie categorie) {
		CategorieDAO cg = DAOFactory.createNewCategorieImpl();
		cg.deleteCategorie(categorie);
	}

	public List<Categorie> selectAllCategorie() {
		List<Categorie> categorie = new ArrayList<>();
		CategorieDAO cg = DAOFactory.createNewCategorieImpl();
		categorie = cg.selectAllCategorie();
		return categorie;

	}
}
