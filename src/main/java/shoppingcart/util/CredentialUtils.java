package shoppingcart.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.mindrot.jbcrypt.BCrypt;

public class CredentialUtils{


    // Method1 to generate a random salt
    public static String generateSaltBCrypt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return BCrypt.gensalt(12, random);
    }
    


    // Method1 to hash a password with a given salt using bcrypt, hashed password is byte array, already converted to string so don't need to convert to hexstring
    public static String hashPasswordWithBCrypt(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }
    
    // Method2 to generate a random salt
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
    
    // Method2 to hash a password With java built-in message digest
    public static String hashPassword(String password, String salt) {
    	

    	try {
    		
        	//combine password with salt
        	String passwordWithSalt = password + salt;
        	
        	//create SHA-256 message digest
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			//update digest with password and salt bytes
			
			byte[] hashBytes = digest.digest(passwordWithSalt.getBytes());
			
			//convert hash bytes to Base64 string
			return Base64.getEncoder().encodeToString(hashBytes);
			
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return null;
		}
    	
    }

    // Method to convert a byte array to a hex string
//    public static String convertToHexString(String input) {
//        StringBuilder hexString = new StringBuilder();
//        for (char character : input.toCharArray()) {
//            hexString.append(Integer.toHexString(character));
//        }
//        return hexString.toString();
//    }
    

    
    
    /*only for test, output eg. 
     * Salt: V4/Jxyz5TFuONy5R7+8rAg==
      Hashed Password: auv5uOMYP0TXB+XfAv2Q4wzMBXk2dkdNVbSdt3f3UsU=*/
    public static void main(String[] args) {
        String salt = CredentialUtils.generateSalt();
        String hashedPassword = CredentialUtils.hashPassword("password123", salt);
       

        System.out.println("Salt: " + salt);
        System.out.println("Hashed Password: " + hashedPassword);

    }


}
