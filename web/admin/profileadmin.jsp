<%-- 
    Document   : profile
    Created on : Feb 10, 2023, 1:06:20 AM
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
        <title>Profile for me!!</title>
        <link
            rel="icon"
            type="image/png"
            sizes="16x16"
            href="assets/images/logo.png"
            />
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

        <div class="preloader">
            <div class="lds-ripple">
                <div class="lds-pos"></div>
                <div class="lds-pos"></div>
            </div>
        </div>

        <div
            id="main-wrapper"
            data-layout="vertical"
            data-navbarbg="skin5"
            data-sidebartype="full"
            data-sidebar-position="absolute"
            data-header-position="absolute"
            data-boxed-layout="full"
            >

            <header class="topbar" data-navbarbg="skin5">
                <nav class="navbar top-navbar navbar-expand-md navbar-dark">
                    <div class="navbar-header" data-logobg="skin5">

                        <a class="navbar-brand" href="${pageContext.request.contextPath}/admin/index">
                            <!-- Logo icon -->
                            <b class="logo-icon" style="margin: 0">
                                <img
                                    src="assets/images/logo.png"
                                    alt="homepage"
                                    class="light-logo"
                                    width="50"
                                    />
                            </b>

                            <span class="logo-text" style="margin-right: 15px">
                                <img
                                    src="${pageContext.request.contextPath}/images/logo_text.png"
                                    width="140" height="50"
                                    alt="homepage"
                                    class="light-logo"
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
                        <ul class="navbar-nav float-start me-auto">
                            <li class="nav-item d-none d-lg-block">
                                <a
                                    class="nav-link sidebartoggler waves-effect waves-light"
                                    href="javascript:void(0)"
                                    data-sidebartype="mini-sidebar"
                                    ><i class="mdi mdi-menu font-24"></i
                                    ></a>
                            </li>
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
                                          >Thêm mới  &nbsp;&nbsp;&nbsp;<i class="fa fa-angle-down"></i
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
                        <ul class="navbar-nav float-end">

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
                </div>

            </aside>

            <div class="page-wrapper">

                <div class="page-breadcrumb">
                    <div class="row">
                        <div class="col-12 d-flex no-block align-items-center">
                            <h4 class="page-title">Hồ sơ của bạn</h4>
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


                <div class="container-fluid">
                    <form action="${pageContext.request.contextPath}/admin/profileadmin" method="POST" id="form1" enctype="multipart/form-data">
                        <div class="row forms">
                            <h3 style="color: red">${requestScope.error}</h3>

                            <div class="col-md-6" >
                                <div class="card">
                                    <div class="card-body" style="height: 383px">

                                        <div style="text-align: center">
                                            <div style="margin-bottom: 20px;">
                                                <img style="width: 150px;" src="data:image/jpg;base64,${sessionScope.admin.base64Image}" alt="" />
                                            </div>


                                            <div style="margin-bottom: 20px;">
                                                <input style="margin-left: 30px;"
                                                       type="file"
                                                       class="custom-file-input"
                                                       id="validatedCustomFile"
                                                       name="photo"
                                                       />
                                                <label
                                                    class="custom-file-label"
                                                    for="validatedCustomFile"
                                                    ></label
                                                >

                                            </div>
                                            <div>
                                                <h4>${sessionScope.admin.acc.userName}</h4>
                                                <h4>Email: ${sessionScope.admin.email}</h4>
                                                <h4>Số điện thoại: ${sessionScope.admin.phone}</h4>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="card">
                                    <div class="card-body" style="height: 383px;">
                                        <h4 class="card-title">Thông tin của bạn</h4>
                                        <input type="hidden" name="submit_both_forms" value="true">
                                        <div class="form-group row">
                                            <label
                                                for="customerName"
                                                class="col-sm-3 text-end control-label col-form-label"
                                                >Họ và tên:
                                            </label>
                                            <div class="col-sm-9">
                                                <input
                                                    name="customerName"
                                                    type="text"
                                                    class="form-control"
                                                    id="customerName"
                                                    placeholder="FullName Here"
                                                    value="${sessionScope.admin.customerName}"
                                                    />
                                            </div>
                                        </div>
                                        <div class="form-group row">
                                            <label
                                                for="phone"
                                                class="col-sm-3 text-end control-label col-form-label"
                                                >Số điện thoại:</label
                                            >
                                            <div class="col-sm-9">
                                                <input
                                                    type="text"
                                                    class="form-control"
                                                    id="phone"
                                                    name="phone"
                                                    placeholder="Phone Here"
                                                    value="${sessionScope.admin.phone}"
                                                    />
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label
                                                for="email"
                                                class="col-sm-3 text-end control-label col-form-label"
                                                >Email:</label
                                            >
                                            <div class="col-sm-9">
                                                <input
                                                    name="email"
                                                    type="text"
                                                    class="form-control"
                                                    id="email"
                                                    placeholder="Contact No Here"
                                                    value="${sessionScope.admin.email}"
                                                    />
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label
                                                for="email"
                                                class="col-sm-3 text-end control-label col-form-label"
                                                >Địa chỉ:</label
                                            >
                                            <div class="col-sm-9">
                                                <input
                                                    name="address"
                                                    type="text"
                                                    class="form-control"
                                                    id="email"
                                                    placeholder="Address Here"
                                                    value="${sessionScope.admin.address}"
                                                    />
                                            </div>
                                        </div>

                                        <div class="form-group row">
                                            <label
                                                for=""
                                                class="col-sm-3 text-end control-label col-form-label"
                                                >Giới tính:</label
                                            >
                                            <div class="col-sm-9" style="margin-top: 10px;">
                                                <input  type="radio" id="gender1" name="gender" value="male" <c:if test="${sessionScope.admin.gender}">checked</c:if>><label for="gender1">Nam</label>&nbsp;&nbsp;&nbsp;
                                                <input  type="radio" name="gender" id="gender2" value="female" <c:if test="${!sessionScope.admin.gender}">checked</c:if>><label for="gender2">Nữ</label>
                                            </div>
                                        </div>
                                        <div class="border-top">
                                            <div class="card-body">
                                                <button onclick="submitForms()" type="button" class="btn btn-primary">
                                                    Submit
                                                </button>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>


                        </div>
                    </form>
                    <footer class="footer text-center">


                    </footer>

                </div>


            </div>


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
                                                    function submitForms() {
                                                        document.getElementById("form1").submit();
                                                    }

            </script>
    </body>
</html>
