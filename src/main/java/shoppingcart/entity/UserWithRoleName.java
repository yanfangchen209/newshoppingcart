package shoppingcart.entity;

public class UserWithRoleName {
	private long id;
	private String userName;
	private String email;
	private String roleName;
	
	
	public UserWithRoleName() {
		super();
	}


	public UserWithRoleName(long id, String userName, String email, String roleName) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.roleName = roleName;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	

}