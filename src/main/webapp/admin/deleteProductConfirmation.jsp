<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete product by id confirmation</title>
<link rel="stylesheet" type="text/css" href="../css/deleteProductConfirmation.css">
</head>
<body>
	<h1>Are you sure to delete this product?</h1>
	
<!--     <form action="deleteUser" method="post">
    	<input type="hidden" name="id" value="${param.id}"/>
        <button type="submit">Yes, Delete</button>
    </form> -->

    <button onclick="window.location.href='deleteProduct?id=${param.id}'">Yes, delete</button>
    
	<button onclick="window.location.href='productList'">No, go back to product list</button>

</body>
</html>