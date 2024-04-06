package shoppingcart.entity;

import java.util.List;

public class UserInfo {
	private User user;
	private List<String> capabilities;
	public UserInfo(User user, List<String> capabilities) {
		super();
		this.user = user;
		this.capabilities = capabilities;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<String> getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(List<String> capabilities) {
		this.capabilities = capabilities;
	}


	
}
