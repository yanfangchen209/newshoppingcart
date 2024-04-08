package shoppingcart.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import shoppingcart.entity.Product;
import shoppingcart.service.ProductDao;

public class PostgresProductDao implements ProductDao{
	
	private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM products WHERE id=?";
	private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM products";
	private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM products WHERE id = ?";
	private static final String UPDATE_PRODUCT = "UPDATE products SET productName = ?, brand = ?, description = ?, category = ?, price = ?, quantity = ? WHERE id = ?";
	private static final String SELECT_PRODUCT_BY_NAME = "SELECT * FROM products WHERE productName=?";
	private static final String ADD_PRODUCT = "INSERT INTO products(productName, brand, description, category, price, quantity) VALUES (?, ?, ?, ?, ?, ?)";

	@Override
	public boolean addProduct(Product product) {
		
		try(Connection connection = Database.getConnection(); 
				PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT, Statement.RETURN_GENERATED_KEYS)
						){
			
			statement.setString(1, product.getProductName());
			statement.setString(2, product.getBrand());
			statement.setString(3, product.getDescription());
			statement.setString(4, product.getCategory());
			statement.setDouble(5, product.getPrice());
			statement.setInt(6, product.getQuantity());
			
			int rows_affacted = statement.executeUpdate();
			boolean addResult = false;
			if(rows_affacted > 0) {
				addResult = true;
			}
			
			return addResult;	
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public List<Product> findALLProducts(){
		
		List<Product> products = new ArrayList<>();
		
		try(Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS);
				ResultSet resultSet = statement.executeQuery()){
			
			while(resultSet.next()) {
				Product product = new Product(
						resultSet.getLong("id"), 
						resultSet.getString("productName"), 
						resultSet.getString("brand"), 
						resultSet.getString("description"), 
						resultSet.getString("category"), 
						resultSet.getDouble("price"), 
						resultSet.getInt("quantity"));
				products.add(product);
			}
			return products;
			
		} catch (SQLException e) {
			
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}
	
	@Override
	public Product findByName(String productName) {
		try(Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME)){
		
			statement.setString(1, productName);
			ResultSet resultSet = statement.executeQuery();
			Product product = null;
			
			if(resultSet.next()) {
				product = new Product(
						resultSet.getLong("id"), 
						resultSet.getString("productName"), 
						resultSet.getString("brand"),
						resultSet.getString("description"),
						resultSet.getString("category"), 
						resultSet.getDouble("price"),
						resultSet.getInt("quantity")
						);
			}
			
			return product;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Product findById(long id) {
		
		Product result = null;
		
		try(Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)){
			
			statement.setLong(1, id);
			
			try(ResultSet resultSet = statement.executeQuery()){
				
				if(resultSet.next()) {
					result = new Product(
							resultSet.getLong("id"),
							resultSet.getString("productName"), 
							resultSet.getString("brand"),
							resultSet.getString("description"),
							resultSet.getString("category"), 
							resultSet.getDouble("price"), 
							resultSet.getInt("quantity")
							);
				}			
			}catch(SQLException e){			
				throw new RuntimeException(e);
			}
		
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
		
	}
	
	@Override
	public boolean updateProduct(Product product) {
		try(Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT)){
			
			boolean updateResult = false;
			

			statement.setString(1, product.getProductName());
			statement.setString(2, product.getBrand());
			statement.setString(3, product.getDescription());
			statement.setString(4, product.getCategory());
			statement.setDouble(5, product.getPrice());
			statement.setInt(6, product.getQuantity());
			statement.setLong(7, product.getId());
			
			int rows_afffected = statement.executeUpdate();
			if (rows_afffected >= 0) {
				updateResult = true;
			}
				
			return updateResult;
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	
	@Override
	public boolean deleteById(long id) {
		try(Connection connection = Database.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID)){
			
			statement.setLong(1, id);
			int rows_deleted = statement.executeUpdate();
			if(rows_deleted > 0) {
				return true;
			}else {
				return false;
			}
			
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}


	

}
