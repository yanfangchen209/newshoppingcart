package shoppingcart.service;
import java.util.List;
import shoppingcart.entity.Product;


//in practice, this should be more bussiness logic and focus on user case(eg.create order which will invoke ProductDao)
//in this project, business logic is simple so it looks the same.
public interface ProductService {
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
