package shoppingcart.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingcart.dao.PostgresRoleDao;
import shoppingcart.dao.PostgresUserDao;
import shoppingcart.entity.Role;
import shoppingcart.entity.User;
import shoppingcart.service.RoleDao;
import shoppingcart.service.UserDao;

/**
 * Servlet implementation class EditUserServlet
 */
public class EditUserServlet extends HttpServlet {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get user name, email and role id from the given id , then set request attribute so that jsp can set them as default value
		long userid = Long.parseLong(request.getParameter("id"));
		UserDao userDao = new PostgresUserDao();
		User user = userDao.find(userid);
		request.setAttribute("user", user);
		
		//get all roles
		RoleDao roleDao = new PostgresRoleDao();
		List<Role> roleList = roleDao.findAll();
		request.setAttribute("roles", roleList);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("editUser.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("userId"));
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		int roleId = Integer.parseInt(request.getParameter("roleId"));
		User user = new User(id, userName, email, roleId);
		
		UserDao userDao = new PostgresUserDao();
		userDao.update(user);
		
		//transfer message to userlistServlet
		HttpSession session  = request.getSession();
		session.setAttribute("userEditMessage", "user information was updated successfully");
		
		response.sendRedirect("userList");
		
	}

}
