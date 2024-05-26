<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <link rel="stylesheet" href='css/adminLogin.css' />
</head>
<body>

<div class="login-container">
<!-- use jsp to get attribute of the request to send message when user and password wrong -->
	${message}
    <h2>Welcome</h2>
    <form action="/shoppingcart/login" method="post">
        <input type="text" name="username" value="${userName}" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Sign In</button>
    </form>
</div>

</body>
</html>

<!--  
placeholder attribute provide hint or example text for the expected value of the input field
value attribute is the default/inital value of the input field

-->