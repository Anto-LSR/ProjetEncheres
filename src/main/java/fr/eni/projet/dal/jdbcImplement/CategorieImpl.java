package fr.eni.projet.dal.jdbcImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.DBTools.ConnectionProvider;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.dal.CategorieDAO;

public class CategorieImpl implements CategorieDAO {

	private final static String SQL_INSERT = "INSERT INTO CATEGORIES (libelle) VALUES (?);";
	private final static String SQL_UPDATE = "UPDATE  CATEGORIES SET libelle = '?' WHERE no_categorie = ?;";
	private final static String SQL_DELETE = "DELETE FROM CATEGORIES WHERE no_categorie = ?;";
	private final static String SQL_SELECTALL = "  SELECT * FROM CATEGORIES ;";
	private final static String SQL_SELECT_BY_LIBELLE = "SELECT * FROM CATEGORIES WHERE libelle = ? ;";


	@Override
	public int insertCategorie(Categorie categorie) {

		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, categorie.getLibelle());

			int nbLigne = pstmt.executeUpdate();
			if (nbLigne != 1) {
				System.err.println("ERREUR SUR L'INSERTION D'UNE CATEGORIE");// <---A MODIFIER
			}

			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int noCategorie = rs.getInt(1);
				return noCategorie;
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return 0;
	}

	@Override
	public void updateCategorie(Categorie categorie) {

		Connection cnx = null;
		PreparedStatement pstmt = null; // requete avec parametre

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, categorie.getLibelle());
			pstmt.setInt(2, categorie.getNoCategorie());
			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

	}

	@Override
	public void deleteCategorie(Categorie categorie) {

		Connection cnx = null;
		PreparedStatement pstmt = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, categorie.getNoCategorie());
			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

	}

	@Override

	public List<Categorie> selectAllCategorie() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Categorie> liste_all_categories = new ArrayList<>();
		Categorie newCategorie = null;

		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQL_SELECTALL);

			while (rs.next()) {

				newCategorie = new Categorie();
				newCategorie.setNoCategorie(rs.getInt("no_categorie"));
				newCategorie.setLibelle(rs.getString("libelle"));
				liste_all_categories.add(newCategorie);

			}

			return liste_all_categories;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, stmt);
		}

		return null;
	}
public Categorie selectByLibelle(Categorie categorie){
	Connection cnx = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Categorie cat = new Categorie();

	try {
		cnx = ConnectionProvider.getConnection();
		pstmt = cnx.prepareStatement(SQL_SELECT_BY_LIBELLE);
		pstmt.setString(1, categorie.getLibelle());
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			cat.setNoCategorie(rs.getInt("no_categorie"));
			cat.setLibelle(rs.getString("libelle"));
		}
		return cat;
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		ConnectionProvider.closeConnection(cnx, pstmt);
	}
	
	
	return null;
}
}
