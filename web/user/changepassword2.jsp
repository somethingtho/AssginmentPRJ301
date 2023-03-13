<%-- 
    Document   : changepassword
    Created on : Jan 23, 2023, 3:59:38 AM
    Author     : daova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/changepassword.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
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

    <c:set var="o" value="${requestScope.cart}"/>
    <header>
        <div class="logo">
            <a href="${pageContext.request.contextPath}/user/index"><img id="logo" src="${pageContext.request.contextPath}/admin/assets/images/logo.png" /></a>
        </div>
        <div class="search row">
            <form action="search" class=" btn_search">
                <div class="col-md-10">
                    <input type="text" name="key" class="form-control" id="search" placeholder="Search...">
                </div>
                <button class="col-md-2" onclick="this.form.submit()">
                    <i class="ti-search"></i>
                </button>
            </form>
        </div>
        <div class="info" style="display: flex;justify-content: space-between; width: 300px;">
            <div class="dropdown_cart">
                <a href="show" class="nut_dropdown">
                    <div class="choose">
                        <i class="ti-shopping-cart">(${requestScope.size})</i><br />
                        <p>Giỏ hàng</p>
                    </div>
                </a>
                <div class="content_dropdown">
                    <c:choose>
                        <c:when test="${requestScope.size != 0}">
                            <h5 style="margin-left: 10px; margin-top: 10px;">Sản phẩm trong giỏ</h5>
                            <hr style="height: 1px;border-width:0;color:gray;background-color:gray">
                            <c:forEach items="${o.items}" var="i">
                                <a class="item" href="item?pid=${i.product.productID}">
                                    <div class="row item-1">
                                        <div class="col-md-2"><img src="data:image/jpg;base64,${i.product.base64Image}" alt="" style="width: 50px;">
                                        </div>
                                        <div class="list-group col-md-10">
                                            <h5 style="text-align: left;">${i.product.productName}</h5>
                                            <p>Số lượng: ${i.quantity}</p>
                                            <div>
                                                <p style="text-align: right; color: red;"><fmt:formatNumber value = "${Math.round((i.product.unitPrice - i.product.unitPrice*i.product.discount)/1000)*1000}" type = "currency"/></p>
                                            </div>
                                        </div>
                                    </div>
                                </a>
                                <hr style="margin-bottom: 10px; height: 1px; background-color: black; color: black;">
                            </c:forEach>

                            <div class="row">
                                <p class="col-md-6">Tổng sản phẩm: ${requestScope.size}</p> 
                                <p class="col-md-6">Tổng tiền(Tạm tính):  <fmt:formatNumber value="${Math.round((o.totalMoney)/1000)*1000}" type="currency"/></p> 
                            </div>
                            <hr style="margin-bottom: 10px; height: 1px; background-color: black; color: black;">
                            <div class="row">
                                <div class="col-md-8"></div>
                                <button style="width: 150px; height: 40px; margin: 10px 0; background-color: orangered; border: none; "
                                        class="col-md-3"><a style="color: white; line-height: 2" href="show">Xem giỏ hàng</a></button></div>
                            </c:when>
                            <c:otherwise><div><img style="width:500px" src="${pageContext.request.contextPath}/images/no_cart.png" alt="alt"/></div></c:otherwise>
                        </c:choose>    
                </div>
            </div>
            <c:choose>
                <c:when test="${not empty sessionScope.account}">
                    <a href="information">
                        <div class="choose" >
                            <i class="ti-user"></i><br />
                            <p>Tài khoản của tôi</p>
                        </div>
                    </a>
                    <a href="logout">
                        <div class="choose" >
                            <i class="ti-user"></i><br />
                            <p>Đăng xuất</p>
                        </div>
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="login.jsp">
                        <div class="choose" >
                            <i class="ti-user"></i><br />
                            <p>Đăng nhập</p>
                        </div>
                    </a>
                    <a href="register.jsp">
                        <div class="choose">
                            <i class="ti-user"></i><br />
                            <p>Đăng ký</p>
                        </div>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>

<nav id="navbar">
    <div class="dropdown_nav">
        <a class="categories" href="mobile">
            <i class="ti-mobile"></i>
            <p>MOBILE</p>
        </a>
        <div class="noidung_dropdown row">
            <div class="list-group col-md-12">
                <h4 style="margin: 20px; color: black;">Hãng sản xuất</h4>
                <c:forEach items="${requestScope.listAllSuppliersSmartPhone}" var="s">
                    <a href="mobile?sid=${s.supplierID}" class="col-md-1" style="width: 125px; height: 30px; text-align: center; line-height: 0.5;">${s.companyName}</a>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="dropdown_nav">
        <a class="categories" href="laptop">
            <i class="ti-desktop"></i>
            <p>LAPTOP</p>
        </a>
        <div class="noidung_dropdown row">
            <div class="list-group col-md-12">
                <h4 style="margin: 20px; color: black;">Hãng sản xuất</h4>
                <c:forEach items="${requestScope.listAllSuppliersLaptop}" var="l">
                    <a href="laptop?sid=${l.supplierID}" class="col-md-1" style="width: 125px; height: 30px; text-align: center; line-height: 0.5;">${l.companyName}</a>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="dropdown_nav">
        <a class="categories" href="tablet">
            <i class="ti-tablet"></i>
            <p>TABLET</p>
        </a>
        <div class="noidung_dropdown row">
            <div class="list-group col-md-12">
                <h4 style="margin: 20px; color: black;">Hãng sản xuất</h4>
                <c:forEach items="${requestScope.listAllSuppliersTablet}" var="t">
                    <a href="tablet?sid=${t.supplierID}" class="col-md-1" style="width: 100px; height: 30px; text-align: center; line-height: 0.5;">${t.companyName}</a>
                </c:forEach>
            </div>
        </div>
    </div>
</nav>

<div class="container">

    <div class="row myinfo">
        <div class="col-md-12 row right">
            <h1>Đổi Mật Khẩu</h1>
            <p>Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người khác</p>
            <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
            <h1 style="color: red;">${requestScope.error}</h1>
            <form action="changepassword2" method="post" id="form_change">
                <input type="hidden" value="${requestScope.email}" name="email">
                <div class="row col-md-10 right_form">
                    <div class="row form_info">
                        <div class="col-md-3">
                            <label for="newPassword">Mật Khẩu Mới</label>
                        </div>
                        <div class="col-md-6">
                            <input class="input_form" type="text" name="newPassword" id="newPassword" placeholder="Nhập mật khẩu mới" required>
                        </div>
                    </div>

                    <div class="row form_info">
                        <div class="col-md-3">
                            <label for="cfNewPassword">Xác Nhận Mật Khẩu</label>
                        </div>
                        <div class="col-md-6">
                            <input class="input_form" type="text" id="cfNewPassword" name="cfNewPassword" placeholder="Xác nhận lại mật khẩu" required>
                        </div>

                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-6"><button class="btn_save" type="button"  onclick="checkForm()" style="color: white;" >Xác Nhận</button></div>
                        </div>
                    </div>

                </div>
            </form>
        </div>
    </div>
    <script>
        function checkForm() {
            var password = document.getElementById('newPassword').value;
            var cfpassword = document.getElementById('cfNewPassword').value;
            if (password !== cfpassword) {
                alert('Nhập mật khẩu mới và mật khẩu xác nhận giống nhau!');
                return;
            } else {
                document.getElementById('form_change').submit();
            }
        }

    </script>
</body>
</html>
