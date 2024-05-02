<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Horizontal Navigation Bar  -->
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' href='${pageContext.request.contextPath}/css/menu.css'>
</head>

<body>
<!-- pageContext.request.contextPath} returns "/shoppingcart" -->
<ul>
  <li><a href="${pageContext.request.contextPath}/admin/home">Home</a></li>
  <li><a href="${pageContext.request.contextPath}/admin/user/userList">Users</a></li> <!-- relative url: localhost:8080/shoppingcart/index.html -->
  <li><a href="${pageContext.request.contextPath}/admin/product/productList">Products</a></li>
  <li style="float:right"><a href="${pageContext.request.contextPath}/logOut">Log Out</a></li>  <!-- relative url: localhost:8080/shoppingcart/index.html -->
</ul>

</body>
</html>