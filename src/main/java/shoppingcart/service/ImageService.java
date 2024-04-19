package shoppingcart.service;

import java.io.InputStream;

import javax.servlet.http.Part;

public interface ImageService {
	public String saveImage(Part filePart);
	public InputStream getImage(String imageName);
	
	public static ImageService getInstance() {
		//we can either store images in local, ftp or google cloud.. which are different implementations
		/*in configuration file, get type and then decide what implementation class instance to return (if, else)so that we
		 * can only change configration file instead of change code 
		 */
		return new LocalFileImageServiceImpl();
	}
}
