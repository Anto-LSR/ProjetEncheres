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
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.EnchereDAO;

public class EnchereImpl implements EnchereDAO {

	private static final String SQL_INSERT = "INSERT INTO Encheres (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?);";
	private static final String SQL_SELECT_ALL_ENCHERES = "SELECT * FROM Encheres;";
	private final static String SQL_SELECT_BY_UTILISATEUR = "SELECT * FROM Encheres WHERE no_utilisateur= ?;";
	private final static String SQL_SELECT_BY_ARTICLE = "SELECT top 1  * FROM ENCHERES  WHERE no_article = ?  order by montant_enchere  desc;";
	@Override
	public void insertEnchere(Enchere enchere) {
		Connection cnx = null;
		PreparedStatement pstmt = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_INSERT);
			pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticlevendu().getNoArticle());
			pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
			pstmt.setInt(4, enchere.getMontantEnchere());

			int nbLigne = pstmt.executeUpdate();
			if (nbLigne != 1) {
				System.err.println("ERREUR SUR L'INSERTION D'UNE ENCHERE");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
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
	public Enchere selectbyNumArticle(Enchere enchereParam) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Enchere enchere = new Enchere();
		
		cnx = ConnectionProvider.getConnection();
		
		try {
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_ARTICLE);
			pstmt.setInt(1, enchereParam.getArticlevendu().getNoArticle());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Utilisateur acheteur = new Utilisateur();
				acheteur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				enchere.setUtilisateur(acheteur);
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				enchere.setArticlevendu(article);
				enchere.setMontantEnchere(rs.getInt("montant_enchere"));
				
			}
			return enchere;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return enchere;
	}

	@Override
	public List<Enchere> selectByUtilisateur(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		Enchere enchere = null;
		List<Enchere> liste_encheres_by_utilisateur = new ArrayList<>();
		

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_UTILISATEUR);
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if (rs.getInt("no_article") != i) {
					i = rs.getInt("no_article");			
				}
					enchere = new Enchere();
					enchere.setDateEnchere(rs.getDate("date_enchere").toLocalDate());
					enchere.setMontantEnchere(rs.getInt("montant_enchere"));
					enchere.getArticlevendu().getNoArticle();
					enchere.getUtilisateur().getNoUtilisateur();

	
					
					liste_encheres_by_utilisateur.add(enchere);
				return liste_encheres_by_utilisateur;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}	
		return null;
	}

}
