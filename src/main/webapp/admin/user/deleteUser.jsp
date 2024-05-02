<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirm Delete</title>
	<link rel='stylesheet' type='text/css' href="/shoppingcart/css/deleteUser.css">
</head>
<body>

    <p>Are you sure you want to delete the user?</p>

    <!--this works also, but below hidden input is better to send parameter id: <form action="deleteUser?id=${param.id}" method="post">
        <button type="submit">Yes, Delete</button>
    </form> -->
    
    <!-- when clikc deleteuser link, it go to deleteservlet with id parameter in url, when forward to this jsp, jsp can 
    get user id by ${param.id}, we can also user getParameter()in deleteservlet to get id, request.setAttribute(id),   -->
    
    <form action="deleteUser" method="post">
    	<input type="hidden" name="id" value="${param.id}"/>
        <button type="submit">Yes, Delete</button>
    </form>
    
<!-- 
1. this form works
    <form action="userList" method="get">
        <button type="submit">No, Go Back</button>
    </form>
    

  2. this works
  <div><button onclick="window.location.href='${pageContext.request.contextPath}/userList'">No, go back to user list page</button></div>  
    

  -->
  <br>
  <button onclick="window.location.href='userList'">No, go back to user list page</button>

 
</body>
</html>