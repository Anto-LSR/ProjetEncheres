package fr.eni.projet.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.CategorieManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class AccueilServlet
 */
@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccueilServlet() {
		super();
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
				System.out.println("deconnecté");
			} else {
				isConnected = true;
				session.setAttribute("connected", true);
			}

		} else {
			isConnected = false;
			request.setAttribute("connected", false);
		}
		CategorieManager cm = CategorieManager.getInstance();
		List<Categorie> categories = cm.selectAllCategorie();
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = null;
		boolean isConnected = true;

		if (session != null) {
			utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			if (utilisateur == null) {
				isConnected = false;
				session.setAttribute("connected", false);
				System.out.println("deconnecté");
				request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
			} else {
				isConnected = true;
				session.setAttribute("connected", true);
				// ********************************************************
				String categorie = request.getParameter("categories");
				String recherche = request.getParameter("recherche");
				String choice = request.getParameter("choice");
				String ventesEnCours = request.getParameter("ventesencours");
				String ventesNonDebutees = request.getParameter("ventesnondebutees");
				String ventesTerminees = request.getParameter("ventesTerminees");
				String encheresOuvertes = request.getParameter("enchouvertes");
				String encheresEnCours = request.getParameter("enchencours");
				String encheresRemportees = request.getParameter("enchremportees");
				ArticleVenduManager avm = ArticleVenduManager.getInstance();
				
				
				List<ArticleVendu>articlesRecherche = avm.selectByFiltres(categorie, recherche, choice, ventesEnCours, ventesNonDebutees, ventesTerminees, encheresOuvertes, encheresEnCours, encheresRemportees, utilisateur);
				CategorieManager cm = CategorieManager.getInstance();
				List<Categorie> categories = cm.selectAllCategorie();
				request.setAttribute("categories", categories);
				request.setAttribute("liste", articlesRecherche);
				
				request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
			}

		} else {
			isConnected = false;
			request.setAttribute("connected", false);
			request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
		}


		
		

	}

}
