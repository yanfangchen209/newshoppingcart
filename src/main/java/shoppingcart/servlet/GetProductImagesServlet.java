package shoppingcart.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shoppingcart.config.Configuration;
import shoppingcart.service.ImageService;
import shoppingcart.service.LocalFileImageServiceImpl;



/**
 * this servlet is used to read images from file system: given a image name and return the image so that other jsp or servlet can use it
 * shopping page will display products with images, browsers cache photos so that it won't download each time loading shopping page.
 * path configuration???
 */
public class GetProductImagesServlet extends HttpServlet {
	
	// Specify the directory path where the images are stored on your local file system
	//In Java, both forward slashes (/) and backward slashes (\) can be used as directory separators in file paths. 
	//private static final String IMAGE_DIRECTORY = "C:\\newshoppingcartproductimages";
	
	//private static final String IMAGE_DIRECTORY = "C:/newshoppingcartproductimages";
	//private static final String IMAGE_DIRECTORY = Configuration.getProperty("pathToReadProductImages");


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		// Get the image name from the request parameter
        String imageName = request.getParameter("imageName");

        // Check if the image name is provided: http://localhost:8080/shoppingcart/getimages? (null), http://localhost:8080/shoppingcart/getimages?imageName=(empty)


            // Check if the file exists and have permission to read
          
                // Set the content type based on the image file's MIME type
              //  response.setContentType(getServletContext().getMimeType(imagePath));
                
            	ImageService imageService = new LocalFileImageServiceImpl();
            	InputStream inputStream = imageService.getImage(imageName);

                // Get the output stream to write the image data
                try (OutputStream outputStream = response.getOutputStream()) {

                    // Copy the image data to the response output stream
                    byte[] buffer = new byte[4096]; // Buffer size for reading input stream
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    // Flush the output stream to ensure all data is written
                    outputStream.flush();
                }


        
    }
}
