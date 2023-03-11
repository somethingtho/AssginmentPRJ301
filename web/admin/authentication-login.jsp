<%-- 
    Document   : authentication-login.jsp
    Created on : Feb 8, 2023, 7:37:25 PM
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
        <meta
            name="keywords"
            content="wrappixel, admin dashboard, html css dashboard, web dashboard, bootstrap 5 admin, bootstrap 5, css3 dashboard, bootstrap 5 dashboard, Matrix lite admin bootstrap 5 dashboard, frontend, responsive bootstrap 5 admin template, Matrix admin lite design, Matrix admin lite dashboard bootstrap 5 dashboard template"
            />
        <meta
            name="description"
            content="Matrix Admin Lite Free Version is powerful and clean admin dashboard template, inpired from Bootstrap Framework"
            />
        <meta name="robots" content="noindex,nofollow" />
        <title>Login</title>
        <!-- Favicon icon -->
        <link
            rel="icon"
            type="image/png"
            sizes="16x16"
            href="assets/images/logo.png"
            />
        <!-- Custom CSS -->
        <link href="dist/css/style.min.css" rel="stylesheet" />
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
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
                    <div id="loginform">
                        <div class="text-center pt-3 pb-3">
                            <span class="db"><a href="/demoall/user/index"><img style="width: 10rem;" src="assets/images/logo.png" alt="logo"
                                                                                /></a></span>
                        </div>
                        <!-- Form -->

                        <form
                            class="form-horizontal mt-3"
                            id="loginform"
                            action="loginadmin"
                            method="POST"
                            >
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
                                            required=""
                                            name="username"
                                            value="${cookie.user.value}"
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
                                            type="password"
                                            name="password"
                                            class="form-control form-control-lg"
                                            placeholder="Mật khẩu"
                                            aria-label="Password"
                                            aria-describedby="basic-addon1"
                                            required=""
                                            value="${cookie.pass.value}"
                                            id="password"
                                            />
                                        <i style="margin-left: -30px; margin-right: 15px; margin-top: 10px; color: black;cursor: pointer; z-index: 10;" id="togglePassword" class="bi bi-eye-slash"></i>
                                    </div>
                                    <div style="float: left"><h4 style="color: white">${requestScope.error}</h4></div>
                                    <div style="float: right">
                                        <input type="checkbox" id="remember" name="remember" value="ON" ${(cookie.cr.value eq 'ON')?"checked":""} >
                                        <label style="color: white" for="remember">Ghi nhớ</label>
                                    </div>
                                </div>
                            </div>
                            <div class="row border-top border-secondary">
                                <div class="col-12">
                                    <div class="form-group">
                                        <div class="pt-3">
                                            <button
                                                class="btn btn-info"
                                                id="to-recover"
                                                type="button"
                                                >
                                                <i class="mdi mdi-lock fs-4 me-1"></i> Quên mật khẩu?
                                            </button>

                                            <button
                                                class="btn btn-success float-end text-white"
                                                type="submit"
                                                
                                                >
                                                Đăng nhập
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div style="margin-bottom: 2rem;">
                        <button
                            type="button"
                            style="background: orange; border: none; height: 3rem; width: 50rem;"
                            >
                            <i class="mdi mdi-account-plus fs-4 me-1" style="color: white"></i> <a style="text-decoration: none; color: white" href="${pageContext.request.contextPath}/admin/authentication-register.jsp"> Đăng ký</a>
                        </button>
                    </div>

                    <div style="margin-bottom: 2rem;">
                        <button
                            type="button"
                            style="background: orange; border: none; height: 3rem; width: 50rem;"
                            >
                            <i class="mdi mdi-account-convert fs-4 me-1" style="color: white"></i> <a style="text-decoration: none; color: white" href="${pageContext.request.contextPath}/user/login.jsp"> Đăng nhập(Người dùng)</a>
                        </button>
                    </div>

                    <div id="recoverform" style="margin-bottom: 2rem;">
                        <div class="text-center">
                            <span class="text-white"
                                  >Nhập địa chỉ e-mail của bạn bên dưới và chúng tôi sẽ gửi cho
                                bạn hướng dẫn cách khôi phục mật khẩu.</span
                            >
                        </div>
                        <div class="row mt-3">
                            <!-- Form -->
                            <form class="col-12" action="${pageContext.request.contextPath}/admin/forgotpassword" method="POST" id="forgot_form">
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
                                        type="email"
                                        class="form-control form-control-lg"
                                        placeholder="Email"
                                        aria-label="Username"
                                        id="email"
                                        name="email"
                                        aria-describedby="basic-addon1"
                                        />
                                </div>
                                <!-- pwd -->
                                <div class="row mt-3 pt-3 border-top border-secondary">
                                    <div class="col-12">
                                        <a
                                            class="btn btn-success text-white"
                                            href="#"
                                            id="to-login"
                                            name="action"
                                            >Quay lại đăng nhập!</a
                                        >
                                        <button
                                            class="btn btn-info float-end"
                                            type="button"
                                            name="action"
                                            onclick="forgotPassw()"
                                            >
                                            Khôi phục
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
        <script src="assets/libs/jquery/dist/jquery.min.js"></script>
        <script src="assets/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <script>
            $(".preloader").fadeOut();
            $("#to-recover").on("click", function () {
                $("#loginform").slideUp();
                $("#recoverform").fadeIn();
            });
            $("#to-login").click(function () {
                $("#recoverform").hide();
                $("#loginform").fadeIn();
            });


            const togglePassword = document.querySelector("#togglePassword");
            const password = document.querySelector("#password");

            togglePassword.addEventListener("click", function () {
                const type = password.getAttribute("type") === "password" ? "text" : "password";
                password.setAttribute("type", type);

                this.classList.toggle("bi-eye");
            });
            
            function forgotPassw(){
                if(document.getElementById('email').value === ""){
                    alert("Please input email!");
                    return;
                }
                document.getElementById('forgot_form').submit();
                
            }
            
        </script>
    </body>
</html>
