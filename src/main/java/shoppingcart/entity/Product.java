package shoppingcart.entity;

public class Product {
	private Long id;
	private String productName;
	private String brand;
	private String description;
	private String category;
	private double price;
	private int quantity;
	private String image;
	
	public Product(Long id, String productName, String brand, String description, String category, double price,
			int quantity, String image) {
		super();
		this.id = id;
		this.productName = productName;
		this.brand = brand;
		this.description = description;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
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
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
}