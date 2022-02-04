package fr.eni.projet.servlets;

import java.io.IOException;

import java.time.LocalDate;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.CategorieManager;
import fr.eni.projet.bll.InputError;
import fr.eni.projet.bo.Categorie;

/**
 * Servlet implementation class NouvelleVenteServlet
 */
@WebServlet("/nouvellevente")
public class NouvelleVenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NouvelleVenteServlet() {
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
			System.out.println("Non Connecté page 'Nouvelle Vente' Inaccesible ");
		} else {

			CategorieManager cm = CategorieManager.getInstance();
			List<Categorie> categories = cm.selectAllCategorie();
			request.setAttribute("categories", categories);

			request.getRequestDispatcher("/WEB-INF/jsp/nouvelleVente.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		if (utilisateur == null) {
			System.err.println("Erreur de session ");
		} else {
			boolean error = false;
			String nomArticle = request.getParameter("article");
			String description = request.getParameter("description");
			String dateDebutEnchere = request.getParameter("debEnchere");
			String dateFinEnchere = request.getParameter("finEnchere");
			String prixInitial = request.getParameter("prixInitial");
			String prixVente = prixInitial;
			String categorie = request.getParameter("categorie");

			ArticleVendu newArticle = new ArticleVendu();
			newArticle.setNomArticle(nomArticle);
			newArticle.setDescription(description);
			LocalDate dateDebut = LocalDate.parse(dateDebutEnchere);
			newArticle.setDateDebutEncheres(dateDebut);
			LocalDate dateFin = LocalDate.parse(dateFinEnchere);
			newArticle.setDateFinEncheres(dateFin);
			int prix = Integer.parseInt(prixInitial);
			newArticle.setPrixInitial(prix);
			Categorie cat = new Categorie();
			cat.setLibelle(categorie);
			newArticle.setUtilisateurVendeur(utilisateur);
			CategorieManager cm = CategorieManager.getInstance();
			cat = cm.selectByLibelle(cat);
			newArticle.setCategorie(cat);
			ArticleVenduManager av = ArticleVenduManager.getInstance();
			List<InputError> errors = av.verifDate(newArticle);
			System.out.println(dateDebut + " - - - " + dateFin);
			if (!errors.isEmpty()) {
				for (InputError err : errors) {
					if (err.getNom().equals("debutAfterFin")) {
						request.setAttribute("debutAfterFin", err.getDescription());
						System.out.println("1");
					}
					if (err.getNom().equals("debutBeforeToday")) {
						request.setAttribute("debutBeforeToday", err.getDescription());
						System.err.println("2");
					}
					if (err.getNom().equals("debutAfterFin")) {
						request.setAttribute("debutAfterFin", err.getDescription());
						System.err.println("3");
					}
				}
				error = true;
			}
			if (cat.getNoCategorie() < 1) {
				System.err.println("Erreur sur la categorie");
				error = true;
			} 
			
			if(!error) {
				int idArticle = av.insertArticle(newArticle);
				request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/WEB-INF/jsp/nouvelleVente.jsp").forward(request, response);

			}
		}
	
	}

}
