package fr.eni.projet.bll;

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.EnchereDAO;

public class EnchereManager {
	private static EnchereManager instance;
	
	private EnchereManager() {
		
	}
	
	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public void insertEnchere(Enchere enchere) {
		EnchereDAO ed = DAOFactory.createNewEnchereImpl();
		ed.insertEnchere(enchere);
	}
	
}
