package shoppingcart.entity;

import org.postgresql.util.ByteBufferByteStreamWriter;

public class UserCredential {
	//TODO this is just a example of constant
	private final static String TEST = "sss";
	
	
	private final long id;
	private final String userName;
	private final String salt;
	private final String passwordHash;
	
	public UserCredential(long id, String userName, String salt, String passwordHash) {
		super();
		this.id = id;
		this.userName = userName;
		this.salt = salt;
		this.passwordHash = passwordHash;
	}

	public long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getSalt() {
		return salt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}
	
	
	
	
}
