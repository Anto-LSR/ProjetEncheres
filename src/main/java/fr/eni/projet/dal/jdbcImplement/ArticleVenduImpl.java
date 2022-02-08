package fr.eni.projet.dal.jdbcImplement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.DBTools.ConnectionProvider;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleVenduDAO;
import fr.eni.projet.dal.CategorieDAO;

public class ArticleVenduImpl implements ArticleVenduDAO {

	private final static String SQL_INSERT = " INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) \r\n"
			+ "VALUES ( ? , ? , ?, ?, ?, ?, ?);";
	private final static String SQL_SELECT_ALL = "SELECT * FROM ARTICLES_VENDUS;";
	private final static String SQL_SELECT_BY_LETTER = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ? OR description LIKE ?;";
	private final static String SQL_SELECT_BY_NAME = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article = ?;";
	private final static String SQL_SELECT_BY_CATEGORIE = " SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ? ;";
	private final static String SQL_SELECT_BY_DATE_FIN = "SELECT * FROM ARTICLES_VENDUS WHERE date_fin_encheres > GETDATE() ORDER BY date_fin_encheres ASC  ";
	private final static String SQL_SELECT_BY_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur= ?;";
	private final static String SQL_SELECT_BY_MES_VENTES_EN_COURS = "SELECT * FROM ARTICLES_VENDUS WHERE date_fin_encheres > GETDATE() AND no_utilisateur = ?;";
	private final static String SQL_SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?;";

	@Override
	public int insertArticle(ArticleVendu articleVendu) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
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
	public List<ArticleVendu> selectAllArticles() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		int i = 0;
		ArticleVendu articleVendu;
		List<ArticleVendu> liste_articles = new ArrayList<>();

		try {
			cnx = ConnectionProvider.getConnection();
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQL_SELECT_ALL);

			while (rs.next()) {
				if (rs.getInt("no_article") != i) {
					i = rs.getInt("no_article");

					articleVendu = new ArticleVendu();

					articleVendu.setNomArticle(rs.getString("nom_article"));
					articleVendu.setDescription(rs.getString("description"));
					articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					articleVendu.setUtilisateurVendeur(utilisateur);
					Categorie categorie = new Categorie();
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					articleVendu.setCategorie(categorie);

					liste_articles.add(articleVendu);
				}
				return liste_articles;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, stmt);
		}

		return null;
	}

	public List<ArticleVendu> selectByLetter(String recherche) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVendu articleVendu = new ArticleVendu();
		List<ArticleVendu> liste_ByLetter = new ArrayList<>();

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_LETTER);
			pstmt.setString(1, recherche);
			pstmt.setString(2, recherche);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articleVendu.setUtilisateurVendeur(utilisateur);
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				articleVendu.setCategorie(categorie);

				liste_ByLetter.add(articleVendu);
			}

			return liste_ByLetter;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return null;
	}

	@Override
	public ArticleVendu selectByName(ArticleVendu articleByName) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVendu articleVendu = new ArticleVendu();

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_NAME);
			pstmt.setString(1, articleByName.getNomArticle());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleVendu.setNoArticle(articleVendu.getNoArticle());
				articleVendu.setNomArticle(articleVendu.getNomArticle());
				articleVendu.setDescription(articleVendu.getDescription());
				articleVendu.setDateDebutEncheres(articleVendu.getDateDebutEncheres());
				articleVendu.setDateFinEncheres(articleVendu.getDateFinEncheres());
				articleVendu.setPrixInitial(articleVendu.getPrixInitial());
				articleVendu.setPrixVente(articleVendu.getPrixVente());
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articleVendu.setUtilisateurVendeur(utilisateur);
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				articleVendu.setCategorie(categorie);

				return articleVendu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return null;
	}

	@Override
	public List<ArticleVendu> selectArticleByCategorie(Categorie categorie) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ArticleVendu> liste_ByCategorie = new ArrayList<>();

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_CATEGORIE);
			pstmt.setInt(1, categorie.getNoCategorie());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ArticleVendu articleVendu = new ArticleVendu();
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articleVendu.setUtilisateurVendeur(utilisateur);
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				articleVendu.setCategorie(categorie);

				liste_ByCategorie.add(articleVendu);
			}
			return liste_ByCategorie;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return null;
	}

	public List<ArticleVendu> selectByUtilisateur(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int i = 0;
		ArticleVendu articleVendu;
		List<ArticleVendu> liste_aticles_by_utilisateur = new ArrayList<>();

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_UTILISATEUR);
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getInt("no_article") != i) {
					i = rs.getInt("no_article");

					articleVendu = new ArticleVendu();
					articleVendu.setNoArticle(rs.getInt("no_article"));
					articleVendu.setNomArticle(rs.getString("nom_article"));
					articleVendu.setDescription(rs.getString("description"));
					articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
					articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
					articleVendu.setPrixInitial(rs.getInt("prix_initial"));
					articleVendu.setPrixVente(rs.getInt("prix_vente"));
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					articleVendu.setUtilisateurVendeur(utilisateur);
					Categorie categorie = new Categorie();
					categorie.setNoCategorie(rs.getInt("no_categorie"));
					articleVendu.setCategorie(categorie);

					liste_aticles_by_utilisateur.add(articleVendu);
				}
				return liste_aticles_by_utilisateur;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return null;
	}

	// Liste des Encheres Ouverte où la date de fin est superieur à la date du jour
	@Override
	public List<ArticleVendu> selectByDateFin() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArticleVendu article;
		List<ArticleVendu> liste_article_by_dateFin = new ArrayList<>();

		cnx = ConnectionProvider.getConnection();
		try {
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQL_SELECT_BY_DATE_FIN);

			while (rs.next()) {
				article = new ArticleVendu();
				article.setNoArticle(rs.getInt(1));
				article.setNomArticle(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setDateDebutEncheres(rs.getDate(4).toLocalDate());
				article.setDateFinEncheres(rs.getDate(5).toLocalDate());
				article.setPrixInitial(rs.getInt(6));
				article.setPrixVente(rs.getInt(7));
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt(8));
				article.setUtilisateurVendeur(utilisateur);
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt(9));
				article.setCategorie(categorie);

				liste_article_by_dateFin.add(article);
			}
			return liste_article_by_dateFin;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, stmt);
		}
		return null;
	}

	@Override
	public List<ArticleVendu> selectByMesVentesEnCours(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVendu article = new ArticleVendu();
		Categorie categorie = new Categorie();
		List<ArticleVendu> liste_mes_ventes_en_cours = new ArrayList<>();

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_MES_VENTES_EN_COURS);
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				article.setNoArticle(rs.getInt(1));
				article.setNomArticle(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setDateDebutEncheres(rs.getDate(4).toLocalDate());
				article.setDateFinEncheres(rs.getDate(5).toLocalDate());
				article.setPrixInitial(rs.getInt(6));
				article.setPrixVente(rs.getInt(7));
				utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt(8));
				article.setUtilisateurVendeur(utilisateur);
				categorie.setNoCategorie(rs.getInt(9));
				article.setCategorie(categorie);

				liste_mes_ventes_en_cours.add(article);
			}
			return liste_mes_ventes_en_cours;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return null;
	}

	@Override
	public List<ArticleVendu> selectByFiltres(String categorie, String recherche, String choice, String ventesEnCours,
			String ventesNonDebutees, String ventesTerminees, String encheresOuvertes, String encheresEnCours,
			String encheresRemportees, Utilisateur utilisateur) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT av.no_utilisateur, av.no_article, av.nom_article, av.description, " + "date_debut_encheres, "
				+ "date_fin_encheres, c.*, " + "e.date_enchere, montant_enchere as enchere_max  "
				+ "FROM ARTICLES_VENDUS as av LEFT JOIN ENCHERES as e ON av.no_article = e.no_article "
				+ "INNER JOIN CATEGORIES as c ON av.no_categorie = c.no_categorie WHERE 1=1 ");
		boolean byFin = false;

		if (choice != null) {

			if (choice.equals("Achats")) {

				// *******************CASES COCHEES *****************************
				if (encheresOuvertes != null && encheresEnCours != null && encheresRemportees != null) {
					sb.append("");

				} else if ((encheresOuvertes != null && encheresEnCours != null)) {
					sb.append("AND av.date_fin_encheres > GETDATE() ");

				} else if (encheresOuvertes != null && encheresRemportees != null) {
					sb.append("");

				} else if (encheresEnCours != null && encheresRemportees != null) {
					sb.append("AND e.no_utilisateur = " + utilisateur.getNoUtilisateur() + " ");

				} else if (encheresOuvertes != null) {
					sb.append("AND av.date_fin_encheres > GETDATE() ");

				} else if (encheresEnCours != null) {
					sb.append(("AND e.no_utilisateur = " + utilisateur.getNoUtilisateur() + " "
							+ "AND date_fin_encheres > GETDATE() AND date_debut_encheres < GETDATE()"));

				} else if (encheresRemportees != null) {
					sb.append("AND e.no_utilisateur = " + utilisateur.getNoUtilisateur()
							+ "AND av.date_fin_encheres < GETDATE() ");
				}
				sb.append("AND e.montant_enchere = " + "COALESCE((SELECT MAX(montant_enchere) FROM ENCHERES e2 "
						+ "WHERE e2.no_article = av.no_article),0) ");

				// ********************CATEGORIE************************
				if (!categorie.equals("Toutes")) {
					sb.append("AND c.libelle = '" + categorie + "' ");
				}

				if (!recherche.isBlank()) {
					sb.append("AND nom_article LIKE '%" + recherche + "%' ");
				}

				sb.append("ORDER  BY av.no_article;");

				// **************************************************
				System.out.println(sb);

			} else if (choice.equals("Ventes")) {

				sb.append("AND av.no_utilisateur = " + utilisateur.getNoUtilisateur() + " ");
				if (ventesEnCours != null && ventesNonDebutees != null && ventesTerminees != null) {
					byFin = true;
				} else if (ventesEnCours != null && ventesNonDebutees != null) {
					sb.append("AND av.date_fin_encheres > GETDATE() ");
				} else if (ventesNonDebutees != null && ventesTerminees != null) {
					sb.append("AND av.date_debut_encheres > GETDATE() OR av.date_fin_encheres < GETDATE() ");
					byFin = true;
				} else if (ventesEnCours != null && ventesTerminees != null) {
					sb.append("AND av.date_debut_encheres < GETDATE() ");
				} else if (ventesEnCours != null) {
					sb.append("AND av.date_fin_encheres> GETDATE() AND av.date_debut_encheres <= GETDATE() ");
					byFin = true;
				} else if (ventesNonDebutees != null) {
					sb.append("AND av.date_debut_encheres > GETDATE() ");
				} else if (ventesTerminees != null) {
					sb.append("AND av.date_fin_encheres < GETDATE() ");
					byFin = true;
				}

				sb.append("AND e.montant_enchere = " + "COALESCE((SELECT MAX(montant_enchere) FROM ENCHERES e2 "
						+ "WHERE e2.no_article = av.no_article),0) ");

				// *****************CATEGORIE
				if (!categorie.equals("Toutes")) {
					sb.append("AND c.libelle = '" + categorie + "' ");
				}

				if (!recherche.isBlank()) {
					sb.append("AND nom_article LIKE '%" + recherche + "%' ");
				}
				// ***************
				if (byFin) {
					sb.append("ORDER BY av.date_fin_encheres;");
				} else {
					sb.append("ORDER BY av.date_debut_encheres;");
				}
				System.out.println(sb);
			}
		} else {
			sb.append("AND av.date_fin_encheres > GETDATE() ");
			System.out.println("test" + categorie);
			if (!categorie.equals("Toutes")) {
				sb.append("AND c.libelle = '" + categorie + "' ");
			}
			sb.append("ORDER BY av.date_fin_encheres;");
		}
		System.out.println(sb);
		String SQL_RECHERCHE = sb.toString();
		// *********************EXECUTION DE LA REQUETE SQL**************

		Connection cnx;
		PreparedStatement pstmt = null;
		ResultSet rs;
		List<ArticleVendu> articlesRecherche = new ArrayList<>();
		cnx = ConnectionProvider.getConnection();

		try {
			pstmt = cnx.prepareStatement(SQL_RECHERCHE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setCategorie((new CategorieImpl()).selectById(rs.getInt("no_categorie")));

				article.setDateFinEncheres(
						(new ArticleVenduImpl()).selectById(article.getNoArticle()).getDateFinEncheres());
				article.setDateDebutEncheres(
						(new ArticleVenduImpl()).selectById(article.getNoArticle()).getDateDebutEncheres());
				article.setDescription(rs.getString("description"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setPrixInitial(rs.getInt("enchere_max"));
				Utilisateur vendeur = new Utilisateur();
				vendeur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				article.setUtilisateurVendeur(new UtilisateurImpl().selectUserById(vendeur));
				articlesRecherche.add(article);

			}
			return articlesRecherche;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return articlesRecherche;
	}
	
	@Override
	public List<ArticleVendu> selectByFiltresDeconnecte(String categorie, String recherche) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT av.no_utilisateur, av.no_article, av.nom_article, av.description, " + "date_debut_encheres, "
				+ "date_fin_encheres, c.*, " + "e.date_enchere, montant_enchere as enchere_max  "
				+ "FROM ARTICLES_VENDUS as av LEFT JOIN ENCHERES as e ON av.no_article = e.no_article "
				+ "INNER JOIN CATEGORIES as c ON av.no_categorie = c.no_categorie WHERE 1=1 "
				+ "AND av.date_fin_encheres > GETDATE() ");
	
			if (!categorie.equals("Toutes")) {
				sb.append("AND c.libelle = '" + categorie + "' ");
			}
			System.out.println("coucou1");
			System.out.println(recherche);
			if (!recherche.isBlank() || recherche == null) {
				sb.append("AND nom_article LIKE '%" + recherche + "%' ");
				
			}
			sb.append("ORDER BY av.date_fin_encheres;");
		
		System.out.println(sb);
		String SQL_RECHERCHE = sb.toString();
		// *********************EXECUTION DE LA REQUETE SQL**************

		Connection cnx;
		PreparedStatement pstmt = null;
		ResultSet rs;
		List<ArticleVendu> articlesRecherche = new ArrayList<>();
		cnx = ConnectionProvider.getConnection();

		try {
			pstmt = cnx.prepareStatement(SQL_RECHERCHE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ArticleVendu article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setCategorie((new CategorieImpl()).selectById(rs.getInt("no_categorie")));

				article.setDateFinEncheres(
						(new ArticleVenduImpl()).selectById(article.getNoArticle()).getDateFinEncheres());
				article.setDateDebutEncheres(
						(new ArticleVenduImpl()).selectById(article.getNoArticle()).getDateDebutEncheres());
				article.setDescription(rs.getString("description"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setPrixInitial(rs.getInt("enchere_max"));
				Utilisateur vendeur = new Utilisateur();
				vendeur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				article.setUtilisateurVendeur(new UtilisateurImpl().selectUserById(vendeur));
				articlesRecherche.add(article);

			}
			return articlesRecherche;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return articlesRecherche;
	}

	@Override
	public ArticleVendu selectById(int id) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVendu articleVendu = new ArticleVendu();

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				articleVendu.setNoArticle(rs.getInt("no_article"));
				articleVendu.setNomArticle(rs.getString("nom_article"));
				articleVendu.setDescription(rs.getString("description"));
				articleVendu.setDateDebutEncheres(rs.getDate("date_debut_encheres").toLocalDate());
				articleVendu.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());
				articleVendu.setPrixInitial(rs.getInt("prix_initial"));
				articleVendu.setPrixVente(rs.getInt("prix_vente"));
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				articleVendu.setUtilisateurVendeur(utilisateur);
				Categorie categorie = new Categorie();
				categorie.setNoCategorie(rs.getInt("no_categorie"));
				articleVendu.setCategorie(categorie);

				return articleVendu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return null;
	}

}
