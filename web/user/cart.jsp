<%-- 
    Document   : cart
    Created on : Jan 23, 2023, 4:14:20 AM
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
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
        <title>Giỏ Hàng của tôi</title>

    </head>

    <%
        response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0);
    %>


    <body >

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


        <fmt:setLocale value = "vi_VN"/>
        <c:set var="o" value="${requestScope.cart}"/>
        <header>
            <div class="logo">
                <a href="index"><img id="logo" src="${pageContext.request.contextPath}/images/logo.png" /></a>
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
                                                    <p style="text-align: right; color: red;"><fmt:formatNumber value = "${i.product.unitPrice*1.1}" type = "currency"/></p>
                                                </div>
                                            </div>
                                        </div>
                                    </a>
                                    <hr style="margin-bottom: 10px; height: 1px; background-color: black; color: black;">
                                </c:forEach>

                                <div class="row">
                                    <p class="col-md-6">Tổng sản phẩm: ${requestScope.size}</p> 
                                    <p class="col-md-6">Tổng tiền(Tạm tính):  <fmt:formatNumber value="${o.totalMoney}" type="currency"/></p> 

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


    <c:set var="o" value="${requestScope.cart}"/>
    <div class="container">
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6 body_cart" style="border: 1px solid black">
                <div class="cart_header">
                    <a href="index"> <i class="ti-angle-left"></i> Mua thêm sản phẩm khác</a>
                    <p>Giỏ hàng của bạn</p>
                </div>
                <div class="row body_content">
                    <div class="">
                        <c:forEach items="${o.items}" var="i">
                            <div class="row cart_header">
                                <div class="col-md-4">
                                    <a href="item?pid=${i.product.productID}"><img style="width: 180px;" src="data:image/jpg;base64,${i.product.base64Image}"/></a><br>
                                    <form action="process" method="post">
                                        <input type="hidden" name="id" value="${i.product.productID}"/>
                                        <div style="display: flex;justify-content: center; justify-items: center;">
                                            <input style="margin-top: 20px; width: 70px; background-color: orange; border: none; color: white;" type="submit" value="Xoá"/></div>   
                                    </form>
                                </div>
                                <div class="row col-md-8">
                                    <div class="col-md-8">
                                        <h4><a href="item?pid=${i.product.productID}">${i.product.productName}</a></h4>
                                    </div>
                                    <div class="col-md-4">
                                        <h5><fmt:formatNumber value = "${i.product.unitPrice*1.1}" type = "currency"/></h5>
                                    </div>
                                    <table class="table table-striped" style="width: 350px;">
                                        <tbody>
                                            <tr>
                                                <td>Màn hình</td>
                                                <td>${i.product.proInfo.screen}</td>
                                            </tr>
                                            <tr>
                                                <td>RAM</td>
                                                <td>${i.product.proInfo.ram}</td>
                                            </tr>
                                            <tr>
                                                <td>Dung lượng</td>
                                                <td>${i.product.proInfo.hardDrive}</td>
                                            </tr>
                                            <tr>
                                                <td>Pin</td>
                                                <td>${i.product.proInfo.batteryCapacity}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <div class="btn_Cart">
                                        <button style="width: 40px; background-color: orange; border: none;"><a href="process?num=-1&id=${i.product.productID}">-</a></button>
                                        <input style="width: 60px; text-align: center;" type="number" readonly value="${i.quantity}">
                                        <button style="width: 40px; background-color: orange; border: none;"><a href="process?num=1&id=${i.product.productID}">+</a></button>
                                    </div>
                                    <p style="display: flex; justify-content: space-between;"><span>Tổng tiền:</span><span style="margin-right: 30px;"><fmt:formatNumber value="${i.product.unitPrice*1.1*i.quantity}" type="currency"/></span></p>
                                </div>

                            </div>
                        </c:forEach>
                        <div class="user_cart">
                            <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
                            <div class="row total_products">
                                <div class="col-md-7">
                                    <h5>Tạm tính (${requestScope.size} sản phẩm)</h5>
                                </div>
                                <div class="col-md-5">
                                    <h5 style="margin-right: 5px">Tổng tiền: <fmt:formatNumber value="${o.totalMoney}" type="currency"/></h5>
                                </div>
                            </div>
                            <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
                            <h4>THÔNG TIN KHÁCH HÀNG</h4>

                            <div>
                                <input type="radio" value="1" <c:if test="${sessionScope.account.gender}">checked</c:if> name="gender" id="gender1"><label for="gender1">Male</label>
                                <input type="radio" name="gender" value="0" <c:if test="${!sessionScope.account.gender}">checked</c:if> id="gender2"><label for="gender2">Female</label>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 infor-username">
                                        <h5 class="user_title">Họ và tên</h5>
                                        <input class="t" type="text" readonly value="${sessionScope.account.customerName}">
                                </div>
                                <div class="col-md-6 infor-phonenumber">
                                    <h5 class="user_title">Số điện thoại</h5>
                                    <input class="" type="text" readonly value="${sessionScope.account.phone}">
                                </div>
                            </div>
                            <div class="infor-email">
                                <h5 class="user_title">Email</h5>
                                <input class="" type="email" readonly value="${sessionScope.account.email}">
                            </div>
                            <h5 class="user_title">Địa chỉ của bạn</h5>

                            <div class="infor-address">
                                <input type="text" readonly value="${sessionScope.account.address}">
                            </div>
                            <div style="margin-top: 20px;"><a href="${pageContext.request.contextPath}/user/infomation" >Đổi thông tin</a></div>
                            <form action="checkout" method="post">
                                <h3><label for="shipper">Chọn đơn vị vận chuyển</label></h3>
                                <select name="shippers" id="shipper">
                                    <c:forEach items="${requestScope.listShippers}" var="ship">
                                        <option value="${ship.shipperID}">${ship.companyName}</option>
                                    </c:forEach>
                                </select>

                                <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">

                                <h3><label for="requiredDate">Chọn thời gian giao hàng</label></h3>
                                <input type="date" id="requiredDate" name="requiredDate" required>
                                <input type="time" id="requiredTime" name="requiredTime" required>

                                <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">

                                <div class="row">
                                    <div class="col-md-8">
                                        <h4 style="font-weight: bold;">Thành tiền: </h4>
                                    </div>
                                    <div class="col-md-4">
                                        <h4 style="color: red;"><fmt:formatNumber type="currency" value="${o.totalMoney}"/></h4>
                                    </div>
                                </div>

                                <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
                                <h3>Chọn phương thức thanh toán</h3>
                                <div class="submit_order">
                                    <input type="radio" name="payments" id="payment1" value="delivery" required>
                                    <label for="payment1">Thanh toán khi nhận hàng</label><br>
                                    <input type="radio" name="payments" id="payment0" value="QR" required>
                                    <label for="payment0">Thanh toán qua QR code</label><br>
                                    <button>Đặt hàng</button>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <p class="text-center" style="margin-top: 30px;">Bằng cách đặt hàng, bạn đồng ý với Điều khoản sử dụng của chúng tôi</p>
    <hr style="height: 1px; background-color: black; color: black; margin-bottom: 0;">
    <footer class="container-fluid">
        <div class="row">
            <div class="col-md-4 row">
                <h3 class="text-center">Liên hệ với chúng tôi</h3>
                <div class="col-md-3 img_mxh"><a href="https://www.facebook.com" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/facebook.jpg"
                                                                                                                                alt=""></a></div>
                <div class="col-md-3 img_mxh"><a href="https://www.skype.com/en/" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/skype.png"
                                                                                                                                 alt=""></a></div>
                <div class="col-md-3 img_mxh"><a href="https://twitter.com/?lang=vi" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/twitter.png"
                                                                                                                                    alt=""></a></div>
                <div class="col-md-3 img_mxh"><a href="https://www.instagram.com/" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/insta.jpg"
                                                                                                                                  alt=""></a></div>

            </div>
            <div class="col-md-4 website_help center-block">
                <h3>Website</h3>
                <p><a href="">Bán hàng cùng chúng tôi</a></p>
                <p><a href="">Tuyển dụng</a></p>
                <p><a href="#">Điều khoản sử dụng</a></p>
                <p><a href="#">Chính sách bảo mật</a></p>
                <p><a href="#">Bảo vệ quyền sở hữu trí tuệ</a></p>
                <p><a target="_blank" rel="noopener noreferrer" href="help.jsp">Trợ giúp <i class="ti-help"></i></a></p>
            </div>

            <div class="col-md-4 row">
                <h3 style="color: rgb(233, 152, 3);">Go where your heart beats</h3>
                <h4>Tải ứng dụng <i class="ti-arrow-down"></i></h4>
                <div class="row app_icon">
                    <p class="col-md-6"><a href="https://www.apple.com/store"><img src="${pageContext.request.contextPath}/images/app_store.png" alt=""></a></p>
                    <p class="col-md-6"><a href="https://play.google.com/"><img src="${pageContext.request.contextPath}/images/google_play.png" alt=""></a></p>
                </div>

                <p>FPT University CNC – Km29 Dai Lo Thang Long, H. Thach That, TP. Ha Noi</p>
            </div>

        </div>
        <hr style=" height: 1px; background-color: black; color: black;">
        <div class="row payment">
            <div class="col-md-6">
                <h3 class="text-center">CÁCH THỨC THANH TOÁN</h3>
                <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/visa.png">
                <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/mastercard.png">
                <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/jbc.jpg">
                <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/cashonde.png">
                <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/napas.png">
            </div>

            <div class="col-md-6">
                <h3 class="text-center">DỊCH VỤ GIAO HÀNG</h3>
                <img src="${pageContext.request.contextPath}/images/ahamove.png">
                <img src="${pageContext.request.contextPath}/images/bestex.png">
                <img src="${pageContext.request.contextPath}/images/ghn.png">
                <img src="${pageContext.request.contextPath}/images/jtex.png">
                <img src="${pageContext.request.contextPath}/images/ninjavan.png">
                <img src="${pageContext.request.contextPath}/images/ship60.png">
            </div>
        </div>
    </footer>

</body>

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

    window.onload = function () {
        document.querySelector(".gearbox").style.display = "none";
    };
</script>
</body>
</html>
