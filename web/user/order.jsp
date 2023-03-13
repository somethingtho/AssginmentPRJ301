<%-- 
    Document   : order
    Created on : Jan 28, 2023, 6:36:37 PM
    Author     : daova
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/order.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <title>Đơn hàng của bạn</title>
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
                                            class="col-md-3"><a style="color: white; line-height: 2" href="${pageContext.request.contextPath}/user/show">Xem giỏ hàng</a></button></div>
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

    <div class="container-fluid">
        <div class="row myinfo">
            <div class="col-md-3 left" style=" margin-left: 30px;">
                <h4>Tài khoản của bạn</h4>
                <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
                <ul>
                    <li style="margin-top: 20px;"><a class="ti-id-badge" href="${pageContext.request.contextPath}/user/information">  Hồ Sơ</a></li>
                    <li style="margin-top: 20px;"><a class="ti-reload" href="${pageContext.request.contextPath}/user/changepassword">  Đổi Mật Khẩu</a></li>
                    <li style="margin-top: 20px;"><a class="ti-receipt" href="${pageContext.request.contextPath}/user/order">  Đơn hàng đã mua</a></li>
                </ul>
            </div>

            <div class="col-md-8 row right">
                <h1>Hello ${sessionScope.account.customerName}! Đơn hàng của bạn</h1>
                <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
                <div style="display: flex; justify-content: space-between">
                    <div><h4>Đây là tất cả đơn hàng của bạn đã mua</h4><c:if test="${not empty requestScope.from && not empty requestScope.to}"><h5>From: ${requestScope.from} To: ${requestScope.to}</h5></c:if></div>
                    <div>
                        <form action="${pageContext.request.contextPath}/user/order" >
                            <span>Lọc theo ngày:</span>
                            <label for="from">From</label>
                            <input id="from" name="from" type="date" value="${requestScope.from}">
                            <label for="to">To</label>
                            <input id="to" type="date" name="to" value="${requestScope.to}">
                            <button type="button" onclick="checkForm()" class="ti-search"></button>
                        </form>
                    </div>
                </div>

                <div class="container right_form">
                    <table style="width: 99rem;" class="table">
                        <tr>
                            <th>Mã đơn hàng</th>
                            <th>Sản phẩm</th>
                            <th>Ngày đặt mua</th>
                            <th>Trạng thái</th>
                            <th>Tổng tiền</th>
                        </tr>

                        <c:forEach items="${requestScope.ordersByCustomerID}" var="order">
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach var="orderDetail" items="${order.orderDetails}">
                                <tr>
                                    <td style="border-top: none; text-align: center;padding-right: 80px;"><a href="${pageContext.request.contextPath}/user/orderdetail?oid=${order.orderID}"><c:if test="${count ==0}">${order.orderID}</c:if></a></td>
                                        <c:set var="count" value="${count+1}"  scope="page"></c:set>
                                    <td style="display: flex;"><a href="${pageContext.request.contextPath}/user/item?pid=${orderDetail.product.productID}"><img style="width: 50px;" src="data:image/jpg;base64,${orderDetail.product.base64Image}"/>
                                            <div>
                                                <a href="${pageContext.request.contextPath}/user/item?pid=${orderDetail.product.productID}">${orderDetail.product.productName}</a>
                                                <p style="margin-top: 5px; font-size: 13px;">
                                                    <c:choose>
                                                        <c:when test="${order.payments}">Thanh toán qua QR code</c:when>
                                                        <c:when test="${!order.payments}">Thanh toán khi nhận hàng</c:when>
                                                    </c:choose>
                                                </p>
                                            </div>
                                        </a></td>
                                    <td>${order.orderDate}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${order.status == 1}">Đã giao hàng</c:when>
                                            <c:when test="${order.status == 0}">Đã huỷ</c:when>
                                            <c:otherwise >Chờ xử lý</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><fmt:formatNumber value="${Math.round((order.totalMoney)/1000)*1000}" type="currency" /></td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </table>

                </div>

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


        const fromDate = document.getElementById("from");
        const toDate = document.getElementById("to");

        // Add an event listener to detect changes in both input fields
        fromDate.addEventListener("change", () => {
            // Check if the "from" date is after the "to" date
            if (fromDate.value > toDate.value) {
                // Swap the values of the two input fields
                const temp = fromDate.value;
                fromDate.value = toDate.value;
                toDate.value = temp;
            }
        });

        toDate.addEventListener("change", () => {
            // Check if the "to" date is before the "from" date
            if (toDate.value < fromDate.value) {
                // Swap the values of the two input fields
                const temp = toDate.value;
                toDate.value = fromDate.value;
                fromDate.value = temp;
            }
        });

        function checkForm() {
            const fromInput = document.getElementById("from");
            const toInput = document.getElementById("to");

            if (fromInput.value === "" || toInput.value === "") {
                alert("Please select both a 'From' and a 'To' date.");
            } else {
                document.forms[1].submit();
            }
        }

    </script>
</body>
</html>
