package fr.eni.projet.dal.jdbcImplement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.DBTools.ConnectionProvider;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleVenduDAO;

public class ArticleVenduImpl implements ArticleVenduDAO {

	private final static String SQL_INSERT = " INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) \r\n"
			+ "VALUES ( ? , ? , ?, ?, ?, ?, ?);";
	private final static String SQL_SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS;";

	@Override
	public int insertArticle(ArticleVendu articleVendu) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(1, articleVendu.getDescription());
			pstmt.setDate(3, Date.valueOf(articleVendu.getDateDebutEncheres()));
			pstmt.setDate(4, Date.valueOf(articleVendu.getDateFinEncheres()));
			pstmt.setInt(5, articleVendu.getPrixInitial());
			pstmt.setInt(6, articleVendu.getUtilisateurVendeur().getNoUtilisateur());
			pstmt.setInt(7, articleVendu.getCategorie().getNoCategorie());

			int nbLigne = pstmt.executeUpdate();
			if (nbLigne != 1) {
				System.err.println("ERREUR SUR L'INSERTION D'UN ARTICLE");// <---A MODIFIER
			}
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int noArticle = rs.getInt(1);
				return noArticle;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return 0;
	}

	@Override
	public ArticleVendu selectBy(ArticleVendu articleVendu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArticleVendu> selectAllArticles() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		int i = 0;
		ArticleVendu articleVendu = new ArticleVendu();
		List<ArticleVendu> liste_articles = new ArrayList<>();

		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQL_SELECT_ALL);

			while (rs.next()) {
				if (rs.getInt("noArticle") != i) {
					i = rs.getInt("noArticle");

					articleVendu = new ArticleVendu();

					articleVendu.setNomArticle(rs.getString("nom_article"));
					articleVendu.setDescription(rs.getString("description"));
					articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					Categorie categorie = new Categorie();
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					articleVendu.setCategorie(categorie);
					
					liste_articles.add(articleVendu);
				}
				return liste_articles;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<ArticleVendu> selectArticleByCategorie() {
		// TODO Auto-generated method stub
		return null;
	}

}
