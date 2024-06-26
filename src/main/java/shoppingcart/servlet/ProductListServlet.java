package shoppingcart.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.dao.PostgresProductDao;
import shoppingcart.dao.PostgresUserDao;
import shoppingcart.entity.Product;
import shoppingcart.service.ProductDao;
import shoppingcart.service.ProductService;
import shoppingcart.service.ProductServiceFactory;


public class ProductListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ProductService productService = ProductServiceFactory.createProductServiceInstance();
		List<Product> allProducts = productService.findALLProducts();
		
		request.setAttribute("allProducts", allProducts);
//		PrintWriter outPrintWriter = response.getWriter();
//		outPrintWriter.print("test...");
		RequestDispatcher rd = request.getRequestDispatcher("productList.jsp");
		rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
