package shoppingcart.servlet;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.service.ShoppingCart;


public class CheckOutServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart)request.getSession().getAttribute("shoppingCart");
		double subtotal = cart.getSubtotal();
		int totalCount = cart.getTotalItemsCount();
		
		DecimalFormat dfFormat = new DecimalFormat("#.##");
		String formattedSubtotal = dfFormat.format(subtotal);
		
		
		request.setAttribute("subtotal", formattedSubtotal);
		request.setAttribute("totalCount", totalCount);
		RequestDispatcher rd = request.getRequestDispatcher("checkOut.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
