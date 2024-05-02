package shoppingcart.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingcart.util.CredentialUtils;


/*All of these URLs /admin/ would trigger the authenticationFilter to execute its logic before the request 
 * reaches the servlet or resource associated with the requested URL. This allows you to enforce 
 * authentication or apply other security measures specifically to URLs under the /admin/ path.
 * for our project here, we only have one filter-AuthenticationFilter
 * This mechanism allows multiple filters to collaborate in processing a request before it reaches 
 * the servlet, and then again in processing the response after it's generated by the servlet. 
 * It enables modular and reusable filter components for implementing cross-cutting concerns 
 * such as authentication, logging, and data transformation.*/



/*In the authentication process, we are using filters that allow us to perform some operations
 *  before a request goes to the controller or before a response is sent to a client.*/
public class AuthenticationFilter implements Filter {

	public static final String USER_SESSION_KEY = "userInfo";

	public AuthenticationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	
	//do filtering, if it is authenciated, let is pass
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		
		HttpServletRequest req = (HttpServletRequest)request;
		//We just want to check if user has an session and user info in the session.
		// Make sure we do not create a session by getSession(false)
		HttpSession session = req.getSession(false);
		boolean isAuthenticated = session != null && session.getAttribute(USER_SESSION_KEY) != null;
		
		if(isAuthenticated) {
			System.out.println("User is authenticated, let it proceed");
			
			//check authorization after authentication:
			//only 3 roles in this app, if it is administrator, if success log in can browser any pages, home, user or product
			//if url start with admin/home, any user with any role successfully logged in can see it
			
			//if url start with admin/user/*, can only be accessed by role: Administrator
			//if url start with admin/product/*, can only be accessed by role: Content Manager
			
			
			//return eg. /shoppingcart/admin/product/productList
			String requestPath = req.getRequestURI();
			//System.out.println(requestPath);
			
			/*If your web application is deployed at the root of the server (e.g., http://localhost:8080/), the context path will be an empty string ("").
If your web application is deployed under a specific context path (e.g., http://localhost:8080/myapp/), the context path will be /myapp.*/
			String contextPath = req.getContextPath();
			
			if(requestPath.startsWith(contextPath + "/admin/user/")) {
				boolean allowed = CredentialUtils.checkRole(req, "Admin") || CredentialUtils.checkRole(req, "User Administrator");
				if(!allowed) {
					throw new RuntimeException("Not an user administrator");
				}
			}

			if(requestPath.startsWith(contextPath + "/admin/product/")) {
				boolean allowed = CredentialUtils.checkRole(req, "Admin") || CredentialUtils.checkRole(req, "Content Manager");
				if(!allowed) {
					throw new RuntimeException("Not an content manager");
				}
			}
			
			//this doFilter method is to figure out which filter to invoke next( passing control to the next filter in the chain ), or if it's 
			//end of chain, which servlet's service()method
			
			//1.
			chain.doFilter(request, response);
			
			
			
			//2.more code,Additional tasks after calling service method and before sending response
			//For example, logging or setting response headers
			//
			
			//3.if check role capability in filter, we can do it here, get part of url and get user role from session,
			//give access to servlet method, get or post(see, edit, add, delete), using if else, but we can also check role 
			//capability in each serlvets.
			
			
		} else {
			//TODO do not hardcode shoppingcart app name   //   /shoppingcart/login,    getContextPath return /shoppingcart
			//If not successfully logged in, redirect to log in page
			((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	public void destroy() {
	}

}
