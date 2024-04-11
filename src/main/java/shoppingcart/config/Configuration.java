package shoppingcart.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    //in bin folder, relative to Configuration class
	//private static final String PROPERTIES_FILE = "../config.properties";
	
	//absoulte url to main folder
	private static final String PROPERTIES_FILE = "/config.properties";
	private static Properties properties;
	
	//specific to java, use getResourceAsStream to get file from war, cannot be regarded as ordinary file
	static {
		properties = new Properties();
		try {
			properties.load(Configuration.class.getResourceAsStream(PROPERTIES_FILE));	
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

}
