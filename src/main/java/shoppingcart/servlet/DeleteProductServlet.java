package shoppingcart.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.dao.PostgresProductDao;
import shoppingcart.service.ProductDao;
import shoppingcart.service.ProductService;
import shoppingcart.service.ProductServiceFactory;

public class DeleteProductServlet extends HttpServlet {
	
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		long id = Long.parseLong(request.getParameter("id"));
	
		
		ProductService productService = ProductServiceFactory.createProductServiceInstance();
		boolean deleteResult = productService.deleteById(id);
		
		
		request.setAttribute("deleteResult", deleteResult);
		
		RequestDispatcher rd = request.getRequestDispatcher("deleteProductResult.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
