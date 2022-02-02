package fr.eni.projet.dal;

import fr.eni.projet.bo.Retrait;

public interface RetraitDAO {

	public void insertRetrait(Retrait retrait);

	public Retrait selectRetraitByNo(Retrait retrait);

	public void updateRetrait(Retrait retrait);

	public void deleteRetrait(Retrait retrait);

}
