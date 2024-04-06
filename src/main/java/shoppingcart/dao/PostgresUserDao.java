package shoppingcart.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import shoppingcart.entity.User;
import shoppingcart.entity.UserCredential;
import shoppingcart.service.UserDao;

import shoppingcart.util.CredentialUtils;

public class PostgresUserDao implements UserDao {


	
	private static final String SELECT_USER_BY_ID = "select * from users where id=?";
	private static final String SELECT_USER_BY_USERNAME = "select * from users where user_name=?";
	private static final String SELECT_ALL_SQL = "SELECT id, user_name, email, role_id FROM users";
	//private static final String SELECT_ALL_SQL = "SELECT u.id, u.user_name, u.email, r.role_name FROM users u join roles r on u.role_id = r.role_id";
	private static final String ADD_USER_SQL = "insert into users(user_name, password_salt, password_hash, email, role_id) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_USER_SQL = "UPDATE users SET user_name = ?, email = ?, role_id = ? WHERE id = ?";
	private static final String DELETE_USER_SQL = "delete from users where id = ?";
	
	
	@Override
	public List<User> findAll(){
		
		try(Connection connection = Database.getConnection();PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
				ResultSet resultset = statement.executeQuery()){
			
			//Iterate the ResetSet to collect data into user list.
			List<User> users = new ArrayList<>();
			while(resultset.next()) {
				//For each row, we read the column values of the row and create an user.
				User user = new User(
						resultset.getLong("id"),
						resultset.getString("user_name"),
						resultset.getString("email"),
						resultset.getInt("role_id")); 
						
				
				//Add the created product in our list.
				users.add(user);
			}
			//Return the users list.
			return users;
		}catch (SQLException e) {
				throw new RuntimeException(e);
		}
	
		
	}
	

	@Override
	public User find(long id) {
			
			try(Connection connection = Database.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);){
				
				/*1: Parameter index. In JDBC, indices start from 1, so this is setting the value for the first parameter in the prepared 
				 * statement. id: The long value that you want to set as the value for the first parameter.*/
				statement.setLong(1, id);
				
				try(ResultSet resultset = statement.executeQuery()){
					User user = null;
					//resultset.next return true means we have data available.
					//And we should have at most one row of data.
					
					if(resultset.next()) {
						user = new User(resultset.getLong("id"), 
								 	resultset.getString("user_name"),
									resultset.getString("email"),
									resultset.getInt("role_id")
								); 
								
					}
					return user;			
					
				}
			} catch (SQLException e) {
					throw new RuntimeException(e);
			}
			
	}
	
	/**
	 * Return user's salt and password if userName is found.
	 * Return null if userName not found
	 * @param userName
	 * @return
	 */
	@Override
	public UserCredential findUserCredential(String userName) {
		
			try(Connection connection = Database.getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USERNAME);){
				
				statement.setString(1, userName);
				
				UserCredential credential = null;
				
				try(ResultSet resultset = statement.executeQuery()){
					if(resultset.next()) {
						String salt = resultset.getString("password_salt");
					 	String hashedPassword = resultset.getString("password_hash");
					 	Long id = resultset.getLong("id");
					 	
					 	credential = new UserCredential(id, userName, salt, hashedPassword);
					}
					return credential;

				}
			} catch (SQLException e) {
					throw new RuntimeException(e);
					
			}
	}
	
	
	
	

	@Override
	public int delete(long id) {
				
			try(Connection connection = Database.getConnection();
						PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL); ){
						
					statement.setLong(1,  id);
						
					int rows_deleted = statement.executeUpdate();
					return rows_deleted;		
						
				} catch (SQLException e) {
						throw new RuntimeException(e);
				}
	}

	
	@Override
	public long add(User user, String password){
		
		
		
		String salt = CredentialUtils.generateSalt();

        // Hash the password with the salt using bcrypt
        String hashedPassword = CredentialUtils.hashPassword(password, salt);

        // Convert the hashed password to a hex string
        //String hashedPasswordHex = CredentialUtils.convertToHexString(hashedPassword);
		
		
		try(Connection connection = Database.getConnection();
			
			//Statement.RETURN_GENERATED_KEYS, with this parameter, we can get the generated key using statement.getGeneratedKeys()
		/*PreparedStatement should return any auto-generated keys after the execution of the SQL statement. This is often used when you 
		 * have an auto-incremented primary key in your database, and you want to retrieve the generated key after an insert operation.*/
			PreparedStatement statement = connection.prepareStatement(ADD_USER_SQL, Statement.RETURN_GENERATED_KEYS);){
			
			
			statement.setString(1, user.getUserName());
			statement.setString(2, salt);
			statement.setString(3, hashedPassword);
			statement.setString(4, user.getEmail());
			statement.setInt(5, user.getRoleId());
			
			/*The executeUpdate() method is part of the PreparedStatement interface in Java, and it's used to execute SQL statements that
			 *  modify data in a database. Typically, it is used for statements like INSERT, UPDATE, or DELETE, which change the data in 
			 *  the database.*/
			statement.executeUpdate();
			
			/*generatedKeys.getInt(1): This retrieves the value of the auto-generated key from the first column of the current row in 
			 * the ResultSet. The parameter 1 represents the column index.*/
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getLong(1);
	            }
	            else {
	                throw new RuntimeException("Add user failed, no ID obtained.");
	            }
	        }
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	//revise username, or email or roleid
	public int update(User user) {
		
		try(Connection connection = Database.getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL);){
				
				
				
				statement.setString(1, user.getUserName());
				statement.setString(2, user.getEmail());
				statement.setInt(3, user.getRoleId());
				statement.setLong(4, user.getId());

				statement.executeUpdate();
				
				int rows_updated = statement.executeUpdate();
				return rows_updated;	
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		
	}

	
	@Override
	public List<String> findCapabilities(long id){
		if(id % 2 == 0) {
			return List.of("canEditUsers", "canEditProduct");
		} else {
			return new ArrayList<>();
		}
		
	}


	
	
	
}
