package shoppingcart.service;

import shoppingcart.dao.PostgresProductDao;

public class ProductServiceFactory {

	public static ProductService createProductServiceInstance() {
		return new ProductServiceImpl(new PostgresProductDao());
	}


}
