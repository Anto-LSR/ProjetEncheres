package fr.eni.projet.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.InputError;
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.helpers.HashPassword;

/**
 * Servlet implementation class ProfilServlet
 */
@WebServlet("/editerprofil")
public class ModifProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifProfilServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		boolean isConnected = true;

		if (session != null) {
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			if (utilisateur == null) {
				isConnected = false;
				session.setAttribute("connected", false);
			} else {
				isConnected = true;
				session.setAttribute("connected", true);
			}
		} else {
			isConnected = false;
			request.setAttribute("connected", false);
		}
		if (isConnected == false) {
			response.sendRedirect(request.getContextPath() + "/accueil");
			System.out.println("Non Connecté page 'Modif Profil' non accessible");
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/modifprofil.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean error = false;
		HttpSession session = request.getSession();
		String motdepasse = HashPassword.hashpassword(request.getParameter("motdepasse"));
		String verif = HashPassword.hashpassword(request.getParameter("confirmation"));
		Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");

		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNoUtilisateur(user.getNoUtilisateur());
		utilisateur.setPseudo(request.getParameter("pseudo"));
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setEmail(request.getParameter("mail"));
		utilisateur.setTelephone(request.getParameter("telephone"));
		utilisateur.setRue(request.getParameter("rue"));
		utilisateur.setCodePostal(request.getParameter("codepostal"));
		utilisateur.setVille(request.getParameter("ville"));
		if (!request.getParameter("motdepasse").trim().isBlank() | request.getParameter("motdepasse") != null) {
			utilisateur.setMotDePasse(request.getParameter("motdepasse"));
		}
		if(!request.getParameter("mail").contains("@")) {
			error = true;
			request.setAttribute("atError", "Le format de l'email n'est pas bon");
		}
		UtilisateurManager um = UtilisateurManager.getInstance();
		List<InputError> errors = um.verifUserModif(utilisateur);
		
		if(!errors.isEmpty()) {
			error = true;
			for (InputError err : errors) {

				if (err.getNom().trim().equals("alphaError")) {
					request.setAttribute("alphaError", err.getDescription());
				} else if (err.getNom().trim().equals("pseudoNull")) {
					request.setAttribute("pseudoNull", err.getDescription());
				} else if (err.getNom().trim().equals("nomNull")) {
					request.setAttribute("nomNull", err.getDescription());
				} else if (err.getNom().trim().equals("prenomNull")) {
					request.setAttribute("prenomNull", err.getDescription());
				} else if (err.getNom().trim().equals("emailNull")) {
					request.setAttribute("emailNull", err.getDescription());
				} else if (err.getNom().trim().equals("rueNull")) {
					request.setAttribute("rueNull", err.getDescription());
				} else if (err.getNom().trim().equals("codePostalNull")) {
					request.setAttribute("codePostalNull", err.getDescription());
				} else if (err.getNom().trim().equals("villeNull")) {
					request.setAttribute("villeNull", err.getDescription());
				} else if (err.getNom().trim().equals("passNull")) {
					request.setAttribute("passNull", err.getDescription());
				}
			}
		}
		if (!motdepasse.equals(verif)) {
			error = true;

			request.setAttribute("passError", "Les mots de passes doivent être identiques");
			
		}
		List<Utilisateur> users = um.selectAllUsers();
		for (Utilisateur u : users) {
			if (u.getNoUtilisateur() != utilisateur.getNoUtilisateur()) {
				if (u.getPseudo().equals(utilisateur.getPseudo())) {
					request.setAttribute("pseudoTaken", "Ce pseudo est déjà pris");
					error = true;

				}
				if (u.getEmail().equals(utilisateur.getEmail())) {
					error = true;
					request.setAttribute("mailTaken", "Cet email est déjà pris");
				}
			}

		}
		if (!error) {
			utilisateur = um.updateUser(utilisateur);
			session.setAttribute("utilisateur", utilisateur);
		}
		request.getRequestDispatcher("/WEB-INF/jsp/modifprofil.jsp").forward(request, response);

	}

}
