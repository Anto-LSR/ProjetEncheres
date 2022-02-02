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
 * Servlet implementation class IdentificationServlet
 */
@WebServlet("/identification")
public class IdentificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IdentificationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/identification.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String identifiant = request.getParameter("identifiant");
		String password = request.getParameter("motdepasse");
		UtilisateurManager um = UtilisateurManager.getInstance();
		Utilisateur utilisateur = new Utilisateur();
		if (identifiant.contains("@")) {
			utilisateur.setEmail(identifiant);
			utilisateur.setMotDePasse(password);
		} else {
			utilisateur.setPseudo(identifiant);
			utilisateur.setMotDePasse(password);
		}
		utilisateur = um.selectByLogin(utilisateur);
		if (utilisateur == null) { // Aucun utilisateur pour cette combinaison
			System.out.println("connection impossible");
			request.getRequestDispatcher("/WEB-INF/jsp/identification.jsp").forward(request, response);
		} else { // L'utilisateur existe : connexion
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			response.sendRedirect(request.getContextPath() + "/profil");
		}

	}

}
