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
 * Servlet implementation class MembreServlet
 */
@WebServlet("/membres")
public class MembreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MembreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			HttpSession session = request.getSession();
			Utilisateur utilisateur = null;
			boolean isConneced = true;

			if (session != null) {
				utilisateur = (Utilisateur) session.getAttribute("utilisateur");
				if (utilisateur == null) {
					isConneced = false;
					session.setAttribute("connected", false);
					System.out.println("déconnecté");

				} else {
					UtilisateurManager um = UtilisateurManager.getInstance();
					utilisateur = um.selectUserById(utilisateur);
					isConneced = true;
					session.setAttribute("connected", true);
				}
			} else {
				isConneced = true;
				request.setAttribute("connected", false);
			}
			if (isConneced == false) {
				response.sendRedirect(request.getContextPath() + "/accueil");
				System.out.println("Non Connecté page 'Membre' non accessible");
			} else {
				// ************************************************************

				int userId = Integer.valueOf(request.getParameter("no_utilisateur"));
				UtilisateurManager um = UtilisateurManager.getInstance();
				utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(userId);
				utilisateur = um.selectUserById(utilisateur);
				request.setAttribute("utilisateur", utilisateur);
				System.out.println("coucou");
				request.getRequestDispatcher("/WEB-INF/jsp/membre.jsp").forward(request, response);

			}
		} catch (Exception e) {
			request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
