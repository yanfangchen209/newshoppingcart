package shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import shoppingcart.dao.PostgresProductDao;
import shoppingcart.entity.Product;
import shoppingcart.entity.ShoppingCartItem;
import shoppingcart.service.ProductDao;
import shoppingcart.service.ProductService;
import shoppingcart.service.ProductServiceFactory;
import shoppingcart.service.ShoppingCart;


public class AddToCartServlet extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart)request.getSession(true).getAttribute("shoppingCart");
		if(cart == null) {
			cart = new ShoppingCart();
		}
		
		//first data validation??
		//get parameter from shop.js: id, quantity
		Long id = Long.parseLong(request.getParameter("id"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//get product details according to id
		ProductService productService = ProductServiceFactory.createProductServiceInstance();
		Product product = productService.findById(id);
		
		//create a shoppingcart item
		ShoppingCartItem newItem = new ShoppingCartItem(id, product.getProductName(), product.getDescription(), 
				product.getImage(), quantity, product.getPrice());
		
		
		
		//call addToCart(ShoppingCartItem item) in ShoppingCart.java to modify shoppingcart

		cart.addToCart(newItem);
		
		
		//don't forget to update session
		request.getSession().setAttribute("shoppingCart", cart);
		
		//send total count back to shop.js so that it can update count in shopping cart icon accordingly.
		int count = cart.getTotalItemsCount();
		Gson gson = new Gson();
		
		//gson.toJson: java-json; gson.fromJson():json->java
		
		//eg."20", 
		String countInJson = gson.toJson(count);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//This line writes the JSON representation of the count variable to the response body , This JSON data will be sent back to the client as part of the HTTP response.
		out.print(countInJson);
		out.flush();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
