package shoppingcart.service;

import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.*;

import shoppingcart.dao.PostgresProductDao;
import shoppingcart.entity.Product;
import shoppingcart.entity.ShoppingCartItem;


//crud operation of shopping cart items
public class ShoppingCart {

	private List<ShoppingCartItem> cartItems = new ArrayList<>();
	
	/*add, if item already exist in shopping cart, just add new quantity to old quantity, else if item not exist, 
	 * add new item to the list*/
	public void addToCart(ShoppingCartItem item) {
			

		ShoppingCartItem itemInCart = null;
		
		//find out if the item to be added to cart is in cart according to id
		for(ShoppingCartItem element: cartItems) {
			if(element.getId() == item.getId()) {
				itemInCart = element;
				break;
			}
		}
		
		//if exist, set new quantity, otherwise new item directly
		if(itemInCart != null) {
			itemInCart.setQuantity(item.getQuantity() + itemInCart.getQuantity());
		}else {
			cartItems.add(item);
		}
		
	}
	
	//update, if newQuantity = 0, delete it from shopping cart, otherwise set new quantity
	public void setItemQuantity(Long id, int newQuantity) {

		ShoppingCartItem itemToUpdate = null;
		for(ShoppingCartItem item: cartItems) {
			if(item.getId() == id) {
				itemToUpdate = item;
				break;
			}
		}		
		if(newQuantity == 0) {
			cartItems.remove(itemToUpdate);
		}else {
			itemToUpdate.setQuantity(newQuantity);
		}
		
	}
	
	//delete, boolean remove(Object o)
	public void deleteItem(Long id) {
		
		//use iterator to modify list
		Iterator<ShoppingCartItem> it = cartItems.iterator();		
		while(it.hasNext()) {
			ShoppingCartItem item = it.next();
			if(item.getId() == id) {
				it.remove();
			}
		}
		
		/* use ordinary for loop to modify list
		for(int i = 0; i < cartItems.size(); i++) {
			ShoppingCartItem item = cartItems.get(i);
			if(item.getId() == id) {
				cartItems.remove(item);
			}
		}
		*/
	}
	
	//get total count of items in shopping cart, in jsp,call it like this： shoppingCart.totalItemsCount
	public int getTotalItemsCount() {
		int totalCount = cartItems.stream()
				.map(item -> item.getQuantity())
				.reduce(0, (count1, count2) -> count1 + count2);
		return totalCount;
		
	}
	
	
	//get total amount of money in shopping cart, in jsp,call it like this： shoppingCart.subtotal
	public double getSubtotal() {
		double subtotal = cartItems.stream()
				.map(item -> item.getPrice() * item.getQuantity())
				.reduce(0.0, (subtotal1, subtotal2) -> subtotal1 + subtotal2);
		return subtotal;	
	}
	
	public double getItemSubTotal(long id) {
		Optional<ShoppingCartItem> item = cartItems.stream().filter(i -> i.getId() == id).findFirst();
				
		double itemSubTotal =  item.get().getPrice() * item.get().getQuantity();
		return itemSubTotal;
	}
	
	
	
	//get, in jsp,call it like this： shoppingCart.allCartItems
	public List<ShoppingCartItem> getAllCartItems(){
		return cartItems;
	}
	
	

}
