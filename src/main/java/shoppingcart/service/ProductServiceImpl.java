package shoppingcart.service;

import java.util.List;

import shoppingcart.entity.Product;

public class ProductServiceImpl implements ProductService {
	
	//dependency injection
	ProductDao productDao;
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}
	/* dependency injection
	ProductDao productDao = new PostgresProductDao();

	*/
	
	@Override
	public List<Product> findALLProducts() {
		return productDao.findALLProducts();
	}
	@Override
	public Product findById(long id) {
		return productDao.findById(id);
	}
	@Override
	public Product findByName(String productName) {
		return productDao.findByName(productName);
	}
	@Override
	public boolean deleteById(long id) {
		return productDao.deleteById(id);
	}
	@Override
	public boolean addProduct(Product product) {
		return productDao.addProduct(product);
	}
	@Override
	public boolean updateProduct(Product product) {
		return productDao.updateProduct(product);
	}
	
	

}
