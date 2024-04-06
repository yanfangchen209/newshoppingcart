<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>show all products</title>
<link rel='stylesheet' type="text/css" href="../css/productList.css">
</head>
<body>
<jsp:include page="menu.jsp"/>
<div>
	<button onclick="window.location.href='add-product'">Add Product</button>
	<form action='search-product' method='post'>
		<input id='productName' name='productName' placeholder='search by product name'>
		<button type="submit">Search</button>		
	</form>
	
	<table class='styled-table'>
		<tr>
			<th>Id</th>
			<th>Product Name</th>
			<th>Category</th>
			<th>Price($)</th>
			<th>Quantity</th>
			<th></th>
		</tr>
		<c:forEach var="item" items = "${allProducts}">
			<tr>
				<td><c:out value="${item.id}"/></td>
				<td><c:out value="${item.productName}"/></td>
				<td><c:out value="${item.category}"/></td>
				<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.price}"/></td>
				<td><c:out value="${item.quantity}"/></td>
				<td><a href="editProduct?id=${item.id}">Edit Product</a>
				    <a href="deleteProductConfirmation.jsp?id=${item.id}">Delete Product</a>
				<td>		
			</tr>
		</c:forEach>			
	</table>
</div>
</body>
</html>