package shoppingcart.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.dao.PostgresRoleDao;
import shoppingcart.dao.PostgresUserDao;
import shoppingcart.entity.Role;
import shoppingcart.entity.User;
import shoppingcart.service.RoleDao;
import shoppingcart.service.UserDao;
import shoppingcart.util.CredentialUtils;

/**
 * Servlet implementation class AddUserServlet
 */
public class AddUserServlet extends HttpServlet {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*before forward the request to jsp, we got the rolelist and set request attribute so that jsp can access later, jsp will use it to 
		in select box (create drop down list), after user input username, email and choose rolename, username, email and roleid will be send 
		to dopost method of adduser servlet
		**/
		CredentialUtils.checkRole(request, "Administrator");
		
		RoleDao roleDao = new PostgresRoleDao();
		List<Role> roleList = roleDao.findAll();
		request.setAttribute("roles", roleList);
		
		RequestDispatcher rd = request.getRequestDispatcher("addUser.jsp");
		rd.forward(request, response);
  	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	    String userName = request.getParameter("userName");
	    String password = request.getParameter("password");
	    String email = request.getParameter("email");
	    int roleId = Integer.parseInt(request.getParameter("roleId"));
	  
	 
		UserDao userDao = new PostgresUserDao();
		User user = new User(userName, email, roleId);
		//add method will store the new user to database
		userDao.add(user, password);
		
		/*
		 * When you use response.sendRedirect, it results in a new request being created, and the request attributes set in the original request
		 *  are not directly accessible in the redirected request. In contrast, the RequestDispatcher.forward method works within the same request, 
		 *  so request attributes are accessible during the forward.
In the scenario where you want to set an attribute in one servlet and access it in another servlet after a redirect, you might need to use an
 alternative approach, such as storing the attribute in session scope or passing it as a query parameter in the URL.
		 * 
		 * 
		 * */
		//method 1：when one servelt redirect to another servlet, sending a message:passing it as a query parameter in the URL
		//forward to jsp to display the products list.
	    //response.sendRedirect("userList?message" + URLEncoder.encode("User has been added successfully."));
		
		//method 2：when one servelt redirect to another servlet, sending a message: storing the attribute in session scope 
		request.getSession().setAttribute("userCreationMessage", "User has been added successfully.");
		
		response.sendRedirect("userList");
	 
	}

}
