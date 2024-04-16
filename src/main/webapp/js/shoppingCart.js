/**
 * 
 */
 
 function quantityChangeHandler(select, cartItemId) {
	
		//select is an plain javascript element, $(select) turn it into an jquery object.
		var quantity = $(select).val();
	
		//don't forget this, if quantity set to 0, delete this item from 0, other method???delete
		
		
	
		console.log("selectinput",quantity);
		console.log("cartItemId", cartItemId);
		
		//send http post to UpdateQuantityServlet with data new quantity and id, get back totalCount, total 
		//subtotal to update shoppingcart icon and page
	
		$.post("updatequantity", {id: cartItemId, quantity: quantity})
			.done(function(data){
			//data is the response, the latest items count in shopping cart, update the shoppingcart icon in header
			console.log(data);
			//update shopping cart icon
			$("#cartCount").text(data.totalCount);
		    $("#totalCount").text(data.totalCount);
			$("#subtotal").text(data.subtotal);
			
			if(quantity == 0){
				//If user set quantity to 0, we need delete this item from shopping cart.
				var row = $(select).parents('.cartItem').first();
				row.remove();
			}
			
			$(select).siblings(".totalCostContainer").children(".totalCost").text(data.itemSubtotal);

	});
}


//pass itemId to DeleteCartItemServlet to delete from shoppingCart list, if success, delete from UI
function deleteCartItem(deleteButton, itemId){

	$.post("deletecartitem", {id: itemId})
		.done(function(data){
			console.log(data);
			//update shopping cart icon
			$("#cartCount").text(data.totalCount);
		    $("#totalCount").text(data.totalCount);
			$("#subtotal").text(data.subtotal);
			
			var row = $(deleteButton).parents('.cartItem').first();
			row.remove();
		});
}