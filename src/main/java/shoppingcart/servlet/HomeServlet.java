package shoppingcart.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//welcome page is the page user want to see, name not appropriate
/**
 * Servlet implementation class WelcomeServlet
 */
public class HomeServlet extends HttpServlet {

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String nameString = request.getParameter("name");
		
		//if a session already exists return it, otherwise don't create a session and just return null
		/*
		 * This pattern is often used to check if a user has an existing session before creating a new one. It's useful when you want 
		 * to perform certain actions or retrieve attributes from the session only if the user is already logged in, and you don't want
		 *  to create a new session unnecessarily.*/
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("userAdminHome.jsp");
		requestDispatcher.forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
