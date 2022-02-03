package fr.eni.projet.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Deconnection
 */
@WebServlet("/deconnection")
public class DeconnectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeconnectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		boolean isConnected = true;
		
		if (session == null) {
			isConnected = false;
			session.setAttribute("connected" , false);
		} else {
			isConnected = true; 
			session.setAttribute("connected", true);
		}
		if (isConnected == false ) {
			request.setAttribute("connected", false);
			//request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);			
			response.sendRedirect(request.getContextPath()+"/accueil");
		} else {
			session.invalidate();
			request.setAttribute("connected", false);
			//request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/accueil");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
