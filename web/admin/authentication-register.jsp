<%-- 
    Document   : authentication-register
    Created on : Feb 8, 2023, 7:40:03 PM
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
        <title>Đăng ký</title>
        <link
            rel="icon"
            type="image/png"
            sizes="16x16"
            href="assets/images/logo.png"
            />
        <link href="dist/css/style.min.css" rel="stylesheet" />
        
    </head>
    <body>
        <fmt:setLocale value = "vi_VN"/>
        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
        %>
        <div class="main-wrapper">
            
            <div class="preloader">
                <div class="lds-ripple">
                    <div class="lds-pos"></div>
                    <div class="lds-pos"></div>
                </div>
            </div>
            <div
                class="auth-wrapper d-flex no-block justify-content-center align-items-center bg-dark"
                >
                <div class="auth-box bg-dark border-top border-secondary">
                    <div>
                        <div class="text-center pt-3 pb-3">
                            <span class="db"
                                  ><img style="width: 100px" src="assets/images/logo.png" alt="logo"
                                  /></span>
                        </div>
                        <!-- Form -->
                        <form class="form-horizontal mt-3" action="">
                            <div class="row pb-4">
                                <div class="col-12">
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span
                                                class="input-group-text bg-success text-white h-100"
                                                id="basic-addon1"
                                                ><i class="mdi mdi-account fs-4"></i
                                                ></span>
                                        </div>
                                        <input
                                            type="text"
                                            class="form-control form-control-lg"
                                            placeholder="Tên đăng nhập"
                                            aria-label="Username"
                                            aria-describedby="basic-addon1"
                                            required
                                            />
                                    </div>
                                    <!-- email -->
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span
                                                class="input-group-text bg-danger text-white h-100"
                                                id="basic-addon1"
                                                ><i class="mdi mdi-email fs-4"></i
                                                ></span>
                                        </div>
                                        <input
                                            type="text"
                                            class="form-control form-control-lg"
                                            placeholder="Email"
                                            aria-label="Username"
                                            aria-describedby="basic-addon1"
                                            required
                                            />
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span
                                                class="input-group-text bg-warning text-white h-100"
                                                id="basic-addon2"
                                                ><i class="mdi mdi-lock fs-4"></i
                                                ></span>
                                        </div>
                                        <input
                                            type="text"
                                            class="form-control form-control-lg"
                                            placeholder="Mật khẩu"
                                            aria-label="Password"
                                            aria-describedby="basic-addon1"
                                            required
                                            />
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span
                                                class="input-group-text bg-info text-white h-100"
                                                id="basic-addon2"
                                                ><i class="mdi mdi-lock fs-4"></i
                                                ></span>
                                        </div>
                                        <input
                                            type="text"
                                            class="form-control form-control-lg"
                                            placeholder=" Nhập lại mật khẩu"
                                            aria-label="Password"
                                            aria-describedby="basic-addon1"
                                            required
                                            />
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-md-12">
                                            <select
                                                class="select2 form-select shadow-none"
                                                style="width: 100%; height: 36px"
                                                >
                                                <option>Đăng ký là</option>
                                                <optgroup label="Role">
                                                    <option value="1">Nhà cung cấp</option>
                                                    <option value="HI">Shipper</option>
                                                </optgroup>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row border-top border-secondary">
                                <div class="col-12">
                                    <div class="form-group">
                                        <div class="pt-3 d-grid">
                                            <button
                                                class="btn btn-block btn-lg btn-info"
                                                type="submit"
                                                >
                                                Đăng ký
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="pt-3 d-grid" style="margin-bottom: 2rem"><button style="background-color:orange;" class="btn btn-block btn-lg btn-info"><a href="${pageContext.request.contextPath}/user/index" style="color: white;">Trở về trang người mua</a></button></div>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- Login box.scss -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Page wrapper scss in scafholding.scss -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Page wrapper scss in scafholding.scss -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Right Sidebar -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Right Sidebar -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- All Required js -->
        <!-- ============================================================== -->
        <script src="assets/libs/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap tether Core JavaScript -->
        <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <!-- ============================================================== -->
        <!-- This page plugin js -->
        <!-- ============================================================== -->
        <script>
            $(".preloader").fadeOut();
        </script>

    </body>
</html>
