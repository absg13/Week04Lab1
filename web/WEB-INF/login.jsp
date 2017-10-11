<%-- 
    Document   : login
    Created on : Oct 5, 2017, 8:26:09 AM
    Author     : 738377
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Remember Me Login Page</h1>
            <div>
                <form action="login" method="POST">
                    Username: <input type="text" name="username" value="${username}"><br>
                    Password: <input type="password" name="password" value="${password}"><br>
                    <input type="submit" value="Login"><br>
                    <input type="checkbox" name="rememberMe" value="remembered"> Remember Me
                </form>
            </div> 
                ${message} 
    </body>         
</html>
