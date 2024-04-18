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
            var brand = document.getElementById('brand').value.trim();
            var description = document.getElementById('description').value.trim();
            var category = document.getElementById('category').value.trim();
            var price = document.getElementById('price').value.trim();
            var quantity = document.getElementById('quantity').value.trim();

            // Basic validation - Check if fields are not empty
            if (productName === '' || brand === '' || description === '' || category === '' || price === '' || quantity === '') {
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
    <form action="add-product" method="post" onsubmit="return validateForm()" enctype="multipart/form-data">
        <label for="productName">Product Name:</label>
        <input id="productName" name="productName" required><br><br>
        
        <label for="brand">Brand:</label>
        <input id="brand" name="brand"  required><br><br>
        
        <label for="description">Description:</label>
        <input id="description" name="description" required><br><br>
        
        
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
        <label for="file">File Upload: </label>
        <input id="file" type="file" name="imageFile" multiple /><br><br>
        <button type="submit">Add Product</button>
    </form>
</body>
</html>
