<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>  
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>shopping cart</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="js/jquery-3.6.4.min.js"></script>
<script src="js/shoppingCart.js"></script>
<link rel="stylesheet" href="css/shoppingCart.css" type="text/css"/>
</head>
<body>
    <jsp:include page="navMenu.jsp"/>
        <h2>Your Cart:</h2>
		<c:forEach var = "item" items = "${shoppingCart.allCartItems}">
			<div class="cartItem" id="cartItemRow">
				<img src="getimages?imageName=${item.image}" alt={item.image} />
			    <span>${item.description}</span>
			    <span>each $${item.price}</span>
	            <span>total $${item.price * item.quantity}</span>
			    <select name="quantity" onChange="quantityChangeHandler(this, ${item.id})">
			    	<option value="0">0</option>
			    	<option value="1">1</option>
			    	<option value="2">2</option>
			    	<option value="3">3</option>
			    	<option value="4">4</option>
			    	<option value="5">5</option>
			    	<option value="6">6</option>
			    </select>
			    <i class="fa fa-trash-o" style="font-size:36px"></i>
			</div>
		</c:forEach>
		<form action="checkout" method="post" class="submitForm">
	        	<p>Subtotal: $${shoppingCart.subtotal}</p>
	        	<p>Total items: ${shoppingCart.totalItemsCount}</p>
	        	<button type="submit">Check Out</button>
        <!--<button class="checkOutButton" onClick="window.location.href='checkout'"}>Check out</button> -->
        </form>	
</body>
</html>