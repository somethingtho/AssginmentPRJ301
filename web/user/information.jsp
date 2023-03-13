<%-- 
    Document   : infouser
    Created on : Jan 26, 2023, 10:51:10 PM
    Author     : daova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/information.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <title>Thông tin của tôi</title>
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
                <form action="${pageContext.request.contextPath}/user/search" class=" btn_search">
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
                    <a href="${pageContext.request.contextPath}/user/show" class="nut_dropdown">
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
                                    <a class="item" href="${pageContext.request.contextPath}/user/item?pid=${i.product.productID}">
                                        <div class="row item-1">
                                            <div class="col-md-2"><img src="data:image/jpg;base64,${i.product.base64Image}" alt="" style="width: 50px;">
                                            </div>
                                            <div class="list-group col-md-10">
                                                <h5 style="text-align: left;">${i.product.productName}</h5>
                                                <p>Số lượng: ${i.quantity}</p>
                                                <div>
                                                    <p style="text-align: right; color: red;"><fmt:formatNumber value = "${i.product.unitPrice*1.1}" type = "currency"/></p>
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
                        <a href="${pageContext.request.contextPath}/user/information">
                            <div class="choose" >
                                <i class="ti-user"></i><br />
                                <p>Tài khoản của tôi</p>
                            </div>
                        </a>
                        <a href="${pageContext.request.contextPath}/user/logout">
                            <div class="choose" >
                                <i class="ti-user"></i><br />
                                <p>Đăng xuất</p>
                            </div>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/user/login.jsp">
                            <div class="choose" >
                                <i class="ti-user"></i><br />
                                <p>Đăng nhập</p>
                            </div>
                        </a>
                        <a href="${pageContext.request.contextPath}/user/register.jsp">
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
            <a class="categories" href="${pageContext.request.contextPath}/user/mobile">
                <i class="ti-mobile"></i>
                <p>MOBILE</p>
            </a>
            <div class="noidung_dropdown row">
                <div class="list-group col-md-12">
                    <h4 style="margin: 20px; color: black;">Hãng sản xuất</h4>
                    <c:forEach items="${requestScope.listAllSuppliersSmartPhone}" var="s">
                        <a href="${pageContext.request.contextPath}/user/mobile?sid=${s.supplierID}" class="col-md-1" style="width: 125px; height: 30px; text-align: center; line-height: 0.5;">${s.companyName}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="dropdown_nav">
            <a class="categories" href="${pageContext.request.contextPath}/user/laptop">
                <i class="ti-desktop"></i>
                <p>LAPTOP</p>
            </a>
            <div class="noidung_dropdown row">
                <div class="list-group col-md-12">
                    <h4 style="margin: 20px; color: black;">Hãng sản xuất</h4>
                    <c:forEach items="${requestScope.listAllSuppliersLaptop}" var="l">
                        <a href="${pageContext.request.contextPath}/user/laptop?sid=${l.supplierID}" class="col-md-1" style="width: 125px; height: 30px; text-align: center; line-height: 0.5;">${l.companyName}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="dropdown_nav">
            <a class="categories" href="${pageContext.request.contextPath}/user/tablet">
                <i class="ti-tablet"></i>
                <p>TABLET</p>
            </a>
            <div class="noidung_dropdown row">
                <div class="list-group col-md-12">
                    <h4 style="margin: 20px; color: black;">Hãng sản xuất</h4>
                    <c:forEach items="${requestScope.listAllSuppliersTablet}" var="t">
                        <a href="${pageContext.request.contextPath}/user/tablet?sid=${t.supplierID}" class="col-md-1" style="width: 100px; height: 30px; text-align: center; line-height: 0.5;">${t.companyName}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </nav>

    <div class="container-fluid ">
        <div class="row myinfo">
            <div class="col-md-3 left" style=" margin-left: 30px;">
                <h4>Tài khoản của bạn</h4>
                <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
                <ul>
                    <li style="margin-top: 20px;"><a class="ti-id-badge" href="information">  Hồ Sơ</a></li>
                    <li style="margin-top: 20px;"><a class="ti-reload" href="changepassword">  Đổi Mật Khẩu</a></li>
                    <li style="margin-top: 20px;"><a class="ti-receipt" href="order">  Đơn hàng đã mua</a></li>
                </ul>
            </div>

            <div class="col-md-8 row right" id="right" style="margin-top: 20px;">
                <h1>Hello ${sessionScope.account.customerName}! Hồ sơ của tôi</h1>
                <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
                <h3>${requestScope.message}</h3>
                <form method="post" action="${pageContext.request.contextPath}/user/information" enctype="multipart/form-data">
                <div class="coi-md-5 img_user">
                    <img style="width: 150px;" src="data:image/jpg;base64,${sessionScope.account.base64Image}"/>
                    <div style="display: flex; width: 184px; flex-direction: column;">
                        <div class="form_update-img" style="margin: 10px 20px 0 0;">
                            <input type="file" name="photo"/>
                        </div>
                    </div>
                </div>
                
                    <div class="row col-md-9 right_form">
                        <h3 style="color: red">${requestScope.error}</h3>
                        <div class="row form_info">
                            <div class="col-md-3">
                                <label  for="username">Tên đăng nhập</label>
                            </div>
                            <div class="col-md-6"><input class="input_form" type="text" readonly id="username" value="${sessionScope.account.acc.userName}"></div>
                        </div>

                        <div class="row form_info">
                            <div class="col-md-3">
                                <label  for="email">Email</label>
                            </div>
                            <div class="col-md-6"><input class="input_form" type="text"  name="email" value="${sessionScope.account.email}" id="email"></div>
                        </div>

                        <div class="row form_info">
                            <div class="col-md-3">
                                <label for="customerName">Họ và tên</label>
                            </div>
                            <div class="col-md-6"><input class="input_form" type="text" value="${sessionScope.account.customerName}" name="customerName" id="customerName"></div>
                        </div>

                        <div class="row form_info">
                            <div class="col-md-3">
                                <label  for="phone">Số điện thoại</label>
                            </div>
                            <div class="col-md-6"><input class="input_form" value="${sessionScope.account.phone}" type="text" name="phone" id="phone"></div>
                        </div>

                        <div class="row form_info">
                            <div class="col-md-3">
                                <label  for="address">Địa chỉ</label>
                            </div>
                            <div class="col-md-6"><input class="input_form" type="text" name="address" value="${sessionScope.account.address}" id="address"></div>
                        </div>

                        <div class="row form_info">
                            <label for="gender" class="col-md-3" >Giới tính</label> &nbsp;&nbsp;

                            <input  type="radio" id="gender1" name="gender" value="male" <c:if test="${sessionScope.account.gender}">checked</c:if>><label for="gender1">Nam</label>&nbsp;&nbsp;&nbsp;
                            <input  type="radio" name="gender" id="gender2" value="female" <c:if test="${!sessionScope.account.gender}">checked</c:if>><label for="gender2">Nữ</label>
                        </div>
                        <div style="display: flex; justify-content: center; justify-items: center;">
                            <button class="btn_save"  style="color: white;">Lưu</button>
                        </div>
                    </div>

                </form>  
            </div>
        </div>
    </div>

    <script>
        window.onscroll = function () {
            myFunction()
        };

        var navbar = document.getElementById("navbar");
        var sticky = navbar.offsetTop;

        function myFunction() {
            if (window.pageYOffset >= sticky) {
                navbar.classList.add("sticky")
            } else {
                navbar.classList.remove("sticky");
            }
        }


    </script>
</body>
</html>
