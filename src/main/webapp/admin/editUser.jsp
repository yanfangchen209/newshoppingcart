<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <link rel="stylesheet" type="text/css" href='../css/editUser.css'>
    <!-- <link rel="stylesheet" type="text/css" href='/shoppingcart/css/editUser.css'> -->
</head>
<body>

    <form action="editUser" method="post">
    
        <label for="userId">User id:</label>
        <input type="text" name="userId" value="${param.id}" readonly>
         <br> <br>

        <label for="username">User name:</label>
        <input type="text" id="username" name="userName" value="${user.userName}" required>
        <br>  <br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" required>
        <br>  <br>
        
        <!-- the form display role name like administrator, but it is role.id that is transfered to server, another serlvet get roleid by variable roleId -->
        <label for="roleName">Role:</label>
        <select id="roleName" name="roleId" required>
        	<c:forEach var="role" items="${roles}">
        		<option value="${role.id}">${role.roleName}</option>
            </c:forEach> 
        </select>
        <br>  <br>

        <button type="submit">Save</button>
    </form>

</body>
</html>