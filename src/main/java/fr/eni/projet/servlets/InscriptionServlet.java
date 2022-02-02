package fr.eni.projet.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projet.bll.InputError;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.helpers.HashPassword;

/**
 * Servlet implementation class InscriptionServlet
 */
@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
		;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean error = false;

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("mail");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		String motdepasse = HashPassword.hashpassword(request.getParameter("motdepasse"));
		String verif = HashPassword.hashpassword(request.getParameter("confirmation"));
		System.out.println(verif);
		if (!motdepasse.equals(verif)) {
			error = true;
			System.out.println("erreur sur les mdp");
			request.setAttribute("passError", "Les mots de passes doivent �tre identiques");
			request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
		}
		UtilisateurManager um = UtilisateurManager.getInstance();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(pseudo);
		utilisateur.setNom(nom);
		utilisateur.setPrenom(prenom);
		utilisateur.setEmail(email);
		utilisateur.setTelephone(telephone);
		utilisateur.setRue(rue);
		utilisateur.setCodePostal(codePostal);
		utilisateur.setVille(ville);
		utilisateur.setMotDePasse(motdepasse);
		utilisateur.setAdministrateur(false);
		System.out.println(utilisateur.toString());

		List<InputError> errors = um.verifUser(utilisateur);// <-----------ERRORS NON RELEVEE
		if (errors.isEmpty() && !error) {
			System.out.println("pas d'erreur");
			um.insertUser(utilisateur);
		} else {
			error = true;
			for (InputError err : errors) {

				if (err.getNom().trim().equals("alphaError")) {
					request.setAttribute("alphaError", err.getDescription());
					request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
				} else if (err.getNom().trim().equals("pseudoNull")) {

					request.setAttribute("pseudoNull", err.getDescription());
					request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
				} else if (err.getNom().trim().equals("nomNull")) {
					request.setAttribute("nomNull", err.getDescription());
					request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
				} else if (err.getNom().trim().equals("prenomNull")) {
					request.setAttribute("prenomNull", err.getDescription());
					request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
				} else if (err.getNom().trim().equals("emailNull")) {
					request.setAttribute("emailNull", err.getDescription());
					request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
				} else if (err.getNom().trim().equals("rueNull")) {
					request.setAttribute("rueNull", err.getDescription());
					request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
				} else if (err.getNom().trim().equals("codePostalNull")) {
					request.setAttribute("codePostalNull", err.getDescription());
					request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
				} else if (err.getNom().trim().equals("villeNull")) {
					request.setAttribute("villeNull", err.getDescription());
					request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
				} else if (err.getNom().trim().equals("passNull")) {
					request.setAttribute("passNull", err.getDescription());
					request.getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
				}
			}
		}
	}

	public boolean isAlphaNumeric(String str) {
		return str != null && str.matches("^[a-zA-Z0-9]*$");
	}

}
