package shoppingcart.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.dao.PostgresProductDao;
import shoppingcart.entity.Product;
import shoppingcart.service.ProductDao;



public class AddProductServlet extends HttpServlet {
	
    //here hardcode categories, in production environemnt we usually create a category entity and create a category table in database
	private static List<String> categories = new ArrayList<>(Arrays.asList("Snack", "Dish Washing", "electronics", "clothing", "books", 
			"beauty and personal care", "sports", "toys", "office supplies", "other"));
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("addProductForm.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        //data validation on server side
        String message = doValidation(productName, price);
        
        if (message != null) {
        	request.setAttribute("message", message);
        	request.setAttribute("categories", categories);
        	request.getRequestDispatcher("addProductForm.jsp").forward(request, response);;
            return;
        }
              
        Product product = new Product(productName, category, price, quantity);
        
        ProductDao productDao = new PostgresProductDao();
        boolean addResult = productDao.addProduct(product);
        request.setAttribute("addResult", addResult);
        
		RequestDispatcher rd = request.getRequestDispatcher("addProductResult.jsp");
		rd.forward(request, response);
		
		
		//to do:
		//check in database
        //also show user entered value when show him error message

        
    }
	
	private String doValidation(String productName, double price) {
		String message = null;
		if (productName == null || productName.trim().isEmpty()) {
			message = "Product name is required";
        } else {
        	boolean productDuplicated = productName.equals("abc");
        	if(productDuplicated) {
        		message = "Product Name already exist.";
        	}
        }
		
		if(message == null) {
			if(price < 0) {
				message = "Product price must be greater than 0";
			}
		}
		
		return message;
		
        
	}



}
