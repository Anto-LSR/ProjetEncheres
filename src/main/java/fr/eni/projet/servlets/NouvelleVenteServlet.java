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
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bll.InputError;
import fr.eni.projet.bll.RetraitManager;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Retrait;

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
			LocalDate today = LocalDate.now();
			CategorieManager cm = CategorieManager.getInstance();
			List<Categorie> categories = cm.selectAllCategorie();
			request.setAttribute("categories", categories);
			request.setAttribute("today", today);

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
			newArticle.setPrixInitial(Integer.valueOf(prixInitial));
			newArticle.setPrixVente(Integer.valueOf(prixInitial));
			Categorie cat = new Categorie();
			cat.setLibelle(categorie);
			newArticle.setUtilisateurVendeur(utilisateur);
			CategorieManager cm = CategorieManager.getInstance();
			cat = cm.selectByLibelle(cat);
			newArticle.setCategorie(cat);

			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codepostal");
			String ville = request.getParameter("ville");

			Retrait retrait = new Retrait();

			retrait.setCodePostal(codePostal);
			retrait.setRue(rue);
			retrait.setVille(ville);
			RetraitManager rm = RetraitManager.getInstance();

			Enchere enchere = new Enchere();
			enchere.setDateEnchere(dateDebut);
			enchere.setMontantEnchere(Integer.valueOf(prixInitial));
			enchere.setUtilisateur(utilisateur);
			EnchereManager em = EnchereManager.getInstance();

			ArticleVenduManager av = ArticleVenduManager.getInstance();
			List<InputError> errors = av.verifDate(newArticle);

			if (!errors.isEmpty()) {
				for (InputError err : errors) {
					if (err.getNom().equals("debutAfterFin")) {
						request.setAttribute("debutAfterFin", err.getDescription());
					}
					if (err.getNom().equals("debutBeforeToday")) {
						request.setAttribute("debutBeforeToday", err.getDescription());
					}
					if (err.getNom().equals("finBeforeToday")) {
						request.setAttribute("finBeforeToday", err.getDescription());
					}
					if (err.getNom().equals("nomNull")) {
						request.setAttribute("nomNull", err.getDescription());
					}
					if (err.getNom().equals("descNull")) {
						request.setAttribute("descNull", err.getDescription());
					}
					if (err.getNom().equals("prixNull")) {
						request.setAttribute("prixNull", err.getDescription());
					}
					if (err.getNom().equals("dateDebutNull")) {
						request.setAttribute("dateDebutNull", err.getDescription());
					}
					if (err.getNom().equals("dateFinNull")) {
						request.setAttribute("dateFinNull", err.getDescription());
					}
				}
				error = true;
			}
			if (cat.getNoCategorie() < 1) {
				System.err.println("Erreur sur la categorie");
				error = true;
			}

			if (!error) {
				int idArticle = av.insertArticle(newArticle);
				newArticle.setNoArticle(idArticle);
				retrait.setArticleVendu(newArticle);
				rm.insertRetrait(retrait);

				enchere.setArticlevendu(newArticle);
				em.insertEnchere(enchere);

				request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/jsp/nouvelleVente.jsp").forward(request, response);

			}
		}

	}

}
