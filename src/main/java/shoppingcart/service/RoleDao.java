package shoppingcart.service;

import java.util.List;

import shoppingcart.entity.Role;

public interface RoleDao {
	
	//1.find all roles
	List<Role> findAll();
	
	//2.find a certain role accoring to given role id
	//Role find(int id);
	
	//3.return role id
	//int add(Role role);
	
	//4.return number of rows deleted
	//int delete(int id);
	
	//5.return number of rows edited
	//int update(int id);
	

}
