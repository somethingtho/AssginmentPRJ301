<%-- 
    Document   : feedback2
    Created on : Feb 21, 2023, 4:02:32 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath}/css/feedback.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/admin/assets/images/logo.png"/>
        <title>FeedBack</title>
    </head>
    <body>

        <div class="container">
            <form class="form-container" action="feedback" method="POST">
                <div class="headline"><span>Contact me</span></div>
                <h3 style="color: red;  margin-bottom: 30px; text-align: center; line-height: 1.8;">${requestScope.msg}</h3>
                <div class="form-line success">
                    <input type="text" class="form-input" value="Input">
                    <label class="top">Name</label>
                    <div class="check-label"></div>
                </div>
                <div class="form-line">
                    <input type="text" class="form-input" required>
                    <label>Your email *</label>
                    <div class="error-label">Field is required!</div>
                    <div class="check-label"></div>
                </div>
                <div class="form-line">
                    <input type="text" class="form-input">
                    <label>Subject</label>
                    <div class="check-label"></div>
                </div>
                <div class="form-line">
                    <textarea class="form-input" placeholder="Message" required></textarea>
                    <label></label>
                    <div class="check-label"></div>
                    <div class="error-label">Field is required!</div>
                </div>

                <input type="button" class="form-button" value="Submit">
            </form>
        </div>

        <script>
            $(document).ready(function () {
                $('.form-input').on('keyup, paste, cut, focusout', function () {
                    var $parent = $(this).parents('.form-line');
                    var input_value = $.trim($(this).val());
                    var required = $(this).is(':required');

                    if (input_value.length > 0) {
                        $parent.find('label').addClass('top');

                        $parent
                                .removeClass('error')
                                .addClass('success');
                    } else {
                        $parent.find('label').removeClass('top');
                        $parent.removeClass('success')

                        if (required) {
                            $parent.addClass('error');
                        }
                    }
                });
            });

            window.onload = function () {
                document.querySelector(".gearbox").style.display = "none";
            };
        </script>

    </body>


</html>
