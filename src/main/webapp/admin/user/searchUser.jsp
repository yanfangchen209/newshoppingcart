<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
    <title>Search Result</title>
     <link rel="stylesheet" type="text/css" href='../../css/searchUser.css'>
</head>
<body>
    <h2>Search Result:</h2>

    <c:if test="${result != null}">
        <p>User Id: ${result.id}</p>
        <p>User Name:${result.userName}</p>
        <p>User Email:${result.email}</p>
        <p>User Role:${result.roleName}</p>
    </c:if>
    <c:if test="${result == null}">
        <p>User not found.</p>
    </c:if>

    <p><a href="userList">Back to User List</a></p>
</body>
</html>