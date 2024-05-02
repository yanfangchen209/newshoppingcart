<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>delete product by id final message</title>
<link rel="stylesheet" type="text/css" href="/shoppingcart/css/deleteProduct.css">
</head>
<body>

	<c:if test="${deleteResult}">
	    <p><c:out value="Product has been deleted successfully." /></p>
	</c:if>
	<c:if test="${not deleteResult}">
	    <p><c:out value="Product deletion failed." /></p>
	</c:if>
<button onclick="window.location.href='productList'"> Go back to product list</button>

</body>
</html>