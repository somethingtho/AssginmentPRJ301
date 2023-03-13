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
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <title>${requestScope.titlePage}</title>

    </head>


    <%
        response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0
        response.setDateHeader("Expires", 0);
        //prevents caching at the proxy server
    %>



    <body>

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




    <div class="container">
        <ul class="breadcrumb" style="margin: 20px 0 0 0; background-color: #fff">
            <li><a href="${pageContext.request.contextPath}/user/index">Trang chủ</a></li>
            <li><a href="${pageContext.request.contextPath}/user/${requestScope.type}">${requestScope.cate.categoryName}</a></li>
            <li><a href="${pageContext.request.contextPath}/user/${requestScope.type}?sid=${requestScope.sup.supplierID}">${requestScope.sup.companyName}</a></li>
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
                            <ol class="carousel-indicators">

                            <c:forEach var="i" begin="0" end="${requestScope.getAllImageByProductID.size()-1}">
                                <li style="border: 1px solid black;" data-target="#myCarousel" data-slide-to="${i}"
                                    class="<c:if test="${i == 0}">active</c:if>"></li>
                                </c:forEach>

                        </ol>
                        <div class="carousel-inner">
                            <c:set value="0" var="sample" scope="page"></c:set>
                            <c:forEach items="${requestScope.getAllImageByProductID}" var="img">
                                <div class="item <c:if test="${sample == 0}">active</c:if>">
                                    <img src="data:image/jpg;base64,${img.base64Image}" alt="img" style="width:500px;">
                                </div>
                                <c:set value="${sample +1}" var="sample" scope="page"></c:set>
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
                    <span>(${requestScope.totalReview} đánh giá)</span>
                </div>


                <div class="price-product">
                    <h4 class="price"> <del><fmt:formatNumber value = "${pro.unitPrice}" type = "currency"/> </del> &nbsp; &nbsp; 
                        <span style="color: red">(<fmt:formatNumber value = "${pro.discount}" type = "percent"/>)
                        </span> <br><fmt:formatNumber value = "${Math.round((pro.unitPrice- pro.unitPrice*pro.discount)/1000)*1000}" type = "currency"/> </h4>
                </div>

                <p>Loại sản phẩm: ${cate.categoryName}</p>
                <p>${pro.productName}</p>
                <c:if test="${requestScope.type eq 'mobile'}">
                    <hr>
                    <div class="row">
                        <div class="col-md-6">
                            <label for="ram_select">Ram</label>
                            <select class="form-control" id="ram_select">
                                <option>${proInfo.ram}</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label for="memory">Bộ nhớ</label>
                            <select class="form-control" id="memory">
                                <option>${proInfo.hardDrive}</option>
                            </select>
                        </div>
                    </div>
                </c:if>
                <hr>
                <form name="f" action="" method="post" class="form_buy">
                    <div style="margin: 20px 0;">
                        <label for="num">Nhập số lượng:</label>
                        <input id="num" style="text-align: center; padding: 4px; margin-left: 1rem" value="1" name="num" type="number" placeholder="Input" min="1" max="${pro.unitsInStock}"/>
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
                <button class="btn_review-submit js-update s-full-width">Đánh giá</button>
        </div>
        <hr>
        <div>
            <div class="col-md-6">
                <h3 class="text-center">Đánh giá trung bình</h3>
                <h3 class="text-center text-danger">${requestScope.rateAvg}/5</h3>
                <div class="text-center">
                    <c:forEach begin="1" end="${star}" var="r">
                        <span class="fa fa-star checked"></span>
                    </c:forEach>
                    <c:forEach begin="1" end="${5-star}">
                        <span class="fa fa-star"></span>
                    </c:forEach>
                </div>
                <h4 class="text-center text-danger">${requestScope.totalReview} Đánh giá</h4>
            </div>
            <div class="col-md-6">
                <c:set var="numberStar" value="5"></c:set>
                <c:forEach items="${requestScope.numberReview}" var="i">
                    ${numberStar} <span class="fa fa-star checked"></span>( ${i} Đánh giá )<div class="progress">
                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="<fmt:formatNumber value = "${i*100/requestScope.numberReview.size() -1}" />" aria-valuemin="0" aria-valuemax="100" style="width:<fmt:formatNumber value = "${i*100/requestScope.numberReview.size() -1}" />%">
                        </div>
                    </div>
                    <c:set var="numberStar" value="${numberStar - 1}"></c:set>
                </c:forEach>
            </div>

        </div>

        <div>
            <div>

                <div class="modal js-modal">
                    <div class="modal-container js-modal-container">

                        <div class="modal-close js-modal-close">
                            <i class="ti-close"></i>
                        </div>

                        <header class="modal-header">
                            <i class="modal-heading-icons ti-comment-alt"></i>
                            Gửi đánh giá
                        </header>

                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/user/review" method="post" id="form_review">
                                <input type="hidden" value="${sessionScope.account.acc.userName}" name="userName">
                                <input type="hidden" value="${pro.productID}" name="productID">
                                <div style="display: flex; justify-content: center;">
                                    <img src="data:image/jpg;base64,${pro.base64Image}" alt="image"  style="width: 200px;"/>
                                </div>
                                <h4 class="text-center">${pro.productName}</h4>
                                <div style="display: flex; justify-content: center">
                                    <div class="rating">
                                        <input type="radio" name="rating"
                                               id="star1" value="1">
                                        <label for="star1" id="star1-label">&#9733;</label>
                                        <input type="radio" name="rating"
                                               id="star2" value="2">
                                        <label for="star2" id="star2-label">&#9733;</label>
                                        <input type="radio" name="rating"
                                               id="star3" value="3">
                                        <label for="star3" id="star3-label">&#9733;</label>
                                        <input type="radio" name="rating"
                                               id="star4" value="4">
                                        <label for="star4" id="star4-label">&#9733;</label>
                                        <input type="radio" name="rating"
                                               id="star5" value="5">
                                        <label for="star5" id="star5-label">&#9733;</label>
                                    </div>
                                </div>
                                <div class="row">

                                    <div class="col-md-6">
                                        <i class="ti-user"></i>
                                        Tên Đăng Nhập:
                                        <input class="modal-input "
                                               placeholder="Name" value="${sessionScope.account.customerName}" readonly>
                                    </div>
                                    <div class="col-md-6">
                                        <i class="ti-email"></i>
                                        Email:
                                        <input id="email" class="modal-input "
                                               placeholder="Email"
                                               readonly value="${sessionScope.account.email}">
                                    </div>
                                </div>

                                <div>
                                    <textarea class="container modal-input" name="contentSend" id="" style="padding:20px; height:80px; margin: 10px 0;"
                                              placeholder="Hãy ghi cảm nhận của bạn....." required=""></textarea>

                                </div>

                                <div class="group_btn_review">

                                    <button type="button" id="update" onclick="checkInput()">
                                        <div class="svg-wrapper-1">
                                            <div class="svg-wrapper">
                                                <svg height="24" width="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                                <path d="M0 0h24v24H0z" fill="none"></path>
                                                <path d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z" fill="currentColor"></path>
                                                </svg>
                                            </div>
                                        </div>
                                        <span>Send</span>
                                    </button>

                                    <button type="button" id="cancel" class="js-modal-cancel noselect" ><span class="text">Cancel</span><span class="icon"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"><path d="M24 20.188l-8.315-8.209 8.2-8.282-3.697-3.697-8.212 8.318-8.31-8.203-3.666 3.666 8.321 8.24-8.206 8.313 3.666 3.666 8.237-8.318 8.285 8.203z"></path></svg></span></button>
                                </div>

                            </form>
                        </div>

                    </div>
                </div>


            </div>    

        </div>


    </div>
    <hr>
    <c:if test="${not empty sessionScope.account}">
        <div class="container">
            <h3 class="">Bình luận của bạn</h3>
            <hr>
            <c:forEach items="${requestScope.getReviewByAccount}" var="review">
                <div class="media reviewbyid container" style="margin-bottom: 40px;">
                    <div class="media-left">
                        <img src="data:image/jpg;base64,${review.cus.base64Image}" class="media-object" style="width:60px">
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">${review.acc.userName}</h4><span>${review.dateRate}</span>
                        <p>
                            <span class="review-content review-content_byid">${review.contentSend}</span> 

                            <button class="btn" onclick="confirmDeleteReview(${review.id}, ${pro.productID})">
                                <svg viewBox="0 0 15 17.5" height="17.5" width="15" xmlns="http://www.w3.org/2000/svg" class="icon">
                                <path transform="translate(-2.5 -1.25)" d="M15,18.75H5A1.251,1.251,0,0,1,3.75,17.5V5H2.5V3.75h15V5H16.25V17.5A1.251,1.251,0,0,1,15,18.75ZM5,5V17.5H15V5Zm7.5,10H11.25V7.5H12.5V15ZM8.75,15H7.5V7.5H8.75V15ZM12.5,2.5h-5V1.25h5V2.5Z" id="Fill"></path>
                                </svg>
                            </button>


                            <button onclick="toggleReviewEdit(${review.id}, ${pro.productID})" class="edit_review">
                                <svg class="css-i6dzq1" stroke-linejoin="round" stroke-linecap="round" fill="none" stroke-width="2" stroke="#FFFFFF" height="24" width="24" viewBox="0 0 24 24">
                                <path d="M17 3a2.828 2.828 0 1 1 4 4L7.5 20.5 2 22l1.5-5.5L17 3z"></path>
                                </svg>
                                Edit
                            </button>
                        </p>
                        <div class="review-edit" id="review${review.id}" style="display:none">
                            <form action="${pageContext.request.contextPath}/user/updatereview" id="updatereview" method="GET">

                                <input type="hidden" name="type" value="update">
                                <input type="hidden" name="pid" value="${pro.productID}">
                                <input type="hidden" name="id" value="${review.id}">
                                <textarea class="review-content-edit" name="contentSendc">${review.contentSend}</textarea>
                                <div class="ratingc">
                                    <input type="radio" name="ratingc"
                                           id="star1c" value="1" <c:if test="${review.rate == 1}">checked</c:if>>
                                           <label for="star1c"  id="star1c-label">1<i class="fa-star fa checked"></i></label>
                                           <input type="radio" name="ratingc"
                                                  id="star2c" value="2" <c:if test="${review.rate == 2}">checked</c:if>>
                                           <label for="star2c"  id="star1c-label">2<i class="fa-star fa checked" style=""></i></label>
                                           <input type="radio" name="ratingc"
                                                  id="star3c" value="3" <c:if test="${review.rate == 3}">checked</c:if>>
                                           <label for="star3c"  id="star1c-label">3<i class="fa-star fa checked"></i></label>
                                           <input type="radio" name="ratingc"
                                                  id="star4c" value="4" <c:if test="${review.rate == 4}">checked</c:if>>
                                           <label for="star4c"  id="star1c-label">4<i class="fa-star fa checked"></i></label>
                                           <input type="radio" name="ratingc"
                                                  id="star5c" value="5" <c:if test="${review.rate == 5}">checked</c:if>>
                                           <label for="star5c"  id="star1c-label">5<i class="fa-star fa checked"></i></label>
                                    </div>
                                    <button type="button" class="save-review" data-review-id="${review.id}" onclick="saveReviewChanges(${review.id})"></button>
                            </form>
                        </div>
                        <c:forEach begin="1" end="${review.rate}" var="i">
                            <span class="fa fa-star checked"></span>
                        </c:forEach>
                        <c:forEach begin="1" end="${5-review.rate}">
                            <span class="fa fa-star"></span>
                        </c:forEach>
                    </div>
                </div>
                <hr>
            </c:forEach>
        </div>
    </c:if>

    <hr>
    <div id="content_review" class="content_review container">
        <h3 class="">Tất cả bình luận</h3>
        <hr>
        <c:forEach items="${requestScope.getAllReview}" var="review">
            <div class="media review container" style="margin-bottom: 40px;">
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
    </div>
    <div style="display: grid; place-items: center;">
        <button class="button" onclick="loadMore()">
            Loadmore
        </button>
    </div>
    <hr>

    <div class="container flashsale">
        <h3>Sản phẩm tương tự</h3>
        <div class="row">
            <c:forEach items="${requestScope.getAllProductsSame}" var="same">
                <a href="${pageContext.request.contextPath}/user/item?pid=${same.productID}" class="col-sm-3">
                    <span class="discount"><fmt:formatNumber value = "${same.discount}" type = "percent"/></span>
                    <div class="pro-img">
                        <img src="data:image/jpg;base64,${same.base64Image}"/>
                    </div>
                    <p class="product-name">${same.productName}</p>

                    <div class="gia gia-sale"><fmt:formatNumber value = "${Math.round((same.unitPrice - same.unitPrice*same.discount)/1000)*1000}" type = "currency"/></div>
                    <p class="gia gia-goc"><fmt:formatNumber value = "${same.unitPrice}" type = "currency"/></p>
                    <div class="buy">MUA NGAY</div>
                </a>
            </c:forEach>
        </div>
    </div>

</div>                
<hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">

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
<script src="${pageContext.request.contextPath}/js/item.js" type="text/javascript"></script>

<script>

            function buy(id) {
                var m = document.f.num.value;
                document.f.action = "buy?pid=" + id + "&num=" + m;
                if (m > ${pro.unitsInStock} || m <= 0) {
                    return;
                }
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

            function checkInput() {
                // Get the form element
                var form = document.getElementById("form_review");

                // Get the input elements
                var ratingInputs = form.elements["rating"];
                var contentSendInput = form.elements["contentSend"];

                // Check if a rating option is selected
                var ratingSelected = false;
                for (var i = 0; i < ratingInputs.length; i++) {
                    if (ratingInputs[i].checked) {
                        ratingSelected = true;
                        break;
                    }
                }
                if (!ratingSelected) {
                    alert("Please select a rating option.");
                    return;
                }

                // Check if the contentSend input is not empty
                if (contentSendInput.value.trim() == "") {
                    alert("Please enter your review.");
                    return;
                }

                // Submit the form if all inputs are valid
                form.submit();
            }




            const updateBtns = document.querySelectorAll('.js-update')
            const modal = document.querySelector('.js-modal')
            const modalClose = document.querySelector('.js-modal-close')
            const modalCancel = document.querySelector('.js-modal-cancel')
            const modalContainer = document.querySelector('.js-modal-container')
            function showUpdate() {
                modal.classList.add('open')
            }

            function hideUpdate() {
                modal.classList.remove('open')
            }

            for (const updateBtn of updateBtns) {
                updateBtn.addEventListener('click', showUpdate)
            }

            modalClose.addEventListener('click', hideUpdate)

            modalCancel.addEventListener('click', hideUpdate)

            modal.addEventListener('click', hideUpdate)

            modalContainer.addEventListener('click', function (event) {
                event.stopPropagation()
            })



            $(document).ready(function () {
                // Listen for changes to the rating input
                $("input[name='rating']").change(function () {
                    // Get the selected rating value
                    var rating = parseInt($(this).val());
                    // Set the color of the stars based on the selected rating
                    for (var i = 1; i <= rating; i++) {
                        $("#star" + i + "-label").addClass("gold");
                    }
                    for (var j = rating + 1; j <= 5; j++) {
                        $("#star" + j + "-label").removeClass("gold");
                    }
                });
            });



            function loadMore() {
                const amount = document.getElementsByClassName('review').length;
                $.ajax({
                    url: "${pageContext.request.contextPath}/user/loadmorereview",
                    type: "get",
                    data: {
                        total: amount,
                        pid: ${pro.productID}

                    },
                    success: function (data) {
                        var row = document.getElementById('content_review');
                        row.innerHTML += data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }


            function toggleReviewEdit(reviewId) {
                const reviewEdit = document.getElementById('review' + reviewId);
                console.log(reviewId);
                if (reviewEdit) {
                    reviewEdit.style.display = reviewEdit.style.display === "none" ? "block" : "none";
                }
            }

            function confirmDeleteReview(reviewId, pid) {
                if (confirm("Are you sure you want to delete this review?"))
                    window.location.href = "${pageContext.request.contextPath}/user/updatereview?type=delete&id=" + reviewId + "&pid=" + pid;
            }

            function saveReviewChanges(id) {
                var form = document.getElementById("updatereview");

                // Get the input elements
                var ratingInputs = form.elements["ratingc"];
                var contentSendInput = form.elements["contentSendc"];

                // Check if a rating option is selected
                var ratingSelected = false;
                for (var i = 0; i < ratingInputs.length; i++) {
                    if (ratingInputs[i].checked) {
                        ratingSelected = true;
                        break;
                    }
                }
                if (!ratingSelected) {
                    alert("Please select a rating option.");
                    return;
                }

                // Check if the contentSend input is not empty
                if (contentSendInput.value.trim() === "") {
                    alert("Please enter your review.");
                    return;
                }
                if (confirm("Are you sure you want to change this review?")) {
                    document.getElementById('updatereview').submit();
                }
            }


</script>
</body>
</html>
