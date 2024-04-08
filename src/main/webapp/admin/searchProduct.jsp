<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>search product</title>
<link rel="stylesheet" type="text/css" href="../css/searchProduct.css">
</head>
<body>

	<c:if test="${searchResult != null}">
	    <p>Id: ${searchResult.id}</p>
	    <p>Product Name: ${searchResult.productName}</p>
	    <p>Brand: ${searchResult.brand}</p>
	    <p>Description: ${searchResult.description}</p>
	    <p>Category: ${searchResult.category}</p>
	    <p>Price: ${searchResult.price}</p>	
	    <p>Quantity: ${searchResult.quantity}</p>    
	    
	</c:if>
	<c:if test="${searchResult == null}">
	    <p>Product not found</p>
	</c:if>	
	
<button onclick="window.location.href='productList'"> Go back to product list</button>

</html>