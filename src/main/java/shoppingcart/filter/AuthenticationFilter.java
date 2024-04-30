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

import shoppingcart.entity.UserInfo;


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
			//this doFilter method is to figure out which filter to invoke next( passing control to the next filter in the chain ), or if it's 
			//end of chain, which servlet's service()method
			chain.doFilter(request, response);
			
			//more code,Additional tasks after calling service method and before sending response
			//For example, logging or setting response headers
			//
		} else {
			//TODO do not hardcode shoppingcart app name   //   /shoppingcart/login,    getContextPath return /shoppingcart
			((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	public void destroy() {
	}

}
