
 function addToCart(button){
 
    //id, select input, button are siblings
	var parent = $(button).parent();
	
	//get id
	var productId = parent.children('input[name="productId"]').val();
	
	//get quantity
	var quantity = parent.children('select[name="quantitySelect"]').val();
	
	
	/*send post request with parameters id and quantity to AddToCartServlet so that we get totalCount of shoppingcart and subtotal, then update 
	shopping cart and shopping cart icon in header, .done(function( data ) { ... }): This is a promise callback function 
	that is executed when the POST request is successfully completed. It takes a function as an argument, which will 
	be called when the request succeeds.
	*/
	$.post("addToCart", {id: productId, quantity: quantity})
	.done(function(data){
		//data is the response, the latest items count in shopping cart, update the shoppingcart icon in header
		console.log(data);
		$("#cartCount").html(data);
	});
	 
}
