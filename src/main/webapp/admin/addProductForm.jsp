<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Product</title>
    <script>
        function validateForm() {
            var productName = document.getElementById('productName').value.trim();
            var category = document.getElementById('category').value.trim();
            var price = document.getElementById('price').value.trim();
            var quantity = document.getElementById('quantity').value.trim();

            // Basic validation - Check if fields are not empty
            if (productName === '' || category === '' || price === '' || quantity === '') {
                alert('All fields must be filled out');
                return false;
            }

            // Additional validation for numeric fields (price and quantity)
            if (isNaN(price) || isNaN(quantity)) {
                alert('Price and Quantity must be numeric values');
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
	<div style="color: red;">${message}</div>
    <form action="add-product" method="post" onsubmit="return validateForm()">
        <label for="productName">Product Name:</label>
        <input id="productName" name="productName" maxlength = 20 required><br><br>
        
        <label for="category">Category:</label>
        <select id="category" name="category" required>
        	<c:forEach var="category" items="${categories}">
        		<option value="${category}">${category}</option>
        	</c:forEach>
        </select><br><br>
        
        <label for="price">Price:</label>
        <input id="price" name="price" type="number" min="0"  step="0.01" required><br><br>
        
        <label for="quantity">Quantity:</label>
        <input id="quantity" name="quantity" type="number" value" min="0" required><br><br>
        
        <button type="submit">Add Product</button>
    </form>
</body>
</html>
