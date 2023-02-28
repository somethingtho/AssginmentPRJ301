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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
    </head>


    <body>
        <fmt:setLocale value = "vi_VN"/>
        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
//prevents caching at the proxy server
        %>
        <div class="container row">

            <form action="${pageContext.request.contextPath}/user/forgotpassword" method="POST">
                <div class="col-md-5"></div>
                <div class="card col-md-10">
                    <a class="singup">Forgot Password</a>
                    <span style="color: red; font-size: 18px;">${requestScope.error}</span>
                    <div class="inputBox1">
                        <input type="text" required="required" name="email">
                        <span class="user">Email</span>
                    </div>
                    <button class="enter" onclick="this.form.submit()">Enter</button>

                    <div class="main_div">
                        <button><a style="text-decoration: none; color: white" href="${pageContext.request.contextPath}/user/register.jsp">Sign up</a></button>
                    </div>
                    <div class="main_div">
                        <button><a style="text-decoration: none; color: white" href="${pageContext.request.contextPath}/user/login.jsp">Login</a></button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
