package shoppingcart.entity;

public class Product {
	private Long id;
	private String productName;
	private String category;
	private double price;
	private int quantity;
	
	public Product(Long id, String productName, String category, double price, int quantity) {
		super();
		this.id = id;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	public Product(String productName, String category, double price, int quantity) {
		super();
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

	


}
