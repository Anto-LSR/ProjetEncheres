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
import fr.eni.projet.bll.UtilisateurManager;
import fr.eni.projet.bo.ArticleVendu;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.jdbcImplement.ArticleVenduImpl;
import fr.eni.projet.helpers.Tools;

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

		try {

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
					UtilisateurManager um = UtilisateurManager.getInstance();
					utilisateur = um.selectUserById(utilisateur);
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
			int min = article.getPrixVente() + 1;
			request.setAttribute("min", min);
			LocalDate today = LocalDate.now();
			LocalDate dateFin = article.getDateFinEncheres();
			String Fin = Tools.convertFormatDate(dateFin);

			Enchere enchere = new Enchere();
			enchere.setArticlevendu(article);
			EnchereManager em = EnchereManager.getInstance();
			enchere = em.selectByNumArticle(enchere);
			int noUtilisateur = enchere.getUtilisateur().getNoUtilisateur();
			UtilisateurManager um = UtilisateurManager.getInstance();
			Utilisateur winner = new Utilisateur();
			winner.setNoUtilisateur(noUtilisateur);
			winner = um.selectUserById(winner);
			String NomWinner = winner.getNom();

			if (dateFin.isBefore(today)) {
				request.setAttribute("NomWinner", NomWinner);

			}
			Enchere lastUserEnchere = new Enchere();
			lastUserEnchere.setArticlevendu(article);
			lastUserEnchere.setUtilisateur(utilisateur);

			lastUserEnchere = em.selectMaxByUser(lastUserEnchere);

			if (lastUserEnchere == null) {
				lastUserEnchere = new Enchere();
				lastUserEnchere.setMontantEnchere(0);

			}
			if ((lastUserEnchere.getMontantEnchere() + utilisateur.getCredit()) < article.getPrixVente()) {
				request.setAttribute("fundError", "Vous ne disposez pas d'assez de crédits");
				System.out.println("pas assez de crédit");
			}
			if (article.getDateDebutEncheres().isAfter(LocalDate.now())) {
				request.setAttribute("notBegin", "l'enchère n'a pas encore débuté");
			}

			System.out.println(utilisateur.getCredit());
			request.getRequestDispatcher("/WEB-INF/jsp/article.jsp").forward(request, response);

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
		HttpSession session = request.getSession();

		// MISE A JOUR DE L'UTILISATEUR DE LA SESSION
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		UtilisateurManager um = UtilisateurManager.getInstance();
		utilisateur = um.selectUserById(utilisateur);

		// RECUPERATION DES PARAMETRES DE LA NOUVELLE ENCHERE
		int proposition = Integer.valueOf(request.getParameter("proposition"));
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));

		ArticleVendu articleConsulte = new ArticleVendu();
		articleConsulte.setNoArticle(noArticle);
		articleConsulte.setUtilisateurVendeur(new ArticleVenduImpl().selectById(noArticle).getUtilisateurVendeur());

		Enchere nouvelleEnchere = new Enchere();
		nouvelleEnchere.setArticlevendu(articleConsulte);
		nouvelleEnchere.setMontantEnchere(proposition);
		nouvelleEnchere.setUtilisateur(utilisateur);
		nouvelleEnchere.setDateEnchere(LocalDate.now());

		// RECUPERATION DE L'ENCHERE PRECEDENTE
		EnchereManager em = EnchereManager.getInstance();
		Enchere lastEnchere = em.selectByNumArticle(nouvelleEnchere);

		if (lastEnchere.getMontantEnchere() > nouvelleEnchere.getMontantEnchere()) {
			request.setAttribute("underError", "Impossible de sous enchérir");
			request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);

		} else {
			if (lastEnchere.getUtilisateur().getNoUtilisateur() == nouvelleEnchere.getUtilisateur()
					.getNoUtilisateur()) {
				request.setAttribute("sameUserError",
						"Aucune autre enchère n'a été faite depuis la votre, impossible d'enchérir");
				// TODO GERER ERREUR SAME USER
			}

			// *********************************************************************************
			Enchere lastUserEnchere = new Enchere();
			lastUserEnchere.setArticlevendu(articleConsulte);
			lastUserEnchere.setUtilisateur(utilisateur);

			lastUserEnchere = em.selectMaxByUser(lastUserEnchere);

			if (lastUserEnchere == null) {
				lastUserEnchere = new Enchere();
				lastUserEnchere.setMontantEnchere(0);

			}
			if ((lastUserEnchere.getMontantEnchere() + utilisateur.getCredit()) < nouvelleEnchere.getMontantEnchere()) {
				request.setAttribute("fundError", "Vous ne disposez pas d'assez de crédits");
				System.out.println("pas assez de crédit");
			} else {
				em.insertEnchere(nouvelleEnchere); // INSERTION NOUVELLE ENCHERE
				// DEBIT DU NOUVEL ENCHERISSEUR (utilisateur)
				System.out.println("Ancien credits de l'utilisateur: " + utilisateur.getCredit());
				System.out.println("Last Montant Enchere : " + lastUserEnchere.getMontantEnchere());
				System.out.println("Montant de l'enchère : " + nouvelleEnchere.getMontantEnchere());

				int nouveauCreditEncherisseur = (utilisateur.getCredit() + lastUserEnchere.getMontantEnchere())
						- nouvelleEnchere.getMontantEnchere();
				System.out.println("Nouveau crédit de l'utilisateur" + nouveauCreditEncherisseur);

				um.updateCredit(nouveauCreditEncherisseur, utilisateur.getNoUtilisateur());

				// VERIFICATION DU PRECEDENT ENCHERISSEUR
				if (lastEnchere.getUtilisateur().getNoUtilisateur() == articleConsulte.getUtilisateurVendeur()
						.getNoUtilisateur()) {
					System.out.println("Pas de remboursement possible au vendeur original");
				} else {

					// RENSEIGNEMENT SUR LE PRECEDENT ENCHERISSEUR
					Utilisateur ancienEncherisseur = um.selectUserById(lastEnchere.getUtilisateur());
					System.out.println("Ancien credit de l'ancien encherisseur : " + ancienEncherisseur.getCredit());
					System.out.println("Montant de l'ancienne enchère : " + lastEnchere.getMontantEnchere());
					int nouveauCreditAncienEncherisseur = ancienEncherisseur.getCredit()
							+ lastEnchere.getMontantEnchere();
					System.out.println("Nouveau crédit de l'ancien enchérisseur : " + nouveauCreditAncienEncherisseur);
					request.setAttribute("success", "Votre enchère a bien étée prise en compte");
				}

				ArticleVenduManager am = ArticleVenduManager.getInstance();
				ArticleVendu article = am.selectByDetails(noArticle);
				request.setAttribute("article", article);
				request.getRequestDispatcher("/WEB-INF/jsp/article.jsp").forward(request, response);

			}
		}
	}
}
