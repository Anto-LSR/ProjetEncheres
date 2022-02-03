package fr.eni.projet.dal.jdbcImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projet.DBTools.ConnectionProvider;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.helpers.HashPassword;

public class UtilisateurImpl implements UtilisateurDAO {
	private final static String SQL_INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) \r\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?);";
	private final static String SQL_LOGIN = "SELECT * FROM UTILISATEURS WHERE pseudo = ? AND mot_de_passe = ? OR email = ? AND mot_de_passe = ?;";
	private final static String SQL_UPDATE_PASS = "UPDATE UTILISATEURS set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?;";
	private final static String SQL_UPDATE = "UPDATE UTILISATEURS set pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ? WHERE no_utilisateur = ?;";
	private final static String SQL_SELECTBYID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?;";
	private final static String SQL_SELECTALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit FROM UTILISATEURS;";

	@Override
	public int insertUser(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, 0);
			pstmt.setInt(11, 0);
			int nbLignes = pstmt.executeUpdate();
			if (nbLignes != 1) {
				System.err.println("ERREUR SUR L'INSERTION UTILISATEUR"); // <---A MODIFIER
			}
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int noUtilisateur = rs.getInt(1);
				return noUtilisateur;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return 0;
	}

	@Override
	public Utilisateur selectUserById(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		cnx = ConnectionProvider.getConnection();

		try {
			pstmt = cnx.prepareStatement(SQL_SELECTBYID);
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				return utilisateur;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return null;
	}

	@Override
	public List<Utilisateur> selectAllUsers() {
		Connection cnx = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Utilisateur> users = new ArrayList<>();

		cnx = ConnectionProvider.getConnection();

		try {
			stmt = cnx.createStatement();
			rs = stmt.executeQuery(SQL_SELECTALL);
			while (rs.next()) {
				Utilisateur user = new Utilisateur();
				user.setNoUtilisateur(rs.getInt(1));
				user.setPseudo(rs.getString(2));
				user.setNom(rs.getString(3));
				user.setPrenom(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setTelephone(rs.getString(6));
				user.setRue(rs.getString(7));
				user.setCodePostal(rs.getString(8));
				user.setVille(rs.getString(9));
				user.setCredit(rs.getInt(10));
				users.add(user);

			}
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, stmt);
		}
		return null;
	}

	@Override
	public Utilisateur updateUser(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		System.out.println(utilisateur.getNoUtilisateur());
		Utilisateur user = new Utilisateur();

		cnx = ConnectionProvider.getConnection();
		try {
			if (utilisateur.getMotDePasse().trim().isBlank() | utilisateur.getMotDePasse() == null) {
				pstmt = cnx.prepareStatement(SQL_UPDATE);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setInt(9, utilisateur.getNoUtilisateur());
			} else {

				pstmt = cnx.prepareStatement(SQL_UPDATE_PASS);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, utilisateur.getNoUtilisateur());
			}

			int nbLignes = pstmt.executeUpdate();

			if (nbLignes != 1) {
				System.out.println("erreur d'update profil");
			} else {
				int idUser = utilisateur.getNoUtilisateur();
				user = selectUserById(utilisateur);
				user.setNoUtilisateur(idUser);
				System.out.println(user.toString());
				return user;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}

		return null;
	}

	@Override
	public void deleteUser(Utilisateur utilisateur) {
		// TODO Auto-generated method stub

	}

	@Override
	public Utilisateur selectByLogin(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Utilisateur user = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_LOGIN);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, HashPassword.hashpassword(utilisateur.getMotDePasse()));
			pstmt.setString(3, utilisateur.getEmail());
			pstmt.setString(4, HashPassword.hashpassword(utilisateur.getMotDePasse()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new Utilisateur();
				user.setPseudo(rs.getString("pseudo"));
				user.setNom(rs.getString("nom"));
				user.setPrenom(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setTelephone(rs.getString("telephone"));
				user.setRue(rs.getString("rue"));
				user.setCodePostal(rs.getString("code_postal"));
				user.setVille(rs.getString("ville"));
				user.setCredit(rs.getInt("credit"));
				user.setNoUtilisateur(rs.getInt("no_utilisateur"));
			}
			return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.closeConnection(cnx, pstmt);
		}
		return user;
	}

}
