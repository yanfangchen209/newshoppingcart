package shoppingcart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetProfileServlet
 */
public class SetProfileServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String hobby = request.getParameter("hobby");
		
		PrintWriter out = response.getWriter();
		out.println("Success! Your profile is saved as follows:");
		out.println("name: " + name );
		out.println("email: " + email);
		out.println("hobby: " + hobby);
	}

}
