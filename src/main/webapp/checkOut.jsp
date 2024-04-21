<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>check out</title>
<link rel="stylesheet" href="css/checkOut.css"/> 
</head>
<body>
	<jsp:include page="navMenu.jsp"/>
	<div class="checkOutPage">
		<div class="orderSummary">
		 <h1>Cart</h1>
		 <span>Subtotal:$${subtotal}</span><br>
		 <span>Total Items:${totalCount}</span>
		</div>
		<div class="address">
			<h1>Address:</h1>
			<form action="placeorder" method="post">
				<label for="firstName">First Name:</label>
				<input id="firstName" type="text" name="firstName" />
				<label for="lastName">Last Name:</label>
				<input id="lastName" type="text" name="lastName" />
				<label for="street">Street:</label>
				<input id="street" type="text" name="street" />
				<label for="city">City:</label>
				<input id="city" type="text" name="city" />
				<label>State:</label>
				<select name="state">
					<option value="oklahoma">OK</option>
					<option value="texas">TX</option>
					<option value="colorodo">CO</option>
				</select>			
				<label for="zipcode">Zip Code:</label>
				<input id="zipcode" type="text" name="zipcode" />
				<label for="phone">Phone Number:</label>
				<input id="phone" type="phone" name="phone" />
				<button class="placeOrderButton">Place Order</button>
			</form>
		</div>
	</div>
</body>
</html>