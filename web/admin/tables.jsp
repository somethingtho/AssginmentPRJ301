<%-- 
    Document   : tables
    Created on : Feb 10, 2023, 1:07:02 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />

        <title>Thống kê website</title>
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
            <button onclick="topFunction()" id="myBtn" title="Go to top"><i style="text-align: center" class="ti-arrow-up"></i></button>
            <header class="topbar" data-navbarbg="skin5">
                <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                    <div class="navbar-header" data-logobg="skin5">
                        <!-- ============================================================== -->
                        <!-- Logo -->
                        <!-- ============================================================== -->
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/index">
                            <!-- Logo icon -->
                            <b class="logo-icon" style="margin: 0">
                                <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->
                                <!-- Dark Logo icon -->
                                <img
                                    src="assets/images/logo.png"
                                    alt="homepage"
                                    class="light-logo"
                                    width="50"
                                    />
                            </b>
                            <!--End Logo icon -->
                            <!-- Logo text -->
                            <span class="logo-text" style="margin-right: 15px">
                                <!-- dark Logo text -->
                                <img
                                    src="${pageContext.request.contextPath}/images/logo_text.png"
                                    alt="homepage"
                                    class="light-logo"
                                    width="140" height="50"
                                    />
                            </span>

                        </a>

                        <a
                            class="nav-toggler waves-effect waves-light d-block d-md-none"
                            href="javascript:void(0)"
                            ><i class="ti-menu ti-close"></i
                            ></a>
                    </div>
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
                                    class="
                                    nav-link
                                    dropdown-toggle
                                    text-muted
                                    waves-effect waves-dark
                                    pro-pic
                                    "
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

                            <li class="sidebar-item selected">
                                <a
                                    class="sidebar-link has-arrow waves-effect waves-dark"
                                    href="javascript:void(0)"
                                    aria-expanded="false"
                                    ><i class="mdi mdi-format-list-bulleted"></i
                                    ><span class="hide-menu">Danh sách </span></a
                                >
                                <ul aria-expanded="false" class="collapse first-level">

                                    <li class="sidebar-item active">
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
                            <h4 class="page-title">Tables</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/index">Trang chủ</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">
                                            Danh sách
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
                        <div class="col-12">
                            <div class="card">
                                <div class="card-body">
                                    <a href="${pageContext.request.contextPath}/admin/listallcategories"><h5 class="card-title mb-0">Danh mục hiện có (${requestScope.listAllCategories.size()})</h5></a>
                                </div>
                                <table class="table text-center">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Tên danh mục</th>
                                            <th scope="col">Cập nhật</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listAllCategories}" var="cate">
                                            <tr>
                                                <th scope="row">${cate.categoryID}</th>
                                                <td>${cate.categoryName}</td>
                                                <td><a href="${pageContext.request.contextPath}/admin/updatecategory?cid=${cate.categoryID}">Cập nhật</a></td>
                                            </tr>
                                        </c:forEach> 

                                    </tbody>
                                </table>
                            </div>


                            <div class="card">
                                <div class="card-body">
                                    <a href="${pageContext.request.contextPath}/admin/listallsuppliers"><h5 class="card-title">Thương hiệu hiện có (${requestScope.listAllSuppliers.size()})</h5></a>
                                </div>
                                <table class="table text-center">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">CompanyName</th>
                                            <th scope="col">Phone</th>
                                            <th scope="col">Emai</th>
                                            <th scope="col">HomePage</th>
                                            <th scope="col">Hồ sơ</th>
                                        </tr>
                                    </thead>
                                    <tbody id="suppliers_body">

                                        <c:forEach items="${requestScope.listAllSuppliers}" var="sup">
                                            <tr class="supplier_entity">
                                                <th scope="row">${sup.supplierID}</th>
                                                <td>${sup.companyName}</td>
                                                <td>${sup.phone}</td>
                                                <td>${sup.email}</td>
                                                <td><a href="${sup.homePage}" target="_blank">${sup.companyName}</a></td>
                                                <td><a href="${pageContext.request.contextPath}/admin/profile?type=sup&id=${sup.supplierID}">Hồ sơ</a></td>
                                            </tr>
                                        </c:forEach>


                                    </tbody>
                                </table>

                            </div>

                            <div class="card">
                                <div class="card-body">
                                    <a href="${pageContext.request.contextPath}/admin/listallshippers"><h5 class="card-title">Đơn vị vận chuyển (${requestScope.listAllShippers.size()})</h5></a>
                                </div>
                                <table class="table text-center">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">CompanyName</th>
                                            <th scope="col">Phone</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Hồ sơ</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.listAllShippers}" var="ship">
                                            <tr>
                                                <th scope="row">${ship.shipperID}</th>
                                                <td>${ship.companyName}</td>
                                                <td>${ship.phone}</td>
                                                <td>${ship.email}</td>
                                                <td><a href="${pageContext.request.contextPath}/admin/profile?type=ship&id=${ship.shipperID}">Hồ sơ</a></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>


                            </div>

                            <div class="card">
                                <div class="card-body">
                                    <a href="${pageContext.request.contextPath}/admin/listallaccounts"><h5 class="card-title mb-0">Tài khoản (${requestScope.listAllCustomers.size()})</h5></a>
                                </div>
                                <div class="table-responsive">
                                    <table class="table text-center">
                                        <thead class="thead-light">
                                            <tr>
                                                <th>#</th>
                                                <th scope="col">Họ và tên</th>
                                                <th scope="col">Email</th>
                                                <th scope="col">Số điện thoại</th>
                                                <th scope="col">Giới tính</th>
                                                <th scope="col">Địa chỉ</th>
                                                <th scope="col">Role</th>
                                                <th scope="col">Hồ sơ</th>
                                            </tr>
                                        </thead>
                                        <tbody class="customtable">
                                            <c:forEach items="${requestScope.listAllCustomers}" var="cus">
                                                <tr>
                                                    <td>${cus.customerID}</td>
                                                    <td>${cus.customerName}</td>
                                                    <td>${cus.email}</td>
                                                    <td><c:if test="${ empty cus.phone}">N/A</c:if>${cus.phone}</td>
                                                        <td>
                                                        <c:choose>
                                                            <c:when test="${cus.gender}">Nam</c:when>
                                                            <c:otherwise>Nữ</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td><c:if test="${ empty cus.address}">N/A</c:if>${cus.address}</td>
                                                        <td>
                                                        <c:choose>
                                                            <c:when test="${cus.acc.role eq 1}"> Admin</c:when>
                                                            <c:otherwise>Customers</c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td><a href="${pageContext.request.contextPath}/admin/profile?type=customer&id=${cus.customerID}">Hồ sơ</a></td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>



                                </div>
                            </div>


                            <div class="card">
                                <div class="card-body">
                                    <a href="${pageContext.request.contextPath}/admin/listallproducts"><h5 class="card-title mb-0">Sản phẩm (${requestScope.listAllProducts.size()})</h5></a>
                                </div>
                                <div class="table-responsive">
                                    <table class="table text-center">
                                        <thead class="thead-light">
                                            <tr>
                                                <th>#</th>
                                                <th scope="col">Tên sản phẩm</th>
                                                <th scope="col">Danh mục</th>
                                                <th scope="col">Thương hiệu</th>
                                                <th scope="col">Giá</th>
                                                <th scope="col">Trong kho</th>
                                                <th scope="col">Đã bán</th>
                                                <th scope="col">Ngừng cung cấp</th>
                                                <th scope="col">Thông tin</th>
                                            </tr>
                                        </thead>
                                        <tbody class="customtable">
                                            <c:forEach items="${requestScope.listAllProducts}" var="pro">
                                                <tr>
                                                    <td>${pro.productID}</td>
                                                    <td><a href="${pageContext.request.contextPath}/user/item?pid=${pro.productID}">${pro.productName}</a></td>
                                                    <td>${pro.category.categoryName}</td>
                                                    <td><a href="${pageContext.request.contextPath}/admin/profile?type=sup&id=${pro.supplier.supplierID}">${pro.supplier.companyName}</a></td>
                                                    <td><fmt:formatNumber value = "${pro.unitPrice}" type = "currency"/></td>
                                                    <td>${pro.unitsInStock}</td>
                                                    <td>${pro.unitsOnOrder}</td>
                                                    <td><input value="${pro.discontinued}" type="checkbox" <c:if test="${pro.discontinued}">checked</c:if> ></td>
                                                    <td><a href="${pageContext.request.contextPath}/admin/updateproduct?pid=${pro.productID}">Thông tin</a></td>
                                                </tr>
                                            </c:forEach>

                                        </tbody>
                                    </table>

                                </div>
                            </div>


                        </div>
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

                window.onscroll = function () {
                    scrollFunction();
                };

                let mybutton = document.getElementById("myBtn");

                function scrollFunction() {
                    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                        mybutton.style.display = "block";
                    } else {
                        mybutton.style.display = "none";
                    }
                }

                function topFunction() {
                    document.body.scrollTop = 0;
                    document.documentElement.scrollTop = 0;
                }




    </script>
</body>
</html>
