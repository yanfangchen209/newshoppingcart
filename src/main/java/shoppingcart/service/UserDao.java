package shoppingcart.service;

import java.util.List;

import shoppingcart.entity.User;
import shoppingcart.entity.UserCredential;



public interface UserDao {
	
	//add a new user, return user id
	long add(User user, String password);
	
	//delete a user
	int delete(long id);
	
	
	//find all users, search
	List<User> findAll();
	
	//find a certain user according id
	User find(long id);
	
	List<String> findCapabilities(long id);
	
	//find a certain user according userName, return salt and hashedpassword
	UserCredential findUserCredential(String userName);
	
	//update
	int update(User user);
	
	//select a user with username = and password=
	//boolean findByUsernamePassword(String userName, String password);
	
	
}
