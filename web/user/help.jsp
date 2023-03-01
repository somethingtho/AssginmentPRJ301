<%-- 
    Document   : help
    Created on : Jan 23, 2023, 2:41:16 AM
    Author     : daova
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Help</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/help.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
    </head>
    <body>

        <fmt:setLocale value = "vi_VN"/>
        <div class="container">
            <div class="header">
                <h1>Đội chăm sóc khách hàng Website!!</h1>
                <p>Chúng tôi muốn lắng nghe câu hỏi và ý kiến đóng góp từ bạn. Hãy phản hồi cho chúng tôi biết vấn đề của bạn nhé! Chúng tôi sẽ liên hệ lại bạn trong 24h tiếp theo..</p>
            </div>
            <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="form_help col-md-8">
                    <form action="${pageContext.request.contextPath}/user/feedback" method="POST">
                        <div class="row ">
                            <h3 style="color: red;">${requestScope.msg}</h3>

                            <div style="margin-top: 30px;">
                                <h3 class="login_title">Bạn cần hỗ trợ với vai trò là người bán hay người mua?</h3>
                                <select name="role" id="role" class="login_input" required>
                                    <c:choose>
                                        <c:when test="${not empty sessionScope.account}">
                                            <option value="">Selected</option>
                                            <optgroup label="Vai trò">
                                                <option value="3" <c:if test="${sessionScope.account.acc.role == 3}">selected</c:if> >Người mua</option>
                                                    <option value="2" >Người bán</option>
                                                    <option value="0" >Đơn vị vận chuyển</option>
                                                </optgroup>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="">Selected</option>
                                            <optgroup label="Vai trò">
                                                <option value="3">Người mua</option>
                                                <option value="2">Người bán</option>
                                                <option value="0">Đơn vị vận chuyển</option>
                                            </optgroup>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                            </div>
                            <c:if test="${not empty sessionScope.account}">
                                <div class="login__form-username">
                                    <h3 class="login_title">Tên đăng nhập</h3>
                                    <input class="login_input" type="text" placeholder="Enter Username..." name="username" value="${sessionScope.account.acc.userName}">
                                </div>
                            </c:if>
                            <div class="login__form-password">
                                <h3 class="login_title">Email</h3>
                                <input class="login_input" type="email" placeholder="Enter Email..." name="email" required value="${sessionScope.account.email}" >
                                <h3 class="login_title">Nội dung phản hồi</h3>
                                <textarea style="height: 200px; padding: 30px;" class="login_input" type="text"
                                          placeholder="Input..." required name="contentSend" ></textarea>
                                <div class="login__btn "><button style="color: white;">Gửi</button></div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            window.onload = function () {
                document.querySelector(".gearbox").style.display = "none";
            };
        </script>
    </body>
</html>
