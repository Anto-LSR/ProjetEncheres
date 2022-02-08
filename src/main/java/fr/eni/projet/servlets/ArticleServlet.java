package fr.eni.projet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.projet.bll.ArticleVenduManager;
import fr.eni.projet.bo.ArticleVendu;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		boolean isConnected = true; 
		
		if (session != null) {
			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
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
		
		//********************************************************
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));
		
		ArticleVenduManager am = ArticleVenduManager.getInstance();
		ArticleVendu article = am.selectByDetails(noArticle);
		request.setAttribute("article", article);
		request.getRequestDispatcher("/WEB-INF/jsp/article.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
