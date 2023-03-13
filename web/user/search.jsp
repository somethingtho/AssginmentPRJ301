<%-- 
    Document   : mobile
    Created on : Jan 23, 2023, 4:36:42 AM
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
        <link href="${pageContext.request.contextPath}/css/products.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <title>Products</title>
    </head>

    <body>



        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
//prevents caching at the proxy server
        %>
        <fmt:setLocale value = "vi_VN"/>
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
                                    <p class="col-md-6">Tổng tiền(Tạm tính):  <fmt:formatNumber value=" ${Math.round((o.totalMoney)/1000)*1000}" type="currency"/></p> 

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

    <div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 phanloai">
                    <form action="${pageContext.request.contextPath}/user/search" id="form1">
                        <input type="hidden" name="key" value="${requestScope.key}" class="form-control" id="search" placeholder="Search...">
                        <div class="form-group">
                            <label for="orderby">Sắp xếp:</label>
                            <select name="orderby" class="form-control" id="orderby" style="width: 180px;" onchange="this.form.submit()">
                                <option selected="selected">Selected</option>
                                <option value="1" <c:if test="${requestScope.orderby == 1}">selected</c:if> >Theo tên a-z</option>
                                <option value="2" <c:if test="${requestScope.orderby == 2}">selected</c:if>>Giá giảm dần</option>
                                <option value="3" <c:if test="${requestScope.orderby == 3}">selected</c:if>>Giá tăng dần</option>
                                </select>
                            </div>

                            <hr style="height: 1px; background-color: black; color: black;">
                            <div style="padding: 10px;">

                                <div class="card">
                                    <div class="price-content">
                                        <div>
                                            <label class="min">Min</label>
                                            <p id="min-value" class="min">${requestScope.minPrice}</p>
                                    </div>

                                    <div>
                                        <label class="max">Max</label>
                                        <p id="max-value" class="max">${requestScope.maxPrice}</p>
                                    </div>
                                </div>

                                <div class="range-slider">
                                    <form action="${pageContext.request.contextPath}/user/search" >
                                        <input type="range" id="from" name="from" class="price_range" value="${requestScope.from}" min="${requestScope.minPrice}" max="${requestScope.maxPrice}" step="10000">
                                        <input type="range" id="to" name="to" class="price_range" value="${requestScope.to}" min="${requestScope.minPrice}" max="${requestScope.maxPrice}" step="10000">
                                        <button style="margin: 30px 0 0 0; background-color: orange; border: none; color: white; height: 30px; width: 280px;" 
                                                onchange="this.form.submit()">Submit</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-9 flashsale">
                    <h4 style="margin-top: 3rem;">Tìm thấy <span style="font-weight: bold">${requestScope.totalSearch}</span> kết quả với từ khoá "<span style="font-weight: bold">${requestScope.key}</span>"</h4>
                    <hr style="margin-right: 20px; margin-top: 0; height: 1px; background-color: black; color: black;">
                    <c:forEach items="${requestScope.listAllType}" var="p">
                        <a href="${pageContext.request.contextPath}/user/item?pid=${p.productID}" class="col-md-3">
                            <div class="pro-img">
                                <img src="data:image/jpg;base64,${p.base64Image}"/>
                            </div>
                            <p class="product-name">${p.productName}</p>
                            <div class="gia gia-sale"><fmt:formatNumber value = "${Math.round((p.unitPrice - p.unitPrice*p.discount)/1000)*1000}" type = "currency"/></div>
                            <p class="gia gia-goc"><fmt:formatNumber value = "${p.unitPrice}" type = "currency"/></p>
                            <div class="buy">BUY NOW</div>
                        </a>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>
    <c:set var="page" value="${requestScope.page}"/>
    <form action="${pageContext.request.contextPath}/user/${requestScope.type}">
        <ul class="pagination pager" style="display: flex; justify-content: center;">
            <li class="previous <c:if test="${requestScope.page == 1}">disabled</c:if>"><a href="${pageContext.request.contextPath}/user/search?key=${requestScope.key}&page=${page-1}"">Trang trước</a></li>
                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                <li style="margin: 0 5px;" class="${i==page?"active":""}"><a href="${pageContext.request.contextPath}/user/search?key=${requestScope.key}&page=${i}">${i}</a> </li>
                </c:forEach>
            <li class="next <c:if test="${requestScope.page == num}">disabled</c:if>"><a href="${pageContext.request.contextPath}/user/search?key=${requestScope.key}&page=${page+1}">Trang sau</a></li>
            </ul> 
        </form>   

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
                <p><a href="${pageContext.request.contextPath}/admin/authentication-register.jsp">Bán hàng cùng chúng tôi</a></p>
                <p><a href="">Tuyển dụng</a></p>
                <p><a href="#">Điều khoản sử dụng</a></p>
                <p><a href="#">Chính sách bảo mật</a></p>
                <p><a href="#">Bảo vệ quyền sở hữu trí tuệ</a></p>
                <p><a target="_blank" rel="noopener noreferrer" href="${pageContext.request.contextPath}/user/help.jsp">Trợ giúp <i class="ti-help"></i></a></p>
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
        <hr style="height: 1px; background-color: black; color: black; margin-bottom: 0;">
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
    <script>
        let minValue = document.getElementById("min-value");
        let maxValue = document.getElementById("max-value");

        function validateRange(minPrice, maxPrice) {
            minValue.innerHTML = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(minPrice);
            maxValue.innerHTML = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(maxPrice);
        }

        const inputElements = document.querySelectorAll(".price_range");

        inputElements.forEach((element) => {
            element.addEventListener("change", (e) => {
                let minPrice = parseInt(inputElements[0].value);
                let maxPrice = parseInt(inputElements[1].value);

                validateRange(minPrice, maxPrice);
            });
        });

        validateRange(inputElements[0].value, inputElements[1].value);

    </script>

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
