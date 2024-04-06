package shoppingcart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import shoppingcart.entity.Role;
import shoppingcart.service.RoleDao;

public class PostgresRoleDao implements RoleDao {
	private static final String SELECT_ALL_SQL = "select * from roles";
	
	@Override
	public List<Role> findAll(){
		
		List<Role> roles = new ArrayList<>();
		
		try(Connection connection = Database.getConnection();PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
				ResultSet resultset = statement.executeQuery()){
			
			//Iterate the ResetSet to collect data into user list.
			
			while(resultset.next()) {
				//For each row, we read the column values of the row and create an user.
				Role role = new Role(
						resultset.getInt("id"),
						resultset.getString("role_name"));
						
				//Add the created product in our list.
				roles.add(role);
			}
		}catch (SQLException e) {
				throw new RuntimeException(e);
		}
	
		//Return the users list.
		return roles;
	}
	


}
