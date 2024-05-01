package shoppingcart.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingcart.dao.PostgresUserDao;
import shoppingcart.entity.User;
import shoppingcart.entity.UserCredential;
import shoppingcart.entity.UserInfo;
import shoppingcart.filter.AuthenticationFilter;
import shoppingcart.service.UserDao;
import shoppingcart.util.CredentialUtils;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	
      
    public LoginServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	    rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String enteredUsername = request.getParameter("username");
		String enteredPassword = request.getParameter("password");
		
		//get salt and hashedpassword from database according to the username
		UserDao userDao = new PostgresUserDao();
		UserCredential credential = userDao.findUserCredential(enteredUsername);
		
		if(credential == null){
			forwardToLoginWithError(request, response);
		}else {
			
			String salt = credential.getSalt();
			String hashedPasswordDB = credential.getPasswordHash();
			
			
			//check if entered password right using java built in method
			// Compare the stored hashed password with the hashed entered password
			String enteredHashPassword = CredentialUtils.hashPassword(enteredPassword, salt);
		
	        boolean passwordMatch = hashedPasswordDB.equals(enteredHashPassword);
			
			
			if(passwordMatch){
				
				/*Returns the current session associated with this request,
			     or if the request does not have a session, creates one.same with request.getSession(true)*/
				//login successfully, create a session
				/*
	The method request.getSession(true) and request.getSession() are functionally equivalent. Both methods are used to retrieve the 
	current session associated with the request. If a session does not exist, both methods will create a new session.*/
				/* In many web applications, a session is created for a user after successful authentication (login). 
				 * Once the user logs in, the application establishes a session and assigns a session ID to the user. 
				 * This session ID is typically stored in a session cookie on the client side.*/
				
				User user = userDao.find(credential.getId());
    			List<String> roleNames = userDao.getRoleName(credential.getId());
    			
    			//given user id, find all role names and add in session attribute USER_SESSION_KEY
				UserInfo userInfo = new UserInfo(user, roleNames);
				HttpSession session  = request.getSession(true);
				

    			
				session.setAttribute(AuthenticationFilter.USER_SESSION_KEY, userInfo);
				//log in successfully, show the content to user
				response.sendRedirect("admin/home");
				
				
				/*Returns the object bound with the specified name in this session, or <code>null</code> if no object is bound under the name.
				Integer count = (Integer)session.getAttribute("count");
				if(count == null) {
					count = 0;
				}
				count = count + 1;
				request.getSession().setAttribute("count", count);
				request.getSession().setAttribute("user", username);
				
				response.setHeader("Content-Type", "text/html");
				response.getWriter().print("<body>");
				response.getWriter().print("<p>hello " + username +  " you visited " + count + " times" + "</p>");
				response.getWriter().print("</body>");
				*/
		
			}else {
				forwardToLoginWithError(request, response);
				
			}
		
		}
	}
	
	private void forwardToLoginWithError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//forward jsp to display login page again
		request.setAttribute("message", "Wrong username or password");
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
	    rd.forward(request, response);
	}



}
