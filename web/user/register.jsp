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

        <div class="gearbox">
            <div class="overlay"></div>
            <div class="gear one">
                <div class="gear-inner">
                    <div class="bar"></div>
                    <div class="bar"></div>
                    <div class="bar"></div>
                </div>
            </div>
            <div class="gear two">
                <div class="gear-inner">
                    <div class="bar"></div>
                    <div class="bar"></div>
                    <div class="bar"></div>
                </div>
            </div>
            <div class="gear three">
                <div class="gear-inner">
                    <div class="bar"></div>
                    <div class="bar"></div>
                    <div class="bar"></div>
                </div>
            </div>
            <div class="gear four large">
                <div class="gear-inner">
                    <div class="bar"></div>
                    <div class="bar"></div>
                    <div class="bar"></div>
                    <div class="bar"></div>
                    <div class="bar"></div>
                    <div class="bar"></div>
                </div>
            </div>
        </div>

        <div class="login-box">

            <form action="register" method="POST" id="register_form">
                <p style="color: red; font-size: 20px; text-align: center">${requestScope.error}</p>
                <h1>Register</h1>
                <div class="user-box">
                    <input required="" id="username" name="username" type="text">
                    <label>Username</label>
                </div>
                <div class="user-box">
                    <input required="" name="email" type="email">
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

            </form>
            <center>
                <button class="btn" onclick="checkform()">
                    SEND
                    <span></span>
                </button></center>
        </div>

        <script>

            function checkform() {

                if (document.getElementById('username') === "") {
                    alert("Please input UserName!!");
                    return;
                }


                if (document.getElementsByName('password') !== document.getElementsByName('cfpassword')) {
                    alert("Please input correct password same confirm password !");
                    return;
                }

                document.getElementById("register_form").submit();

            }

            window.onload = function () {
                document.querySelector(".gearbox").style.display = "none";
            };
        </script>

    </body>

</html>
