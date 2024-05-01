<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        .login-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }

        .login-container input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .login-container button {
            width: 100%;
            padding: 10px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

    </style>
</head>
<body>

<div class="login-container">
<!-- use jsp to get attribute of the request to send message when user and password wrong -->
	${message}
    <h2>Welcome</h2>
    <form action="/shoppingcart/login" method="post">
        <input type="text" name="username" value="${userName}" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="checkbox" id="rememberMe" name="rememberMe"/>
        <label for="rememberMe">Remember me</label>
        <button type="submit">Sign In</button>
    </form>
</div>

</body>
</html>

<!--  
placeholder attribute provide hint or example text for the expected value of the input field
value attribute is the default/inital value of the input field

-->