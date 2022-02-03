package fr.eni.projet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.Utilisateur;

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
				System.out.println("Non Connecté 'Modif Profil'");
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
		HttpSession session = request.getSession();
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

		UtilisateurManager um = UtilisateurManager.getInstance();
		utilisateur = um.updateUser(utilisateur);
		session.setAttribute("utilisateur", utilisateur);
		request.getRequestDispatcher("/WEB-INF/jsp/modifprofil.jsp").forward(request, response);

	}

}
