package shoppingcart.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingcart.dao.PostgresUserDao;
import shoppingcart.service.UserDao;

/**
 * Servlet implementation class DeleteUserServlet
 */
public class DeleteUserServlet extends HttpServlet {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("deleteUser.jsp");
		rd.forward(request, response);
		
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 
When you click the "Edit User" button, typically the user ID associated with the user you want to edit needs to be sent to the server. 
This can be achieved through various mechanisms such as URL parameters, form submission, or AJAX requests.
Let's consider an example where you have a button or link in your user list to edit a specific user. When this button or link is clicked,
 it triggers a request to the server, and the user ID is included as a parameter in the URL.
		 * 
		 * */
		long userId = Long.parseLong(request.getParameter("id"));
		UserDao userDao = new PostgresUserDao();
		int rows_deleted = userDao.delete(userId);
		
//        // Delete the user if found
//        if (rows_deleted > 0) {    
//            request.setAttribute("message", "User deleted successfully!");
//        } else {
//            request.setAttribute("message", "User not found or not deleted.");
//        }
		//to do: message from one servlet to another servlet 

		HttpSession session = request.getSession();
		session.setAttribute("userDeletionMessage", "User has been deleted successfully.");
		response.sendRedirect("userList");
		
	}

}
