package shoppingcart.servlet;

import java.io.IOException;

import javax.naming.ldap.Rdn;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingcart.service.ShoppingCart;

public class PlaceOrderServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//do data validation for customer address info here
		//save order and shipping address info to backend
		
		
		//set shopping cart attribute to null
		HttpSession session = request.getSession(false);
		session.setAttribute("shoppingCart", new ShoppingCart());
		

		
		
		//direct to order confirmation jsp
		RequestDispatcher rd = request.getRequestDispatcher("orderConfirm.jsp");
		rd.forward(request, response);
		
	}

}
