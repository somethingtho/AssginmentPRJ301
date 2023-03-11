<%-- 
    Document   : changpassword2
    Created on : Mar 1, 2023, 4:55:50 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <link href="dist/css/changepassword2.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
    </head>

    <style>
        .btn:last-child{
            float: right;
        }
    </style>
    <body>
        <div style="display: grid; place-items: center">
            <div class="card" >
                <div class="card-header">
                    <div class="text-header">Change Password</div>
                </div>
                <div class="card-body">
                    <form action="${pageContext.request.contextPath}/admin/changepassword2" method="POST" id="form_change">
                        <h3 style="color: red">${requestScope.error}</h3>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input readonly="" class="form-control" name="email" value="${requestScope.email}" id="email" type="email">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input required="" class="form-control" name="newPassword" id="password" type="password">
                        </div>
                        <div class="form-group">
                            <label for="confirm-password">Confirm Password:</label>
                            <input required="" class="form-control" name="cfNewPassword" id="confirm-password" type="password">
                        </div>
                        <input type="button" onclick="checkForm()" class="btn" value="submit"> 
                        <input type="button" onclick="gotoHome()" class="btn" value="GO Home"></form>
                </div>
            </div>
        </div>
        <script>


            function checkForm() {
                var password = document.getElementById('password').value;
                var cfpassword = document.getElementById('confirm-password').value;
                console.log(password);
                console.log(cfpassword);
                if (password !== cfpassword) {
                    alert('Password and Confirm Password the same!');
                    return;
                } else {
                    document.getElementById('form_change').submit();
                }
            }
            function gotoHome() {
                window.location.href = "${pageContext.request.contextPath}/admin/index";
            }
        </script>
    </body>
</html>
