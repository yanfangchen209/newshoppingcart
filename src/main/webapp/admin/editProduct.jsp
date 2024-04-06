<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>edit product</title>
<link rel="stylesheet" type="text/css" href="../css/editProduct.css">
</head>
<body>
	<form action="editProduct" method="post">
		<label for="productId">Id:</label>
		<input id="productId" name="id" value="${product.id}" readonly><br><br>
		<label for="productName">Product Name:</label>
		<input id="productName" name="productName" value="${product.productName}"><br><br>
		<label for="category">Category:</label>
		<input id="category" name="category" value="${product.category}"><br><br>
		 <!-- Format the price with two digits after the decimal point --> 
		<label for="price">Price:</label>

		<input id="price" name="price" value='<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/>'><br><br>
		<label for="quantity">Quantity:</label>
		<input id="quantity" name="quantity" value="${product.quantity}"><br><br>
		<button type="submit">Save</button><br><br>
	</form>
	<button onclick="window.location.href='productList'">Go back to product list</button>

</body>
</html>