package fr.eni.projet.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bll.EnchereManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;

/**
 * Servlet implementation class ArticleServlets
 */
@WebServlet("/article")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleServlet() {
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
		Utilisateur utilisateur = null;

		boolean isConnected = true;

		if (session != null) {
			utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			if (utilisateur == null) {
				isConnected = false;
				session.setAttribute("connected", false);
				System.out.println("déconnecté");
			} else {
				isConnected = true;
				session.setAttribute("connected", true);
			}
		} else {
			isConnected = true;
			request.setAttribute("connected", false);
		}

		// ********************************************************
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));

		ArticleVenduManager am = ArticleVenduManager.getInstance();
		ArticleVendu article = am.selectByDetails(noArticle);
		request.setAttribute("article", article);

		System.out.println(utilisateur.getCredit());
		request.getRequestDispatcher("/WEB-INF/jsp/article.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

		int proposition = Integer.valueOf(request.getParameter("proposition"));
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));
		ArticleVendu articleParam = new ArticleVendu();
		articleParam.setNoArticle(noArticle);

		Enchere enchereParam = new Enchere();
		enchereParam.setArticlevendu(articleParam);
		enchereParam.setMontantEnchere(proposition);
		enchereParam.setUtilisateur(utilisateur);
		enchereParam.setDateEnchere(LocalDate.now());

		EnchereManager em = EnchereManager.getInstance();
		Enchere enchereMax = em.selectByNumArticle(enchereParam);

		System.out.println("enchere max " + enchereMax.getMontantEnchere());
		System.out.println("n° utilisateur enchereur aprecedent " + enchereMax.getUtilisateur().getNoUtilisateur());
		System.out.println("enchere proposée " + enchereParam.getMontantEnchere());
		System.out.println("n° utilisateur encherisseur précedent " + enchereParam.getUtilisateur().getNoUtilisateur());

		if (enchereMax.getMontantEnchere() > enchereParam.getMontantEnchere()) {
			request.setAttribute("fundError", "Vous n'avez pas assez de crédits pour enchérir sur cet article");
		} else if (enchereMax.getUtilisateur().getNoUtilisateur() == enchereParam.getUtilisateur().getNoUtilisateur()) {
			request.setAttribute("sameUserError", "Aucune autre enchère n'a été faite depuis la votre, impossible d'enchérir");
			//TODO GERER ERREUR SAME USER
		
		} else {

			em.insertEnchere(enchereParam);
			request.setAttribute("success", "Votre enchère a bien étée prise en compte");

			System.out.println("bravo");
		}
		ArticleVenduManager am = ArticleVenduManager.getInstance();
		ArticleVendu article = am.selectByDetails(noArticle);
		request.setAttribute("article", article);
		request.getRequestDispatcher("/WEB-INF/jsp/article.jsp").forward(request, response);

	}

}
