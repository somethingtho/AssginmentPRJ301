<%-- 
    Document   : login2
    Created on : Feb 18, 2023, 4:24:11 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <link href="../css/login2.css" rel="stylesheet" type="text/css"/>
    </head>


    <body>

        <fmt:setLocale value = "vi_VN"/>
        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
        %>
        <div class="login-box">
            <p>Login</p>
            <form action="${pageContext.request.contextPath}/user/login" method="POST">
                <p class="col-md-7" style="color: red;">${requestScope.error}</p>
                <div class="user-box">
                    <input required="" name="username" value="${cookie.user.value}" type="text">
                    <label>Username</label>
                </div>
                <div class="user-box">
                    <input required="" name="password" value="${cookie.pass.value}" type="password">
                    <label>Password</label>
                </div>

                <div class="content">
                    <label class="checkBox">
                        <input id="ch1" type="checkbox" name="remember" value="ON" ${(cookie.cr.value eq 'ON')?"checked":""}>
                        <div class="transition"></div>
                    </label>
                    <label class="text_remember">Remember</label>
                </div>
                <div style="display: flex; justify-content: space-between">
                    <button type="submit">
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                        Submit
                    </button>

                    <a href="${pageContext.request.contextPath}/user/register.jsp">
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                        Register
                    </a>
                </div>
            </form>
            <p><a href="${pageContext.request.contextPath}/user/forgotpassword.jsp" class="a2">Forgot password!</a></p>
            <p>Login with role admin? <a href="${pageContext.request.contextPath}/admin/authentication-login.jsp" class="a2">Login!</a></p>
        </div>
        <script>
            
        </script>
    </body>
</html>


