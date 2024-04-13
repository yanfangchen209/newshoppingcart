package shoppingcart.entity;

public class ShoppingCartItem {
	
	private long id;
	private String productName;
	private String description;
	private String image;
	private int quantity;
	private double price;
	
	public ShoppingCartItem(long id, String productName, String description, String image, int quantity, double price) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.image = image;
		this.quantity = quantity;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
