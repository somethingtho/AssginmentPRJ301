<%-- 
    Document   : Products
    Created on : Feb 10, 2023, 1:06:00 AM
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
        <meta name="robots" content="noindex,nofollow" />
        <title>Products</title>
        <!-- Favicon icon -->
        <link
            rel="icon"
            type="image/png"
            sizes="16x16"
            href="assets/images/logo.png"
            />
        <!-- Custom CSS -->
        <link
            href="assets/libs/magnific-popup/dist/magnific-popup.css"
            rel="stylesheet"
            />

        <link
            rel="stylesheet"
            type="text/css"
            href="assets/libs/select2/dist/css/select2.min.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="assets/libs/jquery-minicolors/jquery.minicolors.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="assets/libs/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css"
            />
        <link
            rel="stylesheet"
            type="text/css"
            href="assets/libs/quill/dist/quill.snow.css"
            />

        <link href="dist/css/style.min.css" rel="stylesheet" />
        <link href="dist/css/products.css" rel="stylesheet" type="text/css"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>

        <button onclick="topFunction()" id="myBtn" title="Go to top"><i style="text-align: center" class="ti-arrow-up"></i></button>
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
                                <form class="app-search position-absolute" action="${pageContext.request.contextPath}/admin/listallproducts" method="post">
                                    <input
                                        name="pid"
                                        type="text"
                                        class="form-control"
                                        placeholder="Search by ID &amp; enter"
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

                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/admin/changepass.jsp"
                                       ><i class="mdi mdi-settings me-1 ms-1"></i> Đổi mật khẩu</a
                                    >

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
                                    <li class="sidebar-item active">
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
                            <h4 class="page-title">Sản phẩm</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/index">Trang chủ</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">
                                            Sản phẩm
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

                    <div class="container-fluid"  style="border: 1px solid black; margin: 10px 0 ">
                        <form style="margin-top: 10px;">
                            <div class="form-group row">
                                <label class="col-md-3 mt-3">Thương hiệu</label>
                                <div class="col-md-9">
                                    <c:set var="sup" value="${requestScope.listAllSuppliers}"></c:set>
                                    <c:set var="ci" value="${requestScope.cid}"></c:set>
                                        <select
                                            name="supplierID"
                                            id="supplierID"
                                            class="select2 form-select shadow-none mt-3"
                                            multiple="multiple"
                                            style="height: 36px; width: 100%"
                                            >
                                            <optgroup label="Thương hiệu">
                                            <c:forEach begin="0" end="${sup.size()-1}" var="i">
                                                <option ${ci[i]?"selected":""} value="${sup.get(i).supplierID}">${sup.get(i).companyName}</option>
                                            </c:forEach>
                                        </optgroup>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label class="col-md-3 mt-3">Danh mục</label>
                                <div class="col-md-9">
                                    <select
                                        name="categoryID"
                                        id="categoryID"
                                        class="select2 form-select shadow-none"
                                        style="width: 100%; height: 36px"
                                        >
                                        <option value="Select">Select</option>
                                        <optgroup label="Danh mục">
                                            <c:forEach items="${requestScope.listAllCategories}" var="cate">
                                                <option <c:if test="${requestScope.category != 'Select' && cate.categoryID eq requestScope.category }">selected</c:if> value="${cate.categoryID}">${cate.categoryName}</option>
                                            </c:forEach>
                                        </optgroup>
                                    </select>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label class="col-md-3 mt-3">Sắp xếp theo</label>
                                <div class="col-md-9">
                                    <select
                                        name="orderby"
                                        id="orderby"
                                        class="select2 form-select shadow-none"
                                        style="width: 100%; height: 36px"
                                        >
                                        <option value="Select">Select</option>
                                        <optgroup label="Sắp xếp theo">
                                            <option <c:if test="${requestScope.orderby_raw eq 'ProductID1'}">selected</c:if> value="ProductID1">Mã sản phẩm(Tăng dần)</option>
                                            <option <c:if test="${requestScope.orderby_raw eq 'ProductID2'}">selected</c:if> value="ProductID2">Mã sản phẩm(Giảm dần)</option>
                                            <option <c:if test="${requestScope.orderby_raw eq 'DateCreated1'}">selected</c:if> value="DateCreated1">Ngày tạo(Tăng dần)</option>
                                            <option <c:if test="${requestScope.orderby_raw eq 'DateCreated2'}">selected</c:if> value="DateCreated2">Ngày tạo(Giảm dần)</option>
                                            <option <c:if test="${requestScope.orderby_raw eq 'ProductName'}">selected</c:if> value="ProductName">Tên (A-Z)</option>
                                            <option <c:if test="${requestScope.orderby_raw eq 'UnitPrice1'}">selected</c:if> value="UnitPrice1">Giá(Tăng dần)</option>
                                            <option <c:if test="${requestScope.orderby_raw eq 'UnitPrice2'}">selected</c:if> value="UnitPrice2">Giá(Giảm dần)</option>
                                            <option <c:if test="${requestScope.orderby_raw eq 'UnitsOnOrder1'}">selected</c:if> value="UnitsOnOrder1">Số lượng đã bán(Tăng dần)</option>
                                            <option <c:if test="${requestScope.orderby_raw eq 'UnitsOnOrder2'}">selected</c:if> value="UnitsOnOrder2">Số lượng đã bán(Giảm dần)</option>
                                            <option <c:if test="${requestScope.orderby_raw eq 'UnitsInStock1'}">selected</c:if> value="UnitsInStock1">Số lượng trong kho(Tăng dần)</option>
                                            <option <c:if test="${requestScope.orderby_raw eq 'UnitsInStock2'}">selected</c:if> value="UnitsInStock2">Số lượng trong kho(Giảm dần)</option>
                                            </optgroup>
                                        </select>
                                    </div>
                                </div>


                                <div class="form-group row">
                                    <label class="col-md-3">Discontinued</label>
                                    <div class="col-md-9 row">
                                        <div class="form-check col-md-6">
                                            <input
                                                type="radio"
                                                class="form-check-input"
                                                id="customControlValidation1"
                                                name="discontinued"
                                                value="ON"
                                            <c:if test="${requestScope.discontinued_raw eq 'ON'}">checked</c:if>
                                                />
                                            <label
                                                class="form-check-label mb-0"
                                                for="customControlValidation1"
                                                >Discontinued</label
                                            >
                                        </div>
                                        <div class="form-check col-md-6">
                                            <input
                                                type="radio"
                                                class="form-check-input"
                                                id="customControlValidation2"
                                                value="OFF"
                                            <c:if test="${requestScope.discontinued_raw eq 'OFF'}">checked</c:if>
                                                name="discontinued"
                                                />
                                            <label
                                                class="form-check-label mb-0"
                                                for="customControlValidation2"
                                                >Continued</label
                                            >
                                        </div>
                                    </div>
                                </div>


                                <div class="btn_search">
                                    <button type="submit" class="search_btn">
                                        <span class="search_btn_text">Search</span>
                                    </button>
                                </div>

                            </form>
                        </div>




                        <div id="content" class="row el-element-overlay">

                        <c:forEach items="${requestScope.listAllProducts}" var="product">
                            <div class="col-lg-3 col-md-6">
                                <div class="card">
                                    <div class="el-card-item">
                                        <div class="el-card-avatar el-overlay-1">
                                            <img style="height: 294px;" src="data:image/jpg;base64,${product.base64Image}" alt="user" />
                                            <div class="el-overlay">
                                                <ul class="list-style-none el-info">
                                                    <li class="el-item">
                                                        <a
                                                            class="btn default btn-outline image-popup-vertical-fit el-link"
                                                            href="data:image/jpg;base64,${product.base64Image}"
                                                            ><i class="mdi mdi-magnify-plus"></i
                                                            ></a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                        <div
                                            class="el-card-content"
                                            style="display: flex; flex-direction: column"
                                            >
                                            <button
                                                class="btn_sample">
                                                <a href="${pageContext.request.contextPath}/user/item?pid=${product.productID}" class="mb-0">(#${product.productID}) - ${product.productName}</a>
                                            </button>
                                            <button
                                                class="btn_sample">
                                                <a href="${pageContext.request.contextPath}/admin/updateproduct?pid=${product.productID}">Chỉnh sửa</a>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="load_more">
                        <button class="button" onclick="loadMore()">
                            <span class="box">
                                Load More!
                            </span>
                        </button>
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
        <script src="assets/libs/magnific-popup/dist/jquery.magnific-popup.min.js"></script>
        <script src="assets/libs/magnific-popup/meg.init.js"></script>
        <script src="assets/libs/inputmask/dist/min/jquery.inputmask.bundle.min.js"></script>
        <script src="dist/js/pages/mask/mask.init.js"></script>
        <script src="assets/libs/select2/dist/js/select2.full.min.js"></script>
        <script src="assets/libs/select2/dist/js/select2.min.js"></script>
        <script src="assets/libs/jquery-asColor/dist/jquery-asColor.min.js"></script>
        <script src="assets/libs/jquery-asGradient/dist/jquery-asGradient.js"></script>
        <script src="assets/libs/jquery-asColorPicker/dist/jquery-asColorPicker.min.js"></script>
        <script src="assets/libs/jquery-minicolors/jquery.minicolors.min.js"></script>
        <script src="assets/libs/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
        <script src="assets/libs/quill/dist/quill.min.js"></script>

        <script>
                            function loadMore() {
                                var amount = document.getElementsByClassName('card').length;


                                var supplierID = $('#supplierID').val();
                                var categoryID = $('#categoryID').val();
                                var orderby = $('#orderby').val();
                                var discontinued = $('input[name="discontinued"]:checked').val();

                                $.ajax({
                                    url: "${pageContext.request.contextPath}/admin/loadmore",
                                    type: "get",
                                    data: {

                                        data: JSON.stringify(supplierID),

                                        dataType: "json",
                                        total: amount,
                                        categoryID: categoryID,
                                        orderby: orderby,
                                        discontinued: discontinued
                                    },
                                    contentType: "application/json",

                                    success: function (data) {
                                        var row = document.getElementById('content');
                                        row.innerHTML += data;
                                    },
                                    error: function (xhr) {
                                        //Do Something to handle error
                                    }
                                });
                            }





                            $(".select2").select2();

                            $(".demo").each(function () {
                                $(this).minicolors({
                                    control: $(this).attr("data-control") || "hue",
                                    position: $(this).attr("data-position") || "bottom left",

                                    change: function (value, opacity) {
                                        if (!value)
                                            return;
                                        if (opacity)
                                            value += ", " + opacity;
                                        if (typeof console === "object") {
                                            console.log(value);
                                        }
                                    },
                                    theme: "bootstrap",
                                });
                            });
                            /*datwpicker*/
                            jQuery(".mydatepicker").datepicker();
                            jQuery("#datepicker-autoclose").datepicker({
                                autoclose: true,
                                todayHighlight: true,
                            });
                            var quill = new Quill("#editor", {
                                theme: "snow",
                            });




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
