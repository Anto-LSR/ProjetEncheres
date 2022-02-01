package fr.eni.projet.dal;

import fr.eni.projet.dal.jdbcImplement.UtilisateurImpl;

public class DAOFactory {
	public static UtilisateurDAO createNewUtilisateurImpl() {
		return new UtilisateurImpl();
	}
}
