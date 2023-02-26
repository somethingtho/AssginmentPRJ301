<%-- 
    Document   : item
    Created on : Jan 23, 2023, 4:56:33 AM
    Author     : daova
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/item.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>${requestScope.titlePage}</title>
        <style>
            .slider {
                width: 500px;
                height: 300px;
                background-color: yellow;
                margin-left: auto;
                margin-right: auto;
                margin-top: 0px;
                text-align: center;
                overflow: hidden;
            }

            .image-container {
                width: 1500px;
                background-color: pink;
                height: 300px;
                clear: both;
                position: relative;
                -webkit-transition: left 2s;
                -moz-transition: left 2s;
                -o-transition: left 2s;
                transition: left 2s;
            }

            .slide {
                float: left;
                margin: 0px;
                padding: 0px;
                position: relative;
            }

            #slide-1:target~.image-container {
                left: 0px;
            }

            #slide-2:target~.image-container {
                left: -500px;
            }

            #slide-3:target~.image-container {
                left: -1000px;
            }

            .buttons {
                position: relative;
                top: -20px;
            }

            .buttons a {
                display: inline-block;
                height: 15px;
                width: 15px;
                border-radius: 50px;
            }
            .carousel-control.right{
                background-image: linear-gradient(to right,rgba(0,0,0,.0001) 0,rgba(0,0,0,.5) 1000%);
            }
            .carousel-control.left{
                background-image: linear-gradient(to right,rgba(0,0,0,.5) 0,rgba(0,0,0,.0001) 0%)
            }
        </style>
    </head>

    <%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0);
//prevents caching at the proxy server
    %>




    <body>

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




    <div class="container">
        <ul class="breadcrumb" style="margin: 20px 0 0 0; background-color: #fff">
            <li><a href="index">Trang chủ</a></li>
            <li><a href="${requestScope.type}">${requestScope.cate.categoryName}</a></li>
            <li><a href="${requestScope.type}?sid=${requestScope.sup.supplierID}">${requestScope.sup.companyName}</a></li>
            <li class="active">${requestScope.pro.productName}</li>
        </ul>
        <hr style="margin: 0; height: 1px; background-color: black; color: black;">

        <c:set var="pro" value="${requestScope.pro}"></c:set>
        <c:set var="cate" value="${requestScope.cate}"></c:set>
        <c:set var="proInfo" value="${requestScope.proInfo}"></c:set>


            <div class="row body-product">
                <div class="col-md-6 img-product">

                    <div class="container">
                        <div id="myCarousel" class="carousel slide" data-ride="carousel">
                            <div class="carousel-inner">
                            <c:set value="0" var="sample" scope="page"></c:set>
                            <c:forEach items="${requestScope.getAllImageByProductID}" var="img">
                                <div class="item <c:if test="${sample == 0}">active</c:if>">
                                    <img src="data:image/jpg;base64,${img.base64Image}" alt="img" style="width:500px;">
                                </div>
                                <c:set value="${sample = sample +1}" var="sample" scope="page"></c:set>
                            </c:forEach>
                        </div>

                        <!-- Left and right controls -->
                        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#myCarousel" data-slide="next" >
                            <span class="glyphicon glyphicon-chevron-right"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>


            </div>
            <div class="col-md-6 ">
                <h3>${pro.productName}</h3>
                <fmt:formatNumber value="${requestScope.rateAvg - (requestScope.rateAvg % 1 == 0 ? 0 : 0.5)}"  var="star" type="number" pattern="#" />
                <div>
                    <span>(${requestScope.rateAvg})</span>
                    <c:forEach begin="1" end="${star}" var="r">
                        <span class="fa fa-star checked"></span>
                    </c:forEach>
                    <c:forEach begin="1" end="${5-star}">
                        <span class="fa fa-star"></span>
                    </c:forEach>
                    <span>(${requestScope.getAllReview.size()} đánh giá)</span>
                </div>


                <div class="price-product">
                    <h4 class="price"> <del><fmt:formatNumber value = "${pro.unitPrice*1.2}" type = "currency"/> </del> &nbsp; &nbsp; <fmt:formatNumber value = "${pro.unitPrice*1.1}" type = "currency"/> </h4>
                </div>

                <p>Loại sản phẩm: ${cate.categoryName}</p>
                <p>${pro.productName}</p>
                <hr>
                <form name="f" action="" method="post">
                    <div style="margin: 20px 0;">
                        <label for="num">Nhập số lượng:</label>
                        <input id="num" style="text-align: center; padding: 4px; margin-left: 1rem" value="1" name="num" placeholder="Input"/>
                    </div>
                    <div class="btn_add" onclick="buy('${pro.productID}')"><button>Thêm vào giỏ hàng</button></div>
                </form>
                <hr>
                <p><i class="ti-check-box"></i> Bảo mật thông tin khách hàng 99%</p>
                <hr>
                <p><i class="ti-truck"></i> Giao hàng nhanh trong 7 ngày làm việc kể từ khi đặt hàng</p>
                <hr>
                <p><i class="ti-loop"></i> Đổi trả miễn phí trong 7 ngày đầu sử dụng</p>
                <hr>
                <p><i class="ti-credit-card"></i> Thanh toán nhanh gọn</p>
            </div>

        </div>
        <h3>Thông số kỹ thuật</h3>
        <h4>${pro.productName}</h4>
        <div>
            <table class="table table-bordered">
                <tbody>
                    <tr>
                        <td>Tên sản phẩm</td>
                        <td>${pro.productName}</td>
                    </tr>
                    <tr>
                        <td>Hãng sản xuất</td>
                        <td><a target="_blank" rel="noopener noreferrer" href="${sup.homePage}">${sup.companyName}</a></td>
                    </tr>
                    <tr>
                        <td>Kích thước</td>
                        <td>${proInfo.size}</td>
                    </tr>
                    <tr>
                        <td>Khối lượng</td>
                        <td>${proInfo.weight}</td>
                    </tr>
                    <tr>
                        <td>Chất liệu</td>
                        <td>${proInfo.substance}</td>
                    </tr>
                    <tr>
                        <td>CPU</td>
                        <td>${proInfo.cpu}</td>
                    </tr>
                    <tr>
                        <td>RAM</td>
                        <td>${proInfo.ram}</td>
                    </tr>
                    <tr>
                        <td>Màn hình</td>
                        <td>${proInfo.screen}</td>
                    </tr>
                    <tr>
                        <td>Camera</td>
                        <td>${proInfo.camera}</td>
                    </tr>
                    <tr>
                        <td>Card đồ hoạ</td>
                        <td>${proInfo.graphicsCard}</td>
                    </tr>
                    <tr>
                        <td>Bộ nhớ trong</td>
                        <td>${proInfo.hardDrive}</td>
                    </tr>
                    <tr>
                        <td>Hệ điều hành</td>
                        <td>${proInfo.os}</td>
                    </tr>
                    <tr>
                        <td>Dung lượng pin</td>
                        <td>${proInfo.batteryCapacity}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div>
            <divs style="display: flex; justify-content: space-between;">
                <h3>Đánh giá sản phẩm</h3>
        </div>

        <div>
            <form action="review" method="post">
                <input type="hidden" value="${sessionScope.account.acc.userName}" name="userName">
                <input type="hidden" value="${pro.productID}" name="productID">
                <div class="review_content">
                    <hr style="height:1px;border-width:0;color:gray;background-color:gray">
                    <div style="display: flex; justify-content: center;">
                        <img src="data:image/jpg;base64,${pro.base64Image}" alt="image"  style="width: 200px;"/>
                    </div>
                    <h4 class="text-center">${pro.productName}</h4>
                    <div style="display: flex;justify-content: space-around;">

                        <div>
                            <input type="radio" id="1star" name="star" value="1">
                            <label for="1star" style="font-size: 20px;">1<span class="fa fa-star checked"></span>
                            </label>
                        </div>
                        <div>
                            <input type="radio" id="2star" name="star" value="2">
                            <label for="2star" style="font-size: 20px;">2 <span
                                    class="fa fa-star checked"></span></label>
                        </div>
                        <div>
                            <input type="radio" id="3star" name="star" value="3">
                            <label for="3star" style="font-size: 20px;">3 <span
                                    class="fa fa-star checked"></span></label>
                        </div>
                        <div>
                            <input type="radio" id="4star" name="star" value="4">
                            <label for="4star" style="font-size: 20px;">4 <span
                                    class="fa fa-star checked"></span></label>
                        </div>

                        <div>
                            <input type="radio" id="5star" name="star" value="5">
                            <label for="5star" style="font-size: 20px;">5 <span
                                    class="fa fa-star checked"></span></label>
                        </div>
                    </div>
                    <div style="display: flex; justify-content: center;">
                        <textarea class="container" name="contentSend" id="" style="padding:20px; height:120px; margin: 20px 0;"
                                  placeholder="Hãy ghi cảm nhận của bạn....."></textarea>
                    </div>
                </div>

                <div class="btn_review" style="padding: 0;">
                    <button onclick="this.form.submit()" class="container" id="update">
                        Gửi
                        <i class="ti-check"></i>
                    </button>
                </div>
            </form>
        </div>


    </div>

    <hr>
    <c:forEach items="${requestScope.getAllReview}" var="review">
        <div class="media container" style="margin-bottom: 40px;">
            <div class="media-left">
                <img src="data:image/jpg;base64,${review.cus.base64Image}" class="media-object" style="width:60px">
            </div>
            <div class="media-body">
                <h4 class="media-heading">${review.acc.userName}</h4><span>${review.dateRate}</span>
                <p>${review.contentSend}</p>
                <c:forEach begin="1" end="${review.rate}" var="i">
                    <span class="fa fa-star checked"></span>
                </c:forEach>
                <c:forEach begin="1" end="${5-review.rate}">
                    <span class="fa fa-star"></span>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
    <hr>

    <div class="container flashsale">
        <h3>Sản phẩm tương tự</h3>
        <div class="row">
            <c:forEach items="${requestScope.getAllProductsSame}" var="same">
                <a href="item?pid=${same.productID}" class="col-md-3">
                    <div class="pro-img">
                        <img src="data:image/jpg;base64,${same.base64Image}"/>
                    </div>
                    <p class="product-name">${same.productName}</p>

                    <div class="gia gia-sale"><fmt:formatNumber value = "${same.unitPrice*1.1}" type = "currency"/></div>
                    <p class="gia gia-goc"><fmt:formatNumber value = "${same.unitPrice*1.2}" type = "currency"/></p>
                    <div class="buy">MUA NGAY</div>
                </a>
            </c:forEach>
        </div>
    </div>

</div>                
<hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">

<footer class="container-fluid">
    <div class="row">
        <div class="col-md-4 row">
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
    <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
    <div class="row payment">
        <div class="col-md-6">
            <h3 class="text-center">CÁCH THỨC THANH TOÁN</h3>
            <img class="img-responsive col-sm-2" src="${pageContext.request.contextPath}/images/visa.png">
            <img class="img-responsive col-sm-2" src="${pageContext.request.contextPath}/images/mastercard.png">
            <img class="img-responsive col-sm-2" src="${pageContext.request.contextPath}/images/jbc.jpg">
            <img class="img-responsive col-sm-2" src="${pageContext.request.contextPath}/images/cashonde.png">
            <img class="img-responsive col-sm-2" src="${pageContext.request.contextPath}/images/napas.png">
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
<script src="${pageContext.request.contextPath}/js/item.js" type="text/javascript"></script>

<script>

    function buy(id) {
        var m = document.f.num.value;
        document.f.action = "buy?pid=" + id + "&num=" + m;
        document.f.submit();
    }
    
    
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
