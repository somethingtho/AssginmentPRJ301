<%-- 
    Document   : reply
    Created on : Feb 25, 2023, 3:26:49 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="robots" content="noindex,nofollow" />
        <title>Home</title>
        <link rel="icon" type="image/png" sizes="16x16"
              href="assets/images/logo.png" />
        <link href="assets/libs/flot/css/float-chart.css" rel="stylesheet" />
        <link href="dist/css/style.min.css" rel="stylesheet" />

    </head>
    <body>

        <fmt:setLocale value="vi_VN"/>
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>

        <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5"
             data-sidebartype="full"
             data-sidebar-position="absolute" data-header-position="absolute"
             data-boxed-layout="full">
            <header class="topbar" data-navbarbg="skin5">
                <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                    <div class="navbar-header" data-logobg="skin5">
                        <a class="navbar-brand"
                           href="${pageContext.request.contextPath}/admin/index">
                            <b class="logo-icon" style="margin: 0px;">
                                <img src="assets/images/logo.png" alt="homepage"
                                     class="light-logo" width="50" />
                            </b>
                            <span class="logo-text " style="margin-right: 15px">
                                <img
                                    src="${pageContext.request.contextPath}/images/logo_text.png"
                                    alt="homepage" class="light-logo" width="140" height="50" />
                            </span>
                        </a>
                        <a class="nav-toggler waves-effect waves-light d-block d-md-none"
                           href="javascript:void(0)"><i
                                class="ti-menu ti-close"></i></a>
                    </div>
                    <div class="navbar-collapse collapse" id="navbarSupportedContent"
                         data-navbarbg="skin5">
                        <ul class="navbar-nav float-start me-auto">
                            <li class="nav-item d-none d-lg-block">
                                <a class="nav-link sidebartoggler waves-effect waves-light"
                                   href="javascript:void(0)"
                                   data-sidebartype="mini-sidebar"><i class="mdi mdi-menu
                                                                   font-24"></i></a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#"
                                   id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    <span class="d-none d-md-block">Thêm mới &nbsp;&nbsp;&nbsp;<i
                                            class="fa fa-angle-down"></i></span>
                                    <span class="d-block d-md-none"><i class="fa fa-plus"></i></span>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/admin/addsupplier">Thương
                                            hiệu</a></li>
                                    <li><a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/admin/addcategory">Danh
                                            mục</a></li>
                                    <li>
                                        <hr class="dropdown-divider" />
                                    </li>
                                    <li>
                                        <a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/admin/addproduct">Sản
                                            phẩm</a>
                                    </li>
                                </ul>
                            </li>

                            <li class="nav-item search-box">
                                <a class="nav-link waves-effect waves-dark"
                                   href="javascript:void(0)"><i
                                        class="mdi mdi-magnify fs-4"></i></a>
                                <form class="app-search position-absolute">
                                    <input type="text" class="form-control" placeholder="Search
                                           &amp; enter" />
                                    <a class="srh-btn"><i class="mdi mdi-window-close"></i></a>
                                </form>
                            </li>
                        </ul>
                        <ul class="navbar-nav float-end">
                            <li class="nav-item dropdown">
                                <a class="
                                   nav-link
                                   dropdown-toggle
                                   text-muted
                                   waves-effect waves-dark
                                   pro-pic
                                   " href="#" id="navbarDropdown" role="button"
                                   data-bs-toggle="dropdown" aria-expanded="false">
                                    <img
                                        src="data:image/jpg;base64,${sessionScope.admin.base64Image}"
                                        alt="user" class="rounded-circle" width="31" />
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end user-dd animated"
                                    aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/admin/profileadmin"><i
                                            class="mdi mdi-account me-1 ms-1"></i> Hồ sơ của
                                        tôi</a>

                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/admin/changepass.jsp"><i
                                            class="mdi mdi-settings me-1 ms-1"></i> Đổi mật
                                        khẩu</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/admin/logoutadmin"><i
                                            class="fa fa-power-off me-1 ms-1"></i> Đăng
                                        xuất</a>
                                    <div class="dropdown-divider"></div>
                                    <div class="ps-4 p-10">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/profileadmin"
                                            class="btn btn-sm btn-success btn-rounded text-white">View
                                            Profile</a>
                                    </div>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>
            <aside class="left-sidebar" data-sidebarbg="skin5">
                <div class="scroll-sidebar">
                    <nav class="sidebar-nav">
                        <ul id="sidebarnav" class="pt-4">
                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/index"
                                    aria-expanded="false"><i class="mdi mdi-view-dashboard"></i><span
                                        class="hide-menu">Trang chủ</span></a>
                            </li>
                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/charts"
                                    aria-expanded="false"><i class="mdi mdi-chart-bar"></i><span
                                        class="hide-menu">Thống kê</span></a>
                            </li>

                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/feedbacks"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-help-circle"></i
                                    ><span class="hide-menu">Feedbacks</span></a
                                >
                            </li>

                            
                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/orders"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-tag"></i
                                    ><span class="hide-menu">Orders</span></a
                                >
                            </li>
                            
                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/comments"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-comment-processing"></i
                                    ><span class="hide-menu">Reviews</span></a
                                >
                            </li>

                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link has-arrow waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    aria-expanded="false"><i class="mdi
                                                         mdi-format-list-bulleted"></i><span class="hide-menu">Danh
                                        sách </span></a>
                                <ul aria-expanded="false" class="collapse first-level">

                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/tables"
                                           class="sidebar-link"><i class="mdi mdi-table"></i><span
                                                class="hide-menu"> Tất cả </span></a>
                                    </li>

                                    <li class="sidebar-item">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/listallaccounts"
                                            class="sidebar-link"><i class="mdi mdi-account"></i><span
                                                class="hide-menu"> Người dùng </span></a>
                                    </li>
                                    <li class="sidebar-item">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/listallproducts"
                                            class="sidebar-link"><i class="fab fa-product-hunt"></i><span
                                                class="hide-menu"> Sản phẩm </span></a>
                                    </li>
                                    <li class="sidebar-item">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/listallcategories"
                                            class="sidebar-link"><i class="mdi mdi-group"></i><span
                                                class="hide-menu"> Danh mục </span></a>
                                    </li>
                                    <li class="sidebar-item">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/listallshippers"
                                            class="sidebar-link"><i class="mdi mdi-truck"></i><span
                                                class="hide-menu"> Đơn vị vận chuyển </span></a>
                                    </li>
                                    <li class="sidebar-item">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/listallsuppliers"
                                            class="sidebar-link"><i class="mdi mdi-human-greeting"></i><span
                                                class="hide-menu"> Thương hiệu </span></a>
                                    </li>
                                </ul>
                            </li>

                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link has-arrow waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    aria-expanded="false"><i class="mdi mdi-playlist-plus"></i><span
                                        class="hide-menu">Thêm mới </span></a>
                                <ul aria-expanded="false" class="collapse first-level">
                                    <li class="sidebar-item">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/addproduct"
                                            class="sidebar-link"><i class="fab fa-product-hunt"></i><span
                                                class="hide-menu"> Sản phẩm </span></a>
                                    </li>
                                    <li class="sidebar-item">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/addcategory"
                                            class="sidebar-link"><i class="mdi mdi-group"></i><span
                                                class="hide-menu"> Danh mục </span></a>
                                    </li>
                                    <li class="sidebar-item">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/addshipper"
                                            class="sidebar-link"><i class="mdi mdi-truck"></i><span
                                                class="hide-menu"> Đơn vị vận chuyển </span></a>
                                    </li>
                                    <li class="sidebar-item">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/addsupplier"
                                            class="sidebar-link"><i class="mdi mdi-human-greeting"></i><span
                                                class="hide-menu"> Thương hiệu </span></a>
                                    </li>
                                </ul>
                            </li>




                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link has-arrow waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    aria-expanded="false"><i class="mdi mdi-account-key"></i><span
                                        class="hide-menu">Xác thực</span></a>
                                <ul aria-expanded="false" class="collapse first-level">
                                    <li class="sidebar-item">
                                        <a href="changepass.jsp" class="sidebar-link"><i
                                                class="mdi mdi-key-change"></i><span
                                                class="hide-menu"> Đổi mật khẩu </span></a>
                                    </li>

                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/profileadmin" class="sidebar-link"><i class="mdi
                                                                                      mdi-account-card-details"></i><span class="hide-menu">
                                                Hồ sơ </span></a>
                                    </li>

                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
            </aside>
            <div class="page-wrapper">
                <div class="page-breadcrumb">
                    <div class="row">
                        <div class="col-12 d-flex no-block align-items-center">
                            <h4 class="page-title">Feedbacks</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/index">Trang chủ</a></li>
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/feedbacks">Feedback</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">
                                            Feedback (#${requestScope.listAllMessage.get(0).id})
                                        </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title">Chat Option</h4>
                                    <div class="chat-box scrollable" style="height: 475px">
                                        <!--chat Row -->
                                        <ul class="chat-list">
                                            <!--chat Row -->
                                            <c:forEach items="${requestScope.listAllMessage}" var="message">
                                                <li class="chat-item">
                                                    <c:if test="${not empty message.cus}">
                                                        <div class="chat-img">
                                                            <img src="data:image/jpg;base64,${message.cus.base64Image}" width="50" height="50" alt="user" />
                                                        </div>
                                                    </c:if>
                                                    
                                                    <c:if test="${empty message.cus}">
                                                        <div class="chat-img">
                                                            <img src="assets/images/users/d1.jpg" alt="user" />
                                                        </div>
                                                    </c:if>
                                                    <div class="chat-content">

                                                        <c:choose>
                                                            <c:when test="${not empty message.cus}">
                                                                <h6 class="font-medium"><a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${message.cus.customerID}">${message.cus.customerName}</a></h6>
                                                                </c:when>
                                                                <c:when test="${not empty message.supplier}"><a href="${pageContext.request.contextPath}/admin/profile?type=sup&id=${message.supplier.supplierID}">${message.supplier.companyName}</a></c:when>
                                                                <c:when test="${not empty message.shipper}"><a href="${pageContext.request.contextPath}/admin/profile?type=ship&id=${message.shipper.shipperID}">${message.shipper.companyName}</a></c:when>
                                                                <c:otherwise><h6 class="font-medium">${message.email}</h6></c:otherwise>
                                                        </c:choose>
                                                        <div class="box bg-light-info">
                                                            ${message.contentSend}
                                                        </div>
                                                    </div>
                                                    <div class="chat-time">${message.dateSend}</div>
                                                </li>
                                                <!--chat Row -->
                                                <li class="odd chat-item">
                                                    <c:if test="${not empty message.contentRep}">
                                                        <div class="chat-content">
                                                            <div class="box bg-light-inverse">
                                                                ${message.contentRep}
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <div class="chat-time">${message.dateRep}</div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <c:if test="${empty requestScope.listAllMessage.get(requestScope.listAllMessage.size()-1).contentRep}">
                                    <div class="card-body border-top">
                                        <form action="${pageContext.request.contextPath}/admin/reply" method="POST">
                                            <input type="hidden" value="${requestScope.listAllMessage.get(requestScope.listAllMessage.size()-1).id}" name="id">
                                            <div class="row">
                                                <div class="col-9">
                                                    <div class="input-field mt-0 mb-0">
                                                        <textarea
                                                            id="textarea1"
                                                            name="contentRep"
                                                            placeholder="Type and enter"
                                                            class="form-control border-0"
                                                            ></textarea>
                                                    </div>
                                                </div>
                                                <div class="col-3">
                                                    <button type="submit"
                                                            class="btn-circle btn-lg btn-cyan float-end text-white"
                                                            href="javascript:void(0)"
                                                            ><i class="mdi mdi-send fs-3"></i
                                                        ></button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <footer class="footer text-center">

                </footer>
            </div>
        </div>
        <script src="assets/libs/jquery/dist/jquery.min.js"></script>
        <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <script
        src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
        <script src="assets/extra-libs/sparkline/sparkline.js"></script>
        <script src="dist/js/waves.js"></script>
        <script src="dist/js/sidebarmenu.js"></script>
        <!--Custom JavaScript -->
        <script src="dist/js/custom.min.js"></script>
        <!--This page JavaScript -->
        <!-- <script src="dist/js/pages/dashboards/dashboard1.js"></script> -->
        <!-- Charts js Files -->
        <script src="assets/libs/flot/excanvas.js"></script>
        <script src="assets/libs/flot/jquery.flot.js"></script>
        <script src="assets/libs/flot/jquery.flot.pie.js"></script>
        <script src="assets/libs/flot/jquery.flot.time.js"></script>
        <script src="assets/libs/flot/jquery.flot.stack.js"></script>
        <script src="assets/libs/flot/jquery.flot.crosshair.js"></script>
        <script src="assets/libs/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
        <script src="dist/js/pages/chart/chart-page-init.js"></script>
    </body>
</html>
