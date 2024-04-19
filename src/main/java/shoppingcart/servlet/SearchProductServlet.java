package shoppingcart.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.dao.PostgresProductDao;
import shoppingcart.entity.Product;
import shoppingcart.service.ProductDao;
import shoppingcart.service.ProductService;
import shoppingcart.service.ProductServiceFactory;


public class SearchProductServlet extends HttpServlet {
	
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productName = request.getParameter("productName");
		
		ProductService productService = ProductServiceFactory.createProductServiceInstance();
		Product searchResult = productService.findByName(productName);
		
		request.setAttribute("searchResult", searchResult);
		RequestDispatcher rd = request.getRequestDispatcher("searchProduct.jsp");
		rd.forward(request, response);
	}

}
