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
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.EnchereDAO;

public class EnchereImpl implements EnchereDAO {

	private static final String SQL_INSERT = "INSERT INTO Encheres (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?);";
	private static final String SQL_SELECT_ALL_ENCHERES = "SELECT * FROM Encheres;";
	private static final String SQL_SELECT_BY_CATEGORIE = "SELECT e.no_utilisateur, a.no_article, date_enchere, montant_enchere FROM ENCHERES as e INNER JOIN ARTICLES_VENDUS as a ON e.no_article = a.no_article WHERE a.no_categorie= ?;";
	private static final String SQL_SELECT_BY_UTILISATEUR = "SELECT e.no_utilisateur, a.no_article, date_enchere, montant_enchere FROM ENCHERES as e INNER JOIN ARTICLES_VENDUS as a ON e.no_utilisateur = a.no_utilisateur WHERE e.no_utilisateur= ?;";

	@Override
	public int insertEnchere(Enchere enchere) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_INSERT);
			pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticlevendu().getNoArticle());
			pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchere());

			int nbLigne = pstmt.executeUpdate();
			if (nbLigne != 1) {
				System.err.println("ERREUR SUR L'INSERTION D'UN RETRAIT");
			}
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return 0;
	}

	@Override
	public List<Enchere> selectAlleEncheres() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		int i = 0;
		Enchere enchere = new Enchere();
		List<Enchere> liste_encheres = new ArrayList<>();

		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQL_SELECT_ALL_ENCHERES);

			while (rs.next()) {
				if (rs.getInt("no_utilisateur") != i) {
					i = rs.getInt("no_utilisateur");
					enchere = new Enchere();

					ArticleVendu articleVendu = new ArticleVendu();
					articleVendu.setNoArticle(rs.getInt("no_article"));
					enchere.setArticlevendu(articleVendu);
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					enchere.setUtilisateur(utilisateur);
					enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
					enchere.setMontantEnchere(rs.getInt("montant_enchere"));

					liste_encheres.add(enchere);
				}
				return liste_encheres;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, stmt);
		}

		return null;
	}

	@Override
	public List<Enchere> selectByCategorie() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		int i = 0;
		Enchere enchere = new Enchere();
		List<Enchere> liste_enchere_by_categorie = new ArrayList<>();

		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQL_SELECT_BY_CATEGORIE);

			while (rs.next()) {
				if (rs.getInt("no_article") != i) {
					i = rs.getInt("no_article");

					enchere = new Enchere();
					enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					enchere.setUtilisateur(utilisateur);
					ArticleVendu article = new ArticleVendu();
					article.setNoArticle(rs.getInt("no_article"));
					enchere.setArticlevendu(article);
					enchere.setMontantEnchere(rs.getInt("montant_enchere"));

					liste_enchere_by_categorie.add(enchere);
				}
				return liste_enchere_by_categorie;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, stmt);
		}

		return null;
	}

	@Override
	public List<Enchere> selectByUtilisateur() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		int i = 0;
		Enchere enchere = new Enchere();
		List<Enchere> liste_enchere_by_utilisateur = new ArrayList<>();

		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQL_SELECT_BY_UTILISATEUR);

			while (rs.next()) {
				if (rs.getInt("no_utilisateur") != i) {
					i = rs.getInt("no_utilisateur");

					enchere = new Enchere();
					enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					enchere.setUtilisateur(utilisateur);
					ArticleVendu article = new ArticleVendu();
					article.setNoArticle(rs.getInt("no_article"));
					enchere.setArticlevendu(article);
					enchere.setMontantEnchere(rs.getInt("montant_enchere"));

					liste_enchere_by_utilisateur.add(enchere);
				}
				return liste_enchere_by_utilisateur;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, stmt);
		}

		return null;
	}

	@Override
	public List<Enchere> selectByDateFin() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		int i = 0;
		Enchere enchere = new Enchere();
		List<Enchere> liste_enchere_by_dateFin = new ArrayList<>();
		
		return null;
	}

	@Override
	public List<Enchere> selectByDateDebut() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectbyNumArticle(Enchere enchere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectByName(Enchere enchere) {
		// TODO Auto-generated method stub
		return null;
	}

}
