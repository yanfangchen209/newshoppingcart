package shoppingcart.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.List;
import shoppingcart.servlet.AddProductServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import shoppingcart.config.Configuration;
import shoppingcart.dao.PostgresProductDao;
import shoppingcart.entity.Product;
import shoppingcart.service.ImageService;
import shoppingcart.service.LocalFileImageServiceImpl;
import shoppingcart.service.ProductDao;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024*1024*10, maxRequestSize = 1024*1024*50)
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
		
		//TODO Create a factory
		ImageService imageService = new LocalFileImageServiceImpl();

		//get image
		Part filePart = request.getPart("newImage");
		String submittedFileName = filePart.getSubmittedFileName();
		
		//if user filePart is null(user don't upload a new image), save the product with old image name
		//if user upload a new image, save the product with new image name
		if(submittedFileName.isEmpty()) {
			String  oldImageName = request.getParameter("oldImage");
			Product product = new Product(
					id, productName, brand, description, category, price, quantity,
					oldImageName);
			
			ProductDao productDao = new PostgresProductDao();
			boolean updateResult = productDao.updateProduct(product);
			request.setAttribute("updateResult", updateResult);
			
			RequestDispatcher rd = request.getRequestDispatcher("editProductResult.jsp");
			rd.forward(request, response);
			
		}else {
			String uniqueImageName = imageService.saveImage(filePart);
					
			Product product = new Product(
					id, productName, brand, description, category, price, quantity,
					uniqueImageName);
			
			ProductDao productDao = new PostgresProductDao();
			boolean updateResult = productDao.updateProduct(product);
			request.setAttribute("updateResult", updateResult);
			
			RequestDispatcher rd = request.getRequestDispatcher("editProductResult.jsp");
			rd.forward(request, response);
			
		}
		
	}




}
