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
        <%@include file="../header.jsp" %>
        <!-- Header section end -->


        <!-- Page info -->
        <div class="page-info-section set-bg" data-setbg="img/page-bg/1.jpg">
            <div class="container">
                <div class="site-breadcrumb">
                    <a href="home">Home</a>
                    <span>Courses</span>
                </div>
            </div>
        </div>
        <!-- Page info end -->


        <!-- search section -->
        <section class="search-section ss-other-page">
            <div class="container">
                <div class="search-warp">
                    <div class="section-title text-white">
                        <h2><span>Search your course</span></h2>
                    </div>
                    <div class="row">
                        <div class="col-lg-10 offset-lg-1">
                            <!-- search form -->
                            <form class="course-search-form" action="search_course" method="POST">
                                <input type="text" placeholder="Course Name" name="course_name">
                                <input type="text" class="last-m" placeholder="Category (Back-end or Font-end)" name="cate_name">
                                <button class="site-btn btn-dark" type="submit">Search Couse</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- search section end -->


        <!-- course section -->
        <section class="course-section spad pb-0">
            <div class="course-warp">
                <ul class="course-filter controls">
                    <a href="${pageContext.request.contextPath}/price_down"><li class="control active">Sort by price down</li></a>
                    <a href="${pageContext.request.contextPath}/price_up"><li class="control active">Sort by price up</li></a>
                            <c:forEach items="${category_list}" var="o">
                        <a href="${pageContext.request.contextPath}/course_category?cateid=${o.id}"><li class="control active">${o.categoryName}</li></a>                          
                            </c:forEach>
                </ul>                                       
                <div class="row course-items-area">
                    <!-- course -->
                    <c:forEach items="${courselist}" var="o">
                        <div class="mix col-lg-3 col-md-4 col-sm-6 finance" name="courseId" value="${o.id}">
                            <div class="course-item">
                                <div class="course-thumb set-bg" data-setbg="${o.img}">
                                    <div class="price">Price: ${o.price}$</div>
                                    <div class="price">Price Sale: ${o.priceSale}$</div>
                                </div>
                                <div class="course-info">
                                    <div class="course-text">
                                        <h5>${o.name} (${o.package_name})</h5>
                                        <p>${o.description}</p>
                                        <div class="students">120 Students</div>
                                        <a href="coursedetail?id=${o.id}" class="site-btn">More</a>
                                    </div>
                                    <div class="course-author">
                                        <div class="ca-pic set-bg" data-setbg="img/authors/1.jpg"></div>
                                        <p>${o.created_by}, <span>Developer</span></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <!-- course -->
                </div>

                <nav aria-label="Page navigation example">
                    <ul class="pagination pagination-lg justify-content-center ">
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1">Previous</a>
                        </li>
                        <c:forEach begin="1" end="${endP}" var="i">
                            <li class="page-item"><a class="page-link ${tag==i?"active":""}" href="course?index=${i}">${i} <span class="sr-only">(current)</span></a></li>
                            </c:forEach>
                        <li class="page-item">
                            <a class="page-link" href="#">Next</a>
                        </li>
                    </ul>
                </nav>

                <div class="featured-courses">
                    <div class="featured-course course-item">
                        <div class="course-thumb set-bg" data-setbg="img/courses/f-1.jpg">
                            <div class="price">Price: $15</div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 offset-lg-6 pl-0">
                                <div class="course-info">
                                    <div class="course-text">
                                        <div class="fet-note">Featured Course</div>
                                        <h5>HTNL5 & CSS For Begginers</h5>
                                        <p>Lorem ipsum dolor sit amet, consectetur. Phasellus sollicitudin et nunc eu efficitur. Sed ligula nulla, molestie quis ligula in, eleifend rhoncus ipsum. Donec ultrices, sem vel efficitur molestie, massa nisl posuere ipsum, ut vulputate mauris ligula a metus. Aenean vel congue diam, sed bibendum ipsum. Nunc vulputate aliquet tristique. Integer et pellentesque urna</p>
                                        <div class="students">120 Students</div>
                                    </div>
                                    <div class="course-author">
                                        <div class="ca-pic set-bg" data-setbg="img/authors/1.jpg"></div>
                                        <p>William Parker, <span>Developer</span></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="featured-course course-item">
                        <div class="course-thumb set-bg" data-setbg="img/courses/f-2.jpg">
                            <div class="price">Price: $15</div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 pr-0">
                                <div class="course-info">
                                    <div class="course-text">
                                        <div class="fet-note">Featured Course</div>
                                        <h5>HTNL5 & CSS For Begginers</h5>
                                        <p>Lorem ipsum dolor sit amet, consectetur. Phasellus sollicitudin et nunc eu efficitur. Sed ligula nulla, molestie quis ligula in, eleifend rhoncus ipsum. Donec ultrices, sem vel efficitur molestie, massa nisl posuere ipsum, ut vulputate mauris ligula a metus. Aenean vel congue diam, sed bibendum ipsum. Nunc vulputate aliquet tristique. Integer et pellentesque urna</p>
                                        <div class="students">120 Students</div>
                                    </div>
                                    <div class="course-author">
                                        <div class="ca-pic set-bg" data-setbg="img/authors/2.jpg"></div>
                                        <p>William Parker, <span>Developer</span></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- course section end -->


        <!-- banner section -->
        <section class="banner-section spad">
            <div class="container">
                <div class="section-title mb-0 pb-2">
                    <h2>Join Our Community Now!</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec malesuada lorem maximus mauris scelerisque, at rutrum nulla dictum. Ut ac ligula sapien. Suspendisse cursus faucibus finibus.</p>
                </div>
                <div class="text-center pt-5">
                    <a href="#" class="site-btn">Register Now</a>
                </div>
            </div>
        </section>
        <!-- banner section end -->


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
