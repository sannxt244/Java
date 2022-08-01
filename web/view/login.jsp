<%-- 
    Document   : login
    Created on : Jan 19, 2022, 11:37:41 AM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="UTF-8">
        <meta name="description" content="WebUni Education Template">
        <meta name="keywords" content="webuni, education, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Favicon -->   
        <link href="img/favicon.ico" rel="shortcut icon"/>

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,400i,500,500i,600,600i,700,700i,800,800i" rel="stylesheet">

        <!-- Stylesheets -->
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <link rel="stylesheet" href="css/owl.carousel.css"/>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>



        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header section -->
        <%@include file="../header.jsp" %>
        <!-- Header section end -->

            

        <!-- Hero section -->
        <section class="hero-section set-bg" data-setbg="img/bg.jpg">
            <div class="container">
                <div class="hero-text text-white">
                    <h2>Get The Best Free Online Courses</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla <br> dictum. Ut ac ligula sapien. Suspendisse cursus faucibus finibus.</p>
                </div>
                <div class="row">
                    
                </div>
            </div>
        </section>
        <!-- Hero section end -->


        <!-- Login Form -->
        <div class="loginForm" >
            <div class="loginForm__overlay"></div>
            <div class="loginForm__body">
                <div class="loginForm__title">
                    <h2>Login</h2>           
                    <img onclick="closeForm('loginForm');" class="loginForm_xIcon" src="img/x_icon.png" alt=""/>
                </div>
                <div class="loginForm__fill">
                    <form action="login" method="POST" class="loginForm__form">
                        <input class="loginForm__input" type="text" name="username" placeholder="Username"/> <br/>
                        <input class="loginForm__input" type="password" name="password" placeholder="Password"/> <br/>
                        <div class="error" style="font-size: smaller"><c:if test="${requestScope.loginError != null}" >${requestScope.loginError}</c:if> </div>
                        <div class="error" style="font-size: smaller"><c:if test="${requestScope.failVerify != null}" >${requestScope.failVerify}</c:if> </div>
                        <div class="success text-success" style="font-size: smaller"><c:if test="${requestScope.successRegis != null}" >${requestScope.successRegis}</c:if> </div>
                        <div class="success text-success" style="font-size: smaller"><c:if test="${requestScope.resetSuccess != null}">${requestScope.resetSuccess}</c:if> </div>
                        <div class="success text-success" style="font-size: smaller"><c:if test="${requestScope.sendMailSuccess != null}">${requestScope.sendMailSuccess}</c:if> </div>
                        <div class="success text-success" style="font-size: smaller"><c:if test="${requestScope.successVerify != null}">${requestScope.successVerify}</c:if> </div>
                        <input type="submit" value="Login"/>
                    </form>

                </div>
                <div class="loginForm__option" >
                    <div class="option__sellect"> 
                        <a href="register">Register a new account</a> <br/>
                        <a href="reset">I fogot my password</a>
                    </div>
                </div>
            </div>

        </div>

        <!-- footer section -->
        <footer class="footer-section spad pb-0">
            <div class="footer-top">
                <div class="footer-warp">
                    <div class="row d-flex justify-content-around">
                        <div class="widget-item">
                            <h4>Contact Info</h4>
                            <ul class="contact-list">
                                <li>1481 Creekside Lane <br>Avila Beach, CA 931</li>
                                <li>+53 345 7953 32453</li>
                                <li>yourmail@gmail.com</li>
                            </ul>
                        </div>
                        <div class="widget-item">
                            <h4>Engeneering</h4>
                            <ul>
                                <li><a href="">Applied Studies</a></li>
                                <li><a href="">Computer Engeneering</a></li>
                                <li><a href="">Software Engeneering</a></li>
                                <li><a href="">Informational Engeneering</a></li>
                                <li><a href="">System Engeneering</a></li>
                            </ul>
                        </div>
                        <div class="widget-item">
                            <h4>Graphic Design</h4>
                            <ul>
                                <li><a href="">Applied Studies</a></li>
                                <li><a href="">Computer Engeneering</a></li>
                                <li><a href="">Software Engeneering</a></li>
                                <li><a href="">Informational Engeneering</a></li>
                                <li><a href="">System Engeneering</a></li>
                            </ul>
                        </div>
                        <div class="widget-item">
                            <h4>Development</h4>
                            <ul>
                                <li><a href="">Applied Studies</a></li>
                                <li><a href="">Computer Engeneering</a></li>
                                <li><a href="">Software Engeneering</a></li>
                                <li><a href="">Informational Engeneering</a></li>
                                <li><a href="">System Engeneering</a></li>
                            </ul>
                        </div>
                        
                    </div>
                </div>
            </div>
            <div class="footer-bottom">
                <div class="footer-warp">
                    <ul class="footer-menu">
                        <li><a href="#">Terms & Conditions</a></li>
                        <li><a href="#">Register</a></li>
                        <li><a href="#">Privacy</a></li>
                    </ul>
                    <div class="copyright"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></div>
                </div>
            </div>
        </footer> 
        <!-- footer section end -->


        <!--====== Javascripts & Jquery ======-->
        <script src="js/jquery-3.2.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/circle-progress.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
