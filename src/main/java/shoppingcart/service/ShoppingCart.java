package shoppingcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import shoppingcart.entity.ShoppingCartItem;


//crud operation of shopping cart items
public class ShoppingCart {

	List<ShoppingCartItem> cartItems = new ArrayList<>();
	
	//add
	public boolean addToCart(ShoppingCartItem item) {
		
		//if item already exist in shopping cart, just add new quantity to old quantity
		
		//else if item not exist, add new item to the list
		
	}
	
	//update
	public boolean setItemQuantity(Long id, int newQuantity) {
		//if newQuantity = 0, delete it from shopping cart
		
		//otherwise set new quantity
		
	}
	
	//delete
	public boolean deleteItem(Long id) {
		
		
	}
	
	//get total count of items in shopping cart
	public int totalItemsCount() {
		int totalCount = cartItems.stream()
				.mapToInt(item -> item.getQuantity())
				.reduce(0, (count1, count2) -> count1 + count2);
		return totalCount;
		
	}
	
	
	//get total amount of money in shopping cart
	public double totalSubtotal() {
		double subtotal = cartItems.stream()
				.mapToDouble(item -> item.getPrice() * item.getQuantity())
				.reduce(0, (subtotal1, subtotal2) -> subtotal1 + subtotal2);
		return subtotal;	
	}
	
	
	
	//get
	public List<ShoppingCartItem> getAllCartItems(){
		return cartItems;
	}
	
	

}
