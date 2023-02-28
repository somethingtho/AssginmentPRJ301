<%-- 
    Document   : inputemail
    Created on : Feb 18, 2023, 4:36:12 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link href="../css/register2.css" rel="stylesheet" type="text/css"/>
    </head>
    
    <body>
        <div class="login-box">

            <form action="register" method="POST">
                <p style="color: red; font-size: 20px; text-align: center">${requestScope.error}</p>
                <h1>Register</h1>
                <div class="user-box">
                    <input required="" name="username" type="text">
                    <label>Username</label>
                </div>
                <div class="user-box">
                    <input required="" name="email" type="text">
                    <label>Email</label>
                </div>
                <div class="user-box">
                    <input required="" name="password" type="password">
                    <label>Password</label>
                </div>
                <div class="user-box">
                    <input required="" name="cfpassword" type="password">
                    <label>Password</label>
                </div>
                <center>
                    <button type="submit">
                        SEND
                        <span></span>
                    </button></center>
            </form>
        </div>
    </body>
</html>
