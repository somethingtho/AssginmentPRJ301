<%-- 
    Document   : profile
    Created on : Feb 10, 2023, 1:06:20 AM
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
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <meta name="robots" content="noindex,nofollow" />
        <title>Profile for ${requestScope.entity.companyName}</title>
        <!-- Favicon icon -->
        <link
            rel="icon"
            type="image/png"
            sizes="16x16"
            href="assets/images/logo.png"
            />
        <!-- Custom CSS -->
        <link
            rel="stylesheet"
            type="text/css"
            href="assets/extra-libs/multicheck/multicheck.css"
            />
        <link
            href="assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.css"
            rel="stylesheet"
            />
        <link href="dist/css/style.min.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <fmt:setLocale value = "vi_VN"/>
        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
        %>
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
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/changepass.jsp"><i class="mdi mdi-settings me-1 ms-1"></i> Đổi mật
                                        khẩu</a>

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



                            <li class="sidebar-item selected">
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

                                    <li class="sidebar-item active">
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
                            <h4 class="page-title">Hồ sơ của ${requestScope.entity.companyName}</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/index">Trang chủ</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">
                                            Hồ sơ
                                        </li>
                                    </ol>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- ============================================================== -->
                <!-- End Bread crumb and right sidebar toggle -->
                <!-- ============================================================== -->
                <!-- ============================================================== -->
                <!-- Container fluid  -->
                <!-- ============================================================== -->
                <div class="container-fluid">
                    <!-- ============================================================== -->
                    <!-- Start Page Content -->
                    <!-- ============================================================== -->
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card" style="height: 350px;">
                                <div class="card-body">
                                    <div style="text-align: center">
                                        <h3 style="color: red">${requestScope.msg}</h3>
                                        <div>
                                            <h4>${requestScope.entity.companyName}</h4>
                                            <h4>Email: ${requestScope.entity.email}</h4>
                                            <h4>Số điện thoại: ${requestScope.entity.phone}</h4>
                                            <c:if test="${requestScope.type == 'sup'}"><h4><a href="${pageContext.request.contextPath}/admin/listallproducts?supplierID=${requestScope.entity.supplierID}&categoryID=Select&orderby=Select">Số lượng sản phẩm: ${requestScope.totalProductsBySupplier}</a></h4></c:if>
                                            </div>
                                        </div>
                                    <c:if test="${requestScope.type == 'sup'}">
                                        <div class="row">
                                            <div class="col-6 mt-3">
                                                <div class="bg-dark p-10 text-white text-center">
                                                    <i class="mdi mdi-group fs-3 font-16"></i>
                                                    <h5 class="mb-0 mt-1">${requestScope.totalCategoriesBySupplier}/${requestScope.totalCategories}</h5>
                                                    <small class="font-light">Total Categories</small>
                                                </div>
                                            </div>

                                            <div class="col-6 mt-3">
                                                <div class="bg-dark p-10 text-white text-center">
                                                    <i class="mdi mdi-tag-multiple fs-3 mb-1 font-16"></i>
                                                    <h5 class="mb-0 mt-1">${requestScope.totalOrders}</h5>
                                                    <small class="font-light">Total Orders</small>
                                                </div>
                                            </div>

                                            <div class="col-6 mt-3">
                                                <div class="bg-dark p-10 text-white text-center">
                                                    <i class="mdi mdi-cart fs-3 mb-1 font-16"></i>
                                                    <h5 class="mb-0 mt-1">${requestScope.totalProductSale}</h5>
                                                    <small class="font-light">Total Products Sale</small>
                                                </div>
                                            </div>

                                            <div class="col-6 mt-3">
                                                <div class="bg-dark p-10 text-white text-center">
                                                    <i class="mdi mdi-credit-card fs-3 mb-1 font-16"></i>
                                                    <h5 class="mb-0 mt-1"><fmt:formatNumber type="currency" value="${Math.round((requestScope.totalMoney)/1000)*1000}"/></h5>
                                                    <small class="font-light">Total Money</small>
                                                </div>
                                            </div>

                                        </div>



                                    </c:if>


                                    <c:if test="${requestScope.type == 'ship'}">
                                        <div class="row">

                                            <div class="col-6 mt-3">
                                                <div class="bg-dark p-10 text-white text-center">
                                                    <i class="mdi mdi-human-greeting fs-3 mb-1 font-16"></i>
                                                    <h5 class="mb-0 mt-1">${requestScope.totalSuppliersByShipper}/${requestScope.totalSuppliers}</h5>
                                                    <small class="font-light">Total Suppliers</small>
                                                </div>
                                            </div>


                                            <div class="col-6 mt-3">
                                                <div class="bg-dark p-10 text-white text-center">
                                                    <i class="mdi mdi-truck fs-3 mb-1 font-16"></i>
                                                    <h5 class="mb-0 mt-1">${requestScope.totalOrders}</h5>
                                                    <small class="font-light">Total Order Delivered</small>
                                                </div>
                                            </div>


                                            <div class="col-6 mt-3">
                                                <div class="bg-dark p-10 text-white text-center">
                                                    <i class="mdi mdi-cart fs-3 mb-1 font-16"></i>
                                                    <h5 class="mb-0 mt-1">${requestScope.totalProducts}</h5>
                                                    <small class="font-light">Total Product Delivered</small>
                                                </div>
                                            </div>

                                            <div class="col-6 mt-3">
                                                <div class="bg-dark p-10 text-white text-center">
                                                    <i class="mdi mdi-tag fs-3 mb-1 font-16"></i>/<i class="mdi mdi-tag-remove fs-3 mb-1 font-16"></i>
                                                    <h5 class="mb-0 mt-1">${requestScope.totalOrderSuccess}/${requestScope.totalOrderFail}</h5>
                                                    <small class="font-light">OrderSuccess/OrderFail</small>
                                                </div>
                                            </div>

                                        </div>
                                    </c:if>        


                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card">
                                <form class="form-horizontal" action="profile" method="post">
                                    <input type="hidden" name="name" value="${requestScope.entity.companyName}">
                                    <input type="hidden" name="type" value="${requestScope.type}">
                                    <div class="card-body">
                                        <h4 class="card-title">Thông tin của ${requestScope.entity.companyName}</h4>
                                        <div class="form-group row">
                                            <label
                                                for="fname"
                                                class="col-sm-3 text-end control-label col-form-label"
                                                >Company Name:
                                            </label>
                                            <div class="col-sm-9">
                                                <input
                                                    name="compName"
                                                    type="text"
                                                    class="form-control"
                                                    id="fname"
                                                    placeholder="Input"
                                                    value="${requestScope.entity.companyName}"
                                                    />

                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label
                                                for="lname"
                                                class="col-sm-3 text-end control-label col-form-label"
                                                >Số điện thoại:</label
                                            >
                                            <div class="col-sm-9">
                                                <input
                                                    value="${requestScope.entity.phone}"
                                                    type="text"
                                                    class="form-control"
                                                    id="lname"
                                                    name="phone"
                                                    placeholder="Input"
                                                    />
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label
                                                for="cono1"
                                                class="col-sm-3 text-end control-label col-form-label"
                                                >Email:</label
                                            >
                                            <div class="col-sm-9">
                                                <input
                                                    name="email"
                                                    value="${requestScope.entity.email}"
                                                    type="email"
                                                    class="form-control"
                                                    id="cono1"
                                                    placeholder="Input"
                                                    />
                                            </div>
                                        </div>

                                        <c:if test="${requestScope.type == 'sup'}">
                                            <div class="form-group row">
                                                <label
                                                    for="cono1"
                                                    class="col-sm-3 text-end control-label col-form-label"
                                                    >Trang chủ:</label
                                                >
                                                <div class="col-sm-9">
                                                    <input
                                                        name="page"
                                                        type="text"
                                                        class="form-control"
                                                        id="cono1"
                                                        placeholder="Contact No Here"
                                                        value="${requestScope.entity.homePage}"
                                                        />
                                                </div>
                                            </div>
                                        </c:if>

                                        <div class="form-group row">
                                            <label class="col-sm-3 text-end control-label col-form-label">Trạng thái:</label>
                                            <div class="col-md-9" style="display: flex; justify-content: space-around;">
                                                <div class="form-check">
                                                    <input
                                                        type="radio"
                                                        class="form-check-input"
                                                        id="customControlValidation1"
                                                        name="lock"
                                                        required
                                                        value="OFF"
                                                        onclick="handleClick(this);"
                                                        <c:if test="${requestScope.entity.status}">checked</c:if>
                                                            />
                                                        <label
                                                            class="form-check-label mb-0"
                                                            for="customControlValidation1"
                                                            >Hoạt động</label
                                                        >
                                                    </div>
                                                    <div class="form-check">
                                                        <input
                                                            type="radio"
                                                            class="form-check-input"
                                                            id="customControlValidation2"
                                                            name="lock"
                                                            required
                                                            value="ON"
                                                            onclick="handleClick(this);"
                                                        <c:if test="${!requestScope.entity.status}">checked</c:if>
                                                            />
                                                        <label
                                                            class="form-check-label mb-0"
                                                            for="customControlValidation2"
                                                            >Dừng</label
                                                        >
                                                    </div>

                                                </div>
                                            </div>

                                            <div class="border-top">
                                                <div class="card-body">
                                                    <button type="submit" class="btn btn-primary">
                                                        Submit
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
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

                        <c:if test="${requestScope.type == 'sup'}">
                            <div class="cold-md-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title mb-0">Top 5 sản phẩm bán chạy</h5>
                                    </div>
                                    <table class="table text-center">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Tên sản phẩm</th>
                                                <th scope="col">Danh mục</th>
                                                <th scope="col">Giá</th>
                                                <th scope="col">Kho</th>
                                                <th scope="col">Đã bán</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.productBestSale}" var="p">
                                                <tr>
                                                    <th><a href="${pageContext.request.contextPath}/admin/updateproduct?pid=${p.productID}">${p.productID}</a></th>
                                                    <td><a href="${pageContext.request.contextPath}/user/item?pid=${p.productID}">${p.productName}</a></td>
                                                    <td>${p.category.categoryName}</td>
                                                    <td><fmt:formatNumber type="currency" value="${p.unitPrice}"/></td>
                                                    <td>${p.unitsInStock}</td>
                                                    <td>${p.unitsOnOrder}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="cold-md-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title mb-0">Đơn hàng đã bán (${requestScope.listAllOrders.size()})</h5>
                                    </div>
                                    <table class="table text-center">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">CustomerName</th>
                                                <th scope="col">OrderDate</th>
                                                <th scope="col">RequiredDate</th>
                                                <th scope="col">ShippedDate</th>
                                                <th scope="col">Đơn vị vận chuyển</th>
                                                <th scope="col">Địa chỉ giao hàng</th>
                                                <th scope="col">Payment</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">TotalMoney</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.listAllOrders}" var="o">
                                                <tr>
                                                    <th><a href="${pageContext.request.contextPath}/admin/orderdetail?id=${requestScope.entity.supplierID}&oid=${o.orderID}">${o.orderID}</a></th>
                                                    <td><a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${o.cus.customerID}">${o.cus.customerName}</a></td>
                                                    <td>${o.orderDate}</td>
                                                    <td>${o.requireDate}</td>
                                                    <td>${o.shippedDate}</td>
                                                    <td><a href="${pageContext.request.contextPath}/admin/profile?type=ship&id=${o.shipper.shipperID}">${o.shipper.companyName}</a></td>
                                                    <td>${o.address}</td>
                                                    <td>
                                                        <c:if test="${!o.payments}">Delivered</c:if>
                                                        <c:if test="${o.payments}">QR Code</c:if>
                                                        </td>
                                                        <td>
                                                        <c:choose>
                                                            <c:when test="${o.status == 1}">Success</c:when>
                                                            <c:when test="${o.status == 0}">Fail</c:when>
                                                            <c:when test="${o.status == 3}">Process</c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td>

                                                        <fmt:formatNumber type="currency" value="${Math.round((o.totalMoney)/1000)*1000}"/>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="cold-md-12">
                                <div class="card" style="display: grid; place-items: center;">
                                    <canvas id="myChart1" style="width:100%;max-width:600px"></canvas>
                                </div>
                            </div>

                            <div class="cold-md-12">
                                <div class="card" style="display: grid; place-items: center;">
                                    <canvas id="myChart2" style="width:100%;max-width:600px"></canvas>
                                </div>
                            </div>

                        </c:if>


                        <c:if test="${requestScope.type == 'ship'}">
                            <div class="cold-md-12">
                                <div class="card" >
                                    <div class="card-body">
                                        <h5 class="card-title mb-0">Đơn hàng đã giao</h5>
                                    </div>
                                    <table class="table text-center">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th scope="col">Họ và tên</th>
                                                <th scope="col">Số điện thoại</th>
                                                <th scope="col">Giới tính</th>
                                                <th scope="col">Ngày yêu cầu</th>
                                                <th scope="col">Ngày giao</th>
                                                <th scope="col">Địa chỉ giao hàng</th>
                                                <th scope="col">Hình thức thanh toán</th>
                                                <th scope="col">Tổng tiền</th>
                                                <th scope="col">Trạng thái</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.listOrdersByShipperID}" var="sample">
                                                <tr>
                                                    <td><a href="${pageContext.request.contextPath}/admin/orderdetail?id=${requestScope.entity.shipperID}&oid=${sample.orderID}">${sample.orderID}</a></td>
                                                    <td><a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${sample.cus.customerID}">${sample.cus.customerName}</a></td>
                                                    <td>${sample.cus.phone}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${sample.cus.gender}">Nam</c:when>
                                                            <c:otherwise>Nữ</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>${sample.requireDate}</td>
                                                    <td>${sample.shippedDate}</td>
                                                    <td>${sample.address}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${sample.payments}">QR Code</c:when>
                                                            <c:otherwise>Delivered</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <fmt:formatNumber type="currency" value="${Math.round((sample.totalMoney)/1000)*1000}"/>
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${sample.status == 1}">Success</c:when>
                                                            <c:when test="${sample.status == 0}">Fail</c:when>
                                                            <c:otherwise>Process</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="cold-md-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h5 class="card-title mb-0">Tổng sản phẩm các đã giao thương hiệu</h5>
                                    </div>
                                    <table class="table text-center">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th scope="col">Tên nhà cung cấp</th>
                                                <th scope="col">Số điện thoại</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Trang chủ</th>
                                                <th scope="col">Số lượng sản phẩm</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.listSuppliers}" var="supp">
                                                <tr>
                                                    <td>${supp.sup.supplierID}</td>
                                                    <td><a href="${pageContext.request.contextPath}/admin/profile?type=sup&id=${supp.sup.supplierID}">${supp.sup.companyName}</a></td>
                                                    <td>${supp.sup.phone}</td>
                                                    <td>${supp.sup.email}</td>
                                                    <td><a href="${supp.sup.homePage}">${supp.sup.companyName}</a></td>
                                                    <td>${supp.number}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div> 
                            <div class="row">
                                <div  class="col-md-5">
                                    <div class="card" style="height: 450px">
                                        <canvas style="min-width: 450px; "  id="chartSuccess"></canvas>
                                    </div>
                                </div>

                                <div  class="col-md-7">
                                    <div class="card">
                                        <div id="chartShip" style="min-width: 700px; " class="col-md-6"></div>
                                    </div>
                                </div>

                            </div>        


                            <div  class="col-md-12">
                                <div class="card" >
                                    <canvas  id="chartLate"></canvas>
                                </div>
                            </div>

                        </c:if>     



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
            <script src="assets/extra-libs/multicheck/datatable-checkbox-init.js"></script>
            <script src="assets/extra-libs/multicheck/jquery.multicheck.js"></script>
            <script src="assets/extra-libs/DataTables/datatables.min.js"></script>
            <script>
                                                                /****************************************
                                                                 *       Basic Table                   *
                                                                 ****************************************/
                                                                $("#zero_config").DataTable();

                                                                function handleClick(lock) {
                                                                    let txt;
                                                                    if (lock.value === 'ON')
                                                                        txt = " khoá ";
                                                                    else
                                                                        txt = " mở khoá ";
                                                                    alert("Bạn đang" + txt + "tài khoản này!");
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

                                                                new Chart("myChart1", {
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












                                                                var aValues = [];
                                                                var bValues = [];

                <c:forEach var="item" items="${requestScope.productBestSale}">
                                                                aValues.push("${fn:escapeXml(item.productName)}");
                                                                bValues.push(${item.unitsOnOrder});
                </c:forEach>

                                                                var barColors = ["red", "green", "blue", "orange", "brown"];

                                                                new Chart("myChart2", {
                                                                    type: "bar",
                                                                    data: {
                                                                        labels: aValues,
                                                                        datasets: [{
                                                                                backgroundColor: barColors,
                                                                                data: bValues
                                                                            }]
                                                                    },
                                                                    options: {
                                                                        legend: {display: false},
                                                                        title: {
                                                                            display: true,
                                                                            text: "Top 5 Products Best Sale"
                                                                        }
                                                                    }
                                                                });







                                                                var data1 = [];
                                                                var data2 = [];
                <c:forEach items="${requestScope.listSuppliers}" var="supp">
                                                                data1.push("${fn:escapeXml(supp.sup.companyName)}");
                                                                data2.push(${supp.number});
                </c:forEach>



                                                                var options = {
                                                                    chart: {
                                                                        type: 'bar'
                                                                    },
                                                                    series: [{
                                                                            name: 'Number Products Delivered',
                                                                            data: data2
                                                                        }],
                                                                    xaxis: {
                                                                        categories: data1
                                                                    },
                                                                    options: {
                                                                        title: {
                                                                            display: true,
                                                                            text: "Number Products Delivered"
                                                                        }
                                                                    }
                                                                }

                                                                var chartShip = new ApexCharts(document.querySelector("#chartShip"), options);
                                                                chartShip.render();






                                                                var mValues = ["Success", "Fail"];
                                                                var nValues = [${requestScope.totalOrderSuccess},${requestScope.totalOrderFail}];


                                                                var barColors = [
                                                                    "#b91d47",
                                                                    "#2b5797"
                                                                ];

                                                                new Chart("chartSuccess", {
                                                                    type: "pie",
                                                                    data: {
                                                                        labels: mValues,
                                                                        datasets: [{
                                                                                backgroundColor: barColors,
                                                                                data: nValues
                                                                            }]
                                                                    },
                                                                    options: {
                                                                        title: {
                                                                            display: true,
                                                                            text: "Number Orders Successes/Fails"
                                                                        }
                                                                    }
                                                                });



                                                                var mValues = ["Early", "Later"];
                                                                var nValues = [${requestScope.totalOrders - requestScope.numberOrderLate},${requestScope.numberOrderLate}];


                                                                var barColors = [
                                                                    "#2b5797",
                                                                    "#b91d47"
                                                                    
                                                                ];

                                                                new Chart("chartLate", {
                                                                    type: "pie",
                                                                    data: {
                                                                        labels: mValues,
                                                                        datasets: [{
                                                                                backgroundColor: barColors,
                                                                                data: nValues
                                                                            }]
                                                                    },
                                                                    options: {
                                                                        title: {
                                                                            display: true,
                                                                            text: "Number Orders Late/Early"
                                                                        }
                                                                    }
                                                                });
            </script>
    </body>
</html>
