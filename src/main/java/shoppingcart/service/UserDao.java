package shoppingcart.service;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import shoppingcart.entity.User;
import shoppingcart.entity.UserCredential;
import shoppingcart.entity.UserWithRoleName;



public interface UserDao {
	
	//add a new user, return user id
	long add(User user, String password);
	
	//delete a user
	int delete(long id);
	
	
	//find all users, search
	List<User> findAll();
	
	//find a certain user according id
	User find(long id);
	

	
	//find a certain user according userName, return salt and hashedpassword
	UserCredential findUserCredential(String userName);
	
	//update
	int update(User user);
	
	
	//find all user, but intead of show role id, this time , we show role name for user display
	 List<UserWithRoleName> findAllWithRoleNames();

	 
	 List<String> getRoleName(long userId);
	 
	 
	 
	//List<String> findCapabilities(long id);
	
	//select a user with username = and password=
	//boolean findByUsernamePassword(String userName, String password);
	
	
}
