<%-- 
    Document   : index
    Created on : Feb 8, 2023, 4:37:29 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="robots" content="noindex,nofollow" />
        <title>Reviews</title>
        <link rel="icon" type="image/png" sizes="16x16" href="assets/images/logo.png" />
        <link href="assets/libs/flot/css/float-chart.css" rel="stylesheet" />
        <link href="dist/css/style.min.css" rel="stylesheet" />

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/apexcharts@3.25.0/dist/apexcharts.min.js"></script>
        <link href="dist/css/reviews.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <fmt:setLocale value = "vi_VN"/>
        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
        %>
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>

        <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="full"
             data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
            <header class="topbar" data-navbarbg="skin5">
                <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                    <div class="navbar-header" data-logobg="skin5">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/index">
                            <b class="logo-icon"  style="margin: 0px;">
                                <img src="assets/images/logo.png" alt="homepage" class="light-logo" width="50" />
                            </b>
                            <span class="logo-text " style="margin-right: 15px">
                                <img src="${pageContext.request.contextPath}/images/logo_text.png" alt="homepage" class="light-logo" width="140" height="50" />
                            </span>
                        </a>
                        <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i
                                class="ti-menu ti-close"></i></a>
                    </div>
                    <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
                        <ul class="navbar-nav float-start me-auto">
                            <li class="nav-item d-none d-lg-block">
                                <a class="nav-link sidebartoggler waves-effect waves-light" href="javascript:void(0)"
                                   data-sidebartype="mini-sidebar"><i class="mdi mdi-menu font-24"></i></a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown"
                                   aria-expanded="false">
                                    <span class="d-none d-md-block">Thêm mới  &nbsp;&nbsp;&nbsp;<i class="fa fa-angle-down"></i></span>
                                    <span class="d-block d-md-none"><i class="fa fa-plus"></i></span>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addsupplier">Thương hiệu</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addcategory">Danh mục</a></li>
                                    <li>
                                        <hr class="dropdown-divider" />
                                    </li>
                                    <li>
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addproduct">Sản phẩm</a>
                                    </li>
                                </ul>
                            </li>

                            <li class="nav-item search-box">
                                <a class="nav-link waves-effect waves-dark" href="javascript:void(0)"><i
                                        class="mdi mdi-magnify fs-4"></i></a>
                                <form class="app-search position-absolute" action="${pageContext.request.contextPath}/admin/searchcomments">
                                    <input type="text" name="productID" class="form-control" placeholder="Search All review by ProductID &amp; enter" />
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
                                   " href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <img src="data:image/jpg;base64,${sessionScope.admin.base64Image}" alt="user" class="rounded-circle" width="31" />
                                </a>
                                <ul class="dropdown-menu dropdown-menu-end user-dd animated" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/profileadmin"><i class="mdi mdi-account me-1 ms-1"></i> Hồ sơ của
                                        tôi</a>

                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/changepass.jsp"><i class="mdi mdi-settings me-1 ms-1"></i> Đổi mật
                                        khẩu</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/logoutadmin"><i class="fa fa-power-off me-1 ms-1"></i> Đăng
                                        xuất</a>
                                    <div class="dropdown-divider"></div>
                                    <div class="ps-4 p-10">
                                        <a href="${pageContext.request.contextPath}/admin/profileadmin" class="btn btn-sm btn-success btn-rounded text-white">View Profile</a>
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
                                    aria-expanded="false"
                                    ><i class="mdi mdi-view-dashboard"></i
                                    ><span class="hide-menu">Trang chủ</span></a
                                >
                            </li>
                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link waves-effect waves-dark sidebar-link"
                                    href="${pageContext.request.contextPath}/admin/charts"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-chart-bar"></i
                                    ><span class="hide-menu">Thống kê</span></a
                                >
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
                                    aria-expanded="false"
                                    ><i class="mdi mdi-format-list-bulleted"></i
                                    ><span class="hide-menu">Danh sách </span></a
                                >
                                <ul aria-expanded="false" class="collapse first-level">

                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/tables" class="sidebar-link"
                                           ><i class="mdi mdi-table"></i
                                            ><span class="hide-menu"> Tất cả </span></a
                                        >
                                    </li>

                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/listallaccounts" class="sidebar-link"
                                           ><i class="mdi mdi-account"></i
                                            ><span class="hide-menu"> Người dùng </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/listallproducts" class="sidebar-link"
                                           ><i class="fab fa-product-hunt"></i
                                            ><span class="hide-menu"> Sản phẩm </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/listallcategories" class="sidebar-link"
                                           ><i class="mdi mdi-group"></i
                                            ><span class="hide-menu"> Danh mục </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/listallshippers" class="sidebar-link"
                                           ><i class="mdi mdi-truck"></i
                                            ><span class="hide-menu"> Đơn vị vận chuyển </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/listallsuppliers" class="sidebar-link"
                                           ><i class="mdi mdi-human-greeting"></i
                                            ><span class="hide-menu"> Thương hiệu </span></a
                                        >
                                    </li>
                                </ul>
                            </li>

                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link has-arrow waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-playlist-plus"></i
                                    ><span class="hide-menu">Thêm mới </span></a
                                >
                                <ul aria-expanded="false" class="collapse first-level">
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/addproduct" class="sidebar-link"
                                           ><i class="fab fa-product-hunt"></i
                                            ><span class="hide-menu"> Sản phẩm </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/addcategory" class="sidebar-link"
                                           ><i class="mdi mdi-group"></i
                                            ><span class="hide-menu"> Danh mục </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/addshipper" class="sidebar-link"
                                           ><i class="mdi mdi-truck"></i
                                            ><span class="hide-menu"> Đơn vị vận chuyển </span></a
                                        >
                                    </li>
                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/addsupplier" class="sidebar-link"
                                           ><i class="mdi mdi-human-greeting"></i
                                            ><span class="hide-menu"> Thương hiệu </span></a
                                        >
                                    </li>
                                </ul>
                            </li>




                            <li class="sidebar-item">
                                <a
                                    class="sidebar-link has-arrow waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-account-key"></i
                                    ><span class="hide-menu">Xác thực</span></a
                                >
                                <ul aria-expanded="false" class="collapse first-level">
                                    <li class="sidebar-item">
                                        <a href="changepass.jsp" class="sidebar-link"
                                           ><i class="mdi mdi-key-change"></i
                                            ><span class="hide-menu"> Đổi mật khẩu </span></a
                                        >
                                    </li>

                                    <li class="sidebar-item">
                                        <a href="${pageContext.request.contextPath}/admin/profileadmin" class="sidebar-link"
                                           ><i class="mdi mdi-account-card-details"></i
                                            ><span class="hide-menu"> Hồ sơ </span></a
                                        >
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
                            <h4 class="page-title">Sản phẩm</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/index">Trang chủ</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">
                                            Bình luận
                                        </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="container-fluid">
                    <div class="row">
                        <!-- column -->
                        <div class="col-lg-12">

                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title"><a href="${pageContext.request.contextPath}/admin/comments">Bình luận (${requestScope.totalReview})</a> </h4>
                                    <hr>
                                </div>
                                <div class="comment-widgets scrollable">
                                    <!-- Comment Row -->
                                    <div id="content">
                                        <c:forEach items="${requestScope.top5Review}" var="p">
                                            <div class="products">
                                                <h4 class="" style="margin-left: 20px"><a href="${pageContext.request.contextPath}/user/item?pid=${p.productID}">${p.productName}</a> </h4>
                                                <c:forEach var="r" items="${p.reviews}">
                                                    <div class="d-flex flex-row comment-row mt-0">
                                                        <div class="p-2">
                                                            <img src="data:image/jpg;base64,${r.cus.base64Image}" alt="user" width="50" class="rounded-circle" />
                                                        </div>
                                                        <div class="comment-text w-100">
                                                            <h6 class="font-medium"><a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${r.cus.customerID}">${r.cus.customerName}</a></h6>
                                                            <span class="mb-3 d-block">(${r.rate} <i class="fas fa-star" style="color: gold;"></i>)${r.contentSend}
                                                                <c:if test="${r.status}"><i class="mdi mdi-check-circle"></i></c:if>
                                                                <c:if test="${!r.status}"><i class="mdi mdi-block-helper"></i></c:if>
                                                                </span>
                                                                <div class="comment-footer">
                                                                    <span class="text-muted float-end">${r.dateRate}</span>
                                                                <form action="" method="POST" id="review" name="review">
                                                                    <button type="button" onclick="changeReview('public', '${r.id}')" class="  btn btn-success btn-sm text-white <c:if test="${r.status}">disabled</c:if>">
                                                                            Publish
                                                                        </button>
                                                                        <button type="button" onclick="changeReview('hidden', '${r.id}')" class="btn btn-danger btn-sm text-white <c:if test="${!r.status}">disabled</c:if>">
                                                                            Hidden
                                                                        </button>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>

                                                </c:forEach>
                                                <hr>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div class="btn_loadded">
                                        <button class="btn_loadmore" onclick="loadMore()">
                                            <svg height="24" width="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M0 0h24v24H0z" fill="none"></path><path d="M5 13c0-5.088 2.903-9.436 7-11.182C16.097 3.564 19 7.912 19 13c0 .823-.076 1.626-.22 2.403l1.94 1.832a.5.5 0 0 1 .095.603l-2.495 4.575a.5.5 0 0 1-.793.114l-2.234-2.234a1 1 0 0 0-.707-.293H9.414a1 1 0 0 0-.707.293l-2.234 2.234a.5.5 0 0 1-.793-.114l-2.495-4.575a.5.5 0 0 1 .095-.603l1.94-1.832C5.077 14.626 5 13.823 5 13zm1.476 6.696l.817-.817A3 3 0 0 1 9.414 18h5.172a3 3 0 0 1 2.121.879l.817.817.982-1.8-1.1-1.04a2 2 0 0 1-.593-1.82c.124-.664.187-1.345.187-2.036 0-3.87-1.995-7.3-5-8.96C8.995 5.7 7 9.13 7 13c0 .691.063 1.372.187 2.037a2 2 0 0 1-.593 1.82l-1.1 1.039.982 1.8zM12 13a2 2 0 1 1 0-4 2 2 0 0 1 0 4z" fill="currentColor"></path></svg>
                                            <span>Load More</span>
                                        </button>
                                    </div>
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
            <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
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
            <script>
                                            function changeReview(type, id) {
                                                let text;
                                                if (type === 'hidden')
                                                    text = "ẩn";
                                                else
                                                    text = "hiển thị";
                                                if (confirm("Bạn có chắc muốn " + text + " bình luận này?") === true) {
                                                    document.getElementById("review").action = "${pageContext.request.contextPath}/admin/updatereview?rid=" + id + "&type=" + type;
                                                    document.getElementById("review").submit();
                                                }
                                            }

                                            function loadMore() {
                                                var amount = document.getElementsByClassName('products').length;

                                                $.ajax({
                                                    url: "${pageContext.request.contextPath}/admin/loadmorereviews",
                                                    type: "get",
                                                    data: {
                                                        total: amount
                                                    },

                                                    success: function (data) {
                                                        var row = document.getElementById('content');
                                                        row.innerHTML += data;
                                                    },
                                                    error: function (xhr) {
                                                        //Do Something to handle error
                                                    }
                                                });
                                            }

            </script>
    </body>
</html>
