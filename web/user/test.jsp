<%-- 
    Document   : test
    Created on : Jan 24, 2023, 5:52:52 PM
    Author     : daova
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="test">
            <input type="date" id="requiredDate" name="requiredDate" required>
            <input type="time" id="requiredTime" name="requiredTime" required>
            <input type="submit">
        </form>
    </body>
</html>
