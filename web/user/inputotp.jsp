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
        <title>Password Assistance</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font/themify-icons/themify-icons.css">
        <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
    </head>
    <style>
        .form {
            width: 450px;
            height: 420px;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -ms-flex-direction: column;
            flex-direction: column;
            border-radius: 15px;
            background-color: white;
            -webkit-box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
            box-shadow: 0 4px 30px rgba(0, 0, 0, 0.1);
            -webkit-transition: .4s ease-in-out;
            transition: .4s ease-in-out;
        }

        .form:hover {
            -webkit-box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.1);
            box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.1);
            scale: 0.99;
        }

        .heading {
            position: relative;
            text-align: center;
            color: black;
            top: 3em;
            font-weight: bold;
        }

        .check {
            position: relative;
            -ms-flex-item-align: center;
            align-self: center;
            top: 4em;
        }

        .input {
            position: relative;
            width: 2.5em;
            height: 2.5em;
            margin: 1em;
            border-radius: 5px;
            border: none;
            outline: none;
            background-color: rgb(235, 235, 235);
            -webkit-box-shadow: inset 3px 3px 6px #d1d1d1,
                inset -3px -3px 6px #ffffff;
            box-shadow: inset 3px 3px 6px #d1d1d1,
                inset -3px -3px 6px #ffffff;
            top: 6.5em;
            left: 1.5em;
            padding-left: 15px;
            -webkit-transition: .4s ease-in-out;
            transition: .4s ease-in-out;
        }

        .input:hover {
            -webkit-box-shadow: inset 0px 0px 0px #d1d1d1,
                inset 0px 0px 0px #ffffff;
            box-shadow: inset 0px 0px 0px #d1d1d1,
                inset 0px 0px 0px #ffffff;
            background-color: lightgrey;
        }

        .input:focus {
            -webkit-box-shadow: inset 0px 0px 0px #d1d1d1,
                inset 0px 0px 0px #ffffff;
            box-shadow: inset 0px 0px 0px #d1d1d1,
                inset 0px 0px 0px #ffffff;
            background-color: lightgrey;
        }

        .btn1 {
            position: relative;
            top: 8.5em;
            left: 2.4em;
            width: 26em;
            height: 3em;
            border-radius: 5px;
            border: none;
            outline: none;
            -webkit-transition: .4s ease-in-out;
            transition: .4s ease-in-out;
            -webkit-box-shadow: 1px 1px 3px #b5b5b5,
                -1px -1px 3px #ffffff;
            box-shadow: 1px 1px 3px #b5b5b5,
                -1px -1px 3px #ffffff;
        }

        .btn1:active {
            -webkit-box-shadow: inset 3px 3px 6px #b5b5b5,
                inset -3px -3px 6px #ffffff;
            box-shadow: inset 3px 3px 6px #b5b5b5,
                inset -3px -3px 6px #ffffff;
        }

        .btn2 {
            position: relative;
            top: 9.5em;
            left: 2.4em;
            width: 26em;
            height: 3em;
            border-radius: 5px;
            border: none;
            outline: none;
            -webkit-transition: .4s ease-in-out;
            transition: .4s ease-in-out;
            -webkit-box-shadow: 1px 1px 3px #b5b5b5,
                -1px -1px 3px #ffffff;
            box-shadow: 1px 1px 3px #b5b5b5,
                -1px -1px 3px #ffffff;
        }

        .btn2:active {
            -webkit-box-shadow: inset 3px 3px 6px #b5b5b5,
                inset -3px -3px 6px #ffffff;
            box-shadow: inset 3px 3px 6px #b5b5b5,
                inset -3px -3px 6px #ffffff;
        }

        /*        .input {
                    max-width: 190px;
                    width: 40px;
                    height: 40px;
                    outline: none;
                    margin: 12px;
                    transition: .5s;
                    border: none;
                    border-radius: 10px;
                    padding: 10px;
                    text-align: center;
                    overflow: hidden;
                    white-space: nowrap;
                    text-overflow: ellipsis;
                    transform: rotate(90deg);
                }
        
                input:focus {
                    width: 150px;
                    transform: rotate(0);
                }*/
    </style>

    <body>
        <fmt:setLocale value = "vi_VN"/>
        <%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0);
//prevents caching at the proxy server
        %>
        <div class="row" style="margin-top: 10rem">
            <div class="col-md-5"></div>
            <form class="form col-md-9">
                <p class="heading">Verify</p>
                <svg class="check" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="60px" height="60px" viewBox="0 0 60 60" 
                     xml:space="preserve">  <image id="image0" width="60" height="60" x="0" y="0" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAQAAACQ9RH5AAAABGdBTUEAALGPC/xhBQAAACBjSFJN
                     AAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QA/4ePzL8AAAAJcEhZ
                     cwAACxMAAAsTAQCanBgAAAAHdElNRQfnAg0NDzN/r+StAAACR0lEQVRYw+3Yy2sTURTH8W+bNgVf
                     aGhFaxNiAoJou3FVEUQE1yL031BEROjCnf4PLlxILZSGYncuiiC48AEKxghaNGiliAojiBWZNnNd
                     xDza3pl77jyCyPzO8ubcT85wmUkG0qT539In+MwgoxQoUqDAKDn2kSNLlp3AGi4uDt9xWOUTK3xg
                     hVU2wsIZSkxwnHHGKZOxHKfBe6rUqFGlTkPaVmKGn6iYao1ZyhK2zJfY0FZ9ldBzsbMKxZwZjn/e
                     5szGw6UsD5I0W6T+hBhjUjiF7bNInjz37Ruj3igGABjbtpIo3GIh30u4ww5wr3fwfJvNcFeznhBs
                     YgXw70TYX2bY/ulkZhWfzfBbTdtrzjPFsvFI+T/L35jhp5q2owDs51VIVvHYDM9sa/LY8XdtKy1l
                     FXfM8FVN2/X2ajctZxVXzPA5TZvHpfb6CFXxkerUWTOcY11LX9w0tc20inX2mmF4qG3upnNWrOKB
                     hIXLPu3dF1x+kRWq6ysHpkjDl+7eQjatYoOCDIZF3006U0unVSxIWTgTsI3HNP3soSJkFaflMDwL
                     3OoHrph9YsPCJJ5466DyOGUHY3Epg2rWloUxnMjsNw7aw3AhMjwVhgW4HYm9FZaFQZ/bp6QeMRQe
                     hhHehWKXGY7CAuSpW7MfKUZlAUqWdJ3DcbAAB3guZl9yKC4WYLfmT4muFtgVJwvQx7T2t0mnXK6J
                     XlGGyAQvfNkaJ5JBmxnipubJ5HKDbJJsM0eY38QucSx5tJWTVHBwqDDZOzRNmn87fwDoyM4J2hRz
                     NgAAACV0RVh0ZGF0ZTpjcmVhdGUAMjAyMy0wMi0xM1QxMzoxNTo1MCswMDowMKC8JaoAAAAldEVY
                     dGRhdGU6bW9kaWZ5ADIwMjMtMDItMTNUMTM6MTU6NTArMDA6MDDR4Z0WAAAAKHRFWHRkYXRlOnRp
                     bWVzdGFtcAAyMDIzLTAyLTEzVDEzOjE1OjUxKzAwOjAwIIO3fQAAAABJRU5ErkJggg=="></image>
                </svg>
                <div class="box">
                    <input type="text" name="text1" class="input" maxlength="1" onkeyup="addEventListeners()">
                    <input type="text" name="text2" class="input" maxlength="1" onkeyup="addEventListeners()">
                    <input type="text" name="text3" class="input" maxlength="1" onkeyup="addEventListeners()">
                    <input type="text" name="text4" class="input" maxlength="1" onkeyup="addEventListeners()">
                    <input type="text" name="text5" class="input" maxlength="1" onkeyup="addEventListeners()">
                    <input type="text" name="text6" class="input" maxlength="1" onkeyup="addEventListeners()">

                </div>
                <button class="btn1">Submit</button>
                <button class="btn2">Back</button>
            </form>
        </div>

        <script>
            function addEventListeners() {
                const inputs = document.querySelectorAll('input');
                inputs.forEach((input, index) => {
                    input.addEventListener('input', (event) => {
                        const currentInput = event.target;
                        const currentInputValue = currentInput.value;
                        const isNumber = /^\d+$/.test(currentInputValue);
                        const nextInput = inputs[index + 1];

                        if (isNumber && nextInput) {
                            nextInput.focus();
                        }
                    });

                    // Add an event listener to the current input field to move to the next field
                    // when the user clicks on the input field
                    input.addEventListener('click', (event) => {
                        const currentInput = event.target;
                        const currentInputValue = currentInput.value;
                        const isNumber = /^\d+$/.test(currentInputValue);
                        const nextInput = inputs[index + 1];

                        if (isNumber && nextInput) {
                            nextInput.focus();
                        }
                    });
                });
            }

        </script>


    </body>
</html>
