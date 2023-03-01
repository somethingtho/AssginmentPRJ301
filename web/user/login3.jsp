<%-- 
    Document   : login
    Created on : Jan 23, 2023, 2:20:38 AM
    Author     : daova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
    </head>
    <body>
        <fmt:setLocale value = "vi_VN"/>
        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
//prevents caching at the proxy server
        %>
        <div class="container-fluid">

            <div class="row login_header">
                <div class="logo col-md-1">
                    <a href="index"><img id="logo" src="${pageContext.request.contextPath}/images/logo.png" /></a>
                </div>
                <h1 class="text-center">Chào mừng bạn đến với website. Đăng nhập ngay!!</h1>
            </div>
            <hr style="height: 1px; background-color: black; color: black; margin-bottom: 0;">

            <div class="row">
                <div class="col-md-1"></div>
                <h1 class="col-md-7" style="color: red;">${requestScope.error}</h1>
            </div>

            <div class="row login">
                <form action="login" method="post">
                    <div class="col-md-5 login__form">
                        <div class="login__form-username">
                            <p><lable class="login_title" for="username">Tên đăng nhập</lable>
                            <br>
                            <input class="login_input" type="text" id="username" name="username" placeholder="Enter Username..." required value="${cookie.user.value}"></p>
                        </div>
                        <div class="login__form-password">
                            <p><lable class="login_title" for="password">Mật khẩu</lable><br>
                            <input class="login_input" id="password" name="password" type="password" placeholder="Enter password..." required value="${cookie.pass.value}">
                            <i style="margin-left: -30px; cursor: pointer;" id="togglePassword" class="bi bi-eye-slash"></i>
                            </p>
                            <div style="margin-left: 420px;">
                                <input type="checkbox" id="remember" value="ON" ${(cookie.cr.value eq 'ON')?"checked":""}>
                                <label style="margin-top: 30px; " for="remember" >Ghi nhớ</label>
                            </div>

                            <div class="login__btn "><button onclick="this.form.submit()" style="color: white;">Đăng nhập</button></div>
                            <div class="login__btn "><button><a href="register.jsp">Đăng ký</a></button></div>
                            <div class="login__btn "><button><a href="forgotpassword.jsp">Quên mật khẩu?</a></button></div>
                            <div class="login__btn "><button><a href="${pageContext.request.contextPath}/admin/authentication-login.jsp">Đăng nhập kênh người bán hàng</a></button></div>
                        </div>
                    </div>
                </form>
                <div class="col-md-6 img_right"><img class="img-responsive" src="${pageContext.request.contextPath}/images/fptuniver.jpg" alt=""></div>
            </div>
            <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">

            <footer class="container-fluid">
                <div class="row">
                    <div class="col-md-4 row">
                        <h3 class="text-center">Liên hệ với chúng tôi</h3>
                        <div class="col-md-3 img_mxh"><a href="https://www.facebook.com" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/facebook.jpg"
                                                                                                                                        alt=""></a></div>
                        <div class="col-md-3 img_mxh"><a href="https://www.skype.com/en/" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/skype.png"
                                                                                                                                         alt=""></a></div>
                        <div class="col-md-3 img_mxh"><a href="https://twitter.com/?lang=vi" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/twitter.png"
                                                                                                                                            alt=""></a></div>
                        <div class="col-md-3 img_mxh"><a href="https://www.instagram.com/" target="_blank" rel="noopener noreferrer"><img class="img-responsive" src="${pageContext.request.contextPath}/images/insta.jpg"
                                                                                                                                          alt=""></a></div>

                    </div>
                    <div class="col-md-4 website_help center-block">
                        <h3>Website</h3>
                        <p><a href="">Bán hàng cùng chúng tôi</a></p>
                        <p><a href="">Tuyển dụng</a></p>
                        <p><a href="#">Điều khoản sử dụng</a></p>
                        <p><a href="#">Chính sách bảo mật</a></p>
                        <p><a href="#">Bảo vệ quyền sở hữu trí tuệ</a></p>
                        <p><a target="_blank" rel="noopener noreferrer" href="help.jsp">Trợ giúp <i class="ti-help"></i></a></p>
                    </div>

                    <div class="col-md-4 row">
                        <h3 style="color: rgb(233, 152, 3);">Go where your heart beats</h3>
                        <h4>Tải ứng dụng <i class="ti-arrow-down"></i></h4>
                        <div class="row app_icon">
                            <p class="col-md-6"><a href="https://www.apple.com/store"><img src="${pageContext.request.contextPath}/images/app_store.png" alt=""></a></p>
                            <p class="col-md-6"><a href="https://play.google.com/"><img src="${pageContext.request.contextPath}/images/google_play.png" alt=""></a></p>
                        </div>

                        <p>FPT University CNC – Km29 Dai Lo Thang Long, H. Thach That, TP. Ha Noi</p>
                    </div>

                </div>
                <hr style="margin-right: 20px; height: 1px; background-color: black; color: black;">
                <div class="row payment">
                    <div class="col-md-6">
                        <h3 class="text-center">CÁCH THỨC THANH TOÁN</h3>
                        <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/visa.png">
                        <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/mastercard.png">
                        <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/jbc.jpg">
                        <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/cashonde.png">
                        <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/napas.png">
                    </div>

                    <div class="col-md-6">
                        <h3 class="text-center">DỊCH VỤ GIAO HÀNG</h3>
                        <img src="${pageContext.request.contextPath}/images/ahamove.png">
                        <img src="${pageContext.request.contextPath}/images/bestex.png">
                        <img src="${pageContext.request.contextPath}/images/ghn.png">
                        <img src="${pageContext.request.contextPath}/images/jtex.png">
                        <img src="${pageContext.request.contextPath}/images/ninjavan.png">
                        <img src="${pageContext.request.contextPath}/images/ship60.png">
                    </div>
                </div>
            </footer> 

            <script>
                const togglePassword = document.querySelector("#togglePassword");
                const password = document.querySelector("#password");

                togglePassword.addEventListener("click", function () {
                    // toggle the type attribute
                    const type = password.getAttribute("type") === "password" ? "text" : "password";
                    password.setAttribute("type", type);

                    // toggle the icon
                    this.classList.toggle("bi-eye");
                });

                window.onload = function () {
                    document.querySelector(".gearbox").style.display = "none";
                };

            </script>
    </body>
</html>
