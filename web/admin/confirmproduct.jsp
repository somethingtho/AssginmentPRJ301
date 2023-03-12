<%-- 
    Document   : formaddproduct
    Created on : Feb 8, 2023, 8:03:44 PM
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
        <title>#${requestScope.product.productID}~${requestScope.product.productName}</title>
        <!-- Favicon icon -->
        <link
            rel="icon"
            type="image/png"
            sizes="16x16"
            href="assets/images/logo.png"
            />
        <!-- Custom CSS -->
        <link
            href="assets/libs/jquery-steps/jquery.steps.css"
            rel="stylesheet"
            />
        <link href="assets/libs/jquery-steps/steps.css" rel="stylesheet" />
        <link href="dist/css/style.min.css" rel="stylesheet" />
        <link href="dist/css/confirmproduct.css" rel="stylesheet" type="text/css"/>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <fmt:setLocale value = "vi_VN"/>
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
                            <h4 class="page-title">Xác nhận</h4>
                            <div class="ms-auto text-end">
                                <nav aria-label="breadcrumb">
                                    <ol class="breadcrumb">
                                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/admin/index">Trang chủ</a></li>
                                        <li class="breadcrumb-item active" aria-current="page">
                                            Xác nhận
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
                    <div class="card">
                        <div class="card-body wizard-content">
                            <h4 class="card-title">Sản phẩm</h4>
                            <h6 class="card-subtitle">${requestScope.msg}</h6>
                            <form id="example-form" action="updateproduct" method="post" class="mt-5" enctype="multipart/form-data">
                                <input type="hidden" value="${requestScope.product.productID}" name="pid" >
                                <div>
                                    <h3>Sản phẩm</h3>
                                    <label for="productName">Tên sản phẩm *</label>
                                    <input
                                        id="productName"
                                        name="productName"
                                        type="text"
                                        value="${requestScope.product.productName}"
                                        class="required form-control"
                                        />
                                    <label class="col-md-3 mt-3">Danh mục</label>
                                    <div class="col-md-12">
                                        <select
                                            name="categoryID"
                                            class="select2 form-select shadow-none"
                                            style="width: 100%; height: 36px"
                                            >
                                            <option>Select</option>
                                            <optgroup label="Danh mục">
                                                <c:forEach items="${requestScope.listAllCate}" var="c">
                                                    <option value="${c.categoryID}" <c:if test="${requestScope.product.category.categoryID == c.categoryID}">selected</c:if>>${c.categoryName}</option>
                                                </c:forEach>
                                            </optgroup>
                                        </select>
                                    </div>

                                    <label class="col-md-3 mt-3">Thương hiệu</label>
                                    <div class="col-md-12">
                                        <select
                                            name="supplierID"
                                            class="select2 form-select shadow-none"
                                            style="width: 100%; height: 36px"
                                            >
                                            <option>Select</option>
                                            <optgroup label="Thương hiệu">
                                                <c:forEach items="${requestScope.listAllSup}" var="s">
                                                    <option value="${s.supplierID}" 
                                                            <c:if test="${s.supplierID eq requestScope.product.supplier.supplierID}">selected</c:if>
                                                            >${s.companyName}</option>
                                                </c:forEach>
                                            </optgroup>
                                        </select>
                                    </div>

                                    <label for="price" class="col-md-3 mt-3">Giá tiền</label>
                                    <div class="col-sm-12 row">
                                        <div class="col-md-11">
                                            <input
                                                value="<fmt:formatNumber value = "${product.unitPrice}"/>"
                                                name="price"
                                                type="text"
                                                class="form-control"
                                                />
                                        </div>
                                        <div class="col-md-1">
                                            <span class="input-group-text" id="basic-addon2">đ</span>
                                        </div>
                                    </div>

                                    <label for="stock" class="col-md-3 mt-3"
                                           >Số lượng trong kho *</label
                                    >
                                    <input
                                        value="${requestScope.product.unitsInStock}"
                                        id="stock"
                                        name="stock"
                                        type="text"
                                        class="required form-control"
                                        />
                                    
                                    <label for="discount" class="col-md-3 mt-3"
                                           >Discount *</label
                                    >
                                    <div class="col-sm-12 row">
                                            <div class="col-md-11">
                                                <input
                                                    value=" <fmt:formatNumber type = "number"  value = "${requestScope.product.discount*100}" />"
                                                    type="text"
                                                    class="form-control required"
                                                    aria-label="Recipient 's username"
                                                    aria-describedby="basic-addon2"
                                                    name="discount"
                                                    />
                                            </div>
                                            <div class="col-md-1">
                                                <span class="input-group-text" id="basic-addon2"
                                                      >%</span
                                                >
                                            </div>
                                        </div>

                                    <label class="col-md-3 mt-3">Ngừng sản xuất</label>
                                    <div class="col-md-9" style="display: flex">
                                        <div class="form-check col-md-6">
                                            <input
                                                type="radio"
                                                class="form-check-input"
                                                id="customControlValidation1"
                                                name="discontinued"
                                                <c:if test="${!requestScope.product.discontinued}">checked</c:if>
                                                    value="ON"
                                                    required
                                                    />
                                                <label
                                                    class="form-check-label mb-0"
                                                    for="customControlValidation1"
                                                    >Chưa</label
                                                >
                                            </div>
                                            <div class="form-check col-md-6">
                                                <input
                                                    type="radio"
                                                    class="form-check-input"
                                                    id="customControlValidation2"
                                                    name="discontinued"
                                                    required
                                                <c:if test="${requestScope.product.discontinued}">checked</c:if>
                                                    value="OFF"
                                                    />
                                                <label
                                                    class="form-check-label mb-0"
                                                    for="customControlValidation2"
                                                    >Đã ngưng</label
                                                >
                                            </div>
                                        </div>

                                        <label class="col-md-3 mt-3">Hình ảnh đại diện<br>(Chỉ có thể thay thế không thể để trống)</label>
                                        <div class="col-md-12">
                                            <div class="image-area" style="margin-left: 400px; border: none">
                                                <img  src="data:image/jpg;base64,${requestScope.product.base64Image}" alt="Preview" />
                                        </div>
                                        <div class="custom-file" style="margin-left: 400px">
                                            <input
                                                type="file"
                                                name="photo"
                                                />
                                            <label class="custom-file-label" for="validatedCustomFile"
                                                   >Choose file...</label
                                            >
                                        </div>
                                    </div>
                                    <h3>Thông tin sản phẩm</h3>
                                    <label for="size">Kích thước *</label>
                                    <input
                                        id="size"
                                        name="size"
                                        type="text"
                                        class="required form-control"
                                        value="${requestScope.product.proInfo.size}"
                                        />
                                    <label for="weight">Trọng lượng *</label>
                                    <input
                                        id="weight"
                                        name="weight"
                                        type="text"
                                        class="required form-control"
                                        value="${requestScope.product.proInfo.weight}"
                                        />
                                    <label for="substance">Chất liệu *</label>
                                    <input
                                        id="substance"
                                        name="substance"
                                        type="text"
                                        value="${requestScope.product.proInfo.substance}"
                                        class="required form-control"
                                        />
                                    <label for="cpu">CPU</label>
                                    <input
                                        id="cpu"
                                        name="cpu"
                                        type="text"
                                        class="form-control"
                                        value="${requestScope.product.proInfo.cpu}"
                                        />
                                    <label for="ram">RAM</label>
                                    <input
                                        id="ram"
                                        name="ram"
                                        type="text"
                                        class="form-control"
                                        value="${requestScope.product.proInfo.ram}"
                                        />
                                    <label for="screen">Screen</label>
                                    <input
                                        id="screen"
                                        name="screen"
                                        type="text"
                                        class="form-control"
                                        value="${requestScope.product.proInfo.screen}"
                                        />
                                    <label for="camera">Camera</label>
                                    <input
                                        id="camera"
                                        name="camera"
                                        type="text"
                                        class="form-control"
                                        value="${requestScope.product.proInfo.camera}"
                                        />
                                    <label for="card">Card đồ hoạ</label>
                                    <input
                                        id="card"
                                        name="card"
                                        type="text"
                                        class="form-control"
                                        value="${requestScope.product.proInfo.graphicsCard}"
                                        />
                                    <label for="hdd">Ổ cứng</label>
                                    <input
                                        id="hdd"
                                        name="hdd"
                                        type="text"
                                        class="form-control"
                                        value="${requestScope.product.proInfo.hardDrive}"
                                        />
                                    <label for="os">Hệ điều hành</label>
                                    <input
                                        id="os"
                                        name="os"
                                        type="text"
                                        class="form-control"
                                        value="${requestScope.product.proInfo.os}"
                                        />
                                    <label for="pin">Pin</label>
                                    <input
                                        id="pin"
                                        name="pin"
                                        type="text"
                                        class="form-control"
                                        value="${requestScope.product.proInfo.batteryCapacity}"
                                        />

                                    <label for="DateCreated">DateCreated</label>
                                    <input
                                        id="DateCreated"
                                        name="DateCreated"
                                        type="text"
                                        readonly
                                        class="form-control"
                                        value="${requestScope.product.proInfo.dateCreated}"
                                        />
                                    <h3>Hình ảnh sản phẩm</h3>
                                    <p>Thêm các hình ảnh của sản phẩm</p>

                                    <div class="row">
                                        <c:forEach items="${requestScope.listAllImage}" var="img">
                                            <div class="image-area col-md-6">
                                                <img src="data:image/jpg;base64,${img.base64Image}" alt="Preview" />
                                                <a class="remove-image" href="updateproduct?pid=${requestScope.product.productID}&imgid=${img.id}" onclick="return confirm('Are you sure you want delete image?')" style="display: inline"
                                                   >&#215;</a
                                                >
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <input style="margin-bottom: 20px" type="file" name="file" multiple /> <br />
                                    <input
                                        id="acceptTerms"
                                        name="acceptTerms"
                                        type="checkbox"
                                        class="required"
                                        required
                                        style="margin: 20px 0"
                                        />
                                    <label for="acceptTerms"
                                           >Tôi đồng ý với các Điều khoản và Điều kiện.</label
                                    >
                                </div>
                                <button
                                    onclick="confirmAndSubmit()"
                                    style="
                                    background-color: orange;
                                    border: none;
                                    color: white;
                                    height: 50px;
                                    width: 200px;
                                    margin-top: 20px;
                                    "
                                    >
                                    Submit
                                </button>
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
        <script src="assets/libs/jquery-steps/build/jquery.steps.min.js"></script>
        <script src="assets/libs/jquery-validation/dist/jquery.validate.min.js"></script>
        <script>

                                        alert('Submit the form successfully!')
                                        document.getElementById('example-form').action = '${pageContext.request.contextPath}/admin/updateproduct'; // change the form action to the new URL
                                        document.getElementById('example-form').submit(); // submit the form
                                        }

        </script>
    </body>
</html>
