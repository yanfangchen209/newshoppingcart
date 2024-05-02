<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="shoppingcart.entity.User, shoppingcart.service.UserDao" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html>
<head>
	<title>user list admin</title>
	<link rel="stylesheet" type="text/css" href="/shoppingcart/css/addUser.css">
</head>

<body>

    <form action="addUser" method="post">
        <label for="userName">User Name:</label>
        <input type="text" id="userName" name="userName" required>
        <br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        <br>

        <label for="roleName">Role:</label>
        <select id="roleName" name="roleId" required>
            <c:forEach var="role" items="${roles}">
                <option value="${role.id}">${role.roleName}</option>
            </c:forEach> 
        </select>
        
        <br>
        <br>

        <button type="submit">Add User</button>
    </form>

</body>
</html>
    

</body>
</html>