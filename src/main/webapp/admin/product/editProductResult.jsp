<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>edit product result</title>
<link rel="stylesheet" type="text/css" href="/shoppingcart/css/editProductResult.css">
</head>
<body>

	<c:if test="${updateResult}">
	    <p><c:out value="Product has been updated successfully." /></p>
	</c:if>
	<c:if test="${not updateResult}">
	    <p><c:out value="Product update failed." /></p>
	</c:if>
<button onclick="window.location.href='productList'"> Go back to product list</button>
</html>