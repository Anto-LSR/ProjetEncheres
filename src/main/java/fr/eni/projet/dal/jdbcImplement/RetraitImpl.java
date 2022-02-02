package fr.eni.projet.dal.jdbcImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.projet.DBTools.ConnectionProvider;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.dal.RetraitDAO;

public class RetraitImpl implements RetraitDAO {

	private final static String SQL_INSERT = "INSERT INTO RETRAITS (no_article,rue,code_postal,ville) VALUES (?,?,?,?)";
	private final static String SQL_UPDATE = "UPDATE  RETRAITS SET rue = '?' ,code_postal = '?' ,ville = '?' WHERE no_article = ?;";
	private final static String SQL_DELETE = "DELETE FROM RETRAITS WHERE no_article = ?;";
	private final static String SQL_SELECT = "SELECT no_article, rue, code_postal,ville FROM RETRAITS WHERE no_article = ?;";

	@Override

	public void insertRetrait(Retrait retrait) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_INSERT);
			pstmt.setInt(1, retrait.getArticleVendu().getNoArticle());
			pstmt.setString(2, retrait.getRue());
			pstmt.setString(3, retrait.getCodePostal());
			pstmt.setString(4, retrait.getVille());

			int nbLigne = pstmt.executeUpdate();
			if (nbLigne != 1) {
				System.err.println("ERREUR SUR L'INSERTION D'UN RETRAIT");// <---A MODIFIER
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

	}

	@Override

	public Retrait selectRetraitByNo(Retrait retrait) {

		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Retrait newretrait = new Retrait();

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT);
			pstmt.setInt(1, retrait.getArticleVendu().getNoArticle());

			if (rs.next()) {

				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				newretrait.setArticleVendu(article);

				newretrait.setRue(rs.getString("rue"));
				newretrait.setCodePostal(rs.getString("code_postal"));
				newretrait.setVille(rs.getString("ville"));

			}

			return newretrait;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return null;
	}

	@Override
	public void updateRetrait(Retrait retrait) {

		Connection cnx = null;
		PreparedStatement pstmt = null; // requete avec parametre

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_UPDATE);

			pstmt.setString(1, retrait.getRue());
			pstmt.setString(2, retrait.getCodePostal());
			pstmt.setString(3, retrait.getVille());
			pstmt.setInt(4, retrait.getArticleVendu().getNoArticle());
			pstmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

	}

	@Override
	public void deleteRetrait(Retrait retrait) {

		Connection cnx = null;
		PreparedStatement pstmt = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, retrait.getArticleVendu().getNoArticle());
			pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

	}

}
