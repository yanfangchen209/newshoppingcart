package shoppingcart.servlet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
import shoppingcart.service.ProductDao;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024*1024*10, maxRequestSize = 1024*1024*50)
public class AddProductServlet extends HttpServlet {
	
    //here hardcode categories, in production environemnt we usually create a category entity and create a category table in database
	public static List<String> categories = new ArrayList<>(Arrays.asList("Snack", "Dish Washing", "dairy", "clothing", "books", 
			"Frozen Foods", " Grains and Pasta", "beverages", "fruit", "other"));
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		request.setAttribute("categories", categories);
		RequestDispatcher rd = request.getRequestDispatcher("addProductForm.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        String brand = request.getParameter("brand");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        //retrieve uploaded image using getPart(), filePart isn't file name, we have to use :String submittedFileName = part.getSubmittedFileName();
        Part filePart = request.getPart("imageFile");
        
        
        //generate unique name  using UUID for each product image, include extension
        String uniqueImageName = generateUniqueFileName(filePart);
        
        
        //specify where to store the image, for this project I will store/write the images in my pc disk, and store image name in database
        Configuration configuration = new Configuration();
        String imageDirectory = configuration.getProperty("pathToWriteProductImages");
        String pathToSaveImage = imageDirectory + "/" + uniqueImageName;

//        
//        try {
//        	
//        	filePart.write(pathToSaveImage);
//        	System.out.println("writing complete");
//        }catch(IOException e){
//        	e.printStackTrace();
//        	response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save file.");
//        	return;
//        }
        
        /*
        try (InputStream inputStream = filePart.getInputStream();
                OutputStream outputStream = new FileOutputStream(pathToSaveImage)) {
               // Copy the content of the part to the output stream
               byte[] buffer = new byte[1024];
               int bytesRead;
               while ((bytesRead = inputStream.read(buffer)) != -1) {
                   outputStream.write(buffer, 0, bytesRead);
               }
       }catch (IOException e) {
    	    e.printStackTrace();
    	    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save file.");
    	    return;
    	}
    	*/
        
        try (InputStream inputStream = filePart.getInputStream();
                OutputStream outputStream = new FileOutputStream(pathToSaveImage)) {
               // Copy the content of the part to the output stream
               inputStream.transferTo(outputStream);
       }catch (IOException e) {
    	    e.printStackTrace();
    	    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save file.");
    	    return;
    	}
    	
        
        
        //data validation on server side
        String message = doValidation(productName, price);
        
        if (message != null) {
        	request.setAttribute("message", message);
        	request.setAttribute("categories", categories);
        	request.getRequestDispatcher("addProductForm.jsp").forward(request, response);;
            return;
        }
        
        //store newly added product including the generated unique image name to the database, the GetProductImagesServlet will receive image name for bd
        //and read image from my pc disk and send http response to whoever want to get this image.
        
        Product product = new Product(productName, brand, description, category, price, quantity, uniqueImageName);
        
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
	
	private String generateUniqueFileName(Part part) {
		//get image name of the uploaded image
		String submittedFileName = part.getSubmittedFileName();
		String extention = submittedFileName.substring(submittedFileName.lastIndexOf("."));
		
		String uniqueName = UUID.randomUUID().toString() + extention;
		return uniqueName;
		
	}



}
