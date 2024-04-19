package shoppingcart.service;

import java.io.InputStream;

import javax.servlet.http.Part;

public interface ImageService {
	public String saveImage(Part filePart);
	
	public InputStream getImage(String imageName);
}
