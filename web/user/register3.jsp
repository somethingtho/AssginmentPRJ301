<%-- 
    Document   : register
    Created on : Jan 23, 2023, 2:34:16 AM
    Author     : daova
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng ký tài khoản</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
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
            <div class="row">
                <div class="logo col-md-1">
                    <a href="index"><img id="logo" src="${pageContext.request.contextPath}/images/logo.png" /></a>
                </div>
                <h1 class="text-center">Chào mừng bạn đến với website. Đăng ký ở đây!!</h1>
            </div>

            <hr style="height: 1px; background-color: black; color: black; margin-bottom: 0;">

            <div class="row login">

                <div class="col-md-5 login__form">
                    <p style="color: red; font-size: 20px;">${requestScope.error}</p>
                    <form action="register" method="post">
                        <div class="login__form-username" >
                            <label class="login_title" for="username">Tên đăng nhập</label>
                            <input class="login_input" type="text" name="username" id="username" placeholder="Nhập tên đăng nhập..." required>
                        </div>
                        <div class="login__form-password">
                            <div style="margin-bottom: 20px">
                                <label class="login_title" for="email">Email</label><br>
                                <input class="login_input" id="email" name="email" type="email" placeholder="Nhập email Email..." required>
                            </div>
                            <div style="margin-bottom: 20px;">
                                <label  class="login_title" for="password">Mật Khẩu</label><br>
                                <input class="login_input" type="password" id="password" name="password" placeholder="Nhập mật khẩu..." required>
                                <i style="margin-left: -30px; cursor: pointer;" id="togglePassword" class="bi bi-eye-slash"></i>
                            </div>
                            <div style="margin-bottom: 10px;"><label class="login_title" for="cfpassword"  >Xác Nhận Mật Khẩu</label>
                                <input class="login_input" type="password" name="cfpassword" id="cfpassword"  placeholder="Nhập lại mật khẩu..." required>
                                <i style="margin-left: -30px; cursor: pointer;" id="togglecfPassword" class="bi bi-eye-slash"></i></div>
                            <div class="login__btn "><button style="color: white;">Đăng ký</button></div>
                            <div class="login__btn "><button><a href="login.jsp">Đăng nhập</a></button></div>

                        </div>
                    </form>

                </div>

                <div class="col-md-6 img_right"><img class="img-responsive" src="${pageContext.request.contextPath}/images/fptuniver.jpg" alt=""></div>
            </div>

            <div class="row">
                <div class="col-md-4 row">
                    <h3 class="text-center">Contact us</h3>
                    <div class="col-md-3"><a href=""><button><img class="img-responsive" src="${pageContext.request.contextPath}/images/facebook.jpg"
                                                                  alt=""></button></a></div>
                    <div class="col-md-3"><a href=""><button><img class="img-responsive" src="${pageContext.request.contextPath}/images/skype.png"
                                                                  alt=""></button></a></div>
                    <div class="col-md-3"><a href=""><button><img class="img-responsive" src="${pageContext.request.contextPath}/images/twitter.png"
                                                                  alt=""></button></a></div>
                    <div class="col-md-3"><a href=""><button><img class="img-responsive" src="${pageContext.request.contextPath}/images/insta.jpg"
                                                                  alt=""></button></a></div>

                </div>
                <div class="col-md-4 website_help center-block">
                    <h3>Website</h3>
                    <p><a href="">Sell with us</a></p>
                    <p><a href="">Recruitment</a></p>
                    <p><a href="">Terms of use</a></p>
                    <p><a href="">Privacy Policy</a></p>
                    <p><a href="">Protecting intellectual property rights</a></p>


                </div>

                <div class="col-md-4 row">
                    <h3 style="color: rgb(233, 152, 3);">Go where your heart beats</h3>
                    <h4>Dowload App <i class="ti-arrow-down"></i></h4>
                    <div class="row app_icon">
                        <p class="col-md-6"><a href=""><img src="${pageContext.request.contextPath}/images/app_store.png" alt=""></a></p>
                        <p class="col-md-6"><a href=""><img src="${pageContext.request.contextPath}/images/google_play.png" alt=""></a></p>
                    </div>

                    <p>FPT University CNC – Km29 Dai Lo Thang Long, H. Thach That, TP. Ha Noi</p>
                </div>

            </div>
            <div class="row payment">
                <div class="col-md-6">
                    <h3 class="text-center">Payment Methods</h3>
                    <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/visa.png">
                    <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/mastercard.png">
                    <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/jbc.jpg">
                    <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/cashonde.png">
                    <img class="img-responsive col-md-2" src="${pageContext.request.contextPath}/images/napas.png">
                </div>

                <div class="col-md-6">
                    <h3 class="text-center">Delivery service</h3>
                    <img src="${pageContext.request.contextPath}/images/ahamove.png">
                    <img src="${pageContext.request.contextPath}/images/bestex.png">
                    <img src="${pageContext.request.contextPath}/images/ghn.png">
                    <img src="${pageContext.request.contextPath}/images/jtex.png">
                    <img src="${pageContext.request.contextPath}/images/ninjavan.png">
                    <img src="${pageContext.request.contextPath}/images/ship60.png">
                </div>
            </div>
        </div>
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



            const togglecfPassword = document.querySelector("#togglecfPassword");
            const cfpassword = document.querySelector("#cfpassword");

            togglePassword.addEventListener("click", function () {
                // toggle the type attribute
                const type = cfpassword.getAttribute("type") === "password" ? "text" : "password";
                cfpassword.setAttribute("type", type);

                // toggle the icon
                this.classList.toggle("bi-eye");
            });


            window.onload = function () {
                document.querySelector(".gearbox").style.display = "none";
            };
        </script>
    </body>
</html>
