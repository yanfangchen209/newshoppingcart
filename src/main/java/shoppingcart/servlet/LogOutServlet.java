package shoppingcart.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOutServlet
 */
public class LogOutServlet extends HttpServlet {

/*In many cases, it is advisable to invalidate or "kill" the session when a user logs out of a web application. 
 * This helps ensure the security of the user's session and prevents unauthorized access to sensitive information.
 * Invalidating the session allows the server to release any resources associated with the session, such as memory or database connections. */


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Invalidate the session
            session.invalidate();
        }
        // Redirect to the login page or any other page after logout
        response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
