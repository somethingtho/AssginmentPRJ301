<%-- 
    Document   : index
    Created on : Jan 22, 2023, 8:45:04 PM
    Author     : daova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="content">

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
                <form action="${pageContext.request.contextPath}/user/search" class=" btn_search" id="form_search">
                    <div class="col-md-10">
                        <input type="text" name="key" class="form-control" id="search" placeholder="Search..." required="">
                    </div>
                    <button class="col-md-2" onclick="searchBtn()">
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
                <div class="list-group col-sm-12">
                    <h4 style="margin: 20px; color: black;">Hãng sản xuất</h4>
                    <c:forEach items="${requestScope.listAllSuppliersSmartPhone}" var="s">
                        <a href="${pageContext.request.contextPath}/user/mobile?sid=${s.supplierID}" class="col-sm-1" style="width: 125px; height: 30px; text-align: center; line-height: 0.5;">${s.companyName}</a>
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
                <div class="list-group col-sm-12">
                    <h4 style="margin: 20px; color: black;">Hãng sản xuất</h4>
                    <c:forEach items="${requestScope.listAllSuppliersLaptop}" var="l">
                        <a href="${pageContext.request.contextPath}/user/laptop?sid=${l.supplierID}" class="col-sm-1" style="width: 125px; height: 30px; text-align: center; line-height: 0.5;">${l.companyName}</a>
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
                <div class="list-group col-sm-12">
                    <h4 style="margin: 20px; color: black;">Hãng sản xuất</h4>
                    <c:forEach items="${requestScope.listAllSuppliersTablet}" var="t">
                        <a href="${pageContext.request.contextPath}/user/tablet?sid=${t.supplierID}" class="col-sm-1" style="width: 100px; height: 30px; text-align: center; line-height: 0.5;">${t.companyName}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </nav>

    <div>
        <div class="container fa-slide">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">

                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                </ol>

                <div class="carousel-inner">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/images/banner_1.webp" alt="Banner1" style="width:100%;">
                    </div>

                    <div class="item">
                        <img src="${pageContext.request.contextPath}/images/banner_2.webp" alt="Banner2" style="width:100%;">
                    </div>

                    <div class="item">
                        <img src="${pageContext.request.contextPath}/images/banner_8.webp" alt="Banner8" style="width:100%;">
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/images/banner_7.webp" alt="Banner7" style="width:100%;">
                    </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>

        <div class="container fa-menu">
            <div class="row">
                <a href="${pageContext.request.contextPath}/user/laptop" class="product prf col-sm-4">
                    <img src="${pageContext.request.contextPath}/images/laptop.jpg" class="img-circle center-block" alt="">
                    <p>Laptop</p>
                </a>
                <a href="${pageContext.request.contextPath}/user/mobile" class="product col-sm-4">
                    <img src="${pageContext.request.contextPath}/images/smartphone.jpg" class="img-circle center-block" alt="">
                    <p>Mobile</p>
                </a>

                <a href="${pageContext.request.contextPath}/user/tablet" class="product prf col-sm-4">

                    <img src="${pageContext.request.contextPath}/images/tablet.jpg" class="img-circle center-block" alt="">
                    <p>Tablet</p>
                </a>

            </div>
        </div>

        <div class="container banner">
            <a href="${pageContext.request.contextPath}/user/laptop"><img src="${pageContext.request.contextPath}/images/banner_4.webp" alt=""></a>
        </div>

        <div class="container flashsale">
            <h2>KHUYẾN MÃI HOT</h2>
            <div class="row">
                <c:set var="count" value="0" scope="page" />
                <c:forEach items="${requestScope.listAllProducts}" var="p" >
                        <a href="${pageContext.request.contextPath}/user/item?pid=${p.productID}" class="col-sm-3 card">
                            <input type="hidden" name="pid" value="${p.productID}">
                            <span class="discount"><fmt:formatNumber value = "${p.discount}" type = "percent"/></span>
                            <div class="pro-img">
                                <img src="data:image/jpg;base64,${p.base64Image}"/>
                            </div>
                            <p class="product-name text-center">${p.productName}</p>
                            <div class="gia gia-sale"><fmt:formatNumber value = "${Math.round((p.unitPrice - p.unitPrice*p.discount)/1000)*1000}" type = "currency" /></div>
                            <p class="gia gia-goc"><fmt:formatNumber value = "${p.unitPrice}" type = "currency"/></p>
                            <div ><button class="buy" style="border: none;">MUA NGAY</button></div>
                        </a>
                </c:forEach>
            </div>
        </div>
        <div class="container banner">
            <a href="${pageContext.request.contextPath}/user/mobile?sid=4"><img style="height: 70%" src="${pageContext.request.contextPath}/images/banner_6.webp" alt=""></a>
        </div>

        <div class="container flashsale">
            <h2>ĐIỆN THOẠI NỔI BẬT</h2>
            <div class="row">
                <c:forEach items="${requestScope.listSmartphoneHotSale}" var="p" >
                    <a href="${pageContext.request.contextPath}/user/item?pid=${p.productID}" class="col-sm-3">
                        <input type="hidden" name="pid" value="${p.productID}">
                        <span class="discount"><fmt:formatNumber value = "${p.discount}" type = "percent"/></span>
                        <div class="pro-img">
                            <img src="data:image/jpg;base64,${p.base64Image}"/>
                        </div>
                        <p class="product-name text-center">${p.productName}</p>
                        <div class="gia gia-sale"><fmt:formatNumber value = "${Math.round((p.unitPrice - p.unitPrice*p.discount)/1000)*1000}" type = "currency"/></div>
                        <p class="gia gia-goc"><fmt:formatNumber value = "${p.unitPrice}" type = "currency"/></p>
                        <div class="buy">MUA NGAY</div>
                    </a>
                </c:forEach>
            </div>
        </div>

        <div class="container banner">
            <a href="${pageContext.request.contextPath}/user/item?pid=35"><img src="${pageContext.request.contextPath}/images/banner_7.webp" alt=""></a>
        </div>

        <div class="container flashsale">
            <h2>LAPTOP BÁN CHẠY</h2>
            <div class="row">
                <c:forEach items="${requestScope.listLaptopHotSale}" var="p" >
                    <a href="${pageContext.request.contextPath}/user/item?pid=${p.productID}" class="col-sm-3">
                        <input type="hidden" name="pid" value="${p.productID}">
                        <span class="discount"><fmt:formatNumber value = "${p.discount}" type = "percent"/></span>
                        <div class="pro-img">
                            <img src="data:image/jpg;base64,${p.base64Image}"/>
                        </div>
                        <p class="product-name text-center">${p.productName}</p>
                        <div class="gia gia-sale"><fmt:formatNumber value = "${Math.round((p.unitPrice - p.unitPrice*p.discount)/1000)*1000}" type = "currency"/></div>
                        <p class="gia gia-goc"><fmt:formatNumber value = "${p.unitPrice}" type = "currency"/></p>
                        <div class="buy">MUA NGAY</div>
                    </a>
                </c:forEach>
            </div>
        </div>

        <div class="container flashsale">
            <h2>TABLET BÁN CHẠY</h2>
            <div class="row">
                <c:forEach items="${requestScope.listTabletHotSale}" var="p" >
                    <a href="${pageContext.request.contextPath}/user/item?pid=${p.productID}" class="col-sm-3">
                        <input type="hidden" name="pid" value="${p.productID}">
                        <span class="discount"><fmt:formatNumber value = "${p.discount}" type = "percent"/></span>
                        <div class="pro-img">
                            <img src="data:image/jpg;base64,${p.base64Image}"/>
                        </div>
                        <p class="product-name text-center">${p.productName}</p>
                        <div class="gia gia-sale"><fmt:formatNumber value = "${Math.round((p.unitPrice - p.unitPrice*p.discount)/1000)*1000}" type = "currency"/></div>
                        <p class="gia gia-goc"><fmt:formatNumber value = "${p.unitPrice}" type = "currency"/></p>
                        <div class="buy">MUA NGAY</div>
                    </a>
                </c:forEach>
            </div>
        </div>



        <hr style="height: 1px; background-color: black; color: black; margin-bottom: 0;">

        <footer class="container-fluid">
            <div class="row">
                <div class="col-sm-4 row">
                    <h3 class="text-center">Liên hệ với chúng tôi</h3>
                    <div class="col-sm-3 img_mxh"><a href="https://www.facebook.com" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/facebook.jpg"
                                                                                                                                    alt=""></a></div>
                    <div class="col-sm-3 img_mxh"><a href="https://www.skype.com/en/" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/skype.png"
                                                                                                                                     alt=""></a></div>
                    <div class="col-sm-3 img_mxh"><a href="https://twitter.com/?lang=vi" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/twitter.png"
                                                                                                                                        alt=""></a></div>
                    <div class="col-sm-3 img_mxh"><a href="https://www.instagram.com/" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/insta.jpg"
                                                                                                                                      alt=""></a></div>

                </div>
                <div class="col-sm-4 website_help center-block">
                    <h3>Website</h3>
                    <p><a href="${pageContext.request.contextPath}/admin/authentication-register.jsp">Bán hàng cùng chúng tôi</a></p>
                    <p><a href="">Tuyển dụng</a></p>
                    <p><a href="#">Điều khoản sử dụng</a></p>
                    <p><a href="#">Chính sách bảo mật</a></p>
                    <p><a href="#">Bảo vệ quyền sở hữu trí tuệ</a></p>
                    <p><a target="_blank" rel="noopener noreferrer" href="${pageContext.request.contextPath}/user/help.jsp">Trợ giúp <i class="ti-help"></i></a></p>
                </div>

                <div class="col-sm-4 row">
                    <h3 style="color: rgb(233, 152, 3);">Go where your heart beats</h3>
                    <h4>Tải ứng dụng <i class="ti-arrow-down"></i></h4>
                    <div class="row app_icon">
                        <p class="col-md-6"><a href="https://www.apple.com/store"><img src="${pageContext.request.contextPath}/images/app_store.png" alt=""></a></p>
                        <p class="col-md-6"><a href="https://play.google.com/"><img src="${pageContext.request.contextPath}/images/google_play.png" alt=""></a></p>
                    </div>

                    <p>FPT University CNC – Km29 Dai Lo Thang Long, H. Thach That, TP. Ha Noi</p>
                </div>

            </div>
            <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
            <div class="row payment">
                <div class="col-sm-6">
                    <h3 class="text-center">CÁCH THỨC THANH TOÁN</h3>
                    <img class="img-responsive col-sm-2" src="${pageContext.request.contextPath}/images/visa.png">
                    <img class="img-responsive col-sm-2" src="${pageContext.request.contextPath}/images/mastercard.png">
                    <img class="img-responsive col-sm-2" src="${pageContext.request.contextPath}/images/jbc.jpg">
                    <img class="img-responsive col-sm-2" src="${pageContext.request.contextPath}/images/cashonde.png">
                    <img class="img-responsive col-sm-2" src="${pageContext.request.contextPath}/images/napas.png">
                </div>

                <div class="col-sm-6">
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
        myFunction();
    };

    let mybutton = document.getElementById("myBtn");

    var navbar = document.getElementById("navbar");
    var sticky = navbar.offsetTop;

    function myFunction() {
        if (window.pageYOffset >= sticky) {
            navbar.classList.add("sticky")
        } else {
            navbar.classList.remove("sticky");
        }
    }





    function searchBtn() {
        var input = document.getElementById("search");
        if (input.value === "")
            alert("Please input keyword!");
        else
            document.getElementById("form_search").submit();
    }

</script>


</html>
