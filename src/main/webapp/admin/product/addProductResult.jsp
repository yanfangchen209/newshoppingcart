<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>add product result</title>
</head>
<body>

	<c:if test="${addResult}">
	    <p><c:out value="Product has been added successfully." /></p>
	</c:if>
	<c:if test="${not addResult}">
	    <p><c:out value="Product has not been added successfully." /></p>
	</c:if>
	
<button onclick="window.location.href='productList'"> Go back to product list</button>
</html>