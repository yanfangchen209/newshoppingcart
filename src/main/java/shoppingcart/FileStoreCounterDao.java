package shoppingcart;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class FileStoreCounterDao {
	static int times = 0;
	
	public int getVisitCount() {
		
		
		//String filePath = "C:\\Users\\julie\\eclipse-workspace\\workspacejee\\shoppingcart\\src\\main\\java\\shoppingcart\\storecounter.dat";escape backslashes
		//String filePath = "C:/Users/julie/eclipse-workspace/workspacejee/shoppingcart/src/main/java/shoppingcart/storecounter.dat";
		//best way is to configure the path, not absolute path here
		String filePath = "C:/Users/julie/storecounter.dat";

		//String filePath = "storecounter.dat";
		File dataFile = new File(filePath);
		if(dataFile.exists()) {
	   		 try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))){
	   			 times = dis.readInt();
	   		 } catch (IOException e) {
	   	            e.printStackTrace();
	   	     }
   		
	   	} else {
	   		try {
				dataFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   		times = 0;
	   	}
        
        //increment
        times++;
       
        
        //Write
        try (
        		DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath))) {

            // Reading primitive data types from the binary file  
        	
            
            // Writing primitive data types to the binary file
            dos.writeInt(times);
            
            // String name2 = dis.readUTF();
            //dos.writeUTF("John");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return times;
	}
}
