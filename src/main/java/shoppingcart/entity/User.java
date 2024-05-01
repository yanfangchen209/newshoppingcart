package shoppingcart.entity;

//users table has columns:id, user_name, email, role_id, password_salt, password_hash
//this project assuem each user has only one role
public class User {
	private final long id;
	private final String userName;
	private final String email;
	private final int roleId;
	private final String roleName;
	
	public long getId() {
		return id;
	}
	public User(long id, String userName, String email, int roleId) {
		this(id, userName, email, roleId, null);
	}
	public User(long id, String userName, String email, int roleId, String roleName) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public User(String userName, String email, int roleId) {
		this(0, userName, email, roleId);
	}

	public String getUserName() {
		return userName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}
	
	
	


}
