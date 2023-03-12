<%-- 
    Document   : charts
    Created on : Feb 8, 2023, 8:02:27 PM
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
        <title>Charts</title>
        <!-- Favicon icon -->
        <link
            rel="icon"
            type="image/png"
            sizes="16x16"
            href="assets/images/logo.png"
            />
        <!-- Custom CSS -->
        <link href="assets/libs/flot/css/float-chart.css" rel="stylesheet" />
        <link href="dist/css/style.min.css" rel="stylesheet" />

        <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />-->

        <script src="https://cdn.jsdelivr.net/npm/apexcharts@3.28.2/dist/apexcharts.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/apexcharts@3.25.0/dist/apexcharts.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body onload="drawChart()">
        <!-- ============================================================== -->
        <!-- Preloader - style you can find in spinners.css -->
        <!-- ============================================================== -->
        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>
        <!-- ============================================================== -->
        <!-- Main wrapper - style you can find in pages.scss -->
        <!-- ============================================================== -->
        <div
            id="main-wrapper"
            data-layout="vertical"
            data-navbarbg="skin5"
            data-sidebartype="full"
            data-sidebar-position="absolute"
            data-header-position="absolute"
            data-boxed-layout="full"
            >
            <!-- ============================================================== -->
            <!-- Topbar header - style you can find in pages.scss -->
            <!-- ============================================================== -->
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
                    <!-- ============================================================== -->
                    <!-- End Logo -->
                    <!-- ============================================================== -->
                    <div
                        class="navbar-collapse collapse"
                        id="navbarSupportedContent"
                        data-navbarbg="skin5"
                        >
                        <!-- ============================================================== -->
                        <!-- toggle and nav items -->
                        <!-- ============================================================== -->
                        <ul class="navbar-nav float-start me-auto">
                            <li class="nav-item d-none d-lg-block">
                                <a
                                    class="nav-link sidebartoggler waves-effect waves-light"
                                    href="javascript:void(0)"
                                    data-sidebartype="mini-sidebar"
                                    ><i class="mdi mdi-menu font-24"></i
                                    ></a>
                            </li>
                            <!-- ============================================================== -->
                            <!-- create new -->
                            <!-- ============================================================== -->
                            <li class="nav-item dropdown">
                                <a
                                    class="nav-link dropdown-toggle"
                                    href="#"
                                    id="navbarDropdown"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                                    >
                                    <span class="d-none d-md-block"
                                          >Thêm <i class="fa fa-angle-down"></i
                                        ></span>
                                    <span class="d-block d-md-none"
                                          ><i class="fa fa-plus"></i
                                        ></span>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addsupplier">Thương hiệu</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addcategory">Danh mục</a></li>
                                    <li><hr class="dropdown-divider" /></li>
                                    <li>
                                        <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/addproduct">Sản phẩm</a>
                                    </li>
                                </ul>
                            </li>
                            <!-- ============================================================== -->
                            <!-- Search -->
                            <!-- ============================================================== -->
                            <li class="nav-item search-box">
                                <a
                                    class="nav-link waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    ><i class="mdi mdi-magnify fs-4"></i
                                    ></a>
                                <form class="app-search position-absolute">
                                    <input
                                        type="text"
                                        class="form-control"
                                        placeholder="Search &amp; enter"
                                        />
                                    <a class="srh-btn"><i class="mdi mdi-window-close"></i></a>
                                </form>
                            </li>
                        </ul>
                        <!-- ============================================================== -->
                        <!-- Right side toggle and nav items -->
                        <!-- ============================================================== -->
                        <ul class="navbar-nav float-end">
                            <!-- ============================================================== -->
                            <!-- Comment -->
                            <!-- ============================================================== -->

                            <!-- ============================================================== -->
                            <!-- End Comment -->
                            <!-- ============================================================== -->
                            <!-- ============================================================== -->
                            <!-- Messages -->
                            <!-- ============================================================== -->

                            <!-- ============================================================== -->
                            <!-- End Messages -->
                            <!-- ============================================================== -->

                            <!-- ============================================================== -->
                            <!-- User profile and search -->
                            <!-- ============================================================== -->
                            <li class="nav-item dropdown">
                                <a
                                    class="nav-link dropdown-toggle text-muted waves-effect waves-dark pro-pic"
                                    href="#"
                                    id="navbarDropdown"
                                    role="button"
                                    data-bs-toggle="dropdown"
                                    aria-expanded="false"
                                    >
                                    <img
                                        src="data:image/jpg;base64,${sessionScope.admin.base64Image}"
                                        alt="user"
                                        class="rounded-circle"
                                        width="31"
                                        />
                                </a>
                                <ul
                                    class="dropdown-menu dropdown-menu-end user-dd animated"
                                    aria-labelledby="navbarDropdown"
                                    >
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/profileadmin"
                                       ><i class="mdi mdi-account me-1 ms-1"></i> Thông tin của
                                        tôi</a
                                    >
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/changepass.jsp"
                                       ><i class="mdi mdi-settings me-1 ms-1"></i> Đổi mật khẩu</a
                                    >

                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/logoutadmin"
                                       ><i class="fa fa-power-off me-1 ms-1"></i> Đăng xuất</a
                                    >
                                    <div class="dropdown-divider"></div>
                                    <div class="ps-4 p-10">
                                        <a
                                            href="${pageContext.request.contextPath}/admin/profileadmin"
                                            class="btn btn-sm btn-success btn-rounded text-white"
                                            >Xem hồ sơ</a
                                        >
                                    </div>
                                </ul>
                            </li>
                            <!-- ============================================================== -->
                            <!-- User profile and search -->
                            <!-- ============================================================== -->
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- ============================================================== -->
            <!-- End Topbar header -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <aside class="left-sidebar" data-sidebarbg="skin5">
                <!-- Sidebar scroll-->
                <div class="scroll-sidebar">
                    <!-- Sidebar navigation-->
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
                            <li class="sidebar-item selected">
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
                                        <a href="${pageContext.request.contextPath}/admin/changepass.jsp" class="sidebar-link"
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
                    <!-- End Sidebar navigation -->
                </div>
                <!-- End Sidebar scroll-->
            </aside>
            <!-- ============================================================== -->
            <!-- End Left Sidebar - style you can find in sidebar.scss  -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Page wrapper  -->
            <!-- ============================================================== -->
            <div class="page-wrapper">
                <!-- ============================================================== -->
                <!-- Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <div class="page-breadcrumb">
                    <div class="row">
                        <div class="col-12 d-flex no-block align-items-center">
                            <h4 class="page-title">Charts</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/index">Trang chủ</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">
                                            Thống kê
                                        </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container-fluid">
                    <!-- ============================================================== -->
                    <!-- Start Page Content -->
                    <!-- ============================================================== -->
                    <!-- Chart-1 -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Real Time Chart</h5>
                                    <div id="chart1"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- ENd chart-1 -->
                    <!-- Chart-2 -->

                    <!-- Cards -->
                    <div class="row">
                        <div class="col-md-3">
                            <div class="card mt-0">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="peity_line_neutral left text-center mt-2">
                                            <span
                                                ><span style="display: none">10,15,8,14,13,10,10</span>
                                                <canvas width="50" height="24"></canvas>
                                            </span>
                                            <h6><fmt:formatNumber type = "percent" maxIntegerDigits="3" value = "${requestScope.rateNewAccount}" /></h6>
                                        </div>
                                    </div>
                                    <div class="col-md-6 border-left text-center pt-2">
                                        <h3 class="mb-0 fw-bold">${requestScope.newAccount}</h3>
                                        <span class="text-muted">New Users</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card mt-0">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="peity_bar_bad left text-center mt-2">
                                            <span
                                                ><span style="display: none">3,5,6,16,8,10,6</span>
                                                <canvas width="50" height="24"></canvas>
                                            </span>
                                            <h6><fmt:formatNumber type = "percent" maxIntegerDigits="3" value = "${requestScope.rateOrders}" /></h6>
                                        </div>
                                    </div>
                                    <div class="col-md-6 border-left text-center pt-2">
                                        <h3 class="mb-0 fw-bold">${requestScope.newOrders}</h3>
                                        <span class="text-muted">Orders</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card mt-0">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="peity_line_good left text-center mt-2">
                                            <span
                                                ><span style="display: none">12,6,9,23,14,10,17</span>
                                                <canvas width="50" height="24"></canvas>
                                            </span>
                                            <h6><fmt:formatNumber type = "percent" maxIntegerDigits="3" value = "${requestScope.rateActive}" /></h6>
                                        </div>
                                    </div>
                                    <div class="col-md-6 border-left text-center pt-2">
                                        <h3 class="mb-0">${requestScope.active}</h3>
                                        <span class="text-muted">Active Users</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card mt-0">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="peity_bar_good left text-center mt-2">
                                            <span>12,6,9,23,14,10,13</span>
                                            <h6><fmt:formatNumber type = "percent" maxIntegerDigits="3" value = "${requestScope.rateNewAccount}" /></h6>
                                        </div>
                                    </div>
                                    <div class="col-md-6 border-left text-center pt-2">
                                        <h3 class="mb-0 fw-bold">${requestScope.newAccount}</h3>
                                        <span class="text-muted">Register</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End cards -->
                    <!-- Chart-3 -->
                    <div class="row" style="margin: 20px 0;">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Chart</h5>
                                    <form action="${pageContext.request.contextPath}/admin/charts" method="get">
                                        <select name="year" onchange="this.form.submit()">
                                            <optgroup label="Year">
                                                <option value="2023" <c:if test="${requestScope.year == 2023}">selected</c:if>>2023</option>
                                                <option value="2022" <c:if test="${requestScope.year == 2022}">selected</c:if>>2022</option>
                                            </select>
                                        </form>
                                        <div class="flot-chart">
                                            <div id="chart2"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End chart-3 -->
                        <!-- Charts -->
                        <div class="row">
                            <div class="col-md-5">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title"><a href="${pageContext.request.contextPath}/admin/listallproducts">Number Of Devices(${requestScope.totalProducts})</a></h5>
                                    <canvas id="myChart2" style="width:100%;max-width:650px;height: 400px"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Number Products Of Suppliers</h5>
                                    <div id="chart3" style="width:100%;max-width:650px; height: 400px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title"><a href="${pageContext.request.contextPath}/admin/orders">Number Of Orders(${requestScope.totalOrders})</a></h5>
                                <canvas id="myChart3" style="width:100%;height: 400px"></canvas>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Total Money By Month</h5>
                                <form action="${pageContext.request.contextPath}/admin/charts" method="get">
                                    <select name="yearM" onchange="this.form.submit()">
                                        <optgroup label="Year">
                                            <option value="2023" <c:if test="${requestScope.yearM == 2023}">selected</c:if>>2023</option>
                                            <option value="2022" <c:if test="${requestScope.yearM == 2022}">selected</c:if>>2022</option>
                                        </select>
                                    </form>
                                    <div id="chart4" style="width:100%; height: 400px;"></div>
                                </div>
                            </div>
                        </div>
                        <!-- End Charts -->
                        <!-- ============================================================== -->
                        <!-- End PAge Content -->
                        <!-- ============================================================== -->
                        <!-- ============================================================== -->
                        <!-- Right sidebar -->
                        <!-- ============================================================== -->
                        <!-- .right-sidebar -->
                        <!-- ============================================================== -->
                        <!-- End Right sidebar -->
                        <!-- ============================================================== -->
                    </div>
                    <!-- ============================================================== -->
                    <!-- End Container fluid  -->
                    <!-- ============================================================== -->
                    <!-- ============================================================== -->
                    <!-- footer -->
                    <!-- ============================================================== -->
                    <footer class="footer text-center">

                    </footer>
                    <!-- ============================================================== -->
                    <!-- End footer -->
                    <!-- ============================================================== -->
                </div>
                <!-- ============================================================== -->
                <!-- End Page wrapper  -->
                <!-- ============================================================== -->
            </div>
            <!-- ============================================================== -->
            <!-- End Wrapper -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- All Jquery -->
            <!-- ============================================================== -->
            <script src="assets/libs/jquery/dist/jquery.min.js"></script>
            <!-- Bootstrap tether Core JavaScript -->
            <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
            <!-- slimscrollbar scrollbar JavaScript -->
            <script src="assets/libs/perfect-scrollbar/dist/perfect-scrollbar.jquery.min.js"></script>
            <script src="assets/extra-libs/sparkline/sparkline.js"></script>
            <!--Wave Effects -->
            <script src="dist/js/waves.js"></script>
            <!--Menu sidebar -->
            <script src="dist/js/sidebarmenu.js"></script>
            <!--Custom JavaScript -->
            <script src="dist/js/custom.min.js"></script>
            <!-- this page js -->
            <script src="assets/libs/chart/matrix.interface.js"></script>
            <script src="assets/libs/chart/excanvas.min.js"></script>
            <script src="assets/libs/flot/jquery.flot.js"></script>
            <script src="assets/libs/flot/jquery.flot.pie.js"></script>
            <script src="assets/libs/flot/jquery.flot.time.js"></script>
            <script src="assets/libs/flot/jquery.flot.stack.js"></script>
            <script src="assets/libs/flot/jquery.flot.crosshair.js"></script>
            <script src="assets/libs/chart/jquery.peity.min.js"></script>
            <script src="assets/libs/chart/matrix.charts.js"></script>
            <script src="assets/libs/chart/jquery.flot.pie.min.js"></script>
            <script src="assets/libs/flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
            <script src="assets/libs/chart/turning-series.js"></script>
            <script src="dist/js/pages/chart/chart-page-init.js"></script>

            <script>
                                        var now = new Date();
                                        var offset = +7 * 60; // offset in minutes
                                        var lastDate = now.getTime() + offset * 60 * 1000;
                                        var data = [];
                                        var TICKINTERVAL = 1000;
                                        var XAXISRANGE = 10000;
                                        function getNewSeries(baseval, yrange) {
                                            var newDate = baseval + TICKINTERVAL;
                                            lastDate = newDate;
                                            for (var i = 0; i < data.length - 10; i++) {
                                                // IMPORTANT
                                                // we reset the x and y of the data which is out of drawing area
                                                // to prevent memory leaks
                                                data[i].x = newDate - XAXISRANGE - TICKINTERVAL
                                                data[i].y = 0
                                            }

                                            data.push({
                                                x: newDate,
                                                y: ${requestScope.activeSessions}
                                            })
                                        }

                                        getNewSeries(lastDate, {
                                            min: 10,
                                            max: 90
                                        })

                                        var options = {
                                            series: [{
                                                    data: data
                                                }],
                                            chart: {
                                                id: 'realtime',
                                                height: 350,
                                                type: 'line',
                                                animations: {
                                                    enabled: true,
                                                    easing: 'linear',
                                                    dynamicAnimation: {
                                                        speed: 1000
                                                    }
                                                },
                                                toolbar: {
                                                    show: false
                                                },
                                                zoom: {
                                                    enabled: false
                                                }
                                            },
                                            dataLabels: {
                                                enabled: false
                                            },
                                            stroke: {
                                                curve: 'smooth'
                                            },
                                            title: {
                                                text: 'Dynamic Updating Chart',
                                                align: 'left'
                                            },
                                            markers: {
                                                size: 0
                                            },
                                            xaxis: {
                                                type: 'datetime',
                                                range: XAXISRANGE,
                                            },
                                            yaxis: {
                                                max: 100
                                            },
                                            legend: {
                                                show: false
                                            },
                                        };
                                        var chart = new ApexCharts(document.querySelector("#chart1"), options);
                                        chart.render();
                                        window.setInterval(function () {
                                            getNewSeries(lastDate, {
                                                min: 10,
                                                max: 90
                                            })

                                            chart.updateSeries([{
                                                    data: data
                                                }])
                                        }, 1000)














                                        var data1 = [];
                                        var data2 = [];
                                        var months = [];
            <c:forEach var="item" items="${requestScope.accessByMonth}">
                                        data1.push(${item.second});
                                        months.push(${item.first});
            </c:forEach>

            <c:forEach var="item" items="${requestScope.ordersByMonth}">
                                        data2.push(${item.second});
            </c:forEach>

                                        const monthNames = months.map((month) => {
                                            const date = new Date(Date.UTC(2000, month - 1, 1)); // Create a Date object with the month and year
                                            return date.toLocaleString('default', {month: 'long'}); // Get the full month name
                                        });
                                        function drawChart() {
                                            var chartData = {
                                                series: [
                                                    {
                                                        name: "Number Orders",
                                                        type: "column",
                                                        data: data2,
                                                    },
                                                    {
                                                        name: "Active Users",
                                                        type: "line",
                                                        data: data1,
                                                    },
                                                ],
                                                xaxis: {
                                                    categories: monthNames,
                                                },
                                            };
                                            // Define options for chart
                                            var chartOptions = {
                                                chart: {
                                                    height: 350,
                                                    type: "line",
                                                    stacked: false,
                                                },
                                                dataLabels: {
                                                    enabled: false,
                                                },
                                                colors: ["#008FFB", "#00E396"],
                                                series: chartData.series,
                                                xaxis: chartData.xaxis,
                                            };
                                            // Initialize ApexCharts object with chart data and options
                                            var chart = new ApexCharts(document.querySelector("#chart2"), chartOptions);
                                            // Render the chart
                                            chart.render();
                                        }




                                        var xValues = [];
                                        var yValues = [];
            <c:forEach var="item" items="${requestScope.numberProductsOfCategory}">
                                        xValues.push("${fn:escapeXml(item.categoryName)}");
                                        yValues.push(${item.number});
            </c:forEach>

                                        var barColors = [
                                            "#b91d47",
                                            "#00aba9",
                                            "#2b5797"
                                        ];
                                        new Chart("myChart2", {
                                            type: "pie",
                                            data: {
                                                labels: xValues,
                                                datasets: [{
                                                        backgroundColor: barColors,
                                                        data: yValues
                                                    }]
                                            },
                                            options: {
                                                title: {
                                                    display: true,
                                                    text: "Number of Devices"
                                                }
                                            }
                                        });



                                        var xValues = ["Success", "Process", "Fail"];
                                        var yValues = [${requestScope.totalOrdersSuccess}, ${requestScope.totalProcess}, ${requestScope.totalOrderFail}];

                                        var barColors = [
                                            "#b91d47",
                                            "#00aba9",
                                            "#2b5797"
                                        ];
                                        new Chart("myChart3", {
                                            type: "pie",
                                            data: {
                                                labels: xValues,
                                                datasets: [{
                                                        backgroundColor: barColors,
                                                        data: yValues
                                                    }]
                                            },
                                            options: {
                                                title: {
                                                    display: true,
                                                    text: "Orders"
                                                }
                                            }
                                        });











                                        var dataa = [];
                                        var datab = [];

            <c:forEach var="item" items="${requestScope.productsBySupplier}">
                                        dataa.push("${fn:escapeXml(item.companyName)}");
                                        datab.push(${item.number});
            </c:forEach>
                                        console.log(dataa);
                                        console.log(datab);

                                        var options = {
                                            chart: {
                                                type: 'bar',
                                                height: 350
                                            },
                                            series: [{
                                                    name: 'Products',
                                                    data: datab
                                                }],
                                            xaxis: {
                                                categories: dataa
                                            }
                                        }

                                        var chart3 = new ApexCharts(document.querySelector("#chart3"), options);

                                        chart3.render();



                                        var datae = [];
                                        var monthss = [];
            <c:forEach var="item" items="${requestScope.totalMoneyByMonth}">
                                        datae.push(${item.second});
                                        monthss.push(${item.first});
            </c:forEach>


                                        const monthNamess = monthss.map((month) => {
                                            const date = new Date(Date.UTC(2000, month - 1, 1)); // Create a Date object with the month and year
                                            return date.toLocaleString('default', {month: 'long'}); // Get the full month name
                                        });


                                        var options = {
                                            chart: {
                                                type: 'bar',
                                                height: 350
                                            },
                                            series: [{
                                                    name: 'Money',
                                                    data: datae
                                                }],
                                            xaxis: {
                                                categories: monthNamess
                                            },
                                            yaxis: {
                                                labels: {
                                                    formatter: function (val) {
                                                        return "$" + val.toFixed(2); // set currency format
                                                    }
                                                }
                                            },
                                            tooltip: {
                                                y: {
                                                    formatter: function (val) {
                                                        return "$" + val.toFixed(2); // set currency format for tooltip values
                                                    }
                                                }
                                            },
                                            plotOptions: {
                                                bar: {
                                                    dataLabels: {
                                                        position: 'top',
                                                        formatter: function (val) {
                                                            return "$" + val.toFixed(2); // set currency format for data labels
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        var chart3 = new ApexCharts(document.querySelector("#chart4"), options);

                                        chart3.render();

        </script>
    </body>
</html>
