package shoppingcart.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import shoppingcart.config.Configuration;
class LocalFileImageServiceImpl implements ImageService{

	public LocalFileImageServiceImpl() {
	}

	//Better not use Part here as it's from servlet api.
	@Override
	public String saveImage(Part filePart) {
		String uniqueImageName = generateUniqueFileName(filePart);
		
		//specify where to store the image, for this project I will store/write the images in my pc disk, and store image name in database
		String pathToWriteImage = Configuration.getProperty("pathToWriteProductImages") + "/" + uniqueImageName;
		
		try{
			filePart.write(pathToWriteImage);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return uniqueImageName;
	}
	
	
	private String generateUniqueFileName(Part part) {
		//get image name of the uploaded image
		String submittedFileName = part.getSubmittedFileName();
		String extention = submittedFileName.substring(submittedFileName.lastIndexOf("."));
		
		String uniqueName = UUID.randomUUID().toString() + extention;
		return uniqueName;
		
	}

	@Override
	public InputStream getImage(String imageName) {
		
	
		String pathToReadImage = Configuration.getProperty("pathToReadProductImages") + "/" + imageName;
	
        // Create a File object representing the image file
        File imageFile = new File(pathToReadImage);
        
        if (!imageName.isEmpty()) {
            // Check if the file exists and have permission to read
            if (imageFile.exists() && imageFile.canRead()) {
                try {
                    // Return an input stream to read from the image file
                    return new FileInputStream(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
      
        return null;
	}
}
