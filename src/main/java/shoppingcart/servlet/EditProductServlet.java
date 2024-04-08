package shoppingcart.servlet;

import java.io.IOException;
import java.util.Set;
import java.util.List;
import shoppingcart.servlet.AddProductServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.dao.PostgresProductDao;
import shoppingcart.entity.Product;
import shoppingcart.service.ProductDao;


public class EditProductServlet extends HttpServlet {



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		ProductDao productDao = new PostgresProductDao();
		Product product = productDao.findById(id);
		request.setAttribute("product", product);
		
		List<String> categories = AddProductServlet.categories;
		request.setAttribute("categories", categories);
		
		RequestDispatcher rd = request.getRequestDispatcher("editProduct.jsp");
		rd.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		String productName = request.getParameter("productName");
		String brand = request.getParameter("brand");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
				
		Product product = new Product(
				id, productName, brand, description, category, price, quantity
				);
		ProductDao productDao = new PostgresProductDao();
		boolean updateResult = productDao.updateProduct(product);
		request.setAttribute("updateResult", updateResult);
		
		RequestDispatcher rd = request.getRequestDispatcher("editProductResult.jsp");
		rd.forward(request, response);


	}

}
