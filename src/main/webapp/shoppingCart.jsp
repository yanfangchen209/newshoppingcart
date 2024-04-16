<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>  
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 
 
 <!-- in html, The <select> element does not have a value attribute, but in react, it has value attribute -->
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
    <div class="shoppingcartdisplay">
        <h2>Your Cart:</h2>
		<c:forEach var = "item" items = "${shoppingCart.allCartItems}">
			<div class="cartItem">
				<img src="getimages?imageName=${item.image}" alt={item.image} />
			    <span class="description">${item.description}</span>
			    <span class="unitPrice">each $${item.price}</span>
	            <span class="totalCostContainer">$<span class="totalCost">${item.price * item.quantity}</span></span> 
			    <select class="quantitySelect" name="quantity" onChange="quantityChangeHandler(this, ${item.id})">
				    <option value="0" ${item.quantity == 0 ? 'selected' : ''}>0</option>
				    <option value="1" ${item.quantity == 1 ? 'selected' : ''}>1</option>
				    <option value="2" ${item.quantity == 2 ? 'selected' : ''}>2</option>
				    <option value="3" ${item.quantity == 3 ? 'selected' : ''}>3</option>
				    <option value="4" ${item.quantity == 4 ? 'selected' : ''}>4</option>
				    <option value="5" ${item.quantity == 5 ? 'selected' : ''}>5</option>
				    <option value="6" ${item.quantity == 6 ? 'selected' : ''}>6</option>
			    </select>
			    <i class="fa fa-trash-o" style="font-size:36px" onClick="deleteCartItem(this, ${item.id})"></i>
			</div>
		</c:forEach>
		<form action="checkout" method="post" class="submitForm">
	    	<p>Subtotal: $<span id="subtotal"><fmt:formatNumber type = "number" maxFractionDigits="2" value = "${shoppingCart.subtotal}" /></span></p>
	        	<p >Total items: <span id="totalCount">${shoppingCart.totalItemsCount}</span></p>
	        	<button type="submit">Check Out</button>
	    </form>
	  </div>
</body>
</html> 