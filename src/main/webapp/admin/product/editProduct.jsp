<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>edit product</title>
<link rel="stylesheet" type="text/css" href="/shoppingcart/css/editProduct.css">
</head>
<body>
	<form action="editProduct" method="post" enctype="multipart/form-data">
		<label for="productId">Id:</label>
		<input id="productId" name="id" value="${product.id}" readonly><br><br>
		<label for="productName">Product Name:</label>
		<input id="productName" name="productName" value="${product.productName}"><br><br>
		<label for="brand">Brand:</label>
		<input id="brand" name="brand" value="${product.brand}"><br><br>
		<label for="description">Description:</label>
		<input id="description" name="description" value="${product.description}"><br><br>
		<label for="category">Category:</label>
		<select id="category" name="category"  required>
        	<c:forEach var="category" items="${categories}">
        		 <option value="${category}" <c:if test="${category == product.category}">selected</c:if>>${category}</option>
        	</c:forEach>
        </select><br><br>
		 <!-- Format the price with two digits after the decimal point --> 
		<label for="price">Price:</label>

		<input id="price" name="price" value='<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/>'><br><br>
		<label for="quantity">Quantity:</label>
		<input id="quantity" name="quantity" value="${product.quantity}"><br><br>
		<label>Image:</label><br>
	    <img src="/shoppingcart/getimages?imageName=${product.image}" alt={item.image} } />
	    <input type="hidden" name="oldImage" value="${product.image}" />
		<label for="newImage">New Image Upload:</label>
		<input type="file" id="newImage" name="newImage"><br><br>

		<button type="submit">Save</button><br><br>
	</form>
	<button onclick="window.location.href='productList'">Go back to product list</button>

</body>
</html>