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
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <link href="../css/register2.css" rel="stylesheet" type="text/css"/>
    </head>


    <body>

        <div class="login-box">

            <form action="${pageContext.request.contextPath}/user/register" method="POST" id="register_form">
                <p style="color: red; font-size: 20px; text-align: center">${requestScope.error}</p>
                <h1>Register</h1>
                <div class="user-box">
                    <input required="" id="username" name="username" type="text">
                    <label>Username</label>
                </div>
                <div class="user-box">
                    <input required="" id="email" name="email" type="email">
                    <label>Email</label>
                </div>
                <div class="user-box">
                    <input required="" id="password" name="password" type="password">
                    <label>Password</label>
                </div>
                <div class="user-box">
                    <input required="" id="cfpassword" name="cfpassword" type="password">
                    <label>Password</label>
                </div>

            </form>
            <center>
                <button class="btn" type="button" onclick="checkform()">
                    SEND
                    <span></span>
                </button></center>
        </div>

        <script>

            function checkform() {
                const patternEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                const patternUserName = /^[a-zA-Z0-9_]{3,16}$/;
                const patternPassword = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
                let username = document.getElementById('username').value;
                let email = document.getElementById('username').value;
                let password = document.getElementById('username').value;
                let cfpassword = document.getElementById('username').value;
                if (username === "") {
                    alert("Vui lòng nhập UserName!!");
                    return;
                }
                if (username.len < 3 || username.len > 16) {
                    alert("Vui lòng nhập UserName có độ dài từ 3 đến 16 ký tự!");
                    return;
                }
                if (password !== cfpassword) {
                    alert("Vui lòng nhập 2 mật khẩu giống nhau!");
                    return;
                }
                document.getElementById("register_form").submit();
            }

        </script>

    </body>

</html>
