package fr.eni.projet.bll;

import fr.eni.projet.bo.Retrait;
import fr.eni.projet.dal.DAOFactory;
import fr.eni.projet.dal.RetraitDAO;

public class RetraitManager {

	private static RetraitManager instance;

	public static RetraitManager getInstance() {

		if (instance == null) {
			instance = new RetraitManager();

		}

		return instance;

	}

	public void insertRetrait(Retrait retrait) {
		RetraitDAO rt = DAOFactory.createNewRetraitImpl();
		rt.insertRetrait(retrait);

	}

	public void updateRetrait(Retrait retrait) {
		RetraitDAO rt = DAOFactory.createNewRetraitImpl();
		rt.updateRetrait(retrait);

	}

	public void deleteRetrait(Retrait retrait) {
		RetraitDAO rt = DAOFactory.createNewRetraitImpl();
		rt.deleteRetrait(retrait);
	}

	public Retrait selectRetraitByNo(Retrait retrait) {
		
		
		RetraitDAO rt = DAOFactory.createNewRetraitImpl();
		Retrait newretrait = rt.selectRetraitByNo(retrait);
		
		return newretrait;
	}
	
	
}
