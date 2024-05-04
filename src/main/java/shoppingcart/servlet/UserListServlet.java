package shoppingcart.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import shoppingcart.dao.PostgresUserDao;
import shoppingcart.entity.User;
import shoppingcart.service.UserDao;

/**
 * Servlet implementation class UserListServlet
 */
public class UserListServlet extends HttpServlet {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Call UserDao service to find all users.
		UserDao userService = UserDao.getInstance();
		List<User> userList = userService.findAll();
		  
		
		String creationMessage = (String)request.getSession().getAttribute("userCreationMessage");
		if(creationMessage != null) {
			request.setAttribute("creationMessage", creationMessage);
			
			/*
It depends on your specific use case and requirements. If you want to ensure that the message is displayed only once and not on subsequent
 requests, you may choose to remove the message from the request attributes after displaying it in the JSP.
 Remember that the request attributes are scoped to the current request, so they won't persist across multiple requests. Removing the attribute
  ensures that it won't be available in subsequent requests within the same session. Adjust this based on your specific use case and whether you
   want the message to persist or be a one-time notification.   example:     
   request.setAttribute("message", "Hello from ForwardServlet!");
        // Forward the request to the JSP
        request.getRequestDispatcher("/example.jsp").forward(request, response);
        // Remove the message from the request after forwarding
        request.removeAttribute("message");
        
        
 */
			request.getSession().removeAttribute("userCreationMessage");
		}
		
		String deletionMessage = (String)request.getSession().getAttribute("userDeletionMessage");
		if(deletionMessage != null) {
			request.setAttribute("deletionMessage", deletionMessage);
			request.getSession().removeAttribute("userDeletionMessage");
		}
		
		
		String editMessage = (String)request.getSession().getAttribute("userEditMessage");
		if(editMessage != null) {
			request.setAttribute("editMessage", editMessage);
			request.getSession().removeAttribute("userEditMessage");
		}
		
		

		
		//set request attributes so jsp page can access the users data.
		request.setAttribute("users", userList);
		RequestDispatcher rd = request.getRequestDispatcher("userAdmin.jsp");
		rd.forward(request, response);
		
	}
		
		
		

}
