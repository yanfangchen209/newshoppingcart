package shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import shoppingcart.service.ItemNotFoundException;
import shoppingcart.service.ShoppingCart;

/**
 * Servlet implementation class UpdateQuantityServlet
 */
public class UpdateQuantityServlet extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		int newQuantity = Integer.parseInt(request.getParameter("quantity"));
		
		
		ShoppingCart cart = (ShoppingCart)request.getSession(true).getAttribute("shoppingCart");
		cart.setItemQuantity(id, newQuantity);
		
		/*for checked exception, we must catch or declare it, but for runtime exception, here don't have to catch, here 
		 * because i know it wont have error so i don't catch. When we want to catch, we can send error message as http response or 
		 * print error to log file using log4j, here 
		 * for learning project, i just print error in console
		*/
		/***
		try {
			cart.setItemQuantity(id, newQuantity);
		} catch(ItemNotFoundException exception) {
			
			
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			Map<String, String> result = new HashMap<>();
			result.put("message", "Item not found, this is unexpected.");
			result.put("sucess", "false");
			Gson gson = new Gson();
			String responseInJson = gson.toJson(result);
			out.print(responseInJson);
			out.flush();
			return;
		}
		**/
		
		request.getSession().setAttribute("shoppingCart", cart);
		
		//send data back to shoppingCart.js
		
		int totalCount = cart.getTotalItemsCount();
		double subtotal = cart.getSubtotal();
	
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		DecimalFormat df = new DecimalFormat("#.00");
		map.put("subtotal", df.format(subtotal));
		
		double itemSubtotal = cart.getItemSubTotal(id);
		map.put("itemSubtotal", df.format(itemSubtotal));
		
		Gson gson = new Gson();
		String responseInJson = gson.toJson(map);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(responseInJson);
		out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
