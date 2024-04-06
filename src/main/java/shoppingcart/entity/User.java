package shoppingcart.entity;

public class User {
	private long id;
	private String userName;
	private String email;
	private int roleId;
	
	public long getId() {
		return id;
	}
	public User(long id, String userName, String email, int roleId) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.roleId = roleId;
	}
	public User(String userName, String email, int roleId) {
		super();
		this.userName = userName;
		this.email = email;
		this.roleId = roleId;
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
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	


}
