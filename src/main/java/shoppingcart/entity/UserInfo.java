package shoppingcart.entity;

import java.util.List;

public class UserInfo {
	private User user;
	private List<String> roleNames;
	
	public UserInfo() {
		super();
	}
	public UserInfo(User user, List<String> roleNames) {
		super();
		this.user = user;
		this.roleNames = roleNames;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<String> getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
	
	



	
}
