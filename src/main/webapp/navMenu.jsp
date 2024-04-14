<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Horizontal Navigation Bar  -->
<!DOCTYPE html>
<html>
<head>
	<link rel='stylesheet' href='css/navMenu.css'>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
	<ul>
	    <li><a href="shop">MunchMart</a></li>
	    <li><a href="shop">Products</a></li>
	    <li style="float:right"><a href="login">Log in</a></li>
	    <li style="float:right"><a href="signup">Sign up</a></li>
		<li style="float:right"><a href="shop">Log out</a></li>
		<li style="float:right">
			<a href="shoppingcart">
				<i class="fa" style="font-size:24px">&#xf07a;</i>
	    		<span class='badge' id='cartCount'> ${shoppingcart.totalItemsCount}</span>
	    	</a>
	    </li>
	</ul>
</body>
</html>