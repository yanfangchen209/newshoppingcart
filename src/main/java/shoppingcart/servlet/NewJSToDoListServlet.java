package shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


public class NewJSToDoListServlet extends HttpServlet {

   /*get, display todolist, if no session,create session to store data(no db here), if todos values is null, initialize with some value,
    * get todo list and convert to json data using third party library GSON which added to build file, send to jsp file so that js can render the page
    */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("todos") == null) {
			session.setAttribute("todos", new ArrayList<String>(Arrays.asList("swimming", "cleaning")));
		}
		List<String> todolist = (List<String>)session.getAttribute("todos");
		String result = new Gson().toJson(todolist);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter outPrintWriter = response.getWriter();
		outPrintWriter.print(result);
	} 
	
	@Override
	//add: get the taskname and add, return true if adding successfully, otherwise false
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String taskName = request.getParameter("toDoName");
		HttpSession session = request.getSession();
		boolean addResult = ((List<String>)session.getAttribute("todos")).add(taskName);
		//return true to jsp so that it continue to update(add) web pages
		
		String addResultJson = new Gson().toJson(addResult);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter outWriter = response.getWriter();
		outWriter.print(addResultJson);
		
	} 
	
	//delete:delete an task, return true if delete successfully
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String taskName = request.getParameter("taskName");
		HttpSession session = request.getSession();
		boolean deleteResult = ((List<String>)session.getAttribute("todos")).remove(taskName);
		//return true to jsp so that it continue to update(delete) web pages
		String deleteResultJson = new Gson().toJson(deleteResult);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter outWriter = response.getWriter();
		outWriter.print(deleteResultJson);
		
		
	}
	
	

}
