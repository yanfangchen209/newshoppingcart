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

public class AuthenticationFilter implements Filter {

	public static final String USER_SESSION_KEY = "userInfo";

	public AuthenticationFilter() {
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		//We just want to check if user has an session and user info in the session.
		// Make sure we do not create a session by getSession(false)
		HttpSession session = req.getSession(false);
		boolean isAuthenticated = false;
		if(session != null) {
			UserInfo userInfo = (UserInfo)session.getAttribute(USER_SESSION_KEY);
			if(userInfo != null) {
				isAuthenticated = true;
			}
		}
		
		if(isAuthenticated) {
			System.out.println("User is authenticated, let it proceed");
			chain.doFilter(request, response);
		} else {
			//TODO do not hardcode shoppingcart app name   //   /shoppingcart/login,    getContextPath return /shoppingcart
			((HttpServletResponse)response).sendRedirect(req.getContextPath() + "/login");
		}
	}

	@Override
	public void destroy() {
	}

}
