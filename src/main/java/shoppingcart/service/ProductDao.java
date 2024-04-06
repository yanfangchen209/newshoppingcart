package shoppingcart.service;

import java.util.List;

import org.postgresql.replication.LogSequenceNumber;

import shoppingcart.entity.Product;

public interface ProductDao {
	List<Product> findALLProducts();
	Product findById(long id);
	Product findByName(String productName);

	
	//can also return deleted product, depends on specific needs
	boolean deleteById(long id);
	
	//can also return added product
	boolean addProduct(Product product);
	
	//can also return the updated product
	boolean updateProduct(Product product);

}
