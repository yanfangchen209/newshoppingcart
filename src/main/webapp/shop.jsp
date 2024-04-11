<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>shopping page</title>
<link rel = "stylesheet" href = "css/shop.css" type = "text/css"></link>



</head>

<body>
    <jsp:include page="navMenu.jsp"/>
    	<ul class="productList">
		<c:forEach var = "item" items = "${products}">
       		<li>
	        	<div class="image">
	        		<img src="${pageContext.request.contextPath}/getimages?imageName=${item.image}" alt={item.image} />
	        	</div>
	        	<div class="description">
	        		<c:out value="${item.description}" />
	        	</div> 
	            <div class="brand">
	            	<c:out value="${item.brand}" />
	            </div>
	            <div class="price">
	            	<c:out value="$${item.price}" />
	            </div>
		    	<div class="shopAction">
			    	<input type="hidden" name="productId" value="<c:out value="${item.id}"></c:out>">
			    	<select name="quantitySelect">
				    	<option value="1">1</option>
				    	<option value="2">2</option>
				    	<option value="3">3</option>
				    	<option value="4">4</option>
				    	<option value="5">5</option>
				    	<option value="6">6</option>
			    	</select>
			    	<button class="addToCartButton" onclick="addToCart(this)">Add to Cart</button>
		    	</div>
	        </li>
        </c:forEach>
	</ul>	
	
</body>