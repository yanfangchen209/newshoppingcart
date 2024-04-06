<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Horizontal Navigation Bar  -->
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' href='../css/menu.css'>
</head>

<body>

<ul>
  <li><a href="home">Home</a></li>
  <li><a href="userList">Users</a></li> <!-- relative url: localhost:8080/shoppingcart/index.html -->
  <li><a href="productList">Products</a></li>
  <li style="float:right"><a href="../logOut">Log Out</a></li>  <!-- relative url: localhost:8080/shoppingcart/index.html -->
</ul>

</body>
</html>