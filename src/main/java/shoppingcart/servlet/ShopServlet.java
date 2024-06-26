package shoppingcart.servlet;

import java.io.IOException;
import java.util.List;

import javax.naming.ldap.Rdn;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shoppingcart.dao.PostgresProductDao;
import shoppingcart.entity.Product;
import shoppingcart.entity.ShoppingCartItem;
import shoppingcart.service.ProductDao;
import shoppingcart.service.ProductService;
import shoppingcart.service.ProductServiceFactory;
import shoppingcart.service.ShoppingCart;

/**
 *
/*In a web application, you typically have several options for storing the items in the shopping cart page:

Session Storage: You can store the shopping cart items in the user's session. This approach keeps the data on the server but 
associates it with the user's session, allowing the server to maintain the state across multiple requests. However, session 
storage can be limited by session timeouts and scalability concerns.

Database: You can store the shopping cart items in a database. This approach allows for persistent storage and retrieval of 
cart items. Each user's cart would be associated with their user account or session ID in the database.
 
 */
public class ShopServlet extends HttpServlet {


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart cart = (ShoppingCart)request.getSession(true).getAttribute("shoppingCart");
		if(cart == null) {
			cart = new ShoppingCart();
		}

		//get all product data from database and set request attribute so that jsp can get it and display /shoppingcart
		ProductService productService = ProductServiceFactory.createProductServiceInstance();
		List<Product> products = productService.findALLProducts();
		request.setAttribute("products", products);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("shop.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
