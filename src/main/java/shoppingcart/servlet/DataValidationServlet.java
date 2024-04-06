package shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class DataValidationServlet
 */
public class DataValidationServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userinput = request.getParameter("taskName");
		HttpSession session = request.getSession();
		List<String> todos = (List<String>)session.getAttribute("todos");
		boolean duplicateOrNot = todos.contains(userinput);
		String result = new Gson().toJson(duplicateOrNot);
		PrintWriter outPrintWriter = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		outPrintWriter.print(result); 
	}


}
