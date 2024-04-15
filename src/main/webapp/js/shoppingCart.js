/**
 * 
 */
 
 function quantityChangeHandler(select, cartItemId) {
	
		//select is an plain javascript element, $(select) turn it into an jquery object.
		var quantity = $(select).val();
	
		//don't forget this, if quantity set to 0, delete this item from 0, other method???delete
		
		if(quantity == 0){
			//If user set quantity to 0, we need delete this item from shopping cart.
			var row = $(select).parents('.cartItemRow').first();
			row.remove();
		}
	
		console.log("selectinput",quantity);
		console.log("cartItemId", cartItemId);
		
		//send http post to UpdateQuantityServlet with data new quantity and id, get back totalCount, total 
		//subtotal to update shoppingcart icon and page
	
		$.post("updatequantity", {id: cartItemId, quantity: quantity})
			.done(function(data){
			//data is the response, the latest items count in shopping cart, update the shoppingcart icon in header
			console.log(data);
			//update shopping cart icon
			$("#cartCount").html(data.totalCount);
			
			//update subtotal and total count????
		
		
	});
}