<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	

<html>
<head>
	<title>User list admin</title>
	<link rel="stylesheet" type="text/css" href="/shoppingcart/css/userAdmin.css">
</head>
<body>
<jsp:include page="menu.jsp"/>
<div align="center">
	
	<!--  <button onclick="window.location.href='${pageContext.request.contextPath}/admin/addUser'">Add User</button>-->
	<button onclick="window.location.href='addUser'">Add User</button>
	<!--<a href="addUser">Add user</a>  -->
	
	<form action='searchUser' method = 'post'>
		<input type="text" id="userSearchInput" placeholder="Enter user ID" name = "searchId">
    	<button>Search</button>
	</form>

    
    
    <c:if test="${not empty creationMessage}">
        <div style="color: green">${creationMessage}</div>
    </c:if>
    <c:if test="${not empty deletionMessage}">
        <div style="color: purple">${deletionMessage}</div>
    </c:if>
        <c:if test="${not empty editMessage}">
        <div style="color: purple">${editMessage}</div>
    </c:if>
    
	<table class = "styled-table">
        <tr>
	  	  <th>Id</th>
          <th>User Name</th>
          <th>Email</th>
          <th>Role</th>
          <th></th>
        </tr>
		<!-- Loop through the users to render table rows. -->
        <c:forEach var = "item" items = "${users}"> 
          <tr>
            <td class = "right"><c:out value="${item.id}" /></td>
            <td class = "center"><c:out value="${item.userName}" /></td>
            <td class = "center"><c:out value="${item.email}" /></td>
            <td class = "center"><c:out value="${item.roleId}" /></td>
	    	<td><a href = "editUser?id=${item.id}">Edit User</a>
           		<a href = "deleteUser?id=${item.id}">Delete User</a>
            </td>
            
          </tr>
        </c:forEach>
      </table>

</div>
</body>