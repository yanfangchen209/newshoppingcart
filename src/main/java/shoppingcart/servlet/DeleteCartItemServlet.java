package shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.NonWritableChannelException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import shoppingcart.service.ShoppingCart;


public class DeleteCartItemServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//delete item from backend
		ShoppingCart cart = (ShoppingCart)request.getSession().getAttribute("shoppingCart");
		Long id = Long.parseLong(request.getParameter("id"));
		cart.deleteItem(id);
		request.getSession().setAttribute("shoppingCart", cart);
		
		//send data back to update UI
		double subtotal = cart.getSubtotal();
		//format to two digit decimal
		DecimalFormat df = new DecimalFormat("#.00");
	
		int totalCount = cart.getTotalItemsCount();
		
		Map<String, Object> map = new HashMap<>();
		map.put("subtotal", df.format(subtotal));
		map.put("totalCount", totalCount);
		
		
		Gson gson = new Gson();
		
		//eg:{"subtotal":"123.45","totalCount":10}
		String responseData = gson.toJson(map);
		
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding(responseData);
		out.print(responseData);
		
		/*This line flushes the output stream, ensuring that any buffered data is immediately sent to the client. Flushing the stream 
		is necessary to ensure that the client receives the response promptly, especially if there is no further output to be sent.*/
		out.flush();

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
