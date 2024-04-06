package shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.google.gson.Gson;

/**
 * Servlet implementation class ToDoListServlet
 */
public class ToDoListServlet extends HttpServlet {
	
	//private static List<String> todolist = new ArrayList<>(Arrays.asList("cleaning kitchen", "doing homework", "swimming"));
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if there is session, return, otherwise create a session
		HttpSession session = request.getSession();
		//use static data to hard code for testing display purpose
		if(session.getAttribute("todos") == null) {
			session.setAttribute("todos",  new ArrayList<>(Arrays.asList("cleaning kitchen", "doing homework", "swimming")));
		}
		
		//don't need following line, jsp can get session attribute just like accessing request attribute 
		//request.setAttribute("todos", session.getAttribute("todos"));
		RequestDispatcher rd = request.getRequestDispatcher("todolist.jsp");
		rd.forward(request, response); 
		 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String toDoNameString = request.getParameter("toDoName");
		HttpSession session = request.getSession();
		List<String> todolist = (List<String>)session.getAttribute("todos");
		todolist.add(toDoNameString);
		
		String res = new Gson().toJson(todolist.size());
		PrintWriter out = response.getWriter();
		/*if the attribute might be null, you should perform appropriate null checks and type checking before casting to avoid runtime exceptions.
		 * List<String> res = ((List<String>)session.getAttribute("todos");
		 * PrintWriter out = response.getWriter();
		 * response.setContentType("text/html");
		 * response.setCharacterEncoding("UTF-8");
		 * out.println("<html>");
		 * out.print("<p>res</p>");
		 * out.println("</html>");
		 * out.flush(); 
		 */
		    
		
		
		//response.setContentType("application/xml");
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(res);
		out.flush(); 
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String toDeleteName = request.getParameter("toDeleteName");
		HttpSession session = request.getSession();
		((List<String>)session.getAttribute("todos")).remove(toDeleteName);
		
		String result = new Gson().toJson(true);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8"); 
		out.print(result);
		out.flush(); 
		 
	}
	

}
