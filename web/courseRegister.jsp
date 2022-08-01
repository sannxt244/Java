<%-- 
    Document   : Course
    Created on : May 28, 2022, 2:25:04 PM
    Author     : LAPTOP D&N
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebUni - Education Template</title>
        <meta charset="UTF-8">
        <meta name="description" content="WebUni Education Template">
        <meta name="keywords" content="webuni, education, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Favicon -->   
        <link href="img/favicon.ico" rel="shortcut icon"/>

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,400i,500,500i,600,600i,700,700i,800,800i" rel="stylesheet">

        <!-- Stylesheets -->
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
        <%@include file="header.jsp" %>
        <!-- Header section end -->


        <!-- Page info -->
        <div class="page-info-section set-bg" data-setbg="img/page-bg/1.jpg">
            <div class="container">
                <div class="site-breadcrumb">
                    <h2 style="color:white" ><center>Appropriate subject price</center></h2>
                </div> 
            </div>
        </div>
        <!-- Page info end -->
        

        <!-- course section -->
        <section class="course-section spad pb-0">
            <div class="course-warp">                                       
                <div class="row course-items-area">
                    <!-- course -->

                    <c:forEach items="${requestScope.courseRegister}" var="o">
                        <div class="mix col-lg-3 col-md-4 col-sm-6 finance">
                            <div class="course-item">
                                <div class="course-thumb set-bg" data-setbg="${o.name_package}">
                                    <div>${o.duration} month</div> 
                                    <div class="price">Price: ${o.price}</div>
                                    <a class="course-text" href="buy?packageId=${o.id}&courseId=${requestScope.courseId}"><button>Buy</button></a>
                                    </div>
                            </div>
                        </div>
                    </c:forEach>
                    <!-- course -->
                </div>
            </div>
        </section>
        <!-- course section end -->

        <!-- footer section -->
        <footer class="footer-section spad pb-0">
            <div class="footer-top">
                <div class="footer-warp">
                    <div class="row">
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
                        <div class="widget-item">
                            <h4>Newsletter</h4>
                            <form class="footer-newslatter">
                                <input type="email" placeholder="E-mail">
                                <button class="site-btn">Subscribe</button>
                                <p>*We don’t spam</p>
                            </form>
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
