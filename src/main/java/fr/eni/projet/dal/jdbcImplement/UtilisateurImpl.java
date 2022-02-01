package fr.eni.projet.dal.jdbcImplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.projet.DBTools.ConnectionProvider;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.UtilisateurDAO;

public class UtilisateurImpl implements UtilisateurDAO {
	private final static String SQL_INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) \r\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?);";

	@Override
	public int insertUser(Utilisateur utilisateur) {
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			cnx = ConnectionProvider.getConnection();
			pstmt = cnx.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS );
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
			if(nbLignes != 1) {
				System.err.println("ERREUR SUR L'INSERTION UTILISATEUR"); // <---A MODIFIER
			}
			rs = pstmt.getGeneratedKeys();			
			if(rs.next()) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(Utilisateur utilisateur) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Utilisateur utilisateur) {
		// TODO Auto-generated method stub

	}

}
