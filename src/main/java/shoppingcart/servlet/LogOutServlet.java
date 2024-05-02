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
/*Sessions have a configurable timeout period. If the user does not interact with the web application for a certain amount of time (e.g., 30 minutes),
 *  the session is considered expired and removed from the server.*/
	/*3 ways session out: 1)invalidate in code 2)app crash or undeployed 3)server set time out expires*/

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
