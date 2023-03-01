<%-- 
    Document   : login
    Created on : Jan 23, 2023, 2:20:38 AM
    Author     : daova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Password Assistance</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
        <link href="../css/inputemail.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
    </head>


    <body>
        <fmt:setLocale value = "vi_VN"/>
        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
        %>
        <div class="container row">
            <form action="${pageContext.request.contextPath}/user/forgotpassword" method="POST" id="form_forgot" >
                <div class="col-md-5"></div>
                <div class="card col-md-10">

                    <a class="singup" href="${pageContext.request.contextPath}/user/forgotpassword.jsp">Forgot Password</a>
                    <span style="color: red; font-size: 18px;">${requestScope.error}</span>
                    <div class="inputBox1">
                        <input type="email" required="required" name="email">
                        <span class="user">Email</span>
                    </div>
                    <button class="enter" onclick="checkForm()">Enter</button>

                    <div class="main_div">
                        <a class="btn" style="text-decoration: none; color: white" href="${pageContext.request.contextPath}/user/register.jsp">Sign up</a>
                    </div>
                    <div class="main_div">
                        <a class="btn" style="text-decoration: none; color: white" href="${pageContext.request.contextPath}/user/login.jsp">Login</a>
                    </div>
                </div>
            </form>
        </div>
        <script>

            window.onload = function () {
                document.querySelector(".gearbox").style.display = "none";
            };

            function checkForm() {
                const patternEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                let email = document.getElementsByName('email').value;
                if (email === "") {
                    alert("Vui lòng nhập Email!");
                    return;
                }
            }


        </script>
    </body>
</html>
